package com.example.plugins;



import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class PageInterceptor implements Interceptor {

    private static final String SELECT_ID = "datalist";


    //插件运行的代码，它将代替原有的方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PageInterceptor -- intercept");


        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String selectId = mappedStatement.getId();
            String selectName = selectId.substring(selectId.lastIndexOf(".") + 1).toLowerCase();
            if (selectName.indexOf(SELECT_ID) > -1) {
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

                // 分页参数作为参数对象parameterObject的一个属性
                String sql = boundSql.getSql();
                Map<String, Object> params = (Map<String, Object>) (boundSql.getParameterObject());

                // 重写sql
                String countSql = concatCountSql(sql);
                String pageSql = concatPageSql(sql, params);

               /* System.out.println("重写的 count  sql        :" + countSql);
                System.out.println("重写的 select sql        :" + pageSql);*/
                ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, params,
                        boundSql);
                Connection connection = (Connection) invocation.getArgs()[0];

                PreparedStatement countStmt = null;
                ResultSet rs = null;
                int totalCount = 0;
                try {
                    countStmt = connection.prepareStatement(countSql);
                    parameterHandler.setParameters(countStmt);
                    rs = countStmt.executeQuery();
                    if (rs.next()) {
                        totalCount = rs.getInt(1);
                    }

                } catch (SQLException e) {
                    System.out.println("Ignore this exception" + e);
                } finally {
                    try {
                        rs.close();
                        countStmt.close();
                    } catch (SQLException e) {
                        System.out.println("Ignore this exception" + e);
                    }
                }

                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                //绑定total
                params.put("total", totalCount);
            }
        }

        return invocation.proceed();
    }

    /**
     * 拦截类型StatementHandler
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }


    public String concatCountSql(String sql) {
        StringBuffer sb = new StringBuffer("select count(*) from (");
        sql = sql.toLowerCase();
        sb.append(sql).append(") m");
        return sb.toString();
    }

    public String concatPageSql(String sql, Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        String sort = params.get("sort") == null ? "" : params.get("sort").toString();
        String order = params.get("order") == null ? "" : params.get("order").toString();

        if (StringUtils.isNotBlank(order)) {
            sb.append(" order by ").append(sort).append(" ").append(order);
        }
        sb.append(" limit ").append(params.get("offset")).append(" , ").append(params.get("limit"));
        return sb.toString();
    }

    public void setPageCount() {

    }

}
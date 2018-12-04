<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<html>
<head>
    <title>业务管理后台系统</title>    <%@ include file="../../common/common.jsp" %>
    <script src="<%=path%>/resources/statics/ztree/js/jquery.ztree.core.js"></script>
    <script src="<%=path%>/resources/statics/layui/layui.js"></script>
    <%--<script src="<%=path%>/resources/js/index/tabs.js"></script>--%>
    <link rel="stylesheet" href="<%=path%>/resources/statics/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/resources/css/system/menu/main.css">
    <link rel="stylesheet" href="<%=path%>/resources/statics/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/resources/statics/layui/css/layui.css">
    <script src="<%=path%>/resources/js/system/index/index.js"></script>
    <style>
        td {
            padding: 3px;
        }
    </style>
    <%
        String userName = request.getSession().getAttribute("userName")==null?"":request.getSession().getAttribute("userName").toString();
    %>
    <script type="text/javascript">
	    function logout(){
			window.location.href=path+"/user/toLogin.do";
		}
    </script>

</head>
<body>

<div class="easyui-layout" style="width:100%;height:100%;">

    <div data-options="region:'west'" style="width:169px;">
        <div style="height: 228px;width: 169px;background-size: 168px; background-image: url(../../resources/statics/img/bg/index-logo.jpg)"></div>
        <ul id="menuTree" class="ztree"></ul>
    </div>
    <div data-options="region:'center'">
        <div style="height: 116px;margin-top:-10px;background-image: url(../../resources/statics/img/bg/new-index-top2.jpg);">
            <div style="position: absolute; top: 33px; right: 14px;">
                <div style="width: 360px;height: 36px; line-height: 37px; text-align: center;font-size: larger;">
                    <%=userName%>,欢迎您&nbsp;&nbsp;<a href="<%=path%>/user/toLoginout.do">退出</a>&nbsp;&nbsp;<a href="javascript:addTab('修改密码','<%=path%>/user/toUpdatePwd.do')">修改密码</a>&nbsp;&nbsp;<a href="javascript:alert('帮助中心暂未编辑')">帮助中心</a>
                </div>
            </div>
            <div class="layui-tab layui-tab-card" lay-filter="menuTab" id="menuTab"
                 style="position: relative;top: 74px;width: 100%;z-index: 999; margin-left: 1px;border: 0px;">
                <ul id="topMenu" class="layui-tab-title">
                </ul>
            </div>
        </div>
        <div  style="width:100%;height:85%;background-color: #F2F2F2">
            <div id="tabs" class="easyui-tabs" style="position:relative;width:100%;height:100%;">

            </div>
        </div>
    </div>

</div>
</body>
</html>

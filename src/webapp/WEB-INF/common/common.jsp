<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%
    String path = request.getContextPath();
    String basePath = "";
    String port = String.valueOf(request.getServerPort());
    if("80".equals(port)){
        basePath = request.getScheme() + "://" + request.getServerName() + path + "/";
    }else{
        basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
    }
    request.setAttribute("path", path);
    request.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
    var path = "<%=request.getContextPath()%>";
    var basePath = "<%=basePath%>";
</script>
<link rel="shortcut icon" href="<%=path%>/resources/statics/img/bg/favicon.ico">
<link rel="stylesheet" href="<%=path%>/resources/statics/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/neweasyui/easyui/themes/gray/easyui.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/neweasyui/easyui/themes/icons/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/neweasyui/css/superBlue.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/style/aseCss.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/layer/skin/default/layer.css">
<link rel="stylesheet" href="<%=path%>/resources/statics/bootstrap/css/bootstrap-table.css">
<script type="application/javascript" src="<%=path%>/resources/statics/util/enums.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/neweasyui/easyui/jquery.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/neweasyui/easyui/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/layer/layer.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/neweasyui/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/util/dataUtils.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/util/slm.util.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/resources/statics/easyui/src/jquery.edatagrid.js" charset="UTF-8"></script>
<script src="<%=path%>/resources/statics/bootstrap/js/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=path%>/resources/statics/util/filter.js" charset="UTF-8"></script>

<!-- 引入外部文件blockUI用来显示加载时的遮罩层 -->
<script type="text/javascript" src="<%=path%>/resources/statics/util/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/statics/util/jquery.rotate.1-1.js"></script>

<script type="text/javascript">
    // lib参考 http://bookshadow.com/weblog/2014/09/26/jquery-blockui-js-introduction/
    // 改变默认的文字提示
    $.blockUI.defaults.message = "<img width='100%' src='<%=path%>/resources/statics/img/bg/loading_blue.gif'>";
    $.blockUI.defaults.css.zIndex = 999999;
    // 控制在ajax过程中显示
    // $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
</script>







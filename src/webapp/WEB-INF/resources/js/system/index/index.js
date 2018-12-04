var curMenu = null, zTree_Menu = null;
var icoObj = null;
layui.use('element', function () {
    var element = layui.element();

    //一些事件监听
    element.on('tab(menuTab)', function(data){
        var layer_tab_index= data.index;
        var menuParentId = $("#menuTab").find("li")[layer_tab_index].getAttribute("lay-id");
        var treeObj = $("#menuTree");
        setting.async.otherParam = {"menuParentId":menuParentId};
        $.fn.zTree.init(treeObj, setting);
        zTree_Menu = $.fn.zTree.getZTreeObj("menuTree");
        treeObj.addClass("showIcon");
    });

});
var setting = {
    view: {
        showLine: false,
        showIcon: false,
        selectedMulti: false,
        dblClickExpand: false,
        addDiyDom: addDiyDom
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    async: {
        enable: true,//异步加载
        url: basePath + "/menu/getMenuListForWest.do",//请求地址
        // otherParam:{"menuParentId":menuParentId},//提交的其他参数,json的形式
        dataType: "json",//返回数据类型
        type: "post",//请求方式
        dataFilter: null//数据过滤
    },
    callback: {
        beforeClick: beforeClick,
        onClick: clickTreeNode
    }
};
function beforeClick(treeId, treeNode) {
    if (treeNode.type==1){
        return true;
    }
    if (treeNode.level == 0) {
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        zTree.expandNode(treeNode);
        return false;
    }
    return true;
}
function clickTreeNode(event, treeId, treeNode) {
    if (treeNode.type!=1){
        return false;
    }
    // 点击叶子节点时进行跳转操作
    if (!treeNode.isParent) {
        openTab(treeNode);
    }
}
function addDiyDom(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    icoObj.before(switchObj);

    if (treeNode.level > 1) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
        switchObj.before(spaceStr);
    }
}
function openTab(treeNode) {
    // var node = treeNode;
    // var nodeStr = "";
    // while (node) {
    //     nodeStr = node.name + "->" + nodeStr;
    //     node = node.getParentNode();
    // }
    // var nodeStr = nodeStr.substring(0, nodeStr.length - 2);
    // $("#title").panel({title: nodeStr});
    xy_addTabByMenu(treeNode.name, basePath + treeNode.iurl);
}
function xy_addTabByMenu(title, url) {
    if(title === "贷款产品配置"){
        var tab = "tabs";
        var parent$ = self.parent.$;
        parent$('#' + tab).tabs('close', title);
        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        parent$('#' + tab).tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    } else {
        if ($('#tabs').tabs('exists', title)) {
            $('#tabs').tabs('select', title);
            var tab = $('#tabs').tabs('getSelected');
            $('#tabs').tabs('update', {
                tab: tab,
                options: {
                    title: title,
                    href: url
                }
            });
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
            $('#tabs').tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
    }
    /*if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        $('#tabs').tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }*/
}
$(document).ready(function () {
    initTopMenu();
});
function initTopMenu() {
    $.ajax({
        type: "POST",
        url: path+"/menu/getMenuListForTop.do",
        // data: data,
        dataType: "json",
        success: function(data){
            var topMenuHtml = "";
            for (var i=0;i<data.length;i++) {
                topMenuHtml += '<li lay-id="'+data[i].id+'">'+data[i].name+'<\/li>';
            }
            $("#topMenu").append(topMenuHtml);
        },
        error: function () {
            $.messager.alert("警告", "顶部菜单加载失败!")
        }
    });
}
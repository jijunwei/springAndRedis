/**
 * Created by skyer on 2017/7/4.
 */
$.fn.contextMenus=function(){
    var $tabs=$(this);

    var temphtml='<div id="tabs-contextmenuparent"><div id="tabs-contextmenu" class="easyui-menu" style="width:150px">'+
        '<div id="mm-tabclose">关闭</div>'+
        '<div id="mm-tabcloseall">关闭全部</div>'+
        '<div id="mm-tabcloseother">关闭其他</div>'+
 /*       '<div class="menu-sep"></div>'+
        '<div id="mm-tabcloseright">关闭右侧标签</div>'+
        '<div id="mm-tabcloseleft">关闭左侧标签</div>'+*/
        '</div></div>';
    $("body").append(temphtml);
    $.parser.parse($('#tabs-contextmenuparent'));
    var $menus=$("#tabs-contextmenu");
    $(document).on("dblclick",".tabs-inner",function(){
        var currtab_title = $(this).children("span").text();
        var $link=$(".tabs-title:contains("+currtab_title+")",$tabs);
        if($link.is('.tabs-closable')){
            $tabs.tabs('close',currtab_title);
        }
    });
    $(document).on("contextmenu",".tabs-inner",function(e){

        $menus.menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        var subtitle =$(this).children("span").text();
        $menus.data("currtab",subtitle);
        return false;

    });
    //关闭当前
    $('#mm-tabclose',$menus).click(function() {
        var currtab_title = $menus.data("currtab");
        var $link=$(".tabs-title:contains("+currtab_title+")",$tabs);

        if ($link.is('.tabs-closable')) {
            $tabs.tabs('close', currtab_title);
        }
    });
    //全部关闭
    $('#mm-tabcloseall',$menus).click(function() {
        $('.tabs-inner span',$tabs).each(function(i, n) {
            if ($(this).is('.tabs-closable')) {
                var t = $(n).text();
                $tabs.tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother',$menus).click(function() {
        var currtab_title = $menus.data("currtab");
        $('.tabs-inner span').each(function(i, n) {
            if ($(this).is('.tabs-closable')) {
                var t = $(n).text();
                if (t != currtab_title)
                    $tabs.tabs('close', t);
            }
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright',$menus).click(function() {
        var currtab_title = $menus.data("currtab");
        var $li=$(".tabs-title:contains("+currtab_title+")",$tabs).parent().parent();
        var nextall = $li.nextAll();

        if (nextall.length == 0) {
            $.messager.alert('提示','已经是最后一个了');
            return false;
        }
        nextall.each(function(i, n) {
            if ($('a.tabs-close', $(n)).length > 0) {
                var t = $('a:eq(0) span', $(n)).text();
                $tabs.tabs('close', t);
            }
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft',$menus).click(function() {
        var currtab_title = $menus.data("currtab");
        var $li=$(".tabs-title:contains("+currtab_title+")",$tabs).parent().parent();

        var prevall =$li.prevAll();
        if (prevall.length == 1) {
            $.messager.alert('提示','已经是第一个了');
            return false;
        }
        prevall.each(function(i, n) {
            if ($('a.tabs-close', $(n)).length > 0) {
                var t = $('a:eq(0) span', $(n)).text();
                $tabs.tabs('close', t);
            }
        });
        return false;
    });
};
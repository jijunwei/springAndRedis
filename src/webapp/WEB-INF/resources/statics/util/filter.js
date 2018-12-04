$.ajaxSetup({
    complete: function (XMLHttpRequest, textStatus) {
        var sessionStatus = XMLHttpRequest.getResponseHeader('sessionstatus');
        if (sessionStatus == 'timeout') {
            $.messager.alert("提示", "由于您长时间没有操作, session已过期, 请重新登录.", "info", function () {
                window.location.href=basePath+"user/toLogin.do";
            });
        }
    },
    error: function (XMLHttpRequest, textStatus) {
        var sessionStatus = XMLHttpRequest.getResponseHeader('sessionstatus');
        if (sessionStatus == 'timeout') {
            $.messager.alert("提示", "由于您长时间没有操作, session已过期, 请重新登录.", "info", function () {
                window.location.href=basePath+"user/toLogin.do";
            });
        }
    }

});


var sy = $.extend({}, sy);/* 定义全局对象，类似于命名空间或包的作用 */

/**
 * @author 信贷项目组
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
sy.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/*
 * easyui Form增加loadData方法,使其支持二级数据对象
 */
$.extend($.fn.form.methods, {
	setValues: function (myself, data) {  
        var form = $(myself);  
          
        var opts = $.data(form[0], "form").options;  
          
        var cols = "," + data.items + ",";  
        for (var name in data.row) {  
            if (cols.indexOf(name) >= 0) {  
                var val = data.row[name];                  
                form.find("[id=\"" + name + "\"]").textbox("setValue",val);  
            }  
        }  
          
        opts.onLoadSuccess.call(form, data);  
        form.form("validate");  
    } 
});
/**
 * 
 */
;
(function($) {
	"use strict";
	function SlmUtil() {
		
	}
	
	$.extend(SlmUtil.prototype, {
	        slmAjax : function slmAjax(reqName,reqPara,errFun,sucFun,isAsync) {
	        	var urlData={};
	        	var path="test";
	        	
	        	if(typeof(reqPara)=="object"&&Object.prototype.toString.call(reqPara).toLowerCase()=="[object object]"&&!reqPara.length){
	        		urlData = reqPara;
	        	}
	        	if(isAsync==null||isAsync==undefined||isAsync=="undefined"||(typeof(isAsync)=="string"&&isAsync.trim()=="")){
	        		isAsync=true;
	        	}
	        	$.ajax({
                    url : reqName,
                    data : urlData,
                    type : 'POST',
                    error :  function(XMLHttpRequest, textStatus, errorThrown) {
                        $.messager.alert("错误提示", "内部通讯错误","info");
                    },
                    success : function(response,textStatus) {
                        var callbacks = $.Callbacks("once");
                        callbacks.add(sucFun);
                        callbacks.fire(response);
                    }
                });
	        }
	    });
	
	
    $.extend(SlmUtil.prototype, {
    	isBlank: function isBlank(s){
    		if(typeof(s)=="undefined"){
    			return true;
    		}else if(s){
    			if(null==s||(typeof(s)=="string"&&s.trim()=="")||(s+"").toUpperCase()=="NULL"){
    				return true;
    			}else{return false;}
    		}else{
    			return true;
    		}   		
    	}
    });

	$.SlmUtil = new SlmUtil();
	
})(jQuery);
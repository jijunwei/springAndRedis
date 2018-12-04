var Enums = (function() {
	return{
		//证件类型
		CERTIFICATE_TYPE : [['01','身份证'],['02','户口本'],['03','护照'],['04','军官证'],['05','士兵证'],['06','港澳居民来往内地通行证'],
		                    ['07','台湾同胞来往内地通行证'],['08','临时身份证'],['09','外国人居住证'],['10','警官证'],
		                    ['11','驾驶证'],['12','社会保障号'],['13','其他证件']], 
		//性别
		SEX : [['0','男'],['1','女']],              
		//与本人的关系
		RELATIONSHIP_WITH : [['01','夫妻'],['02','父母'],['03','子女'],['04','兄弟'],['05','姐妹'],['06','兄妹'],
		                     ['07','姐弟'],['08','朋友'],['09','同事'],['10','房东'],['11','亲属'],['12','其他']],
		//业务状态
		BUSI_STATE : [['00','申请登记']],
		//是否
		/*SEX : [['0','否'],['1','是']], */
		YES_NO:[['0','否'],['1','是']],
       //分期状态
		PERIOD_STATE:[['0','未分期'],['1','已分期']],
		//付款方式
		PAY_WAY:[['0','日付'],['1','月付期'],['2','季付'],['3','半年付'],['4','年付']],
		//所属部门
		ROLE_DEPARTMENT:[['0','科技部'],['1','运营部']],
		
	   transferEnumToOptions : function(enumObj, addNullOption, defaultValue) {
			var options = new Array;
			var seq = 0;
			if(addNullOption) {
				options[0] = {};
				options[0].showText = "";
				options[0].value = "";
				seq = 1;
			}
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i + seq] = {};
					options[i + seq].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i + seq].value = enumObj[i][0];
					if (defaultValue && defaultValue == options[i + seq].value) {
						options[i + seq].checked = 'true';
					}
				}
			}
			return options;
		},
		transferEnumToJson : function(enumObj) {
			var options = new Array;
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i] = {};
					options[i].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i].value = enumObj[i][0];
				}
			}
			return options;
		}

	};
})();
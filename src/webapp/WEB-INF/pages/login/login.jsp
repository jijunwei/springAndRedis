<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>业务管理后台系统</title>  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../../common/common.jsp" %>
  <%-- <link rel="stylesheet" href="<%=path%>/resources/css/login/css/bootstrap.min.css"> --%>
  <link rel="stylesheet" href="<%=path%>/resources/css/login/css/font-awesome.min.css">
  <link rel="stylesheet" href="<%=path%>/resources/css/login/css/AdminLTE.min.css">
  <link rel="stylesheet" href="<%=path%>/resources/css/login/css/all-skins.min.css">
  <link rel="stylesheet" href="<%=path%>/resources/css/login/css/main.css">
  <link rel="stylesheet" href='<%=path%>/resources/statics/style/style.login.css'>
</head>
<body class="hold-transition login-page">
<div class="login-box" id="rrapp" v-cloak>
	<div id="bodybg">   
	    <img src="<%=path%>/resources/statics/img/bg/login-bg-blured.jpg" class="stretch"/>   
	</div>
  	<div id="bodyLogin" style="margin-left: -20%; margin-top: -3%; width: 1000px;height: 600px">
    	<div id="bodybg1">
	    	<img src="<%=path%>/resources/statics/img/bg/ProspectImg.png" class="stretch"/>   
		</div> 
      	
      	<div style="width: 295px;height: 500px;margin-left: 65%;margin-top: 18%">
		      <div id="logo">
					<img src="<%=path%>/resources/statics/img/bg/logoXJa.png" style="width: 75px; height: 100px; margin-left: 10%;margin-top: -15%;z-index: 1;position: absolute; " /> 
			  </div>
		      <div class="form-group has-feedback">
		      	<span id="userNameSpan" style="color:red"></span>
		        <input type="text" class="form-control" v-model="userName" placeholder="用户名" autocomplete="off">
		        <!-- <span class="glyphicon glyphicon-user form-control-feedback"></span> -->
		      </div>
		      <div class="form-group has-feedback">
		      	<span id="passwordSpan" style="color:red"></span>
		        <input type="password" class="form-control" v-model="password" placeholder="密码" autocomplete="off">
		        <!-- <span class="glyphicon glyphicon-lock form-control-feedback"></span> -->
		      </div>
		      <div class="form-group has-feedback">
		      	<span id="captchaSpan" style="color:red"></span>
		        <input type="text" class="form-control" v-model="captcha" @keyup.enter="login" placeholder="验证码" autocomplete="off">
		        <!-- <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span> -->
		      </div>
		      <div class="form-group has-feedback">
		        <img alt="如果看不清楚，请单击图片刷新！" style="width: 180px;height: 50px" class="pointer" :src="src" @click="refreshCode">
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" @click="refreshCode">点击刷新</a>
		      </div>
		      <div class="row">
		        <div class="col-xs-8">
		          <div class="checkbox icheck">
		          </div>
		        </div>
		        <div class="col-xs-4">
		          <button type="button" class="btn btn-primary btn-block btn-flat" style="width: 305px;margin-left: -315%" @click="login">登录</button>
		        </div>
		      </div>
      	</div>
  </div>
</div>

<script type="application/javascript" src="<%=path%>/resources/statics/libs/jquery.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/libs/vue.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/libs/bootstrap.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/libs/jquery.slimscroll.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/libs/fastclick.min.js" charset="UTF-8"></script>
<script type="application/javascript" src="<%=path%>/resources/statics/libs/app.js" charset="UTF-8"></script>

<script type="text/javascript">
var vm = new Vue({
	el:'#rrapp',
	data:{
		userName: '',
		password: '',
		captcha: '',
		error: false,
		src: 'captcha.do'
	},
	beforeCreate: function(){
		if(self != top){
			top.location.href = self.location.href;
		}
	},
	methods: {
		refreshCode: function(){
			this.src = "captcha.do?t=" + $.now();
		},
		login: function (event) {
			var data = "userName="+vm.userName+"&password="+vm.password+"&captcha="+vm.captcha;
			var password = vm.password;
			var userName = vm.userName; 
			if(userName.length!=11){
				document.getElementById('userNameSpan').innerHTML = '用户名位数不合法！'; 
				return false;
			}else if(userName.length==11){
				$("#userNameSpan").empty();
			}
			$.ajax({
				type: "POST",
			    url: path+"/user/login.do",
			    data: data,
			    dataType: "json",
			    success: function(result){
			    	if(result.msg=="验证码不正确!"){
						document.getElementById('captchaSpan').innerHTML = result.msg;
						document.getElementById('userNameSpan').innerHTML = ''; 
						vm.refreshCode();
					}
			    	else if(result.success){//登录成功
						//判断首次登陆是否需要修改密码
						$("#userNameSpan").empty();
						var obj=result.obj;
						if(obj=="0"){
							window.location.href =path+'/user/toUpdatePwdFirst.do';
						}else if(obj=="1"){
							window.location.href =path+'/user/toIndex.do';
						}
						$("#userNameSpan").empty();
					}else if(!result.success){
						document.getElementById('userNameSpan').innerHTML = '用户名密码不正确！'; 
						$("#captchaSpan").empty();
						//document.getElementById('captchaSpan').innerHTML = ''; 
						vm.refreshCode();
					}
					
				}
			});
		}
	}
});
</script>
</body>
</html>

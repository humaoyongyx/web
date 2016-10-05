<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title><spring:message code="login.head.title" /></title>
<style type="text/css">
  body{
     background-image:url("${path}/resources/images/bg.jpg"); 
     background-repeat:no-repeat;
     background-size:cover;
  }

</style>
</head>
<body>
	<script>
	
	var _loginFormId="#_loginForm";
	var _loginFormAction="${path}/login";
	_validateSession();
	$(document).ready(function() {
	
		//_validateSession();
		_init();
		
		$('input').iCheck({
		    checkboxClass: 'icheckbox_minimal-green',
		    radioClass: 'iradio_minimal-green',
		    increaseArea: '20%' // optional
		  });
		
		var validateForm=$(_loginFormId).validate();	
		
		$(_loginFormId).submit(function() {
 			var valid=validateForm.form();
 	
 			var options = {
 				    type:  "post",
 				    dataType:"json",
 				    url:_loginFormAction,
 					beforeSubmit : function() {
 					},
 					success : function(result) {
 	                  if(result.status ==1){
 	                	 location.replace("${path}/main") ;
 	                  }else{
 	                		showErrorResult(result.message);
 	                  }
 						
 					},
 					error : function(error) {
 						showErrorResult(error);
 					
 					}
 				};
 			if(valid){
 				$(this).ajaxSubmit(options);
 			 }
				return false;
		
 		});
 		
		 $("#username").focus(function(){
			 hideErrorResult();
		 });
		 
			
		 $("#password").focus(function(){
			 hideErrorResult();
		 });
		
 		
		
	});
	
	function _init(){
		hideErrorResult();
	}
	
	function showErrorResult(message){
	     $("#error_result").html(message);
 	      $("#error_result").show();
	}
	
	function hideErrorResult(){
	      $("#error_result").hide();
	}
	
	function _validateSession(){
		if(parent){
		    if(parent._main_tabs_li_home){
		    	parent.location.reload(true);
		    }
		}
	}
	
	</script>


	<div class="container">
		<div class="row" style="height:20%"></div>
		<div class="row">
		  <div class="col-md-3">
		  
		  </div>
		  <div class="col-md-6">
					  <center>
					      <p><h1><strong><font color="white"><spring:message code="login.form.title" /></font></strong></h1></p>
					      <p>&nbsp;&nbsp;</p>
					  </center>
		           	<form class="form-horizontal" role="form"  id="_loginForm" method="post"  >
						<div class="form-group">
							<div class="col-sm-12">
								<input type="text" class="form-control" id="username" name="username" placeholder="<spring:message code="login.username" />" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="login.password" />" required>
							</div>
						</div>
						
					<div class="form-group">
							<div class="col-sm-8 col-xs-6"">
								<input type="text" id="captcha" name="captcha" maxlength="4" class="form-control" placeholder="<spring:message code="login.captcha" />"   required /> 
							</div>
								<div class="col-sm-4 col-xs-6">
									<img src="${path }/captcha-image" onclick="this.src='captcha-image?d='+new Date().getTime()"  class="img-responsive center-block" />  
							</div>
						</div>
				<%-- 		<div class="form-group">
							<div class="col-sm-12">
								  <input type="checkbox" name="rememberMe"> &nbsp;  <strong><font color="white"><spring:message code="login.rememberMe" /></font></strong>
							</div>
						</div> --%>
						<div class="form-group">
							<div class="col-sm-12">
								<button type="submit" class="btn btn-success btn-block"> <strong><font color="white"><spring:message code="login.login" /></font></strong></button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
							<button  id="error_result"  class="btn btn-danger btn-block" disabled="disabled"></button>
							</div>
						</div>
					
						
			  </form>
		  </div>
		  	<div class="col-md-3">
		  
		  </div>
		
		</div>

	</div>


</div>

</body>
</html>
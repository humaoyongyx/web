<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>密码设置</title>

</head>
<body>
	<script>
	
	var formUrl="${path}/basic/updatePassword";
	var formId="#form_add_password";
	$(document).ready(function() {
		
		
		$(formId).submit(function() {
 			var valid=validateForm.form();
			var options = {
				    type:  "post",
				   dataType:"json",
				    url:formUrl,
					beforeSubmit : function() {
					},
					success : function(result) {
	                  if(result.status==1){
	 	               	     swal("", "新增或修改成功！","success");
	                  }else {
	               	       swal("新增或修改失败", result.message,"error");
	                  }
						
					},
					error : function(error) {
						   swal("系统异常",error,"error");
					}
				};
			if(valid){
				$(this).ajaxSubmit(options);
			 }
				
				return false;
		
		});
		var validateForm=$(formId).validate();		 
		 
	});
	</script>

 
  
  
  	<div class="container-fluid">
  	  
            <center> <h1>密码设置</h1>  </center>
            <p>&nbsp;&nbsp;</p>
  	        <p>&nbsp;&nbsp;</p>
  	        
  	 <div class="row">
  	 
      <div class="col-md-3">
		  
	 </div>
  	 <div class="col-md-6">
  	 
  	 	 <form class="form-horizontal" role="form"  id="form_add_password" >
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">旧密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="旧密码"  required>
				</div>
			</div>
					<div class="form-group">
				<label for="name" class="col-sm-2 control-label">新密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="newPassword" name="newPassword"  placeholder="新密码"  required>
				</div>
			</div>
					<div class="form-group">
				<label for="name" class="col-sm-2 control-label">确认密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="确认密码"  required>
				</div>
			</div>
			<div>&nbsp;&nbsp;</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"  class="btn btn-success"  >提交</button>
				</div>
			</div>
		</form>
  	
  	 
  	 </div>
  	 
  	   <div class="col-md-3">
		  
	 </div>
  	 
	</div>

	
</body>
</html>
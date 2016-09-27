<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>用户资料</title>

</head>
<body>
	<script>
	
	</script>

 
  
  
  	<div class="container-fluid">
  	  
            <center> <h1>用户资料</h1>  </center>
            <p>&nbsp;&nbsp;</p>
  	        <p>&nbsp;&nbsp;</p>
  	        
  	 <div class="row">
  	 
      <div class="col-md-3">
		  
	 </div>
  	 <div class="col-md-6">
  	 <form class="form-horizontal" role="form"  id="form_add_menu" action="${path}/module/menu/addOrUpdate">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" placeholder="名称"  value="${user.name}">
				</div>
			</div>
			 <div class="form-group">
				<label for="photo" class="col-sm-2 control-label">头像</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="photo" name="photo" placeholder="头像"  value="${user.photo}">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<select name="sex" id="sex" class="form-control"  placeholder="性别"   required >
					        <c:if test="${user.sex!=null&& user.sex=='1'}"> <option value="1" selected="selected">男</option></c:if>
					        <c:if test="${user.sex!=null&& user.sex=='0'}"> <option value="1" selected="女">男</option></c:if>
<!-- 						    <option value="1">男</option>
						    <option value="0">女</option>
 -->						</select>
				</div>
			</div>
		    <div class="form-group">
				<label for="mobile" class="col-sm-2 control-label">手机号码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mobile" name="mobile"  placeholder="手机号码"  value="${user.mobile}">
				</div>
			</div>
		    <div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email" name="email" placeholder="邮箱"  value="${user.email}">
				</div>
			</div>
		    <div class="form-group">
				<label for="address" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="address" name="address" placeholder="地址"  value="${user.address}">
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
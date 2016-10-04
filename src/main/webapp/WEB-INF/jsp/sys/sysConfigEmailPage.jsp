<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>

<div class="container-fluid">
	<div>&nbsp;&nbsp;</div>
	<!-- begin -->
	<form class="form-horizontal" role="form" id="form_sys_email">

		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">host</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="host" placeholder="host" value="${emaiBean.host }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">protocol</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="protocol" placeholder="protocol" value="${emaiBean.protocol }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">port</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="port" placeholder="port" value="${emaiBean.port }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">username</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="username" placeholder="username" value="${emaiBean.username }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">password</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="password" placeholder="password" value="${emaiBean.password }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">timeout</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="timeout" placeholder="timeout" value="${emaiBean.timeout }">
			</div>
		</div>
		<div class="form-group">
			<label for="pid" class="col-sm-2 control-label">auth</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="auth" placeholder="auth" value="${emaiBean.auth }">
			</div>
		</div>
		
		 <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				     <input type="button" class="btn btn-success" value="修改" onclick="modify_email()" />&nbsp;
				     <input type="button" class="btn btn-success" value="提交" onclick="submit_email()" id="email_submit" />&nbsp;
					 <input type="button" class="btn btn-success" value="取消" onclick="cancel_email()" id="email_cancel" />&nbsp;
				</div>
		  </div>		
	</form>
	<div>&nbsp;</div>
	<form class="form-horizontal" role="form" id="form_sys_email_test">
	   <div class="form-group">
			<label for="pid" class="col-sm-2 control-label">主题</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="subject" placeholder="主题" required>
			</div>
		</div>
		  <div class="form-group">
			<label for="pid" class="col-sm-2 control-label">收件人</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="to" placeholder="收件人（多个收件人按;分割）"  required>
			</div>
		</div>
		 <div class="form-group">
			<label for="pid" class="col-sm-2 control-label">内容</label>
			<div class="col-sm-4">
			<textarea class="form-control" rows="4" name="content" required></textarea>
			</div>
		</div>
		 <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				     <input type="button" class="btn btn-success" value="发送" onclick="send_email()" />&nbsp;
				</div>
		  </div>		
	</form>
	<!-- end -->

</div>

<script>
var validateForm_mail_test;
$(document).ready(function() {
         $("input:text","#form_sys_email").attr("disabled",true);
         $("#email_submit").hide();
         $("#email_cancel").hide();
         validateForm_mail_test=$("#form_sys_email_test").validate();		
});

function modify_email(){
	$("input:text","#form_sys_email").attr("disabled",false);
    $("#email_submit").show();
    $("#email_cancel").show();
}
function submit_email(){
			var options = {
				     type:  "post",
				     dataType:"json",
					 url:"${path}/sys/updateEmail",
					beforeSubmit : function() {
					},
					success : function(result) {
                    if(result.status ==1){
                    	refresh_email();
                 	   swal("", "更新成功！","success");
                    }else{
                 	   swal("更新失败", result.message,"error");
                    }
						
					},
					error : function(error) {
						  swal("系统异常", error,"error");
					}
				};
				$("#form_sys_email").ajaxSubmit(options);
}

function cancel_email(){
	  refresh_email();
}

function refresh_email(){
	$(sys_emailPage).load("${path}/sys/showEmail");
}

function send_email(){
	var valid=validateForm_mail_test.form();
	var options = {
		     type:  "post",
		     dataType:"json",
			 url:"${path}/sys/testSendMail",
			beforeSubmit : function() {
			},
			success : function(result) {
           if(result.status ==1){
        	   swal("", "发送成功！","success");
           }else{
        	   swal("发送失败", result.message,"error");
           }
				
			},
			error : function(error) {
				  swal("系统异常", error,"error");
			}
		};
	if(valid){
		   $("#form_sys_email_test").ajaxSubmit(options);
		 }
		
}

</script>


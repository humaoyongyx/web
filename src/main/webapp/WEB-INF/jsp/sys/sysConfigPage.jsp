<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>


<title>系统配置</title>
</head>
<body>
	<script>
			$(document).ready(function() {
				loadPage();
			});
	          var sys_schedulerPage="#sys_scheduler";
			function loadPage(){
				$(sys_schedulerPage).load("${path}/sys/showScheduler/",{status:1});
			}
	</script>
	

 <div class="container-fluid">	
     <center>
                    <h1>系统配置</h1>
     </center>

 <div>
 
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
			  <li role="presentation" class="active"><a href="#sys_home" role="tab" data-toggle="tab">系统配置</a></li>
			  <li role="presentation"><a href="#sys_scheduler" role="tab" data-toggle="tab">调度器配置</a></li>
			  <li role="presentation"><a href="#sys_mail" role="tab" data-toggle="tab">邮件配置</a></li>
			</ul>
			
			<!-- Tab panes -->
		<div class="tab-content">
			  <div role="tabpanel" class="tab-pane active" id="sys_home"></div>
			   <div role="tabpanel" class="tab-pane" id="sys_scheduler"></div>
			   <div role="tabpanel" class="tab-pane" id="sys_mail"></div>
			
		</div>
 
 
 </div>
 
 
 </div>
  
  
</body>
</html>
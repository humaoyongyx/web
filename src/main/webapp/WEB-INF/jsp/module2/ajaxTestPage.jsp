<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>Ajax请求测试页面</title>

</head>
<body>
	<script type="text/javascript">
		
	</script>


	<div class="container-fluid">
		<center>
			<h3>Ajax请求测试页面</h3>
		</center>


      <div class="row">
        &nbsp;&nbsp;
      </div>

		<form id="form">


			<div class="row">
				<div class="col-sm-1" ></div>

				<div class="col-sm-10">
					<div class="row">

						<div class="col-sm-2">
							<select class="form-control" id="requestType">
								<option>GET</option>
								<option>POST</option>
								<option>PUT</option>
								<option>DELETE</option>
							</select>
						</div>
						<div class="col-sm-8">
							<input class="form-control" type="text" placeholder="URL" id="url">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onclick="sendRequest()">发送请求</button>
							<button type="button" class="btn btn-primary" onclick="login()">登录</button>
						</div>

					</div>
					<div class="row">&nbsp;</div>
					<div class="row">
					   <div class="col-sm-4">
					       &nbsp;  <b>参数列表</b>
					   </div> 
					</div>
					<div class="row">&nbsp;</div>
				   <div class="row param">
				             <div class="col-sm-4">
					    	  	<input class="form-control" type="text" placeholder="name">
					    	</div>
					    	  <div class="col-sm-6">
					    	   	<input class="form-control" type="text" placeholder="value">
					    	</div>
				   </div>
				   <div class="row">&nbsp;</div>
				   <div class="row param">
				             <div class="col-sm-4">
					    	  	<input class="form-control" type="text" placeholder="name">
					    	</div>
					    	  <div class="col-sm-6">
					    	   	<input class="form-control" type="text" placeholder="value">
					    	</div>
				   </div>
				   <div class="row">&nbsp;</div>
				   <div class="row param">
				             <div class="col-sm-4">
					    	  	<input class="form-control" type="text" placeholder="name">
					    	</div>
					    	  <div class="col-sm-6">
					    	   	<input class="form-control" type="text" placeholder="value">
					    	</div>
				   </div>
					
				
				<div class="row">&nbsp;</div>
				<div class="row">
					   <div class="col-sm-4">
					       &nbsp;  <b>返回结果</b>
					   </div> 
				</div>
				<div class="row">&nbsp;</div>
				   <div class="row">
				             <div class="col-sm-10" > 
				                <div id="returnContent" style="border:1px solid grey;height:100px">
				             
				                </div>
					    	  	  
					    	</div>
				   </div>
					
				</div>
				<div class="col-sm-1" ></div>
				
				</div>
			</div>

		</form>




	</div>



<script>


function login(){
	$.ajax({
		type:"post",
		url:"http://192.168.66.230:8081/cas/login",
		data:{username:"root",password:"root"},
		success:function(msg){
			console.log(msg);
			$("#returnContent").html(msg);
		}
		
	});
}
    function sendRequest(){
        var jessionId=";JSESSIONID=89A0E10E98AEDA34CC7D9208DC5F736C";
    	var requestType=$("#requestType").val();
    	var url=$("#url").val()+jessionId;
    	var params={};
    	$(".param").each(function(index){
    		var name=$($(this).find("input")[0]).val();
    		var value=$($(this).find("input")[1]).val();
    		if(name!=null && $.trim(name)!="" && value!=null && $.trim(value)!="" ){
    			console.log("name:"+name+",value:"+value);
    			params[name]=value;
    		}
    		
    		
    	});
    	
    	
    	
    	$.ajax({
    		type:requestType,
    		url:url,
    		data:params,
    		success:function(msg){
    			console.log(msg);
    			$("#returnContent").html(msg);
    		}
    		
    	});
    	
    }


</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title>Main page</title>
</head>
<body>

<script>
      function test_close(obj) {
    	console.log(obj)
    	console.log($(obj))
    	console.log($($(obj).parent()[0]).parent())
  }
      
    
</script>


<ul class="nav nav-tabs" role="tablist" id="myTab" class="xxx">
  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
  <li role="presentation" id="test"><a href="#profile" role="tab" data-toggle="tab">Profile  <button type="button" class="close" onclick="test_close(this)"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a> </li>
  
  <li role="presentation"><a href="#messages" role="tab" data-toggle="tab">Messages</a></li>
  <li role="presentation"><a href="#settings" role="tab" data-toggle="tab">Settings</a></li>
    <li role="presentation"><a href="#settings2" role="tab" data-toggle="tab">Settings2</a></li>
</ul>

<div class="tab-content" id="tab-content">
  <div role="tabpanel" class="tab-pane active" id="home">
         <iframe src="${path}/userInfo/page2"></iframe>
  </div>
  
    <div role="tabpanel" class="tab-pane " id="settings2">
         <iframe src="${path}/userInfo/page2"></iframe>
  </div>
  <div role="tabpanel" class="tab-pane" id="profile">...</div>
  <div role="tabpanel" class="tab-pane" id="messages">...</div>
  <div role="tabpanel" class="tab-pane" id="settings">...</div>
</div>
  
<div>
      <input type="button" value="add" onclick="addTab( <c:out value='${obj}'/>)">
</div>
<script>
  $(function () {
    
    
  })
  
    var  id=0;
    function addTab (obj){
  	  console.log(obj.message+obj.status)
  	  $("#tab-content").append(
			'<div role="tabpanel" class="tab-pane " id="test'+id+'"><iframe src="${path}/userInfo/page2"></iframe></div>'
	  )
  	  $("#myTab").append(
  			  '<li role="presentation" ><a href="#test'+id+'" role="tab" data-toggle="tab">Settings'+id+'</a></li>'
  	  );
 id++;
 $('#myTab a:last').tab('show');
    }
</script>












</body>
</html>
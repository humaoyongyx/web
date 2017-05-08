<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>测试上传下载包括数据库</title>

</head>
<body>
<script type="text/javascript">

   /*  $.getJSON("http://localhost:8081/evcard-vehicleScheduling-rest/schedulingTask/test", function(json){
	   console.log(json);
	}); */


</script>



	<div class="container-fluid">
		<center>
			<h1>测试上传下载包括数据库</h1>
		</center>



		<a href="/pics/photo.png">photo</a> <a href="/pics/test.docx">file</a>
		<a href="/files/test.docx">file</a> <br/>
		<a href="${path}/test/download?key=df2.docx">download</a><br/>
		<a href="${path}/test/download2?key=upload3.docx">download2</a><br/>
		<a href="${path}/test/deleteFile?key=test.docx">delete</a><br/>
	<form id="uploadForm" action="http://127.0.0.1:8081/evcard-vehicleScheduling-rest/resource/addRoleUsersAfterDel" >
	 <input type="text" name="roleId" value="_ss_001_1300" />
	  <input type="text" name="name" value="test" />
		 <input type="text" name="userIds[0]" value="test" />
		<input type="text" name="userIds[1]" value="area" />
		 <input type="text" name="userIds[2]" value="test3" />
		 <input type="submit" value="Submit" class="btn btn-primary" /> <br />
		</form>

	<form id="uploadForm" action="http://127.0.0.1:8081/evcard-vehicleScheduling-rest/resource/addRoleWithResources" >
	 <input type="text" name="systemId" value="3" />
	  <input type="text" name="name" value="test" />
		 <input type="text" name="resourceIds[0]" value="_ss_002_22210" />
		<input type="text" name="resourceIds[1]" value="_ss_002_22211" />
		 <input type="text" name="resourceIds[2]" value="_ss_002_22213" />
		 <input type="submit" value="Submit" class="btn btn-primary" /> <br />
		</form>
		
		<form id="uploadForm" action="http://127.0.0.1:8081/evcard-vehicleScheduling-rest/inspect/operUploadTask;JSESSIONID=46D42123217268DE5C01496B3F4E8A9C" method="post" enctype="multipart/form-data">
			<input id="files" type="file" name="files" /> <br /> 
		    <input id="files" type="file" name="files" /> <br />
			<input id="files" type="file" name="files" /> <br />
	        <input type="text" name="id" value="testxxx" /> <br />
	           <input type="text" name="createTime" value="2010-11-11 12:12:12" /> <br />
			<input type="submit" value="Submit" class="btn btn-primary" /> <br />
		</form>
		
		<form id="uploadForm" action="http://127.0.0.1:8081/evcard-vehicleScheduling-rest/preference/operUpdateUserPreference" method="post" >
		 <input type="text" name="userId" value="test" />
		 <div class="f_left">  
        <input type="checkbox" name="problemTypes" value="1" checked="checked"/>听歌  
        <input type="checkbox" name="problemTypes" value="3" checked="checked"/>书法  
        <input type="checkbox" name="problemTypes" value="3" checked="checked"/>看电影  
        </div>  
			<input type="submit" value="Submit" class="btn btn-primary" /> <br />
		</form>
		
	 <p>post</p>	
	 
	 
      <form id="uploadForm" action="http://localhost:8081/evcard-vehicleScheduling-rest/dispatch/operLockTask" method="post" >
		      <input type="hidden" name="_method" value="post" />
		      <input type="text" name="id" value="" />
			<input type="submit" value="Submit" class="btn btn-primary" /> <br />
			
			
		</form>
		
		<div>
		
		 <input type="text" name="id" id="idtest" value="" />
		 <input type="button" name="id" value="认领" onclick="test()" />
		 
		</div>
		
			<div>
		
		 <input type="text" name="id" id="idtest2" value="" />
		 <input type="button" name="id" value="解锁" onclick="test2()" />
		 
		</div>
		
					<div>
		
		 <input type="text" name="id" id="idtest3" value="" />
		 <input type="text" name="message"  value="" />
		 <input type="button" name="id" value="提交评论" onclick="test3()" />
		 
		</div>
		

<script>
      function test(){
    	  $.ajax({

    		   type: "POST",

    		   url: "http://localhost:8081/evcard-vehicleScheduling-rest/dispatch/operLockTask;jsessionid=5E14831F7B7E5CF50AAF973C7261C587",

    		   data: "id="+$("#idtest").val(),

    		   success: function(json){

    		     console.log(json);

    		   }

    		});
      }
     // var url2="http://localhost:8081/evcard-vehicleScheduling-rest/dispatch/operLockTask";
      var url2="http://localhost:8081/evcard-vehicleScheduling-rest/dispatch/operUnlockTask";
     // url2=url2+";jsessionid=DBEC621492050C6BC3F4BA180970D83C?pageNo=1";
      url2=url2+";jsessionid=5E14831F7B7E5CF50AAF973C7261C587?pageNo=1";
      function test2(){
    	  $.ajax({

    		   type: "POST",

    		   url: url2,

    		   data: "id="+$("#idtest2").val(),

    		   success: function(json){

    		     console.log(json);

    		   }

    		});
      }
      
      
      var url3="http://localhost:8081/evcard-vehicleScheduling-rest/common/operSubmitComments";
      // url2=url2+";jsessionid=DBEC621492050C6BC3F4BA180970D83C?pageNo=1";
       url3=url3+";jsessionid=5E14831F7B7E5CF50AAF973C7261C587";
   
      function test3(){
    	  $.ajax({

    		   type: "POST",

    		   url: url3,

    		   data: "id="+$("#idtest3").val(),

    		   success: function(json){

    		     console.log(json);

    		   }

    		});
      }
</script>
		
</body>
</html>
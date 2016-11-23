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




	<div class="container-fluid">
		<center>
			<h1>测试上传下载包括数据库</h1>
		</center>



		<a href="/pics/photo.png">photo</a> <a href="/pics/test.docx">file</a>
		<a href="/files/test.docx">file</a> <br/>
		<a href="${path}/test/download?key=df2.docx">download</a><br/>
		<a href="${path}/test/download2?key=upload3.docx">download2</a><br/>
		<a href="${path}/test/deleteFile?key=test.docx">delete</a><br/>

		<form id="uploadForm" action="${path}/test/upload3" method="post" enctype="multipart/form-data">
			<input id="file" type="file" name="file" /> <br /> 
			<input type="submit" value="Submit" class="btn btn-primary" /> <br />
		</form>
</body>
</html>
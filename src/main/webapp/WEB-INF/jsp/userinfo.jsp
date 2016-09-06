<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- <link href="${path}/resources/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" /> --%>

<link href="${path}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${path}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${path}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/dataTables.bootstrap.min.js"></script>


<title>userinfo page</title>

<style type="text/css">
.table th, .table td {
    text-align: center;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#example').DataTable({
				"ajax" : "${path}/userInfo/getAll",
				"autoWidth" : true,//自动宽度
				"columns" : [ {
					"data" : "id"
				}, {
					"data" : "name"
				}, {
					"data" : "salary"
				}, {
					"data" : "sex"
				}, {
					"data" : "descn"
				} ],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : false,
					"searchable" : false
				} ],
				"oLanguage" : {//国际语言转化
					"sLengthMenu" : "显示 _MENU_ 记录",
					"sZeroRecords" : "对不起，查询不到任何相关数据",
					"sEmptyTable" : "未有相关数据",
					"sLoadingRecords" : "正在加载数据-请等待...",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
					"sInfoEmpty" : "当前显示0到0条，共0条记录",
					"sInfoFiltered" : "（数据库中共为 _MAX_ 条记录）",
					"sProcessing" : "正在加载数据...",
					"sSearch" : "模糊查询：",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : " 上一页 ",
						"sNext" : " 下一页 ",
						"sLast" : " 尾页 "
					}
				},
			});
		});
	</script>
	<h1>userinfo page</h1>
	<div>
		<table id="example" class="table table-striped table-condensed table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>薪资</th>
					<th>性别</th>
					<th>描述</th>
				</tr>
			</thead>
		</table>

	</div>


</body>
</html>
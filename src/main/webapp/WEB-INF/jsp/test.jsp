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
<link href="${path}/resources/css/sweetalert.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${path}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/sweetalert.min.js"></script>
<title>userinfo page</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function() {
 dtable=   $('#example').DataTable( {
        "ajax": {  
         //   "url": "${path}/resources/data/objects2.js",   
          "url": "${path}/test/getDataTablePage",
            "data": function ( d ) {  
                var level1 ="test"  ;
                //添加额外的参数传给服务器  
                d.test = level1;  
            }  
        },
       "processing": true,
        "serverSide": true, 
     "ordering": false, //排序功能
     "pageLength": 10,
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "salary" },
            { "data": "startDate" }
       
        ],
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
        "columnDefs" :[
                       {
                           "targets": [0], // 目标列位置，下标从0开始
                           "render": function(data, type, row) { // 返回自定义内容
                               return '<font color="red">'+data+'</font>';
                           }
                       },
                       {
                           "targets": [4], // 目标列位置，下标从0开始
                           "data": "salary", // 数据列名
                           "render": function(data, type, full) { // 返回自定义内容
                               return ' <input type="button" class="btn btn-primary" value="导出" onclick="alert(\''+data+'\')"/>';
                           }
                       },
                       {
                           "targets": [5], // 目标列位置，下标从0开始
                           "data": "salary", // 数据列名
                       	"visible" : false,
    					"searchable" : false
                       }
                ]
    } );
} );

function reload(){
	dtable.ajax.reload();
}
</script>


              <h1>userinfo page</h1>
              
              <input type="button" class="btn btn-primary" value="导出" onclick="reload()"/>
              <table id="example" class="table table-striped table-condensed table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Salary</th>
                <th>Start date</th>
                   <th>operatiron</th>
                   <th>id</th>
            </tr>
        </thead>
    </table>
              
</body>
</html>
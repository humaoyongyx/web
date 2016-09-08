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
            "url": "${path}/resources/data/objects.js",   
            "data": function ( d ) {  
                var level1 ="test"  ;
                //添加额外的参数传给服务器  
                d.test = level1;  
            }  
        },
        "processing": true,
        "serverSide": true,
     "ordering": false, //排序功能
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "extn" },
            { "data": "start_date" },
            { "data": "salary" }
        ],
        "columnDefs" :[
                       {
                           "targets": [6], // 目标列位置，下标从0开始
                           "data": "salary", // 数据列名
                           "render": function(data, type, full) { // 返回自定义内容
                               return ' <input type="button" class="btn btn-primary" value="导出" onclick="alert(\''+data+'\')"/>';
                           }
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
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
                   <th>operatiron</th>
            </tr>
        </thead>
    </table>
              
</body>
</html>
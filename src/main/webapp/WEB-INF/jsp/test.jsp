<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
<link href="${path}/resources/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path}/resources/js/jquery.dataTables.min.js"></script>
<title>userinfo page</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable( {
        "ajax": "${path}/resources/data/objects.js",
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "extn" },
            { "data": "start_date" },
            { "data": "salary" }
        ]
    } );
} );
</script>
              <h1>userinfo page</h1>
              <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>
              
</body>
</html>
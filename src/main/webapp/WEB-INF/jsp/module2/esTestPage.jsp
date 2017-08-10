<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>ES测试</title>

<style type="text/css">
    tag{
       color:red;
     }
     em{
        color:red;
     }
    
</style>
</head>
<body>
<script type="text/javascript">

   /*  $.getJSON("http://localhost:8081/evcard-vehicleScheduling-rest/schedulingTask/test", function(json){
	   console.log(json);
	}); */
	
	$(document).ready(function() {
		
		dTable=	$('#table').DataTableServer({
			 "ajax": {
				 "url":"${path}/api/esPage",
				 "data":function(data){
					 data.value=$("#search").val();
				 }
			 },
				"columnDefs" : [ {
					"name": "id",
				   "data":"id",
				   "targets" : [ 1 ]
				 
				},{
					"name": "name",
						"data": "name",
						"targets" : [ 2 ],
				},{
					"name": "nameHighlight",
					"data": "nameHighlight",
					"targets" : [ 0 ],
				  }
				]
			}); 
		
	});
     function searchFun(e){
		//console.log($(e).val())
		/* var result="";
	    $.getJSON("/web/api/es",{value:$(e).val()}, function(json){
	       result="total:"+json.total+"<br/>";
	        for(key in json.data){
	        	 result+=json.data[key].nameHighlight+"<br/>";
	        }
	        $("#result").html(result);
	   });  */
    	 dTable.ajax.reload();
	
		
	}

</script>



	<div class="container-fluid">
		<center>
			<h1>ES测试</h1>
		</center>


    <div id="seachDiv" >
        <form action="">
               搜索：<input id="search" name="seach" value="" onkeyup="searchFun(this)">
        </form>
    </div>
	 <div id="result" >
	        
	        
	       <table id="table" class="table table-striped table-condensed table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>名称高亮</th>
					<th>id</th>
					<th>名称</th>
				
				</tr>
			</thead>
		</table>
		
	</div>	
		
</body>
</html>
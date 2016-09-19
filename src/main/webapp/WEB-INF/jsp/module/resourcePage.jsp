<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>


<title>Resource Page</title>
</head>
<body>
	<script>
			$(document).ready(function() {
				initTreeviewData();
		
			});
	
			
			
			function initTreeviewData(){
				$.getJSON("${path}/basic/menus", function(json){
					$('#resource_menu').treeview(json);
					$('#resource_menu').treeview('expandAll', { silent: true });
					    $('#resource_menu').on('nodeSelected', function(event, data) {
						    /*     console.log(data);
						           $("#menuId").val(data.id);
						           $("#form_resource").submit();  */
						           $("#show").load("${path}/module/resource/show",{menuId:data.id},function(){
						        	/*    $('#resource_menu').treeview('remove'); */
						           });
						        
						       
						});
					});
				
			}
	</script>
	

 <div class="container-fluid">	
     <center>
                    <h1>资源设置</h1>
     </center>

 
 <div class="row" >
  <div class="col-md-3" >
  <br/>
  <br/>
                    <div id="resource_menu"></div>
  </div>
  <div class="col-md-8">
 
               <!-- table_add_menu -->
 <div id="table_add_menu">
 	   <div class="pull-right">
            <input type="button" class="btn btn-success" value="返回" onclick="back()" />&nbsp;
		</div>
		<div class="clearfix"></div>
		<br/>
		
		<div id="show">
		
		</div>
		
		<form class="form-horizontal" role="form"  id="form_resource" action="${path}/module/resource/show">
		  
		   <input type="hidden" id="menuId" name="menuId"/>
		    <c:forEach items="${resourceBeans}" var="item">
		      <div class="form-group">
				<label for="pid" class="col-sm-2 control-label">${item.name}</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="pid" name="pid"  value="${item.action}" readonly="readonly">
				</div>
			</div>
		              
		  </c:forEach>
		
		  
		</form>

		</div>
  <!-- end table_add_menu -->
</div>
 
 </div>
  
  
</body>
</html>
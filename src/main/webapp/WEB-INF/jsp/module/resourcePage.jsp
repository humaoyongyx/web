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
	     
	      var menuId;
	
			$(document).ready(function() {
				initTreeviewData();
			});
			
			function initTreeviewData(){
				$.getJSON("${path}/basic/menus", function(json){
					$('#resource_menu').treeview(json);
					$('#resource_menu').treeview('expandAll', { silent: true });
					    $('#resource_menu').on('nodeSelected', function(event, data) {
					    	       menuId=data.id;
						           $("#resourcePagePart").load("${path}/module/resource/show",{menuId:data.id},function(){
						        	   $("#resourcePart_submit").hide();
					   				   $("#resourcePart_cancel").hide();
						           });
						       
						});
					});
				
			}
			
			
         function modify_resource(){
        	 $("input","#resourcePagePart").attr("readonly",false);
        		$("#resourcePart_submit").show();
				$("#resourcePart_cancel").show();
         }
         function cancel_resource(){
        	   $("#resourcePagePart").load("${path}/module/resource/show",{menuId:menuId},function(){
        		   $("#resourcePart_submit").hide();
   				   $("#resourcePart_cancel").hide();
        	   });
         }
         
         function submit_resource(){
        			var options = {
        				     type:  "post",
        				     dataType:"json",
        					 url:"${path}/module/resource/addOrUpdate",
        					beforeSubmit : function() {
        					},
        					success : function(result) {
                               if(result.status ==1){
                            	   cancel_resource();
                            	   swal("", "更新成功！","success");
                               }else{
                            	   swal("更新失败", result.message,"error");
                               }
        						
        					},
        					error : function(error) {
        						  swal("系统异常", error,"error");
        					}
        				};
        				$("#form_addOrUpdate_resource").ajaxSubmit(options);
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
 
               <!-- table_add_resource -->
			 <div id="table_add_menu">
			 
					<br/>
				  <form class="form-horizontal" role="form"  id="form_addOrUpdate_resource">	
				 
					<div id="resourcePagePart">
					
					</div>
					</form>
			
		 </div>
  <!-- end table_add_resource-->
</div>
 
 </div>
  
  
</body>
</html>
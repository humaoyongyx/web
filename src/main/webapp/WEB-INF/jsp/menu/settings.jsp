<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>目录设置</title>

</head>
<body>
	<script>
	$(document).ready(function() {
		 init();
 		menuTable=	$('#menu').DataTable({
 			"dom" : "tip",
 			// "searching": false,
 		  //  "ordering": false,
			 "ajax": {"url":"${path}/menu/getAll" },
				"columnDefs" : [ {
					 "data":"id",
					 "targets" : [ 0 ],
					 "searchable" : false,
					  "orderable": false,
					 "render": function(data, type, row) { // 返回自定义内容
						     if(data==1 || data==2){
						    	 return "";
						     }
                             return '<div class="checkbox"><label><input type="checkbox" value="'+data+'" ></label></div>';                       
	                    }
					
				},{
					"name": "id",  
					 "data":"id",
					"targets" : [ 1 ],
				},{
					"name": "pid",  
					 "data":"pid",
					"targets" : [ 2 ],
					
				},{
					"name": "text",  
					 "data":"text",
					"targets" : [3],
					
				},{
					"name": "icon", 
					"data":"icon",
					"targets" : [4],
					"render": function(data, type, row) { // 返回自定义内容
						if(!$.isEmptyObject(data)){
							 return ' <span class="'+data+'"></span>';
						}else{
							return "";
						}
                       
                    }
				},{
					"name": "url",  
					"data":"url",
					"targets" : [ 5 ]
				},
				{
					"name": "orderNo",  
					"data":"orderNo",
					"targets" : [ 6 ]
					
				}
				]
			}); 
 		
 		 $('#menu_search').on( 'keyup click', function () {
 			    menuTable.search( $('#menu_search').val(), false,false).draw();
 		    } );
 		 
 		$(addFormDiv).submit(function() {
 			var valid=validateForm.form();
 	
 			var options = {
 				     type:  "post",
 					beforeSubmit : function() {
 					},
 					success : function(result) {
 	                  if(result =="success"){
 	               	   $(addFormDiv).resetForm();
 	                 	back();
 	                 	reload();
 	               	     swal("", "新增或修改成功！","success");
 	                  }else{
 	               	   swal("", "新增或修改失败！","error");
 	                  }
 						
 					},
 					error : function(result) {
 						   swal("", "新增或修改异常！","error");
 					}
 				};
 			if(valid){
 				$(this).ajaxSubmit(options);
 			 }
				
				return false;
		
 		});
 		 
 		var validateForm=$(addFormDiv).validate();
 		
 		 
	});
	
	var idPage="menu";
	var pageDiv="#table_page_"+idPage;
	var addDiv="#table_add_"+idPage;
	var addFormDiv="#form_add_"+idPage;
	
	function init(){
		$(addDiv).hide();
	}
	
	function add(){
		$(pageDiv).hide();
		$(addDiv).show();
	}
	
	function modify(){
		var id=checkSelected1();
	 	if(id){
		    menuTable.rows().data().each(function(row,i){
			  if(row.id==id){
				 $("#id").val(row.id);
				 $("#pid").val(row.pid);
				 $("#text").val(row.text);
				 $("#icon").val(row.icon);
				 $("#url").val(row.url);
				 $("#orderNo").val(row.orderNo);
			  }
		   });
			$(pageDiv).hide();
			$(addDiv).show();
		}else{
			   swal("", "请选择一项，或只能修改一项！","info");
		} 
	
		
	}
	
	function back(){
		$(pageDiv).show();
		$(addDiv).hide();
		
	}
	
	function checkSelected1(){
		var i=0;
		var id=0;
		$("input:checked","#menu").each(function(){
			  i++;
			  id=$(this).val();
		});
		if( i==1){
			return id;
		}else{
			return false;
		}
	}
	
	function checkSelected(){
		var flag=false;
		$("input:checked","#menu").each(function(){
				flag= true;
		});
		return flag;
	}
	
	
	function del(){
		
		if(checkSelected()){
			 swal({
				  title:"",
				  text: "你确定要删除选中的信息吗？",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "是的，删除它！",
				  closeOnConfirm: false,
				  confirmButtonText:"确定",  
		          cancelButtonText:"取消",  
				  html: false
				}, function(){
					
					$("input:checked","#menu").each(function(){
						var id=$(this).val();
						$.post("${path}/menu/delete", { id:id},
						          function(result){
									  if(result =="success"){
										    reload();
						               	   swal("", "删除成功！","success");
						                }else{
						               	   swal("", "删除失败！","error");
						              }
					    });
				    });
					
				}); 
		}else{
			
			   swal("", "请至少选中一项！","info");
		}
		 
	
	}

	
	function reload() {
		menuTable.ajax.reload();
	}
	
	function menu_add(){
		var options = {
			     type:  "post",
				beforeSubmit : function() {
				},
				success : function(result) {
                  if(result =="success"){
               	   $(addFormDiv).resetForm();
                 	back();
                 	reload();
               	     swal("", "新增或修改成功！","success");
                  }else{
               	   swal("", "新增或修改失败！","error");
                  }
					
				},
				error : function(result) {
					   swal("", "新增或修改异常！","error");
				}
			};
			$(addFormDiv).ajaxSubmit(options);
	}
	</script>

 
  
  
  	<div class="container-fluid">
  	  <center>
  	         <h1>目录设置</h1>
  	  </center>
  	 
  	 
  	 
  <!-- table_page_menu -->
    <div  id="table_page_menu">
    
  	   <form id="menuForm" action="${path}/menu/exportExcel">
			<div class="pull-right">
				   模糊查询： <input type="text" id="menu_search" name="text"> &nbsp;
				   <input type="submit" class="btn btn-success" value="导出Excel" />&nbsp;
				   <input type="button" class="btn btn-success" value="新增" onclick="add()" />&nbsp;
				   <input type="button" class="btn btn-success" value="删除" onclick="del()" />&nbsp;
				   <input type="button" class="btn btn-success" value="修改" onclick="modify()" />&nbsp;
			</div>
		</form>

		<div class="clearfix"></div>
		<br/>
		<table id="menu" class="table table-striped table-condensed table-bordered" cellspacing="0" >
			<thead>
				<tr>
				    <th>选择</th>
					<th>id</th>
					<th>父目录ID</th>
					<th>名称</th>
					<th>图标</th>
					<th>资源链接</th>
					<th>顺序</th>
				</tr>
			</thead>
		</table>
		
	</div>	
 <!-- end table_page_menu -->
 
 <!-- table_add_menu -->
 <div id="table_add_menu">
 	   <div class="pull-right">
            <input type="button" class="btn btn-success" value="返回" onclick="back()" />&nbsp;
		</div>
		<div class="clearfix"></div>
		<br/>
		<form class="form-horizontal" role="form"  id="form_add_menu" action="${path}/menu/add"">
		     <input type="hidden" id="id" name="id"/>
			<div class="form-group">
				<label for="pid" class="col-sm-2 control-label">父目录ID</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="pid" name="pid" placeholder="父目录ID" >
				</div>
			</div>
			<div class="form-group">
				<label for="text" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="text" name="text" placeholder="名称" required>
				</div>
			</div>
			<div class="form-group">
				<label for="icon" class="col-sm-2 control-label">图标</label>
				<div class="col-sm-4">
					   <input type="text" class="form-control" id="icon" name="icon" placeholder="图标"> 
				</div>
			</div>
		    <div class="form-group">
				<label for="url" class="col-sm-2 control-label">资源链接</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="url" name="url" placeholder="资源链接" >
				</div>
			</div>
		     <div class="form-group">
				<label for="orderNo" class="col-sm-2 control-label">顺序</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="orderNo" name="orderNo" placeholder="顺序" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"  class="btn btn-success"  >提交</button>
				</div>
			</div>
		</form>



		</div>
  <!-- end table_add_menu -->
 
 
	</div>

	
</body>
</html>
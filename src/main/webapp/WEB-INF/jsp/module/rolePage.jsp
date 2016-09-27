<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>角色设置</title>

</head>
<body>
	<script>
	
	var idPage="role";
	var pageId="#"+idPage;
	var tableSearchId=pageId+"_search";
	var pageDiv="#table_page_"+idPage;
	var addOrUpdateDiv="#table_addOrUpdate_"+idPage;
	var addOrUpdateFormDiv="#form_addOrUpdate_"+idPage;
	
	var deleteActionUrl="${path}/module/role/delete";
	var deleteAllActionUrl="${path}/module/role/deleteAll";
	var addOrUpdateActionUrl="${path}/module/role/addOrUpdate";
	var getPageActionUrl="${path}/module/role/show";
	var dTable;
	var roleResource="#roleResource";
	
	var dTableOptions= {
 			 "dom" : "tip",
			 "ajax": {"url":getPageActionUrl },
			"columnDefs" : [
			   {     
				   "data":"id",
					 "targets" : [ 0 ],
					 "searchable" : false,
					  "orderable": false,
					 "render": function(data, type, row) { // 返回自定义内容
						     if(data==1 ){
						    	 return "";
						     }
                             return '<div class="checkbox"><label><input type="checkbox" value="'+data+'" ></label></div>';                       
	                    }
					
				},{
					"name": "id",  
					 "data":"id",
					"targets" : [ 1 ],
				},{
					"name": "name",  
					 "data":"name",
					"targets" : [ 2 ],
					
				}
		 ]
	};
	
	function modifyCopy(row){
		 $("#id").val(row.id);
		 $("#name").val(row.name);
	}
	
	
	
	
	$(document).ready(function() {
		  init();
		  dTable=	$(pageId).DataTable(dTableOptions); 
 		
 		 $(tableSearchId).on( 'keyup click', function () {
 			dTable.search( $(tableSearchId).val(), false,false).draw();
 		    } );
 		 
 		$(addOrUpdateFormDiv).submit(function() {
 			var valid=validateForm.form();
 	
 			var options = {
 				    type:  "post",
 				   dataType:"json",
 				    url:addOrUpdateActionUrl,
 					beforeSubmit : function() {
 					},
 					success : function(result) {
 	                  if(result.status==1){
	 	               	   $(addOrUpdateFormDiv).resetForm();
	 	                 	back();
	 	                 	reload();
	 	               	     swal("", "新增或修改成功！","success");
 	                  }else {
 	               	       swal("新增或修改失败", result.message,"error");
 	                  }
 						
 					},
 					error : function(error) {
 						   swal("系统异常",error,"error");
 					}
 				};
 			if(valid){
 				$(this).ajaxSubmit(options);
 			 }
				
				return false;
		
 		});
 		var validateForm=$(addOrUpdateFormDiv).validate();		 
 		 
	});
	

	
	function init(){
		$(addOrUpdateDiv).hide();
	}
	
	
	function add(){
		$(pageDiv).hide();
		$(addOrUpdateDiv).show();
		$(roleResource).load("${path}/module/role/showRoleResourcePage",{roleId:1},function(){
			$(":text",addOrUpdateFormDiv).val("");
			$("input:hidden",addOrUpdateFormDiv).val("");
			$(":checkbox",addOrUpdateFormDiv).prop("checked",false);
		});
	}
	
	function modify(){
		var id=checkSelected1();
	 	if(id){
		    dTable.rows().data().each(function(row,i){
			  if(row.id==id){
				  modifyCopy(row);
			  }
		   });
			$(pageDiv).hide();
			$(addOrUpdateDiv).show();
			$(roleResource).load("${path}/module/role/showRoleResourcePage",{roleId:id});
		}else{
			   swal("", "请选择一项，或只能修改一项！","info");
		} 
	
		
	}
	
	function back(){
		$(pageDiv).show();
		$(addOrUpdateDiv).hide();
		
	}
	
	function checkSelected1(){
		var i=0;
		var id=0;
		$("input:checked",pageId).each(function(){
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
		$("input:checked",pageId).each(function(){
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
					
					
					var ids=new Array();
					$("input:checked",pageId).each(function(){
						var id=$(this).val();
						ids.push(id);
						
				    });
					$.post(deleteAllActionUrl, { ids:ids},
					          function(result){
								   if(result.status ==1){
									    reload();
					               	   swal("", "删除成功！","success");
					                }else{
					               	   swal("删除失败！",result.message,"error");
					              } 
				    },"json");
					
				}); 
		}else{
			
			   swal("", "请至少选中一项！","info");
		}
		 
	
	}

	
	function reload() {
		dTable.ajax.reload();
	}
	
	function selectAll(obj){
	   var checkBoxClass="."+$(obj).val();
	   var checkFlag= $(obj).prop("checked");
	   $(checkBoxClass).prop("checked",checkFlag);
	}
	</script>

 
  
  
  	<div class="container-fluid">
  	  <center>
  	         <h1>角色设置</h1>
  	  </center>
  	 
  	 
  	 
  <!-- table_page_role -->
    <div  id="table_page_role">
    
  	   <form id="roleForm" action="${path}/module/role/exportExcel">
			<div class="pull-right">
				   模糊查询： <input type="text" id="role_search" name="text"> &nbsp;
				   <input type="submit" class="btn btn-success" value="导出Excel" />&nbsp;
				   <input type="button" class="btn btn-success" value="新增" onclick="add()" />&nbsp;
				   <input type="button" class="btn btn-success" value="删除" onclick="del()" />&nbsp;
				   <input type="button" class="btn btn-success" value="修改" onclick="modify()" />&nbsp;
			</div>
		</form>

		<div class="clearfix"></div>
		<br/>
		<table id="role" class="table table-striped table-condensed table-bordered" cellspacing="0"  width="100%">
			<thead>
				<tr>
				    <th>选择</th>
					<th>角色id</th>
					<th>名称</th>
				</tr>
			</thead>
		</table>
		
	</div>	
 <!-- end table_page_role -->
 
 <!-- table_add_role -->
 <div id="table_addOrUpdate_role">
 	   <div class="pull-right">
            <input type="button" class="btn btn-success" value="返回" onclick="back()" />&nbsp;
		</div>
		<div class="clearfix"></div>
		<br/>
		<form class="form-horizontal" role="form"  id="form_addOrUpdate_role" >
		     <input type="hidden" id="id" name="id"/>
			<div class="form-group">
				<label for="pid" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name" name="name" placeholder="名称" required>
				</div>
			</div>
		
	       <div class="form-group">&nbsp;&nbsp;</div>
			<div class="form-group">
		         <div  id="roleResource" >
		         
		         </div>
	       </div>
	       
	        <div class="form-group">&nbsp;&nbsp;</div>
	        
	       	<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"  class="btn btn-success"  >提交</button>
				</div>
			</div>
	</form>
	
		</div>
  <!-- end table_add_role -->
 
 
	</div>

	
</body>
</html>
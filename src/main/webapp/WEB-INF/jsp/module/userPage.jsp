<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>用户设置</title>

</head>
<body>
	<script>
	
	var idPage="user";
	var pageId="#"+idPage;
	var tableSearchId=pageId+"_search";
	var pageDiv="#table_page_"+idPage;
	var addOrUpdateDiv="#table_addOrUpdate_"+idPage;
	var addOrUpdateFormDiv="#form_addOrUpdate_"+idPage;
	
	var deleteActionUrl="${path}/module/user/delete";
	var deleteAllActionUrl="${path}/module/user/deleteAll";
	var addOrUpdateActionUrl="${path}/module/user/addOrUpdate";
	var getPageActionUrl="${path}/module/user/show";
	var getUserRolesUrl="${path}/module/role/getUserRoles";
	var dTable;
	
	var currentUserRoleJson;
	
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
					
				},{
					"name": "nameId",  
					 "data":"nameId",
					"targets" : [ 3 ],
					
				},{
					"name": "status",  
					"data":"status",
					"targets" : [ 4 ]
				},{
					"name": "roleName",  
					"data":"roleName",
					"targets" : [5]
					
				}
		 ]
	};
	
	function modifyCopy(row){
		 $("#id").val(row.id);
		 $("#name").val(row.name);
		 $("#nameId").val(row.nameId);
		 $("#roleName").val(row.roleName);
		 $("#status").val(row.status);
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
 	                  if(result.status ==1){
 	               	   $(addOrUpdateFormDiv).resetForm();
 	                 	back();
 	                 	reload();
 	               	     swal("", "新增或修改成功！","success");
 	                  }else{
 	               	      swal("新增或修改失败", result.message,"error");
 	                  }
 						
 					},
 					error : function(error) {
 						   swal("系统异常", error,"error");
 					}
 				};
 			if(valid){
 				$("#user_multiselect_to option").prop("selected",true);
 				$(this).ajaxSubmit(options);
 			 }
				
				return false;
		
 		});
 		var validateForm=$(addOrUpdateFormDiv).validate();		
 		
 		 $('#user_multiselect').multiselect(); 
 		 
 		$.getJSON(getUserRolesUrl,{userId:"${user.id}"},function(json){
 			currentUserRoleJson=json;
		});
 		
	});
	

	
	function init(){
		$(addOrUpdateDiv).hide();
	}
	
	function cleanAddForm(){
		$(":text",addOrUpdateFormDiv).val("");
		$("input:hidden",addOrUpdateFormDiv).val("");
		$("#status option:first").prop("selected",true);
		$("#user_multiselect").empty();
		$("#user_multiselect_to").empty();
		enablePasword();
	}
	
	
	function cleanModifyForm(){
		$("#user_multiselect").empty();
		$("#user_multiselect_to").empty();
		disablePasword();
	}
	
	function add(){
		$(pageDiv).hide();
		$(addOrUpdateDiv).show();
		cleanAddForm();
		$.each(currentUserRoleJson,function(i,v){
			$("#user_multiselect").append('<option value="'+v.id+'" >'+v.name+'</option>');
		});
	}
	
	function modify(){
		var id=checkSelected1();
	 	if(id){
	 		cleanModifyForm();
		    dTable.rows().data().each(function(row,i){
			  if(row.id==id){
				  modifyCopy(row);
				   var roleIds=row.roleId.split(",");
				  $.each(currentUserRoleJson,function(i,v){
					  if($.inArray(v.id+"",roleIds)>-1){
						  $("#user_multiselect_to").append('<option value="'+v.id+'" >'+v.name+'</option>'); 
					  }else{
						  $("#user_multiselect").append('<option value="'+v.id+'" >'+v.name+'</option>');
					  }
			       });
			  }
		   });
			$(pageDiv).hide();
			$(addOrUpdateDiv).show();
			
			
		}else{
			   swal("", "请选择一项，或只能修改一项！","info");
		} 
	}
	
	function disablePasword(){
		$("#password").attr("disabled",true);
		$("#password").val("");
		$("#resetPassword").show();
	}
	
	function enablePasword(){
		$("#password").attr("disabled",false);
		$("#password").val("");
		$("#resetPassword").hide();
	}
	
	function resetPasswordFunc(obj){
	   var flag=$("#password").attr("disabled");
	   if(flag=="disabled"){
		   $(obj).text("取消");
		   $("#password").attr("disabled",false);
	   }else{
		   $(obj).text("重置");
		   $("#password").val("");
		   $("#password").attr("disabled",true);
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
	
	</script>

 
  
  
  	<div class="container-fluid">
  	  <center>
  	         <h1>用户设置</h1>
  	  </center>
  	 
  	 
  	 
  <!-- table_page_user -->
    <div  id="table_page_user">
    
  	   <form id="userForm" action="${path}/module/user/exportExcel">
			<div class="pull-right">
				   模糊查询： <input type="text" id="user_search" name="text"> &nbsp;
				   <input type="submit" class="btn btn-success" value="导出Excel" />&nbsp;
				   <input type="button" class="btn btn-success" value="新增" onclick="add()" />&nbsp;
				   <input type="button" class="btn btn-success" value="删除" onclick="del()" />&nbsp;
				   <input type="button" class="btn btn-success" value="修改" onclick="modify()" />&nbsp;
			</div>
		</form>

		<div class="clearfix"></div>
		<br/>
		<table id="user" class="table table-striped table-condensed table-bordered" cellspacing="0"  width="100%">
			<thead>
				<tr>
				    <th>选择</th>
					<th>id</th>
					<th>名称</th>
					<th>用户Id</th>
					<th>状态</th>
					<th>用户组</th>
				</tr>
			</thead>
		</table>
		
	</div>	
 <!-- end table_page_user -->
 
 <!-- table_add_user -->
 <div id="table_addOrUpdate_user">
 	   <div class="pull-right">
            <input type="button" class="btn btn-success" value="返回" onclick="back()" />&nbsp;
		</div>
		<div class="clearfix"></div>
		<br/>
		<form class="form-horizontal" role="form"  id="form_addOrUpdate_user" >
		     <input type="hidden" id="id" name="id"/>
			<div class="form-group">
				<label for="pid" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name" name="name" placeholder="名称" required>
				</div>
			</div>
			<div class="form-group">
				<label for="text" class="col-sm-2 control-label">用户Id</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="nameId" name="nameId" placeholder="用户Id" required>
				</div>
			</div>
		    <div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="password" name="password" placeholder="密码" >
				</div>
			     <div class="col-sm-2">
						<button type="button"  class="btn btn-success"  id="resetPassword" onclick="resetPasswordFunc(this)">重置</button>
				</div>
			</div>
		     <div class="form-group">
				<label for="orderNo" class="col-sm-2 control-label">状态</label>
				<div class="col-sm-4">
					<select name="status" id="status" class="form-control"  placeholder="状态"  required>
					    <option value="1">有效</option>
					    <option value="2">锁定</option>
					    <option value="3">失效</option>
					</select>
					<!-- <input type="text" class="form-control" id="status" name="status" placeholder="状态" required> -->
				</div>
			</div>
			<div class="form-group">
				<label for="icon" class="col-sm-2 control-label">用户组</label>
				<div class="col-sm-4">
					   <!-- <input type="text" class="form-control" id="roleName" name="roleName" placeholder="用户组" required>  -->
							<div class="row">
									<div class="col-xs-5">
									     <p class="text-center">未添加的角色列表</p>
										<select name="roleIdFrom" id="user_multiselect" class="form-control" size="8" multiple="multiple">
										</select>
									</div>
									
									<div class="col-xs-2">
									   <p class="text-center">&nbsp;&nbsp;</p>
										<button type="button" id="user_multiselect_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
										<button type="button" id="user_multiselect_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
										<button type="button" id="user_multiselect_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
										<button type="button" id="user_multiselect_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
									</div>
									
									<div class="col-xs-5">
									     <p class="text-center">此用户的角色列表</p>
										<select name="roleIdTo" id="user_multiselect_to" class="form-control" size="8" multiple="multiple">
										</select>
									</div>
						 </div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"  class="btn btn-success"  >提交</button>
				</div>
			</div>
		</form>



		</div>
  <!-- end table_add_user -->
 
 
	</div>

	
</body>
</html>
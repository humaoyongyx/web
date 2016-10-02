<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>调度器设置</title>

</head>
<body>
	<script>
	
	var idPage="scheduler";
	var pageId="#"+idPage;
	var tableSearchId=pageId+"_search";
	var pageDiv="#table_page_"+idPage;
	var addOrUpdateDiv="#table_addOrUpdate_"+idPage;
	var addOrUpdateFormDiv="#form_addOrUpdate_"+idPage;
	
	var deleteAllActionUrl="${path}/module2/scheduler/deleteAll";
	var addActionUrl="${path}/module2/scheduler/add";
	var updateActionUrl="${path}/module2/scheduler/update";
	var getPageActionUrl="${path}/module2/scheduler/show";
	var dTable;
	
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
					"name": "beanName",  
					 "data":"beanName",
					"targets" : [ 3 ],
					
				},{
					"name": "description",  
					 "data":"description",
					"targets" : [ 4 ],
					
				},{
					"name": "cron",  
					 "data":"cron",
					"targets" : [ 5 ],
					
				},{
					"name": "params",  
					 "data":"params",
					"targets" : [ 6 ],
					
				},{
					"name": "status",  
					"data":"status",
					"targets" : [ 7 ],
					 "render": function(data, type, row) { 
					     if(data==1 ){
					    	 return "有效";
					     }else{
					    	 return "失效";
					     }
                    }
				},{
					"name": "runStatus",  
					"data":"runStatus",
					"visible" : false,
					"searchable" : false,
					"targets" : [ 8 ]
					
				}
		 ]
	};
	
	function modifyCopy(row){
		 $("#id").val(row.id);
		 $("#name").val(row.name);
		 $("#beanName").val(row.beanName);
		 $("#description").val(row.description);
		 $("#cron").val(row.cron);
		 $("#params").val(row.params);
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
 				$(this).ajaxSubmit(options);
 			 }
				
				return false;
		
 		});
 		var validateForm=$(addOrUpdateFormDiv).validate();		
 		
	});
	

	
	function init(){
		$(addOrUpdateDiv).hide();
	}
	
	function cleanAddForm(){
		  $(addOrUpdateFormDiv).resetForm();
		  $(addOrUpdateFormDiv).attr("action",addActionUrl);
	}
	
	
	function cleanModifyForm(){
		  $(addOrUpdateFormDiv).resetForm();
		  $(addOrUpdateFormDiv).attr("action",updateActionUrl);
	}
	
	function add(){
		$(pageDiv).hide();
		$(addOrUpdateDiv).show();
		cleanAddForm();
	}
	
	function modify(){
		var id=checkSelected1();
	 	if(id){
	 		cleanModifyForm();
		    dTable.rows().data().each(function(row,i){
			  if(row.id==id){
				  modifyCopy(row);
			  }
		   });
			$(pageDiv).hide();
			$(addOrUpdateDiv).show();
			
			
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
	
	</script>

 
  
  
  	<div class="container-fluid">
  	  <center>
  	         <h1>调度器设置</h1>
  	  </center>
  	 
  	 
  	 
  <!-- table_page_scheduler -->
    <div  id="table_page_scheduler">
    
  	   <form id="schedulerForm" >
			<div class="pull-right">
				   模糊查询： <input type="text" id="scheduler_search" name="text"> &nbsp;
				   <input type="button" class="btn btn-success" value="新增" onclick="add()" />&nbsp;
				   <input type="button" class="btn btn-success" value="删除" onclick="del()" />&nbsp;
				   <input type="button" class="btn btn-success" value="修改" onclick="modify()" />&nbsp;
			</div>
		</form>

		<div class="clearfix"></div>
		<br/>
		<table id="scheduler" class="table table-striped table-condensed table-bordered" cellspacing="0"  width="100%">
			<thead>
				<tr>
				    <th>选择</th>
					<th>id</th>
					<th>名称</th>
					<th>bean名称</th>
					<th>描述</th>
					<th>cron表达式</th>
					<th>参数</th>
					<th>状态</th>
					<th>运行状态</th>
				</tr>
			</thead>
		</table>
		
	</div>	
 <!-- end table_page_scheduler -->
 
 <!-- table_add_scheduler -->
 <div id="table_addOrUpdate_scheduler">
 	   <div class="pull-right">
            <input type="button" class="btn btn-success" value="返回" onclick="back()" />&nbsp;
		</div>
		<div class="clearfix"></div>
		<br/>
		<form class="form-horizontal" role="form"  id="form_addOrUpdate_scheduler" >
		     <input type="hidden" id="id" name="id"/>
			<div class="form-group">
				<label for="pid" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name" name="name" placeholder="名称" required>
				</div>
			</div>
			<div class="form-group">
				<label for="text" class="col-sm-2 control-label">bean名称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id=beanName name="beanName" placeholder="bean名称" required>
				</div>
			</div>
			<div class="form-group">
				<label for="text" class="col-sm-2 control-label">描述</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="description" name="description" placeholder="描述" >
				</div>
			</div>
			<div class="form-group">
				<label for="text" class="col-sm-2 control-label">cron表达式</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="cron" name="cron" placeholder="cron表达式" required>
				</div>
			</div>
		    <div class="form-group">
				<label for="password" class="col-sm-2 control-label">参数</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="params" name="params" placeholder="参数" >
				</div>
			</div>
		     <div class="form-group">
				<label for="orderNo" class="col-sm-2 control-label">状态</label>
				<div class="col-sm-4">
					<select name="status" id="status" class="form-control"  placeholder="状态"  required>
					        <option value="0">失效</option>
					        <option value="1">有效</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"  class="btn btn-success"  >提交</button>
				</div>
			</div>
		</form>



		</div>
  <!-- end table_add_scheduler -->
 
 
	</div>

	
</body>
</html>
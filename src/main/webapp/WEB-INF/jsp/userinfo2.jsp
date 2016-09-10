<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title>userinfo page</title>

</head>
<body>
	<script type="text/javascript">
	
		$(document).ready(function() {
		 dTable=$('#example').DataTable({
			 "ajax": {
				 "url":"${path}/userInfo/getUserInfoPage",
				 "data":function(data){
					 data.name=$("#userinfo_seach").val();
					 
				 }
			 },
			 "dom":"tip",
			/*  "bPaginate": true, //翻页功能
			  "bFilter": false, //列筛序功能 */
			   "processing": true,
		        "serverSide": true, 
			 "lengthChange": false,//是否允许用户自定义显示数量
			 "searching": false,//本地搜索
             "ordering": false, //排序功能
             "Info": true,//页脚信息
             "pageLength": 10,
             responsive: true,
             "order": [
                       [0, "asc" ]
                   ],//第一列排序图标改为默认
				"columnDefs" : [ {
					"name": "id",  
					 "data":"id",
					"targets" : [ 0 ],
					"visible" : false,
					"searchable" : false
				 
				},{
					"name": "name",  
					 "data":"name",
					"targets" : [ 1 ],
					"searchable" : false
					
				},{
					"name": "salary", 
					"data":"salary",
					"targets" : [ 2 ],
					  orderable: false
				},{
					"name": "sex",  
					"data":"sex",
					"targets" : [ 3 ]
				},{
					"name": "descn",  
					"data":"descn",
					"targets" : [ 4 ]
				},
				{
					"name": "photo",  
					"data":"photo",
					"targets" : [ 5 ],
					"render": function(data, type, row) { // 返回自定义内容
						if(!$.isEmptyObject(data)){
							 return ' <img class="img" width="60px" height="40px" alt="" src="'+data+'" />';
						}else{
							return "";
						}
                       
                    }
					
				},
				{
					   "targets": [6], // 目标列位置，下标从0开始
                       "data": "id", // 数据列名
                       "render": function(data, type, row) { // 返回自定义内容
                           return ' <input type="button" class="btn btn-primary" value="删除" onclick="userInfoDelete('+data+')"/>';
                       }
				}
				
				],
		/* 	 	"language" : {//国际语言转化
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
				},  */
			});
		 
		/*  $('#userinfo_seach').on( 'keyup click', function () {
			   dTable.search( $('#userinfo_seach').val(), false,false).draw();
		    } ); */
		    
			$('#uploadForm').submit(function() {
                /*   if($("#file").val()==""){
                	   alert("请选择文件");
                       return false;
                  } */
					var options = {
						//dataType : "json",  
						beforeSubmit : function() {
						},
						success : function(result) {
                           if(result !="fail"){
								 swal("", "上传成功！","success");
								 //document.location.reload(true);
								 //document.location.replace(location.href) ;
									/* $(".img","#example").each(function(index,obj){
										$(obj).attr("src",$(obj).attr("src"));
									}); */
								 setTimeout( "pageReload()" , 1000 );
							
							}
							
						},
						error : function(result) {
							alert(result);
						}
					};
					$(this).ajaxSubmit(options);
					return false;
				});
		    
			 $('#selectDate').datepicker({
				
			 });     
		 
		});
		
		function reload() {
			dTable.ajax.reload();
		}
		
		function pageReload(){
			document.location.reload(true);
		}
		function ajaxSubmit() {
			$('#uploadForm').submit();
		}
	</script>
	<h1>userinfo page</h1>
	
	<input type="text" class="form-control" id="selectDate">
	<form class="form-inline" role="form" id="formAdd">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail2">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon">@</div>
      <input class="form-control" type="email" placeholder="Enter email">
    </div>
  </div>
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword2">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-default">Sign in</button>
</form>
	
	<div>
			<form id="uploadForm" action="${path}/test/upload" method="post"
			enctype="multipart/form-data">
			<input id="file" type="file" name="file" /> <br /> <input
				type="button" value="Submit" class="btn btn-primary"
				onclick="ajaxSubmit()" /> <br /> <input type="hidden"
				name="hidden" value="hidden" />
		</form>
	<form id="userinfoForm" action="${path}/userInfo/exportExcel">
           <input type="hidden" name="test" value="test"/>
            <div  class="pull-right">
             <input type="text" id="userinfo_seach" name="name"> <input type="button" class="btn btn-primary" value="查询" onclick="userInfoSearch()"/>
	    <input type="button" class="btn btn-primary" value="导出" onclick="exportExcel()"/>&nbsp;&nbsp;
	     <input type="button" class="btn btn-primary" value="新增" onclick="userInfoAdd()"/>&nbsp;&nbsp;
	   <!--   <input type="text" id="userinfo_seach" > <input type="button" class="btn btn-primary" value="查询" onclick="userInfoSearch()"/> -->
	    </div>
     </form>
	   
	    <div class="clearfix"></div>
		<table id="example" class="table table-striped table-condensed table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>薪资</th>
					<th>性别</th>
					<th>描述</th>
					<th>照片</th>
					<th>操作</th>
				</tr>
			</thead>
		<%-- 	<tbody>
			<c:forEach items="${ userInfos }"  var="userInfo">
				<tr id="userInfo${ userInfo.id }">
					<td>${ userInfo.id }</td>
					<td>${ userInfo.name}</td>
					<td>${ userInfo.salary}</td>
					<td>${ userInfo.sex}</td>
					<td>${ userInfo.descn}</td>
					<td>${ userInfo.photo}</td>
					<td>
					  <input type="button" class="btn btn-primary" value="删除" onclick="userInfoDelete('${ userInfo.id }')"/>
					</td>
				</tr>
			</c:forEach>	
			</tbody> --%>
		</table>


     
	</div>


<script type="text/javascript">
  function userInfoDelete(id){
	  swal({
		  title:"",
		  text: "你确定要删除此条信息吗？",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "是的，删除它！",
		  closeOnConfirm: false,
		  confirmButtonText:"确定",  
          cancelButtonText:"取消",  
		  html: false
		}, function(){
			$.ajax({
				   type: "POST",
				   url: "${path}/userInfo/delete",
				   data: {id:id},
				   success: function(result){
					   result=$.parseJSON(result);
				     if(result.status==1){
				    	 dTable.row("#userInfo"+id).remove().draw(false);
				    	 swal("", "删除成功！","success");
				      }else{
				    	 swal("", "删除失败！","error");
				     }
				   }
				});
		
		  
		});
  }

  function userInfoAdd(){
		$.ajax({
			   type: "POST",
			   url: "${path}/userInfo/insert",
			   data: {name:'issac hu',salary:'1234',sex:'男',descn:'Edinburgh',photo:'/pics/google.png'},
			   success: function(result){
				   result=$.parseJSON(result);
			     if(result.status==1){
			    	 //dTable.row.add( [null,'Fiona White', 1234, '男','Edinburgh','',''] ).draw();
			    	// dTable.row.add( {name:"Fiona White"} ).draw();
			    	// document.location.reload();
			    	  dTable.ajax.reload();
			      }else{
			    	 swal("", "增加失败！","error");
			     }
			   }
			});
	 
  }
  
  function userInfoAdd2(data){
		$.ajax({
			   type: "POST",
			   url: "${path}/userInfo/insert",
			   data: data,
			   success: function(result){
				   result=$.parseJSON(result);
			     if(result.status==1){
			    	 //dTable.row.add( [null,'Fiona White', 1234, '男','Edinburgh','',''] ).draw();
			    	// dTable.row.add( {name:"Fiona White"} ).draw();
			    	// document.location.reload();
			    	  dTable.ajax.reload();
			      }else{
			    	 swal("", "增加失败！","error");
			     }
			   }
			});
	 
}
  
  function userInfoSearch(){
	  dTable.ajax.reload();
  }
  
  function exportExcel(){
/* 	  var url="${path}/userInfo/exportExcel";
      window.open(url); */
      $("#userinfoForm").submit();
  }
</script>

</body>
</html>
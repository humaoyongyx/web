<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../taglib.jsp"%>
  	
  	<script>
	
	var citiesUrl="${path}/basic/getCites";
	
	
	$(document).ready(function() {
     init();
	var photo= 	$("#photo").fileinput({
		    overwriteInitial: true,
		    maxFileSize: 1500, 
		    showClose: false,
		    showCaption: false,
		    showBrowse: false,
		    browseOnZoneClick: true,
		    removeLabel: '',
		    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		    removeTitle: '取消选择',
		    elErrorContainer: '#kv-avatar-errors-2',
		    msgErrorClass: 'alert alert-block alert-danger',
		    defaultPreviewContent: '<img src="${userBean.photo}"  style="width:160px"><h6 class="text-muted">点击选择(文件大小不能超过1.5M)</h6>',
		    layoutTemplates: {main2: '{preview}  {remove} {browse}'},
		    allowedFileExtensions: ["jpg", "png", "gif"]
		}); 
	 	
		$.getJSON(citiesUrl,{pid:0},function(json){
			$.each(json,function(i,v){
				$("#address_province_select").append("<option value='"+v.id+"'>"+v.name+"</option>");
			});
 			
		});
		
		
		$("#form_userProfile").submit(function() {
 			var valid=validateForm.form();
 			var options = {
 				    type:  "post",
 				   dataType:"json",
 				    url:"${path}/basic/updateUserProfile",
 					beforeSubmit : function() {
 					},
 					success : function(result) {
 	                  if(result.status==1){
	 	               	     swal("", "新增或修改成功！","success");
	 	                  	 reload();
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
 		var validateForm=$("#form_userProfile").validate();		 
		
	 	
	});
	
	
	function init(){
	 	 $(":text","#form_userProfile").attr("disabled",true);
		 $("select","#form_userProfile").attr("disabled",true); 
		 $("#roleName").attr("disabled",true);
		 $("#submit").hide();
		 $("#cancel").hide();
		 
	}
	
	function enable(){
		 $("#roleName").attr("disabled",true);
	 	 $(":text","#form_userProfile").attr("disabled",false);
		 $("select","#form_userProfile").attr("disabled",false); 
		 $("#submit").show();
		 $("#cancel").show();
	}
	
	function cancelFunc(){
		reload();
	}
	
	function reload(){
		 $("#userProfile_center").load("${path}/basic/showUserProfilePart");
	}
	
	function provinceChange(obj){
		$("#address_city_select").empty();
		$("#address_city_select").append("<option value=''>城市</option>");
		$.getJSON(citiesUrl,{pid:$(obj).val()},function(json){
			$.each(json,function(i,v){
				$("#address_city_select").append("<option value='"+v.id+"'>"+v.name+"</option>");
			});
		});
	}
	
	function cityChange(obj){
		$("#address_district_select").empty();
		$("#address_district_select").append("<option value=''>区县</option>");
		$.getJSON(citiesUrl,{pid:$(obj).val()},function(json){
			$.each(json,function(i,v){
				$("#address_district_select").append("<option value='"+v.id+"'>"+v.name+"</option>");
			});
		});
	}
	
	function districtChange(obj){
		var address=$("#address_province_select").find("option:selected").text()+""+ $("#address_city_select").find("option:selected").text()+""+ $("#address_district_select").find("option:selected").text();
		$("#address").val(address);
	}
	</script>
  	
  	
  	
  	
  	
  	
  	<div>
  	 <form class="form-horizontal" role="form"  id="form_userProfile"    enctype="multipart/form-data" >
  	        
  	          <input type="hidden" name="id" value="${userBean.id}">
  	         <input type="hidden" name="nameId" value="${userBean.nameId}">
  	         <input type="hidden" name="photo" value="${userBean.photo}">
  	 
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" placeholder="名称"  value="${userBean.name}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">用户组</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${userBean.roleName}" readonly="readonly" disabled="disabled" id="roleName">
				</div>
			</div>
			 <div class="form-group">
				<label for="photo" class="col-sm-2 control-label">头像</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" id="photo" name="photoFile"  >
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<select name="sex" id="sex" class="form-control"  placeholder="性别"   >
					       <option value=""></option>
   					       <option value="1"  <c:if test="${userBean.sex!=null&& user.sex=='1'}">  selected="selected" </c:if>>男</option>
						    <option value="0" <c:if test="${userBean.sex!=null&& user.sex=='0'}">  selected="selected" </c:if> >女</option>
					</select>
				</div>
			</div>
		    <div class="form-group">
				<label for="mobile" class="col-sm-2 control-label">手机号码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mobile" name="mobile"  placeholder="手机号码"  value="${userBean.mobile}">
				</div>
			</div>
		    <div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email" name="email" placeholder="邮箱"  value="${userBean.email}">
				</div>
			</div>
			 <div class="form-group">
				<label for="address" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-2">
						<select  id="address_province_select" class="form-control"   onchange="provinceChange(this)" >
					           <option value="">省份</option>
					    </select>
				</div>
				<div class="col-sm-2">
						<select id="address_city_select" class="form-control"   onchange="cityChange(this)"  >
					           <option value="">城市</option>
					    </select>
				</div>
				<div class="col-sm-2">
						<select id="address_district_select" class="form-control"   onchange="districtChange(this)"  >
					           <option value="">区县</option>
					    </select>
				</div>
			</div>
		    <div class="form-group">
				<label for="address" class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="address" name="address" placeholder="地址"  value="${userBean.address}">
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">经纬度</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="axis" name="axis" placeholder="经纬度" >
				</div>
			    <div class="col-sm-2">
							<input type="button" class="btn btn-primary " data-toggle="modal" data-target="#modal_map" value="经纬度查询">
						   </input>
				</div>
			</div>
			<div>&nbsp;&nbsp;</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button"  class="btn btn-success"  onclick="enable()">修改</button>
					<button type="submit"  class="btn btn-success"  id="submit" >提交</button>
					<button type="button"  class="btn btn-success"   id="cancel"  onclick="cancelFunc()" >取消</button>
				</div>
			</div>
		</form>
  </div>
  
  <!-- Modal -->
<div class="modal fade" id="modal_map" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">地图选择</h4>
      </div>
      <div class="modal-body">
          <div class="container-fluid" >
                 <iframe  id="modal_map_body" style="border:0px;width:100%;height:60%"></iframe>
           </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="save_axis()" data-dismiss="modal">保存</button>
      </div>
    </div>
  </div>
</div>
  
  
  
  <script>
		$('#modal_map').on('show.bs.modal', function (e) {
			 $("#modal_map_body").attr("src","${path}/basic/showMap");
		})
		
		$('#modal_map').on('shown.bs.modal', function (e) {
			 var value=$("#address").val();
			 $("#modal_map_body").contents().find("#map_address").val(value);
		})
		
		function save_axis(){
		    var value=	 $("#modal_map_body").contents().find("#map_degree").val();
		      $("#axis").val(value);
		}
</script>
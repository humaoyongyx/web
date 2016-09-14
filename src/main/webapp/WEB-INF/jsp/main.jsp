<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title>Main page</title>
</head>
<body>


<ul class="nav nav-tabs" role="tablist" id="_main_tabs" >
  <li role="presentation" class="active" id="_main_tabs_li_home"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_home" role="tab" data-toggle="tab"  id="_main_tabs_a_home">Home</a></li>
 <!--  <li role="presentation" id="_main_tabs_li_profile"><a onclick="_main_tabs_saveStatus(this)"  href="#_main_tabs_content_profile" role="tab" data-toggle="tab" id="_main_tabs_a_profile">Profile  <button type="button" class="close" onclick="_main_tabs_close('profile')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a> </li>
 --></ul>

<div class="tab-content" id="_main_content" style="width:100%;height:100%">
  <div role="tabpanel" class="tab-pane active" id="_main_tabs_content_home" >
         <iframe src="${path}/userInfo/page2" style="width:100%;height:100%"></iframe>
  </div>
  
<%--     <div role="tabpanel" class="tab-pane " id="_main_tabs_content_profile">
         <iframe src="${path}/userInfo/page2"></iframe>
  </div> --%>
</div>
  
<div>
      <input type="button" value="add" onclick="_main_tabs_addTab( <c:out value='${obj}'/>)">
</div>

<script>
_main_tabs_lastActived_id=new Array();
_main_tabs_lastActived_id.push("home");

function _main_tabs_close(id,event) {
	  var aId="#_main_tabs_a_"+id;
	  var liId="#_main_tabs_li_"+id;
	  var cId="#_main_tabs_content_"+id;
	  if($(liId).hasClass("active")){
		   var pAId="#_main_tabs_a_"+_main_tabs_lastActived_id[1];
  	        var pLiId="#_main_tabs_li_"+_main_tabs_lastActived_id[1];
		  	  $(pAId).tab('show');
		  	  $(liId).remove();
		  	  $(cId).remove();
	  }else{
	 	  $(liId).remove();
  	      $(cId).remove();
	  }
	  $(_main_tabs_lastActived_id).each(function(i,v){
			if(_main_tabs_lastActived_id[i]==id){
				_main_tabs_lastActived_id.splice(i,1);
			}
		});
	  stopEventBubble(event);
}

function stopEventBubble(event){
    var e=event || window.event;

    if (e && e.stopPropagation){
        e.stopPropagation();    
    }
    else{
        e.cancelBubble=true;
    }
}

var  id=0;
function _main_tabs_addTab(obj){
	  	  $("#_main_content").append(
			'<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">Settings'+id+'</div>'
	      );
	     $("#_main_tabs").append(
			  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">Settings'+id+'<button type="button" class="close" onclick="_main_tabs_close('+id+')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
 	  );
		_main_tabs_lastActived_id.unshift(id+"");
     id++;
     $('#_main_tabs a:last').tab('show');
}

function _main_tabs_saveStatus(obj){
	var id=$(obj).attr("id").replace("_main_tabs_a_","");
	$(_main_tabs_lastActived_id).each(function(i,v){
		if(_main_tabs_lastActived_id[i]==id){
			_main_tabs_lastActived_id.splice(i,1);
		}
	});
	_main_tabs_lastActived_id.unshift(id);
}

</script>



</body>
</html>
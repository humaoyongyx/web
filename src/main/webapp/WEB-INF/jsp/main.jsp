<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title>Web控制台 </title>
<style type="text/css">
#_main_layout_center  .nav-tabs>li>a {
     padding: 10px 5px 10px 15px;
 }
</style>
</head>
<body>
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
	  _stopEventBubble(event);
}

function _stopEventBubble(event){
    var e=event || window.event;

    if (e && e.stopPropagation){
        e.stopPropagation();    
    }
    else{
        e.cancelBubble=true;
    }
}



function _main_tabs_addTab(id,url,name){
	  $("#_main_content").append(
		   '<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">  <iframe id="_main_tabs_iframe_'+id+'" src="${path}'+url+'" style="border:0px;width:100%;" onload="_resizeIframeHeight(this)"></iframe></div>'
      );
	  $("#_main_tabs").append(
				  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">'+name+'<button type="button" class="close" style="margin-top:-10px;margin-left:5px" onclick="_main_tabs_close(\''+id+'\',event)"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
	  );
	_main_tabs_lastActived_id.unshift(id+"");
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


function _main_tabs_showTabs(id,url,name){
	var aId="#_main_tabs_a_"+id;
	if($.inArray(id,_main_tabs_lastActived_id)!=-1){
	    $(aId).tab('show');
	    $(_main_tabs_lastActived_id).each(function(i,v){
			if(_main_tabs_lastActived_id[i]==id){
				_main_tabs_lastActived_id.splice(i,1);
			}
		});
	    _main_tabs_lastActived_id.unshift(id+"");
	}else{
		_main_tabs_addTab(id,url,name);
	}
}


</script>

<script>
_main_page_hideMenu=true;
  function _main_page_menuToggle(){
	  if (_main_page_hideMenu) {
          $('#left_layout').hide();
          $('#right_layout').removeClass("col-xs-10 col-sm-10 col-md-10 col-lg-10");
          $('#right_layout').addClass("col-xs-12 col-sm-12 col-md-12 col-lg-12");
      } else {
          $('#left_layout').show();
          $('#right_layout').removeClass("col-xs-12 col-sm-12 col-md-12 col-lg-12");
          $('#right_layout').addClass("col-xs-10 col-sm-10 col-md-10 col-lg-10");
      }
	  _main_page_hideMenu=!_main_page_hideMenu;
  }


</script>
	
	
	
<div class="container-fluid clear_padding_margin main_page" id="main_page">
  <!-- left-menu-->
  <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 clear_padding_margin left_layout" id="left_layout">

    <div class="header_logo">

      <!--<img src="app/image/logo.jpg" class="block" />-->
    </div>

    <div class="menu">
      <ul class="nav">
           <!-- menu -->
             

         <div id="_menu_treeView"></div>
      
      <!-- end  menu -->     
      </ul>
    </div>

    <!-- end left-menu-->
  </div>


  <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10 clear_padding_margin right_layout" id="right_layout">

    
<nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0px;">

  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
       <ul class="nav navbar-nav ">
      <span onclick="_main_page_menuToggle()" class="glyphicon  glyphicon-align-justify" style="font-size:1.2em;color:green;padding:15px"></span>
      </ul>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li ><a href="#" >Messages <span class="badge">3</span></a></li>
        <li><a href="#"><img src="${user.photo } " class="img-circle" alt="Responsive image" style="width:20px;height:20px"></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.name } <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="${path}/logout">退出</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->

</nav>

    <div class="container-fluid" >
      <div class="row">
            <!-- content -->
            
          <div id="_main_layout_center">
			<ul class="nav nav-tabs" role="tablist" id="_main_tabs" >
			  <li role="presentation" class="active" id="_main_tabs_li_home"><a style="padding-right:15px" onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_home" role="tab" data-toggle="tab"  id="_main_tabs_a_home">主页</a></li>
			</div>
			<div class="tab-content" id="_main_content" >
			
			  <div role="tabpanel" class="tab-pane active " id="_main_tabs_content_home" >
			  
			        <iframe src="${path}/userInfo/page2"  id="_main_tabs_iframe_home"  style="border:0px;width:100%" onload="_resizeIframeHeight(this)"></iframe>
			  </div> 
			  
			</div>
            
            <!--  end content -->
      </div>
    </div>
    
  </div>
</div>
	
	
<script>
	
$(document).ready(function() {
	  _init();

});

function _init(){
	  _initTreeviewData();
}
	
function _initTreeviewData(){
	$.getJSON("${path}/basic/menus", function(json){
		$('#_menu_treeView').treeview(json);
		$('#_menu_treeView').on('nodeSelected', function(event, data) {
			var id=data.nameId;
			var url=data.url;
			var name=data.text;
			_main_tabs_showTabs(id,url,name);
			});
		});
	
}

function _resizeIframeHeight(obj){
	$(obj).height($(document.body).height()-100);
}

$(window).resize(function(){
	 var iframeId="#_main_tabs_iframe_"+_main_tabs_lastActived_id[0];
	 $(iframeId).height($(document.body).height()-100);
	});
	
</script>
	

</body>
</html>
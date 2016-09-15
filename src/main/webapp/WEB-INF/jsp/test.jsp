<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="header.jsp"%>

<title>test page </title>
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

/* var  id=0;
function _main_tabs_addTab(obj){
	  	  $("#_main_content").append(
			'<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">Settings'+id+'</div>'
	      );
	     $("#_main_tabs").append(
			  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">Settings'+id+'<button type="button" class="close" style="margin-top:-10px;margin-left:5px" onclick="_main_tabs_close('+id+')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
 	  );
		_main_tabs_lastActived_id.unshift(id+"");
     id++;
     $('#_main_tabs a:last').tab('show');
} */

_main_tabs_id=0;
function _main_tabs_addTab(id){
	  $("#_main_content").append(
		'<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">Settings'+id+'</div>'
    );
   $("#_main_tabs").append(
		  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">Settings'+id+'<button type="button" class="close" style="margin-top:-10px;margin-left:5px" onclick="_main_tabs_close(\''+id+'\')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
 );
	_main_tabs_lastActived_id.unshift(id+"");
	_main_tabs_id++;
$('#_main_tabs a:last').tab('show');
}

function _main_tabs_addTab(id,url){
	  $("#_main_content").append(
		'<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">  <iframe src="${path}'+url+'" style="border:0px;width:100%;height:100%"></iframe></div>'
  );
 $("#_main_tabs").append(
		  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">Settings'+id+'<button type="button" class="close" style="margin-top:-10px;margin-left:5px" onclick="_main_tabs_close(\''+id+'\')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
);
	_main_tabs_lastActived_id.unshift(id+"");
$('#_main_tabs a:last').tab('show');
}


function _main_tabs_addTab(id,url,name){
	  $("#_main_content").append(
		   '<div role="tabpanel" class="tab-pane " id="_main_tabs_content_'+id+'">  <iframe src="${path}'+url+'" style="border:0px;width:100%;height:100%"></iframe></div>'
      );
	  $("#_main_tabs").append(
				  '<li role="presentation" id="_main_tabs_li_'+id+'"><a onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_'+id+'" role="tab" data-toggle="tab" id="_main_tabs_a_'+id+'">'+name+'<button type="button" class="close" style="margin-top:-10px;margin-left:5px" onclick="_main_tabs_close(\''+id+'\')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a></li>'
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
function _main_tabs_showTabs(id){
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
		_main_tabs_addTab(id);
	}
}

function _main_tabs_showTabs(id,url){
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
		_main_tabs_addTab(id,url);
	}
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
             
     <!--    <li class="cursor_pointer"><a onclick="_main_tabs_showTabs('home')" class="clear-border_radius"><span class="glyphicon glyphicon-home" ></span> Home</a></li>

        <li>
          <a href="#collapse_Profile" data-toggle="collapse" class="clear-border_radius"><span class="glyphicon glyphicon-user"></span> Profile<span class="glyphicon glyphicon-folder-close pull-right" ></span></a>
          <ul class="collapse collapseable nav" id="collapse_Profile">
            <li><a onclick="_main_tabs_showTabs('test')"><span class="glyphicon glyphicon-ok"></span> Test Page</a></li>
            <li><a onclick="_main_tabs_showTabs('chart')"><span class="glyphicon glyphicon-ok"></span> View Echart</a></li>
          </ul>
        </li>

         <li>
          <a href="#collapse_settings" data-toggle="collapse" class="clear-border_radius"><span class="glyphicon glyphicon-user"></span> Setting<span class="glyphicon glyphicon-folder-close pull-right" ></span></a>
          <ul class="collapse collapseable nav" id="collapse_settings">
            <li><a onclick="_main_tabs_showTabs('setting')"><span class="glyphicon glyphicon-ok"></span> setting</a></li>
            <li><a onclick="_main_tabs_showTabs('setting2')"><span class="glyphicon glyphicon-ok"></span> setting2</a></li>
          </ul>
        </li> -->


   <div id="tree"></div>
      <!-- end  menu -->     
      </ul>
    </div>

    <!-- end left-menu-->
  </div>


  <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10 clear_padding_margin right_layout" id="right_layout">

    <!--toggle menu -->
    <div id="toggle_menu " class="toggle_menu">
            <div class="pull-left">
               <span onclick="_main_page_menuToggle()" class="glyphicon  glyphicon-align-justify" style="font-size:1.5em;color:white;padding:5px"></span>
            </div>
              <div class="pull-right " style="margin-right:20px">
                        <div class="dropdown">
	                        <span class="dropdown-toggle" data-toggle="dropdown" style="font-size:1.2em;color:white;padding:5px" >My Account <b class="caret"></b></span>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a [routerLink]="['/']">Logout</a></li>
	                        </ul>
	                      </div>
            </div>

    </div>
    <div style="height:100%;margin-top:-50px;">
       <div id="rigth_main" class="rigth_main">
          <!-- content -->
          
            <div id="_main_layout_center">
			<ul class="nav nav-tabs" role="tablist" id="_main_tabs" >
			  <li role="presentation" class="active" id="_main_tabs_li_home"><a style="padding-right:15px" onclick="_main_tabs_saveStatus(this)" href="#_main_tabs_content_home" role="tab" data-toggle="tab"  id="_main_tabs_a_home">Home</a></li>
			</div>
			<div class="tab-content" id="_main_content" style="width:100%;height:100%">
			  <div role="tabpanel" class="tab-pane active" id="_main_tabs_content_home" >
			         <iframe src="${path}/userInfo/page2" style="border:0px;width:100%;height:100%"></iframe>
			  </div>
			</div>
			  
       <!--  end content -->
       </div>
    </div>
  </div>
</div>
	
	
		<script>
		
	$(document).ready(function() {
		var tree = [
		            {
		              text: "Parent 1",
		              selectable:false,
		              nodes: [
		                {
		                  text: "Child 1",
		                  selectable:false,
		                  nodes: [
		                    {
		                      text: "Grandchild 1",
		                      icon: 'glyphicon glyphicon-earphone',
		                      data:{id:"id",url:"url"}
		                    },
		                    {
		                      text: "Grandchild 2"
		                    }
		                  ]
		                }
		              ]
		            },
		            {
		              text: "Parent 2",
		            	   tags: ['<span class="glyphicon glyphicon-folder-close" ></span>'],
		            },
		            {
		              text: "Parent 3"
		            },
		            {
		              text: "Parent 4"
		            },
		            {
		              text: "Parent 5",
		            	  icon: 'glyphicon glyphicon-earphone',
		            }
		          ];
		getTreeviewData();
		
		
	});
	
function getTreeviewData(){
	$.getJSON("${path}/menu/getMenus", function(json){
		$('#tree').treeview(json);
		$('#tree').on('nodeSelected', function(event, data) {
			var id=data.nameId;
			var url=data.url;
			var name=data.text;
			_main_tabs_showTabs(id,url,name);
			});
		});
	
}

	</script>
	

</body>
</html>
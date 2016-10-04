<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>

   <div class="container-fluid">	
        <div>&nbsp;&nbsp;</div>
       <!-- begin -->
          	<form class="form-horizontal" role="form"  id="form_sys_scheduler" >
                    <c:forEach items="${schedulerList }" var="item">
                            <div class="form-group">
                             <div class="col-sm-4" >
                                     <c:choose>
                                          <c:when test="${item.runStatus==0 }"><button class="btn btn-danger btn-block" disabled="disabled">${item.name }</button></c:when>
                                          <c:when test="${item.runStatus==1 }"> <button class="btn btn-success btn-block" disabled="disabled">${item.name }</button></c:when>
                                     </c:choose>
                             </div>
                              <div class="col-sm-2 text-center" >
                                    <button type="button" class="btn btn-info" disabled="disabled">Cron : ${item.cron }</button>
                             </div>
                             <div class="col-sm-4" >
                               <c:if test="${item.runStatus==1 }">
                                            <button type="button" class="btn btn-default"  disabled="disabled">启动</button>
                                            <button type="button" class="btn btn-danger"  onclick="stop('${item.id}')" >停止</button>
                                            <button type="button" class="btn btn-primary"  onclick="update('${item.id}')" >刷新</button>
                               </c:if>
                               <c:if test="${item.runStatus==0 }">
                                             <button type="button" class="btn btn-success"  onclick="start('${item.id}')" >启动</button>
                                            <button type="button" class="btn btn-default"  disabled="disabled" >停止</button>
                                            <button type="button" class="btn btn-default"  disabled="disabled"  >刷新</button>
                               </c:if>
                                
                            </div>
                           </div>
                    </c:forEach>
            </form>
       
        <!-- end -->
        
 </div>
 
 <script>
 
 
		 function update(id){
		 	$.post("${path}/sys/updateSchedulerRefresh",{id:id},function(result){
		 		  if(result.status ==1){
		 			 refresh_scheduler();
		            	     swal("", "刷新成功！","success");
		             }else{
		            	   swal("刷新失败！",result.message,"error");
		           } 
		 		
		 	},"json");
		 }
 
 
        function start(id){
        	$.post("${path}/sys/addSchedulerStart",{id:id},function(result){
        		  if(result.status ==1){
        			     refresh_scheduler();
	               	     swal("", "启动成功！","success");
	                }else{
	               	   swal("启动失败！",result.message,"error");
	              } 
        		
        	},"json");
        }
        
        function stop(id){
        	$.post("${path}/sys/addSchedulerStop",{id:id},function(result){
      		  if(result.status ==1){
      			        refresh_scheduler();
	               	     swal("", "停止成功！","success");
	                }else{
	               	   swal("停止失败！",result.message,"error");
	              } 
      		
      	},"json");
        }
        
        function refresh_scheduler(){
        	$(sys_schedulerPage).load("${path}/sys/showScheduler",{status:1});
        }
 </script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		    <c:forEach items="${roleResourcePage}" var="menuParent">
                        <label class="col-sm-2 control-label"><b> ${menuMap.get(menuParent.key).text} </b></label>
                        <div class="col-sm-4" style="padding-left:0px">
		                 <c:forEach items="${menuParent.value}" var="menu" varStatus="status" >  
		                       <c:set var="count" value="0"></c:set>
		                        <label class="checkbox-inline">  <b>${menuMap.get(menu.key).text}</b></label>
		                        <c:forEach items="${menu.value}" var="roleResource" >
		                        <c:if test="${roleResource.rsId != null}" >
		                                     <c:set var="count" value="${count+1}"></c:set>
		                        </c:if>
		                   
				                        <label class="checkbox-inline">
		                                        <input type="checkbox"  class="_rr_checkbox_${status.index}" name="resourceIds" <c:if test="${roleResource.rsId != null}" >checked="checked"</c:if> value="${roleResource.resourceId }">${roleResource.resourceName}
		                                 </label>
		                       </c:forEach>
		                         <label class="checkbox-inline">
		                                        <input type="checkbox"  value="_rr_checkbox_${status.index}"  <c:if test="${count == 4}" >checked="checked"</c:if> onclick="selectAll(this)">全选
		                         </label>
		                       <br/>
		              </c:forEach>
		             </div>
		  </c:forEach>		

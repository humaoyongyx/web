<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <form class="form-horizontal" role="form" >
		         <h1>sdfds</h1>
		    <c:forEach items="${roleResourcePage}" var="item">
		           map键名：${item.key}<Br>
		             <c:forEach items="${item.value}" var="list" >  
		                  ${list.menuName} ,    ${list.resourceName}    show<input type="checkbox"  name="show"  <c:if test="list.rsId!=null"></c:if>checked="checked"> <Br>
		              </c:forEach>
		  </c:forEach>		
	  
</form>
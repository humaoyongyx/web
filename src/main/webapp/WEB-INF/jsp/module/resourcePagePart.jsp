<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <form class="form-horizontal" role="form" >
		  
		    <c:forEach items="${resourceBeans}" var="item">
		      <div class="form-group">
				<label for="pid" class="col-sm-2 control-label">${item.name}</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="pid" name="pid"  value="${item.action}" readonly="readonly">
				</div>
			</div>
		              
		  </c:forEach>		
	  
</form>
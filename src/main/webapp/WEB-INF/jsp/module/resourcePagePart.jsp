<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
		    <c:forEach items="${resourceBeans}" var="item" varStatus="status" >
					      <input type="hidden"  name="resource[${status.index}].id" value="${item.id}" />
					      <div class="form-group">
								<label for="pid" class="col-sm-2 control-label">${item.name}</label>
								<div class="col-sm-4">
									<input type="text" class="form-control"  name="resource[${status.index}].url"  value="${item.url}" readonly="readonly">
								</div>
						   </div>
		  </c:forEach>
	      <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				     <input type="button" class="btn btn-success" value="修改" onclick="modify_resource()" />&nbsp;
				     <input type="button" class="btn btn-success" value="提交" onclick="submit_resource()" id="resourcePart_submit" />&nbsp;
					 <input type="button" class="btn btn-success" value="取消" onclick="cancel_resource()" id="resourcePart_cancel" />&nbsp;
				</div>
		  </div>		
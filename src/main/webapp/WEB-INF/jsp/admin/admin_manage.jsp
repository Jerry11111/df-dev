<%@ page language= "java" contentType = "text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="pagerForm" method="get" action="${ctx}/admin/listAllAdmins?navTabId=${navTabId}&abc=1">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.account}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/listAllAdmins?navTabId=${navTabId}" method="get">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					登陆账号<input type="text" name="account" value="${account}"/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${currPerm['AUTH_ADD_ADMIN'] == 'A' || loginId == 0}">
				<li><a class="add" href="${ctx}/admin/addAdmin_html?navTabId=${navTabId}" target="navTab"><span>添加</span></a></li>
			</c:if>
			<c:if test="${currPerm['AUTH_MODIFY_ADMIN'] == 'A' || loginId == 0}">
			<li><a class="edit" href="${ctx}/admin/editAdmin_html?id={sid_user}&navTabId=${navTabId}" target="navTab"><span>修改</span></a></li>
			</c:if>
			<c:if test="${currPerm['AUTH_REMOVE_ADMIN'] == 'A' || loginId == 0}">
			<li><a class="delete" href="${ctx}/admin/removeAdmin?id={sid_user}&navTabId=${navTabId}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>登陆账号</th>
				<th>真实姓名</th>
				<th>创建者</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="admin" items="${admins}">
				<tr target="sid_user" rel="${admin.id}">
					<td>${admin.id}</td>
					<td>${admin.account}</td>
					<td>${admin.name}</td>
					<td>${admin.creator.name}</td>
					<td>
					<c:choose>
    				<c:when test="${admin.status == 0}">
    					启用
    				</c:when>
				    <c:otherwise>
				    	禁用
				    </c:otherwise>
					</c:choose>
					</td>
					<td>${admin.createTimestamp}</td>
					<td>${admin.modifyTimestamp}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${total}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${total}" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>

<%@ page language= "java" contentType = "text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageContent">
	<form method="post" action="${ctx}/admin/modifyAdmin?navTabId=${navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input name="id" type="hidden" value="${admin.id}"/>
			<p>
				<label>登陆账号：</label>
				<input name="acc" type="text" size="30"  readonly="true" alt="请输入登陆账号" value="${admin.account}"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input name="name" class="required" type="text" size="30"   alt="请输入真实姓名" value="${admin.name}"/>
			</p>
			<p>
				<label>新密码：</label>
				<input name="auth" type="password" size="30"  alt="请输入密码" id="auth" />
			</p>
			<p>
				<label>重复密码：</label>
				<input name="rpassword" type="password" size="30"  alt="请输入再次输入密码" equalid="#auth" />
			</p>
			<p>
				<label>状态：</label>
				<select name="status" class="required combox" >
					<option value="0" <c:if test="${admin.status == 0}">selected</c:if>>启用</option>
					<option value="1" <c:if test="${admin.status == 1}">selected</c:if>>禁用</option>
				</select>
			</p>
			<p>
				<label></label>
			</p>
			<p>
				<label>权限：</label>
				<input name="perm" type="hidden" id="perm" size=30/>
			</p>
			<ul class="tree treeFolder treeCheck expand" oncheck="kkk" id="tree" disabled="disabled">
			</ul>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
$(function(){
	var allPerms = eval('${allPerms}');
	var currPerm = eval('${perms}');
	var perms = $('#tree input[name="perm_holder"]');
	var pp = "";
	var tt = $('#tree');
	for(var i = 0; i < allPerms.length; i++ ){
		tt.append(buildPermTree(allPerms[i]));
	}
	if(pp.charAt(pp.length - 1) == ','){
		pp = pp.substring(0, pp.length - 1);
	}
	$('#perm').val(pp);
	
	function buildPermTree(perm){
		if(perm.child.length == 0){
			var a = $('<a/>').attr('tname', 'perm_holder').attr('tvalue', perm.id).html(perm.name);
			var p = $('<li/>').append(a);
			if('${admin.id}' == 0){
				a.attr('checked', 'true');
				return p;
			}
			if($.inArray(perm.id, currPerm) >= 0){
				a.attr('checked', 'true');
				pp += perm.id + '=A,';
			}
			return p;
		}
		var cperms = perm.child;
		var u = $('<ul/>');
		var t = $('<li/>').append($('<a/>').html(perm.name)).append(u);
		for(var i = 0; i < cperms.length; i++ ){
			u.append(buildPermTree(cperms[i]));
		}
		return t;
	}
});
function kkk(){
	var perms = $('#tree input[name="perm_holder"]');
	var pp = "";
	$(perms).each(function(){
		var v = $(this).attr('value');
		var checked = $(this).attr('checked');
		if(checked){
			pp += v + '=A,';
		}
	});
	if(pp.charAt(pp.length - 1) == ','){
		pp = pp.substring(0, pp.length - 1);
	}
	$('#perm').val(pp);
}
</script>

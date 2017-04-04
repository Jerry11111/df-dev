<%@ page language= "java" contentType = "text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageContent">
	<form method="post" action="${ctx}/admin/addAdmin?navTabId=${navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>登陆账号：</label>
				<input name="acc" class="required" type="text" size="30"  alt="请输入登陆账号"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input name="name" class="required" type="text" size="30"  alt="请输入真实姓名"/>
			</p>
			<p>
				<label>新密码：</label>
				<input name="auth" class="required" type="password" size="30"  alt="请输入密码"/>
			</p>
			<p>
				<label>重复密码：</label>
				<input name="rpassword" class="required" type="password" size="30"  alt="请输入再次输入密码"/>
			</p>
			<p>
				<label>状态：</label>
				<select name="status" class="required combox">
					<option value="0" selected>启用</option>
					<option value="1">禁用</option>
				</select>
			</p>
			<p>
				<label></label>
				
			</p>
			<p>
				<label>权限：</label>
				<input name="perm" type="hidden" id="perm" size=30/>
			</p>
			<ul class="tree treeFolder treeCheck expand" oncheck="kkk" id="tree">
			</ul>
			<!--  
			<ul class="tree treeFolder treeCheck expand" oncheck="kkk">
	<li><a >框架面板</a>
		<ul>
			<li><a tname="name" tvalue="value1" checked="true">我的主页</a></li>
			<li><a tname="name" tvalue="value2">页面一</a></li>
			<li><a tname="name" tvalue="value3">替换页面一</a></li>
			<li><a tname="name" tvalue="value4">页面二</a></li>
			<li><a tname="name" tvalue="value5">页面三</a></li>
		</ul>
	</li>

	<li><a tname="name" tvalue="test1">Test 1</a>
		<ul>
			<li><a tname="name" tvalue="test1.1">Test 1.1</a>
				<ul>
					<li><a tname="name" tvalue="test1.1.1" checked="true">Test 1.1.1</a></li>
					<li><a tname="name" tvalue="test1.1.2" checked="false">Test 1.1.2</a></li>
				</ul>
			</li>
			<li><a tname="name" tvalue="test1.2" checked="true">Test 1.2</a></li>
		</ul>
	</li>
	<li><a tname="name" tvalue="test2" checked="true">Test 2</a></li>
</ul>-->
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
	var tt = $('#tree');
	for(var i = 0; i < allPerms.length; i++ ){
		tt.append(buildPermTree(allPerms[i]));
	}
	//alert(tt.html());
	//alert(allPerms[0].id);
	
	function buildPermTree(perm){
		if(perm.child.length == 0){
			var p = $('<li/>').append($('<a/>').attr('tname', 'perm_holder').attr('tvalue', perm.id).html(perm.name));
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
	
	function buildPermCheckRes(){
		var perms = $('#tree checkbox[name="perm_holder"]');
		alert(perms);
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

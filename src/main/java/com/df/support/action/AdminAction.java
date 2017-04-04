package com.df.support.action;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.support.dao.AdminDao;
import com.df.support.param.AdminParam;
import com.df.support.pojo.Admin;
import com.df.support.utils.Auth;
import com.df.support.utils.Constants;
import com.df.support.utils.DwzRes;
import com.df.support.utils.MD5;
import com.df.support.utils.Page;
import com.df.support.utils.PermScan;
import com.df.support.utils.Permission;
import com.df.support.utils.Res;

@Controller
@RequestMapping("/admin")
@Auth(name = "管理员管理", value = "AUTH_ADMIN")
public class AdminAction {
	@Resource(name="admin-dao")
	private AdminDao adminDao;
	
	
	@RequestMapping("index_html")
	@Auth(name = "管理员页面", value = "AUTH_ADMIN_PAGE")
	public String index_html(HttpSession sess){
		List<Permission> allPerms = PermScan.getAllPermissions();
		JSONArray ja = JSONArray.fromObject(allPerms);
		sess.setAttribute("allPerms", ja.toString());
		return "index";
	}
	
	@RequestMapping("login_html")
	public String login_html(HttpSession sess){
		return "login";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public String login (HttpServletRequest req,
			HttpSession sess, 
			@RequestParam("acc") String account, 
			@RequestParam("auth") String auth) {
		Res res = Res.newInstance();
		String msg = "";
		int r = 0;
		Admin admin = adminDao.getAdminByAccount (account);
		if (admin == null) {
			r = 1;
			msg = "用户不存在";
		} else if (admin.status == 1) {
			r = 1;
			msg = "用户被禁用";
		}else if (!admin.auth.equalsIgnoreCase(auth)) {
			r = 1;
			msg = "认证失败";
		} else {
			sess.setAttribute (Constants.LOGIN_ID, (Integer)admin.id);
			sess.setAttribute (Constants.ADMIN, admin);
			LinkedHashMap<String, String> perms = Admin.parsePermissions(admin.permission);
			sess.setAttribute (Constants.ADMIN_PERMISSION, perms);
			//sess.setMaxInactiveInterval (adminDao.getMaxLoginInactiveTime ());
		}
		res.r(r).msg(msg);
		return res.toJSON();
	}
	@RequestMapping("logout")
	@ResponseBody
	public String logout (HttpSession sess) {
		sess.invalidate ();
		return Res.newInstance().toJSON();
	}
	
	public static class PermissionDefinitionJSON {
		public String id;
		public String name;
		public List<PermissionDefinitionJSON> child;
	}
	
	

	@RequestMapping("getAllPermissionsJSON")
	@ResponseBody
	public String getAllPermissionsJSON () {
		Permission p = new Permission ();
		p.child = PermScan.getAllPermissions ();
//		Permission pagep = adminPageManager.getPagePermissions (adminPageManager.getAllAdminPages ());
//		if (pagep != null)
//			p.child.add (pagep);
		Res res = Res.newInstance();
		res.data(p);
		return res.toJSON();
	}
	@RequestMapping("listAllAdmins")
	//@Auth(name = "登陆", value = "AUTH_LOGIN")
	public String listAllAdmins (HttpSession sess, 
								Model model, 
								@RequestParam ("navTabId")String navTabId, 
								@RequestParam (value = "account", required = false)String account,
								@RequestParam (value = "numPerPage", required = false)Integer pageSize,
								@RequestParam (value = "pageNum", required = false)Integer currPage,
								@RequestParam (value = "page", required = false)Page page
								) {
		int start = 0;
		int limit = 20;
		if(currPage != null){
			start = (currPage - 1) * pageSize;
			limit = pageSize;
		}
		Res res = Res.newInstance();
		Integer userId = (Integer)sess.getAttribute("loginId");
		res.put("userId", userId);
		res.put("permissions", userId);
		//r.permissions = getAllPermissionsJSON ();
		AdminParam param = new AdminParam();
		param.account = account;
		param.start = start;
		param.limit = limit;
		List<Admin> admins = adminDao.getAllAdmins (param);
		
		for(Admin a : admins ){
			a.creator = adminDao.getAdminById(a.creatorId);
		}
		int total = adminDao.getRowNumber(param);
		model.addAttribute("admins", admins);
		model.addAttribute("account", account);
		model.addAttribute("navTabId", navTabId);
		model.addAttribute("total", total);
		model.addAttribute("currPage", currPage);
		model.addAttribute("total", total);
		return "admin/admin_manage";
	}
	@RequestMapping("changePassword")
	@ResponseBody
	public String changePassword (HttpSession sess, @RequestParam ("old_auth")String oldAuth, @RequestParam ("new_auth") String newAuth) {
		Res res = Res.newInstance();
		int userId = (Integer)sess.getAttribute("loginId");
		Admin self = adminDao.getAdminById (userId);
		if (!self.auth.equals (oldAuth)) {
			res.r(Res.ERROR).msg("BAD PASSWORD");
		} else {
			if (!self.auth.equals (newAuth)) {
				self.auth = newAuth;
				adminDao.updateAdmin (self);
			}
		}
		return res.toJSON();
	}
	
	@RequestMapping("addAdmin_html")
	public String addAdmin_html(@RequestParam ("navTabId")String navTabId, Model model){
		model.addAttribute("navTabId", navTabId);
		return "admin/add_admin";
	}
	@RequestMapping("editAdmin_html")
	public String editAdmin_html(@RequestParam ("id")Integer id, 
			@RequestParam ("navTabId")String navTabId,
			Model model){
		Admin admin = adminDao.getAdminById (id);
		model.addAttribute("admin", admin);
		LinkedHashMap<String, String> perms = Admin.parsePermissions(admin.permission);
		JSONArray ja = JSONArray.fromObject(perms.keySet());
		model.addAttribute ("perms", ja.toString());
		model.addAttribute("navTabId", navTabId);
		return "admin/edit_admin";
	}
	@RequestMapping("modifyPassword_html")
	public String modifyPassword_html(){
		return "modify_password";
	}
	@RequestMapping("modifyPassword")
	@ResponseBody
	public String modifyPassword (HttpSession sess, 
			@RequestParam ("auth")  String auth
			) {
		DwzRes res = DwzRes.newInstance();
		res.msg("OK");
		int userId = (Integer)sess.getAttribute("loginId");
		Admin admin = adminDao.getAdminById (userId);
		if (!auth.isEmpty ()){
			auth = MD5.encrypt(auth).toLowerCase();
			admin.auth = auth;
		}
		adminDao.updateAdmin (admin);
		return res.toJSON();
	}
	@RequestMapping("addAdmin")
	@ResponseBody
	@Auth(name = "添加管理员", value = "AUTH_ADD_ADMIN")
	public String addAdmin (HttpSession sess, 
			@RequestParam ("acc")String account, 
			@RequestParam ("auth") String auth, 
			@RequestParam ("name") String name, 
			@RequestParam ("perm") String perm,
			@RequestParam ("status") int status, 
			@RequestParam ("navTabId")String navTabId
			) {
		DwzRes res = DwzRes.newInstance();
		res.navTabId(navTabId).msg("OK");
		int userId = (Integer)sess.getAttribute("loginId");
		try {
			Admin self = adminDao.getAdminById (userId);
			self.permissions = Admin.parsePermissions(self.permission);
			Admin admin = new Admin ();
			admin.account = account;
			auth = MD5.encrypt(auth).toLowerCase();
			admin.auth = auth;
			admin.name = name;
			admin.status = status;
			admin.permission = perm;
			admin.creatorId = self.id;
			admin.createTimestamp = new Timestamp (System.currentTimeMillis ());
			admin.modifyTimestamp = admin.createTimestamp;
			admin.permissions = verifyPermission (self, perm);
			admin.encodePermissions ();
			adminDao.addAdmin (admin);
		} catch (Exception e) {
			res.r(DwzRes.ERROR).msg("ERROR " + e.toString ());
			e.printStackTrace ();
		}
		return res.toJSON();
	}
	
	public LinkedHashMap<String, String> verifyPermission (Admin self, String perm) {
		// verify permission
		LinkedHashMap<String, String> permissions = Admin.parsePermissions (perm);
		Set<String> removeList = new HashSet<String> ();
		for (Map.Entry<String, String> e: permissions.entrySet ()) {
			if (self.id == 0) {
//				if (!self.hasPermission (e.getKey (), "")) {
//					removeList.add (e.getKey ());
//				}
			} else {
//				if (!self.hasPermission (e.getKey (), Permission.PERM_GRANT)) {
//					removeList.add (e.getKey ());
//				}
			}
		}
//		for (String s: removeList) {
//			permissions.remove (s);
//		}
		return permissions;
	}
	@RequestMapping("modifyAdmin")
	@ResponseBody
	@Auth(name = "修改管理员", value = "AUTH_MODIFY_ADMIN")
	public String modifyAdmin (HttpSession sess, 
			@RequestParam ("id") int id, 
			@RequestParam ("auth")  String auth, 
			@RequestParam ("name")  String name,
			@RequestParam ("perm")  String perm,
			@RequestParam ("status")  int status, 
			@RequestParam ("navTabId")String navTabId
			) {
		DwzRes res = DwzRes.newInstance();
		res.navTabId(navTabId).msg("OK");
		int userId = (Integer)sess.getAttribute("loginId");
		if (userId == id) {
			res.r(DwzRes.ERROR).msg("ERROR: CAN NOT MODIFY SELF").callbackType(DwzRes.NOT_CLOSE);
			return res.toJSON();
		}
		if (0 == id) {
			res.r(DwzRes.ERROR).msg("ERROR: CAN NOT MODIFY ROOT").callbackType(DwzRes.NOT_CLOSE);
			return res.toJSON();
		}
		Admin self = adminDao.getAdminById (userId);
		Admin admin = adminDao.getAdminById (id);
		if (!auth.isEmpty ()){
			auth = MD5.encrypt(auth).toLowerCase();
			admin.auth = auth;
		}
		admin.name = name;
		admin.status = status;
		// verify permission
		admin.permissions = verifyPermission (self, perm);
		admin.encodePermissions ();
		admin.modifyTimestamp = new Timestamp(System.currentTimeMillis());
		adminDao.updateAdmin (admin);
		return res.toJSON();
	}
	
	@RequestMapping("removeAdmin")
	@ResponseBody
	@Auth(name = "删除管理员", value = "AUTH_REMOVE_ADMIN")
	public String removeAdmin (HttpSession sess, @RequestParam ("id") int id, @RequestParam ("navTabId")String navTabId){
		DwzRes res = DwzRes.newInstance();
		res.msg("OK").navTabId(navTabId).callbackType(DwzRes.NOT_CLOSE);
		int userId = (Integer)sess.getAttribute("loginId");
		if (userId == id) {
			res.r(DwzRes.ERROR).msg("ERROR: CAN NOT REMOVE SELF");
			return res.toJSON();
		}
		if (id == 0) {
			res.r(DwzRes.ERROR).msg("ERROR: CAN NOT REMOVE ROOT USER");
			return res.toJSON();
		}
		Admin self = adminDao.getAdminById (userId);
		if (self == null) {
			res.r(DwzRes.ERROR).msg("LOGOUT");
			return res.toJSON();
		}
		Admin admin = adminDao.getAdminById (id);
		boolean allowRemove = false;
		if (userId != 0) {
			while (admin != null) {
				if (admin.creatorId == 0)
					break;
				if (admin.creatorId == userId) {
					allowRemove = true;
					break;
				}
				admin = adminDao.getAdminById (admin.creatorId);
			}
			if (!allowRemove) {
				res.r(DwzRes.ERROR).msg("ERROR: YOU DO NOT HAVE THE PERMISSION TO REMOVE THE USER");
				return res.toJSON();
			}
		}
		adminDao.deleteAdmin (id);
		return res.toJSON();
	}
}

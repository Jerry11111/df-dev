package com.df.support.pojo;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class Admin {
	public int id;
	public String account;
	public String name;
	public String auth;
	public String permission;
	public int creatorId;
	public int status;
	public Timestamp createTimestamp;
	public Timestamp modifyTimestamp;
	public Admin creator;
	
	public Admin getCreator() {
		return creator;
	}

	public void setCreator(Admin creator) {
		this.creator = creator;
	}

	public static final int STATUS_DISABLED = 1;
	
	public Map<String,String> permissions;
	
	public boolean hasPermission (String name, String s) {
		String v = permissions.get (name);
		if (v == null)
			return false;
		if (s.isEmpty () || s == null)
			return true;
		for (char c: s.toCharArray ()) {
			if (v.indexOf (c) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasPermission (String name, char s) {
		return hasPermission (name, "" + s);
	}
	
	public void encodePermissions () {
		StringBuffer sb = null;
		if (permissions != null) {
			for (Map.Entry<String, String> e: permissions.entrySet ()) {
				if (e.getValue () == null || e.getValue ().isEmpty ())
					continue;
				if (sb == null)
					sb = new StringBuffer ();
				else
					sb.append (",");
				sb.append (e.getKey ());
				sb.append ("=");
				sb.append (e.getValue ());
			}
		}
		permission = sb == null ? null : sb.toString ();
	}
	
	public static LinkedHashMap<String, String> parsePermissions (String permission) {
		LinkedHashMap<String, String> permissions = new LinkedHashMap<String, String> ();
		if (permission != null && permission.length () > 0) {
			try {
				String[] perms = permission.split (",");
				for (String perm: perms) {
					perm = perm.trim ();
					String[] p = perm.split ("=");
					permissions.put (p[0].trim (), p[1].trim ());
				}
			} catch (Exception e) {
				e.printStackTrace ();
				return null;
			}
		}
		return permissions;
	}
	
	public void parsePermissions () {
		permissions = parsePermissions (permission);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getModifyTimestamp() {
		return modifyTimestamp;
	}

	public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
	}
	
	
}

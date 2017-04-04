package com.df.support.utils;

import java.util.ArrayList;
import java.util.List;

public class Permission {
	// PARENT1|显示名:PARENT2|显示名:NAME|显示名=[C|R|U|D]
	public static final char PERM_DENY = 'N';
	public static final char PERM_ACCESS = 'A';
	public static final char PERM_GRANT = 'G';
	
	
	public String name;
	public String id;
	
	public Permission () {
		
	}
	
	public Permission (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public List<Permission> child = new ArrayList<Permission>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Permission> getChild() {
		return child;
	}

	public void setChild(List<Permission> child) {
		this.child = child;
	}
	
}

package com.df.support.dao;

import java.util.List;

import com.df.support.param.AdminParam;
import com.df.support.pojo.Admin;

public interface AdminDao {
	public List<Admin> getAllAdmins(AdminParam param);
	
	public int getRowNumber (AdminParam param);

	public Admin getAdminById(int id);

	public Admin getAdminByAccount(String id);

	public void addAdmin(Admin admin);

	public void updateAdmin(Admin admin);

	public void deleteAdmin(int id);

}

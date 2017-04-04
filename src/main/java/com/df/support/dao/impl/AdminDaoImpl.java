package com.df.support.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.df.support.dao.AdminDao;
import com.df.support.mapper.IAdminMapper;
import com.df.support.param.AdminParam;
import com.df.support.pojo.Admin;

@Repository("admin-dao")
public class AdminDaoImpl implements AdminDao{
	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}


	@Override
	public List<Admin> getAllAdmins(AdminParam param) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		return adminMapper.getAllAdmins(param);
	}

	@Override
	public Admin getAdminById(int id) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		return adminMapper.getAdminById(id);
	}

	@Override
	public Admin getAdminByAccount(String id) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		return adminMapper.getAdminByAccount(id);
	}

	@Override
	public void addAdmin(Admin admin) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		adminMapper.addAdmin(admin);
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		adminMapper.updateAdmin(admin);
		
	}

	@Override
	public void deleteAdmin(int id) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		adminMapper.deleteAdmin(id);
		
	}

	@Override
	public int getRowNumber(AdminParam param) {
		IAdminMapper adminMapper = sqlSession.getMapper(IAdminMapper.class);
		return adminMapper.getRowNumber(param);
	}

}

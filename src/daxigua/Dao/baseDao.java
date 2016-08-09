package daxigua.Dao;

import java.util.List;

import daxigua.Bean.userBean;

public interface baseDao {

	public List<userBean> listAll() throws Exception;
	public void add(userBean user) throws Exception;
	public int getSize() throws Exception;
	public void remove(int id) throws Exception;
	public void modify(userBean user) throws Exception;
	public userBean findByname(String name) throws Exception;
}

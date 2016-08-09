package daxigua.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.Util.DBUtil;

public class userImpl implements baseDao{

	public List<userBean> listAll() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from daxigua order by id";
		List<userBean> user = new LinkedList<userBean>();
		Connection con = DBUtil.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			userBean myuser = new userBean();
			myuser.setId(rs.getInt("id"));
			myuser.setUid(rs.getString("uid"));
			myuser.setUgrade(rs.getInt("ugrade"));
			myuser.setUstatus(rs.getInt("ustatus"));
			myuser.setUpswd(rs.getString("upswd"));
			myuser.setUpath(rs.getString("uother"));
			user.add(myuser);
		}
		
		DBUtil.close(con);
		
		return user;
	}

	public void add(userBean user) throws Exception {
		
		String sql = "insert into daxigua(uid,upswd,ustatus,ugrade,uother) values (?,?,?,?,?)";
		Connection con = DBUtil.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, user.getUid());
		ps.setString(2, user.getUpswd());
		ps.setInt(3, user.getUstatus());
		ps.setInt(4, user.getUgrade());
		ps.setString(5, user.getUpath());
		
		int resp = ps.executeUpdate();
		if (resp == 0)
			System.out.println("update failed!!");
		
		DBUtil.close(con);
		
	}

	public void remove(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from daxigua where id = ?";
		Connection con = DBUtil.getCon();
		PreparedStatement ps = con.prepareStatement(sql);		
		ps.setInt(1, id);
		
		int resp = ps.executeUpdate();
		if (resp == 0)
			System.out.println("update failed!!");
		
		DBUtil.close(con);
	}

	public void modify(userBean user) throws Exception {

		String sql = "update daxigua set uid = ?,upswd = ?,ustatus = ?,ugrade = ?,uother =? where id = ?";
		Connection con = DBUtil.getCon();
		PreparedStatement ps = con.prepareStatement(sql);	
		
		ps.setString(1, user.getUid());
		ps.setString(2, user.getUpswd());
		ps.setInt(3, user.getUstatus());
		ps.setInt(4, user.getUgrade());
		ps.setString(5, user.getUpath());
		ps.setInt(6, user.getId());
		
		int resp = ps.executeUpdate();
		if (resp == 0)
			System.out.println("update failed!!");
		
		DBUtil.close(con);
		
	}

	@SuppressWarnings("null")
	public userBean findByname(String name) throws Exception {
		// TODO Auto-generated method stub
		userBean myuser = null;
		String sql = "select * from daxigua where uid = ?";
		Connection con = DBUtil.getCon();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setString(1, name);
//		System.out.println(sql);
		ResultSet rs = prep.executeQuery();
//		System.out.println(sql);
//		System.out.println(rs);
		if (rs.next()) {
			myuser = new userBean();
			myuser.setId(rs.getInt(1));
			myuser.setUid(rs.getString(2));
			myuser.setUpswd(rs.getString(3));
			myuser.setUstatus(rs.getInt(4));
			myuser.setUgrade(rs.getInt(5));
			myuser.setUpath(rs.getString(6));
		}
		
		DBUtil.close(con);
		
		if (null != myuser)
			return myuser;
		else
			return null;
	}

	public int getSize() throws Exception {
		int size = 0;
		String sql = "select count(*) as 'all' from daxigua";
		Connection con = DBUtil.getCon();
		PreparedStatement prep = con.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			size = Integer.parseInt(rs.getString(1));
		}
		return size;
	}

}

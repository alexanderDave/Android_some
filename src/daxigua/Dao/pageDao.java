package daxigua.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daxigua.Bean.userBean;
import daxigua.Util.DBUtil;
import daxigua.factoy.daoFactoy;

public class pageDao {
	
	private int page_size = 10;

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	
	

	public int getPage(){
		
		int totalPages = 0;
		baseDao dao = (baseDao) daoFactoy.getInstace("userDao");
		try {
			int size = dao.getSize();
			System.out.println(size);
			totalPages = size%page_size ==0 ?size/page_size :size/page_size+1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("totalPages"+totalPages);
		return totalPages;
		
	}
	
	
	public List<userBean> show(int currentPage){
		Connection con = DBUtil.getCon();
		userBean myuser;
		int beginRecord = (currentPage-1)*page_size;//开始记录
        //int endRecord = page_size;//从开始到结束的记录数
        String sql = "select * from daxigua limit "+beginRecord+","+page_size+"";
        List<userBean> personList = new ArrayList<userBean>();
        try {
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        //resultSet的get方法赋值得到一个Person对象
	            while(rs.next()){
	                myuser = new userBean();
	                myuser.setId(rs.getInt(1));
	    			myuser.setUid(rs.getString(2));
	    			myuser.setUpswd(rs.getString(3));
	    			myuser.setUstatus(rs.getInt(4));
	    			myuser.setUgrade(rs.getInt(5));
	    			myuser.setUpath(rs.getString(6));
	                personList.add(myuser);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally{
	           
					DBUtil.close(con);
				
	        }
        
        return personList;
				
	}
}

package daxigua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.factoy.daoFactoy;

//@WebServlet("/newuser.act")
public class addServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getContextPath();
		String uid =req.getParameter("uid");
		String upd =req.getParameter("upswd");
		//System.out.println(uid+upd);
				userBean bean = new userBean();
				bean.setUid(uid);
				bean.setUpswd(upd);
				bean.setUgrade(0);
				bean.setUstatus(1);
				bean.setUpath("");
				//System.out.println(bean.getUid()+bean.getUgrade()+bean.getId()+bean.getUpswd());
				baseDao dao = (baseDao) daoFactoy.getInstace("userDao");
				
				
				
					try {
						dao.add(bean);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					resp.sendRedirect(path+"/page.do?currentPage=1");
			
		
	}
}

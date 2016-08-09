package daxigua.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.factoy.daoFactoy;

//@WebServlet("*.act")
public class IDUServer extends HttpServlet{
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
		System.out.println("here");
		req.setCharacterEncoding("utf-8");
		String path = req.getContextPath();
		resp.setCharacterEncoding("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String uri = req.getRequestURI();
		String action = uri.substring((uri.lastIndexOf("/")),(uri.lastIndexOf(".")));
		
		switch (action) {
			case "newuser":
				userBean bean = new userBean();
				bean.setUid((String)req.getAttribute("uid"));
				bean.setUpswd((String)req.getAttribute("upswd"));
				baseDao dao = (baseDao) daoFactoy.getInstace("userDao");

					try {
						dao.add(bean);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					resp.sendRedirect(path+"/Sys_manage.jsp");
				break;
				
			case "del":
				int id = (int) req.getAttribute("id");
				baseDao dao1 = (baseDao) daoFactoy.getInstace("userDao");
					try {
						dao1.remove(id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resp.sendRedirect(path+"/Sys_manage.jsp");
				break;
				
			case "modify":
				int id2 = (int) req.getAttribute("id");
				baseDao dao2 = (baseDao) daoFactoy.getInstace("userDao");
					try {
						dao2.remove(id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resp.sendRedirect(path+"/Sys_manage.jsp");
				break;
	
			default:
				resp.sendRedirect(path+"/MyHtml.html");
				break;
		}
		
		
		
		
		
		
		
		
		
		
	}
}

package daxigua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daxigua.Dao.baseDao;
import daxigua.factoy.daoFactoy;

//@WebServlet("/del.act")
public class delServlet extends HttpServlet{
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
			String path = req.getContextPath();
				int id = Integer.parseInt(req.getParameter("id"));
				System.out.println(1);
				baseDao dao = (baseDao) daoFactoy.getInstace("userDao");
					try {
						dao.remove(id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(2);
					resp.sendRedirect(path+"/page.do?currentPage=1");

		
	}
}

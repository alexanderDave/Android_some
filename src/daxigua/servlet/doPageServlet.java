package daxigua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daxigua.Bean.userBean;
import daxigua.Dao.pageDao;


//@WebServlet("/page.do")
public class doPageServlet extends HttpServlet{
	
	//private static final long serialVersionUID = 1L;
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		pageDao page = new pageDao();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		List<userBean> userList = page.show(currentPage);
		
		request.setAttribute("userList", userList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", page.getPage());
		request.getRequestDispatcher("/Sys_manage.jsp").forward(request, resp);

        }
}

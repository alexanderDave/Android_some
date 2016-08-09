package daxigua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.Util.Comm;
import daxigua.factoy.daoFactoy;

//@WebServlet("/Syslogin.do")
public class SyslogServlet extends HttpServlet{
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
		String name = req.getParameter("userName");
		String pswd = req.getParameter("pwd");
		String code = req.getParameter("code").toLowerCase();
		
//		PrintWriter pw = resp.getWriter();
//		pw.write(pswd+code+name);
//		System.out.println(pswd+code+name);
		String content = req.getContextPath();
		//System.out.println(content);
		HttpSession session = req.getSession();
		String location = content+"/MyHtml.html";
		if (!(session.getAttribute("code").toString().toLowerCase().equals(code))) {
			resp.sendRedirect(location);
		}
		else {
			if (Comm.Syslogin(name, pswd)) {
				session.setAttribute("uname", name);
				//req.getRequestDispatcher("/Sys_manage.jsp").forward(req, resp);
				resp.sendRedirect(content+"/page.do?currentPage=1");
			}else{
				resp.sendRedirect(location);
			}
		}
	}
}

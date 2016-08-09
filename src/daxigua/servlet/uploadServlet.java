package daxigua.servlet;
/**
 * @author daiyuhan
 * @param
 * This is a simple way deal with picture.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import daxigua.Util.PicUtil;

public class uploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				this.doPost(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String basepath = request.getContextPath();
		String myname = (String) request.getSession().getAttribute("uname");
		System.out.println(myname);
//		int mynum = Integer.parseInt(request.getParameter("num"));
//		System.out.println(mynum);
		//int num = Integer.parseInt(request.getParameter(mynum.trim()));
		//String path = basepath+"/Upload/";
		//System.out.println(path);
		String savepath = this.getServletContext().getRealPath("/Upload");
		System.out.println(savepath);
		
//		File file = new File(savepath);
//		if (!file.exists() && !file.isDirectory())
//			file.mkdir();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");
		
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			return ;
		}
		/**
		 *  old function
		try {
//			FileItem fileitem = (FileItem) fileUpload.parseRequest(request);
			List<FileItem> list = fileUpload.parseRequest(request);
			FileItem fileitem = list.get(0);		
			String name = null;
			if (fileitem.isFormField()) {
				name = fileitem.getFieldName();
				String value = fileitem.getString("utf-8");
			}else{
				name = fileitem.getName();
				System.out.println(name);
			}
			
		name = name.substring(name.lastIndexOf("\\")+1);
		InputStream is = fileitem.getInputStream();
		//System.out.println(path+"/"+name);
		FileOutputStream fos = new FileOutputStream(savepath+"/"+name);
		
		byte[] bt = new byte[1024];
		int len = 0;
		while((len = is.read(bt))>0){
			fos.write(bt, 0, len);
		}
		fos.close();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 * 
		 */
		List<FileItem> list;
		try {
			//获取上传文件列表
			list = fileUpload.parseRequest(request);
			//获取图片流列表
			List<BufferedImage> ls= PicUtil.getPic(list);
			//拼接图片成完整一张
			BufferedImage is = PicUtil.makePic(ls);
			//将图片裁截成不同张
			List<String> ls2 = PicUtil.divPic(is, 10,savepath,myname);
			request.setAttribute("imgList", ls2);
			request.getRequestDispatcher("/beifen.jsp").forward(request, response);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}

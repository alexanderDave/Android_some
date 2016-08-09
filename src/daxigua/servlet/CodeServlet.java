package daxigua.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/create.do")
public class CodeServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "No-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		HttpSession session = req.getSession();
		int width = 80, height = 35;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		
		Graphics p = img.getGraphics();
		Random random = new Random();
		p.setColor(getRandcolor(200, 250));
		p.fillRect(1, 1, width-1, height-1);
		p.setColor(new Color(102,102,102));
		p.drawRect(0, 0, width-1, height-1);
		p.setFont(new Font("Arial Black", Font.PLAIN, 18));
		
		p.setColor(getRandcolor(160, 200));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);// 起点的x坐标
			int y = random.nextInt(height - 1);// 起点的y坐标
			int x1 = random.nextInt(6) + 1;// x轴偏移量
			int y1 = random.nextInt(12) + 1;// y轴偏移量
			p.drawLine(x, y, x + x1, y + y1);
		}
		
		// 随机生成线条，让图片看起来更加杂乱
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(12) + 1;
			int y1 = random.nextInt(6) + 1;
			p.drawLine(x, y, x - x1, y - y1);
		}	
		
		String srand = "";
		for (int i = 0; i < 4; i++) {
			String temp = getRandchar();
			srand += temp;
			p.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			p.drawString(temp, 18 * i + 7, 15 + random.nextInt(15));
		}
		session.setAttribute("code", srand.toString());
		p.dispose();
		
		try {
			ImageIO.write(img, "JPEG", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 String getRandchar() {
		 int rand = (int) Math.round(Math.random() * 2);// 将0～2的小数四舍五入生成整数
			long itmp = 0;
			char ctmp = ' ';
			// 根据rand的值来决定是生成一个大写字母、小写字母和数字
			switch (rand) {
			// 生成大写字母的情形
			case 1:
				itmp = Math.round(Math.random() * 25 + 65);
				ctmp = (char) itmp;
				return String.valueOf(ctmp);
				// 生成小写字母
			case 2:
				itmp = Math.round(Math.random() * 25 + 97);
				ctmp = (char) itmp;
				return String.valueOf(ctmp);
				// 生成数字
			default:
				itmp = Math.round(Math.random() * 9);
				return String.valueOf(itmp);
			}		 
	}

	Color getRandcolor(int fc,int bc){
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
		
	}
}

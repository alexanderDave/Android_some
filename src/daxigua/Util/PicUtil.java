package daxigua.Util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;

public class PicUtil {
	public static List<BufferedImage> getPic(List<FileItem> list){
		List<BufferedImage> imgList = new ArrayList<BufferedImage>();
		for (int i = 0; i < list.size(); i++) {
			FileItem item = list.get(i);
			try {
				if (null != item) {
					InputStream input = item.getInputStream();
					BufferedImage bi = ImageIO.read(input);
					imgList.add(bi);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return imgList;
	}
	
	public static BufferedImage makePic(List<BufferedImage> list){
		int width = 0,height = 0,
			_width=0,_height = 0,__height,
		pciNum = list.size();
		int[] temp_hight = new int[pciNum];
		List<int[]> imgRGB = new ArrayList<int[]>();
		int[] _imgRGB;
		for (int i = 0; i < list.size(); i++) {
			temp_hight[i] = _height = list.get(i).getHeight();
			if (i == 0) {
				width = list.get(i).getWidth();
			}
			height += _height;
			_imgRGB = new int[width * _height];
			_imgRGB = list.get(i).getRGB(0, 0, width, _height, _imgRGB, 0, width);
			imgRGB.add(_imgRGB);
		}
		_height = 0;
		
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		for (int i = 0; i < pciNum; i++) {
			__height = temp_hight[i];
			if (i != 0 ) {
				_height += __height;
			}
			//img.setRGB(0,_height, width, __height, imgRGB.get(i), 0, width);
			img.setRGB(0,_height, width, __height, imgRGB.get(i), 0, width);
		}
		return img;
	}
	
	public static List<String> divPic(BufferedImage bi,int rows,String path,String name){
		List<String> ls = new ArrayList<String>();
	        try {
	           if(rows<=0||rows>20) 
	        	   rows = 2; // 切片行数        
	           int srcHeight = bi.getHeight(); // 源图宽度
	           int srcWidth = bi.getWidth(); // 源图高度
	           if (srcWidth > 0 && srcHeight > 0) {
	                Image img;
	                ImageFilter cropFilter;
	                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
	                //int destWidth = srcWidth; // 每张切片的宽度
	                int destHeight = srcHeight; // 每张切片的高度
	               // 计算切片的宽度和高度
	               if (srcHeight % rows == 0) {
	                    destHeight = srcHeight / rows;
	                } else {
	                    destHeight = (int) Math.floor(srcWidth / rows) + 1;
	                }
	                // 循环建立切片
	               // 改进的想法:是否可用多线程加快切割速度
	               for (int i = 0; i < rows; i++) {
	                    
	                        // 四个参数分别为图像起点坐标和宽高
	                       // 即: CropImageFilter(int x,int y,int width,int height)
	                        cropFilter = new CropImageFilter(0,i * destHeight,
	                        		srcWidth, destHeight);
	                        img = Toolkit.getDefaultToolkit().createImage(
	                                new FilteredImageSource(image.getSource(),
	                                        cropFilter));
	                        BufferedImage tag = new BufferedImage(srcWidth,
	                                destHeight, BufferedImage.TYPE_INT_RGB);
	                        Graphics g = tag.getGraphics();
	                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
	                        g.dispose();
	                        File file = new File(path+"/"+name);
	                        if (!file.exists() && !file.isDirectory()) {
								file.mkdir();
							}
	                        
	                        String dir = path+"/"+name+"/cut_image_" + i + ".jpg";     
	                        File f = new File(dir);     
	                        ImageIO.write(tag, "JPEG", f);
	                        //String str = "file:///"+dir;
	                        String local = "/Upload/"+name+"/cut_image_" + i + ".jpg";
	                    ls.add(local);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    
		
		
		return ls;
	}
	
	
	
}

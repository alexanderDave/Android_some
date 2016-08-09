package daxigua.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.factoy.daoFactoy;



public class Comm {
	
	//public final static Integer PAGE_SISE= 10;
	/*
	public static String MD5(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	*/
	public static String MD5(String str) {
		byte[] bytes = str.getBytes();
		char[] hexDigist = {'0','1','2','3','4','5','6','7',
				'8','9','a','b','c','d','e','f'};
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] temp = md.digest();
			char[] string = new char[16*2];
			
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = temp[i];
				string[k++] = hexDigist[byte0 >>> 4 & 0xf];
				string[k++] = hexDigist[byte0 & 0xf];
				
				str = new String(string);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static boolean loginserver(String uname,String upswd){
		baseDao dao = (baseDao) daoFactoy.getInstace("userDao");
//		System.out.println(uname+upswd);
		userBean user = null;
		try {
			user = dao.findByname(uname);
			//System.out.println(user.getUid());
			if (null != user) {
				if (user.getUstatus() != 0){
					if (upswd.equals(user.getUpswd()))
						return true;
				}
				else
					return false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	public static boolean Syslogin(String uname,String upswd){
		baseDao dao = (baseDao) daoFactoy.getInstace("userDao");
		System.out.println(uname+upswd);
		userBean user = null;
		try {
			user = dao.findByname(uname);
			if (null != user) {
				if (user.getUstatus() != 0){
					if (user.getUgrade() == 1) {
						if (upswd.equals(user.getUpswd()))
							return true;
						else
							return false;
					}
					else 
						return false;
				}
				else
					return false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	
}

package daxigua.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import daxigua.Bean.userBean;
import daxigua.Dao.baseDao;
import daxigua.factoy.daoFactoy;

public class test1 {

	/**
	 * @param args
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
//				System.out.println(k);
//				System.out.println(string[k]);
				string[k++] = hexDigist[byte0 >>> 4 & 0xf];
//				System.out.println(k);
//				System.out.println(string[k]);
				string[k++] = hexDigist[byte0 & 0xf];
				
			}
			str = new String(string);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println(MD5("daiyuhan"));
//	}

}

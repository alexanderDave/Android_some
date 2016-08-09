package daxigua.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class urlprasUtil {
	public static String getUrl_head(String url){
		
		String url_head = null;
		if(url.contains("?"))
			url_head = url.substring(0,url.indexOf("?"));
		else
			url_head = url;
		
		return url_head;
	}
	
public static String getUrl_body(String url){
		
		String url_body = null;
		if(url.contains("?"))
			url_body = url.substring(url.indexOf("?")+1,url.length());
		else
			url_body = url;
		
		return url_body;
	}
	
	public static Map<String, String> getParams(String url){
		String url_body = getUrl_body(url);
		String[] strArg = url_body.split("&");
		List<String> list = new ArrayList<String>();
		for(int i=0; i<strArg.length; i++){
			list.add(strArg[i]);		
		}
		Map<String, String> params = new HashMap<String, String>();
		for (int i = 0; i <list.size();i++) {
			String[] strAgr = list.get(i).split("=");
			params.put(strAgr[0], strAgr[1]);
		}
		
		return params;
	}
}

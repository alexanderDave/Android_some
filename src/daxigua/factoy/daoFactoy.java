package daxigua.factoy;

import daxigua.daoImpl.userImpl;

public class daoFactoy {
	public static Object getInstace(String name){
		Object obj = null;
		if ("userDao".equals(name))
			obj = new userImpl();
		return obj;
	}
}

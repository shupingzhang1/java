package server;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class tset {
	public static void main(String[] args) {
		adminServer admin=new adminServer();
		System.out.println(admin.getAllAdminInfo());
	}
}

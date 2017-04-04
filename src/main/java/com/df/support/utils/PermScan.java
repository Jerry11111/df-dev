package com.df.support.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PermScan {
	private static List<Permission> permissions;
	
	
	public static List<Permission> getAllPermissions(){
		if(permissions == null){
			permissions = scanPackage("com.df.dev.action");
		}
		return permissions;
	}
	
//	public static Set<Auth> scanPackage(String pkgName){
//		String relPath = pkgName.replace('.', '/');
//		Set<Auth>authSet = new HashSet<Auth>();
//		List<Permission>permList = new ArrayList<Permission>();
//		 try {
//			 URL url = Thread.currentThread().getContextClassLoader().getResource(relPath);
//			 File f = new File(url.toURI());
//			 File[] files = f.listFiles();
//			 for(File fe : files){
//				 if(!fe.isFile() || !fe.getName().endsWith(".class")){
//					 continue;
//				 }
//				 Class<?> clazz = Class.forName(pkgName + "." + fe.getName().substring(0, fe.getName().indexOf(".class")));
//				 Auth tAuth = clazz.getAnnotation(Auth.class);
//				 Permission pperm = new Permission();
//				 if(tAuth != null){
//					 authSet.add(tAuth);
//					 pperm.id = tAuth.value();
//					 pperm.name = tAuth.name();
//				 }
//				 Method[] methods = clazz.getMethods();
//				 for(Method m : methods){
//					 Auth auth = m.getAnnotation(Auth.class);
//					 if(auth != null){
//						 Permission perm = new Permission();
//						 perm.id = auth.value();
//						 perm.name = auth.name();
//						 pperm.child.add(perm);
//						 authSet.add(auth);
//					 }
//				 }
//				 
//			 }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 return authSet;
//	}
	public static List<Permission> scanPackage(String pkgName){
		String relPath = pkgName.replace('.', '/');
		Set<Auth>authSet = new HashSet<Auth>();
		List<Permission>permList = new ArrayList<Permission>();
		 try {
			 URL url = Thread.currentThread().getContextClassLoader().getResource(relPath);
			 File f = new File(url.toURI());
			 File[] files = f.listFiles();
			 for(File fe : files){
				 if(!fe.isFile() || !fe.getName().endsWith(".class")){
					 continue;
				 }
				 Class<?> clazz = Class.forName(pkgName + "." + fe.getName().substring(0, fe.getName().indexOf(".class")));
				 Auth tAuth = clazz.getAnnotation(Auth.class);
				 Permission pperm = new Permission();
				 if(tAuth != null){
					 authSet.add(tAuth);
					 pperm.id = tAuth.value();
					 pperm.name = tAuth.name();
				 }
				 Method[] methods = clazz.getMethods();
				 for(Method m : methods){
					 Auth auth = m.getAnnotation(Auth.class);
					 if(auth != null){
						 Permission perm = new Permission();
						 perm.id = auth.value();
						 perm.name = auth.name();
						 pperm.child.add(perm);
						 authSet.add(auth);
						 if(!permList.contains(pperm)){
							 permList.add(pperm);
						 }
					 }
				 }
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return permList;
	}
	
	public static void test(){
		String pkgName = "com.appstore.action";
//		Set<Auth> authSet = scanPackage(pkgName );
//		System.out.println(authSet);
		List<Permission> permList = scanPackage(pkgName );
		System.out.println(permList);
	}
	
	
	public static void main(String[]args){
		test();
	}

}

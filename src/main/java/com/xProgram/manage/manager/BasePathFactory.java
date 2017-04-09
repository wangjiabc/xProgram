package com.xProgram.manage.manager;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BasePathFactory
 * @Description: 璺緞宸ュ巶绫�
 * @author guosheng.zhu
 * @date 2011-6-2 涓嬪崍04:39:46
 */
public class BasePathFactory {

	/**
	 * 鑾峰彇鏍硅矾寰�
	 * 
	 * @return
	 */
	public static String getBasePath() {
		String path = BasePathFactory.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			return null;
		}
		return path;
	}

	/**
	 * @Title: getClassPath
	 * @Description: 鑾峰緱classpath(........../WebRoot/WEB-INF/classes/)
	 * @param @return
	 * @return String
	 */
	public static String getClassPath() {
		return BasePathFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	/**
	 * @Title: getWebRootPath
	 * @Description: 鑾峰彇URL璇锋眰璺緞
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	public static String getWebRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
	}

	/**
	 * @Title: getResourcePath
	 * @Description: 鑾峰彇璧勬簮鏂囦欢璺緞
	 * @param @param resourceName
	 * @param @return
	 * @return String
	 */
	public static String getResourcePath(String resourceName) {
		return BasePathFactory.class.getResource("//" + resourceName).getPath();
	}
}

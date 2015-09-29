package org.developerworld.frameworks.shiro.utils;

import org.apache.commons.lang.StringUtils;

/**
 * shiro工具类
 * 
 * @author Roy Huang
 * @version 20120529
 * 
 */
public class ShiroUtils {

	public static String buildPermissionByResource(String resource) {
		return buildPermissionByResourceAndOperationAndInstance(resource, null,
				null);
	}

	public static String buildPermissionByResourceAndOperation(String resource,
			String operation) {
		return buildPermissionByResourceAndOperationAndInstance(resource,
				operation, null);
	}

	public static String buildPermissionByResourceAndInstance(String resource,
			String instance) {
		return buildPermissionByResourceAndOperationAndInstance(resource, null,
				new String[] { instance });
	}

	public static String buildPermissionByResourceAndInstance(String resource,
			String[] instance) {
		return buildPermissionByResourceAndOperationAndInstance(resource, null,
				instance);
	}

	public static String buildPermissionByResourceAndOperationAndInstance(
			String resource, String operation, String[] instance) {
		String rst = "";
		rst += StringUtils.isBlank(resource) ? "?" : resource;
		rst += ":" + (StringUtils.isBlank(operation) ? "?" : operation);
		if (instance != null) {
			for (String _instance : instance)
				rst += ":" + (StringUtils.isBlank(_instance) ? "?" : _instance);
		}
		return rst;
	}
}

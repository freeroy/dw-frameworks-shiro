package org.developerworld.frameworks.shiro.authz.permission;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.Permission;

/**
 * 在原来的统配符权限基础上。增加?符识别 若判断时要求拥有的权限节为?，代表该节不用判断
 * 
 * @author Roy Huang
 * @version 20120426
 * 
 */
public class WildcardPermission extends org.apache.shiro.authz.permission.WildcardPermission {

	public final String WILDCARD_TOKEN_2 = "?";

	public WildcardPermission(String permissionString) {
		super(permissionString);
	}

	@Override
	public boolean implies(Permission p) {
		// p为输入的权限，待判断要求拥有的权限
		// this为指向当前subject已经拥有的其中一个权限
		if (!(p instanceof WildcardPermission))
			return false;
		WildcardPermission wp = (WildcardPermission) p;

		List<Set<String>> otherParts = wp.getParts();
		List<Set<String>> thisParts = getParts();

		int i = 0;
		for (Set<String> otherPart : otherParts) {
			// If this permission has less parts than the other permission,
			// everything after the number of parts contained
			// in this permission is automatically implied, so return true
			if (thisParts.size() - 1 < i) {
				return true;
			} else {
				Set<String> part = thisParts.get(i);
				// 原判断修改位置:若要求拥有的权限位为?，代表该位不用判断
				if (!otherPart.contains(WILDCARD_TOKEN_2)) {
					if (!part.contains(WILDCARD_TOKEN)
							&& !part.containsAll(otherPart)) {
						return false;
					}
				}
				i++;
			}
		}

		// If this permission has more parts than the other parts, only imply it
		// if all of the other parts are wildcards
		for (; i < thisParts.size(); i++) {
			Set<String> part = thisParts.get(i);
			if (!part.contains(WILDCARD_TOKEN)) {
				return false;
			}
		}

		return true;
	}
}

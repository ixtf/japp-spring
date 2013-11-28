package com.hengyi.japp.report;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.CommonUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.exception.SearchException;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.shiro.MyAuthorizationInfo;

public class MyUtil extends CommonUtil {
	public static void checkSearch(String s) throws Exception {
		if (StringUtils.isBlank(s))
			throw new SearchException(s);
		String _s = StringUtils.replace(s, "*", "");
		if (StringUtils.isBlank(_s))
			throw new SearchException(_s);
	}

	public static Collection<? extends Menu> getAllParent(Menu menu) {
		List<Menu> menus = Lists.newArrayList();
		Menu parent = menu.getParent();
		if (parent == null)
			return menus;
		menus.add(parent);
		menus.addAll(getAllParent(parent));
		return menus;
	}

	public static Collection<? extends Menu> getAllSub(Menu menu) {
		List<Menu> menus = Lists.newArrayList(menu.getSubs());
		for (Menu m : menus)
			menus.addAll(getAllSub(m));
		return menus;
	}

	public static Collection<? extends Menu> getAllSub(Iterable<Menu> menus) {
		Set<Menu> result = Sets.newHashSet();
		for (Menu menu : menus)
			result.addAll(getAllSub(menu));
		return result;
	}

	public static boolean isSuperUser() {
		return SecurityUtils.getSubject().hasRole(Constant.ROLE_SUPERUSER);
	}

	private static final String REPORT_PERMISSION_PREFIX = "report:view:";

	public static AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) throws Exception {
		if (principals.getPrimaryPrincipal().equals(Constant.ADMIN_PRINCIPAL)) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addObjectPermission(new AllPermission());
			return info;
		}
		return new MyAuthorizationInfo();
	}

	public static void checkAuthorization(Report report) {
		SecurityUtils.getSubject().checkPermission(
				REPORT_PERMISSION_PREFIX + report.getNodeId());
	}

	public static Permission generatePermission(Report report) {
		return new WildcardPermission(REPORT_PERMISSION_PREFIX
				+ report.getNodeId());
	}

	public static Iterable<Report> getAllReport(Operator operator,
			Neo4jOperations template, MenuService menuService) {
		Set<Report> reports = Sets.newHashSet();
		Set<Menu> menus = Sets.newHashSet();
		for (Report report : operator.getReports(template))
			reports.add(report);
		for (Menu menu : operator.getMenus(template))
			menus.add(menu);
		for (Role role : operator.getRoles(template)) {
			reports.addAll(Sets.newHashSet(role.getReports(template)));
			menus.addAll(Sets.newHashSet(role.getMenus(template)));
		}
		menus.addAll(MyUtil.getAllSub(menus));
		for (Menu menu : menus) {
			reports.addAll(menuService.findAllReport(menu));
		}
		reports.addAll(menuService.findAllReport(menus));
		return reports;
	}

	// public static MenuItem getHomeMenuItem() {
	// MenuItem menuItem = new MenuItem();
	// menuItem.setValue("首页");
	// menuItem.setIcon("ui-icon-home");
	// menuItem.setUrl("/");
	// return menuItem;
	// }
	//
	// public static MenuItem getLogoutMenuItem() {
	// MenuItem menuItem = new MenuItem();
	// menuItem.setValue("退出");
	// menuItem.setIcon("ui-icon-extlink");
	// menuItem.setUrl("/logout");
	// menuItem.setStyle("margin-right:10px");
	// return menuItem;
	// }
}

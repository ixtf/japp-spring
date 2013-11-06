package com.hengyi.japp.report.shiro;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.hengyi.japp.report.Constant;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.event.menu.MenuUpdateEvent;
import com.hengyi.japp.report.event.operator.LogoutEvent;
import com.hengyi.japp.report.event.operator.OperatorUpdateEvent;
import com.hengyi.japp.report.event.role.RoleUpdateEvent;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.service.RoleService;

public class MyAuthorizationInfo extends SimpleAuthorizationInfo {
	private static final long serialVersionUID = 8377116339522991974L;
	private Operator operator;
	private Neo4jOperations template;
	private EventBus eventBus;
	private CacheService cacheService;
	private OperatorService operatorService;
	private RoleService roleService;
	private MenuService menuService;

	public MyAuthorizationInfo() throws Exception {
		super();
		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		template = context.getBean(Neo4jOperations.class);
		eventBus = context.getBean(EventBus.class);
		cacheService = context.getBean(CacheService.class);
		operatorService = context.getBean(OperatorService.class);
		roleService = context.getBean(RoleService.class);
		menuService = context.getBean(MenuService.class);
		this.operator = cacheService.getCurrentOperator();
		// TODO 权限修改，需要重新登入，以后尝试改进，无须重新登入，刷新权限
		// eventBus.register(this);
		initAuthorizationInfo();
	}

	private void initAuthorizationInfo() {
		setRoles(null);
		setObjectPermissions(null);
		setStringPermissions(null);
		// 重新获取用户，获取新的权限
		operator = operatorService.findOne(operator.getNodeId());
		for (Role role : operator.getRoles(template))
			addRole(role.getName());
		if (isSuperUser())
			addObjectPermission(new AllPermission());
		else
			for (Report report : MyUtil.getAllReport(operator, template,
					menuService))
				addObjectPermission(MyUtil.generatePermission(report));
	}

	private boolean isSuperUser() {
		return roles != null && roles.contains(Constant.ROLE_SUPERUSER);
	}

	@Subscribe
	public void logoutEvent(LogoutEvent event) {
		if (event.getOperator().equals(operator))
			eventBus.unregister(this);
	}

	@Subscribe
	public void authorizationUpdateEvent(OperatorUpdateEvent event) {
		if (!event.getNodeId().equals(operator.getNodeId()))
			return;
		initAuthorizationInfo();
	}

	@Subscribe
	public void authorizationUpdateEvent(RoleUpdateEvent event) {
		if (isSuperUser())
			return;
		Role updatedRole = roleService.findOne(event.getNodeId());
		for (Role role : operator.getRoles())
			if (updatedRole.equals(role))
				initAuthorizationInfo();
	}

	@Subscribe
	public void authorizationUpdateEvent(MenuUpdateEvent event) {
		if (isSuperUser())
			return;
		initAuthorizationInfo();
	}
}

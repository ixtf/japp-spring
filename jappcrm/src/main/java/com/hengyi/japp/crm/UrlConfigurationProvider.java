package com.hengyi.japp.crm;

import com.hengyi.japp.common.CommonUrlConfigurationProvider;

public class UrlConfigurationProvider extends CommonUrlConfigurationProvider {
	// @Override
	// protected ConfigurationBuilder getConfigurationBuilder(
	// ServletContext context) {
	// ConfigurationBuilder builder = super.getConfigurationBuilder(context);
	// WebApplicationContext springContext = WebApplicationContextUtils
	// .getRequiredWebApplicationContext(context);
	// for (CrmUrlService crmUrlService : springContext.getBeansOfType(
	// CrmUrlService.class).values())
	// crmUrlService(builder, crmUrlService);
	// return builder;
	// }
	//
	// private void crmUrlService(ConfigurationBuilder builder,
	// CrmUrlService crmUrlService) {
	// builder.addRule(
	// Join.path(crmUrlService.getIndicatorNewPath()).to(
	// crmUrlService.getIndicatorUpdateView()))
	// .addRule(
	// Join.path(crmUrlService.getIndicatorManagePath()).to(
	// crmUrlService.getIndicatorManageView()))
	// .addRule(
	// Join.path(crmUrlService.getReportNewPath()).to(
	// crmUrlService.getReportUpdateView()))
	// .addRule(
	// Join.path(crmUrlService.getReportManagePath()).to(
	// crmUrlService.getReportManageView()));
	// }

	// @Override
	// public Configuration getConfiguration(ServletContext context) {
	// WebApplicationContext springContext = WebApplicationContextUtils
	// .getRequiredWebApplicationContext(context);
	// UrlUtil urlUtil = springContext.getBean(UrlUtil.class);
	// Condition loggedIn = new Condition() {
	// @Override
	// public boolean evaluate(Rewrite event, EvaluationContext context) {
	// return SecurityUtils.getSubject().isAuthenticated();
	// }
	// };
	// Condition admin = new Condition() {
	// @Override
	// public boolean evaluate(Rewrite event, EvaluationContext context) {
	// return SecurityUtils.getSubject().hasRole(ADMIN_PRINCIPAL);
	// }
	// };
	// return ConfigurationBuilder
	// .begin()
	// .addRule()
	// .when(Direction.isInbound()
	// .and(Path.matches(urlUtil.getLoginPath()))
	// .and(loggedIn))
	// .perform(
	// Redirect.temporary(context.getContextPath()
	// + urlUtil.getHomePath()))
	// .addRule(
	// Join.path(urlUtil.getLoginPath()).to(
	// urlUtil.getLoginView()))
	// .addRule()
	// .when(Direction.isInbound()
	// .and(Path.matches(urlUtil.getHomePath())).and(admin))
	// .perform(
	// Redirect.temporary(context.getContextPath()
	// + urlUtil.getAdminHomePath()))
	// .addRule(
	// Join.path(urlUtil.getAdminHomePath()).to(
	// urlUtil.getAdminHomeView()))
	// .addRule(
	// Join.path(urlUtil.getThemePath()).to(
	// urlUtil.getThemeView()))
	// .when(loggedIn)
	// .addRule(
	// Join.path(urlUtil.getHomePath()).to(
	// urlUtil.getHomeView()))
	//
	// .addRule(
	// Join.path(urlUtil.getCrmTypeNewPath()).to(
	// urlUtil.getCrmTypeUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCrmTypesPath()).to(
	// urlUtil.getCrmTypesView()))
	//
	// .addRule(
	// Join.path(urlUtil.getCertificateNewPath()).to(
	// urlUtil.getCertificateUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCertificatesPath()).to(
	// urlUtil.getCertificatesView()))
	//
	// .addRule(
	// Join.path(urlUtil.getCommunicateeNewPath()).to(
	// urlUtil.getCommunicateeUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCommunicateesPath()).to(
	// urlUtil.getCommunicateesView()))
	//
	// .addRule(
	// Join.path(urlUtil.getIndicatorValueNewPath()).to(
	// urlUtil.getIndicatorValueUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getIndicatorValuesPath()).to(
	// urlUtil.getIndicatorValuesView()))
	//
	// .addRule(
	// Join.path(urlUtil.getCustomerNewPath()).to(
	// urlUtil.getCustomerUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCustomersPath()).to(
	// urlUtil.getCustomersView()))
	// .addRule(
	// Join.path(urlUtil.getCustomerIndicatorNewPath()).to(
	// urlUtil.getCustomerIndicatorUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCustomerIndicatorsPath()).to(
	// urlUtil.getCustomerIndicatorsView()))
	// .addRule(
	// Join.path(urlUtil.getCustomerReportNewPath()).to(
	// urlUtil.getCustomerReportUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getCustomerReportsPath()).to(
	// urlUtil.getCustomerReportsView()))
	//
	// .addRule(
	// Join.path(urlUtil.getStorageNewPath()).to(
	// urlUtil.getStorageUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getStoragesPath()).to(
	// urlUtil.getStoragesView()))
	// .addRule(
	// Join.path(urlUtil.getStorageIndicatorNewPath()).to(
	// urlUtil.getStorageIndicatorUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getStorageIndicatorsPath()).to(
	// urlUtil.getStorageIndicatorsView()))
	// .addRule(
	// Join.path(urlUtil.getStorageReportNewPath()).to(
	// urlUtil.getStorageReportUpdateView()))
	// .addRule(
	// Join.path(urlUtil.getStorageReportsPath()).to(
	// urlUtil.getStorageReportsView()));
	// }

	@Override
	public int priority() {
		return 10;
	}
}

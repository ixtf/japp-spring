package com.hengyi.japp.report.web;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JappReportFilter implements Filter {
	private static final Pattern domainPattern = domainPattern();
	private static final String DEFAULT_JAPPREPORTSERVER = "http://192.168.0.13:8080/report";

	private String jappReportServer;
	private Set<String> allowDomains;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		jappReportServer = filterConfig.getInitParameter("JappReportServer");
		if (jappReportServer == null)
			jappReportServer = DEFAULT_JAPPREPORTSERVER;

		allowDomains = new HashSet<>();
		allowDomains.add(matcherDomain(jappReportServer));
		allowDomains.add("localhost");
		allowDomains.add("127.0.0.1");

		String s = filterConfig.getInitParameter("allowDomains");
		if (s != null)
			for (String domain : s.split(",|;"))
				allowDomains.add(domain.trim());

		allowDomains = Collections.unmodifiableSet(allowDomains);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (checkReq(req))
			chain.doFilter(req, resp);
		else
			resp.sendRedirect(jappReportServer);
	}

	private boolean checkReq(HttpServletRequest req) {
		if (allowDomains.contains(req.getRemoteHost())
				|| allowDomains.contains(req.getRemoteAddr()))
			return true;
		else {
			String referer = req.getHeader("referer");
			return referer != null
					&& allowDomains.contains(matcherDomain(referer));
		}
	}

	// private boolean checkReqIp(HttpServletRequest req) {
	// return allowIps.contains(req.getRemoteHost())
	// || allowIps.contains(req.getRemoteAddr());
	// }
	//
	// private boolean checkReferer(HttpServletRequest req) {
	// String referer = req.getHeader("referer");
	// return referer != null && jappReportServerIp.equals(matcherIp(referer));
	// }
	//
	// public static final Pattern ipPattern() {
	// String regex0_199 = "(1\\d{2})|(?:[1-9]?\\d)";
	// String regex0_255 = "(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
	// String regexIpOne = "(" + regex0_199 + "|" + regex0_255 + ")";
	// String regexIp0_3 = "(" + regexIpOne + "\\.){3}";
	// String regex = "^((http|https)://)?(?<ip>" + regexIp0_3 + regexIpOne
	// + ")\\S+$";
	// return Pattern.compile(regex);
	// }
	//
	// public static final String matcherIp(String url) {
	// if (url == null)
	// return null;
	// Matcher matcher = ipPattern.matcher(url.trim());
	// if (matcher.find())
	// return matcher.group("ip");
	// return null;
	// }

	private static Pattern domainPattern() {
		String regex = "^((http|https)://)?(?<domain>[^:/]*)\\S*$";
		return Pattern.compile(regex);
	}

	private static final String matcherDomain(String url) {
		if (url == null)
			return null;
		Matcher matcher = domainPattern.matcher(url.trim());
		if (matcher.find())
			return matcher.group("domain");
		return null;
	}

	@Override
	public void destroy() {
	}

	public static void main(String[] args) {
		System.out.println(matcherDomain(DEFAULT_JAPPREPORTSERVER));
		System.out.println(matcherDomain("http://www.sina.com.cn/dsds"));
		String s = "192.168.0.125;192.168.0.124,192.168.17.117";
		for (String ip : s.split(",|;"))
			System.out.println(ip);
	}
}

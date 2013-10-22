package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -1470555038437736246L;
}

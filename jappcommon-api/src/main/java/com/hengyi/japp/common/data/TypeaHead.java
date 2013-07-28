package com.hengyi.japp.common.data;

import java.io.Serializable;

public class TypeaHead implements Serializable {
	private static final long serialVersionUID = 2062027227282176242L;
	private String name;
	private String value;
	private String[] tokens;

	public TypeaHead(String name, String value, String... tokens) {
		super();
		this.name = name;
		this.value = value;
		this.tokens = tokens;
	}

	public TypeaHead(String name, String value) {
		this(name, value, name);
	}

	public TypeaHead(String value) {
		this(value, value);
	}

	public String getValue() {
		return value;
	}

	public String[] getTokens() {
		return tokens;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

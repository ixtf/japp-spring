package com.hengyi.japp.common.sap;

class Money{
	private int amount;
	private String currency;
	Money(int amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}
	Money plus(Money other){
		if(null==other)return null;
		if(currency!=other.currency)throw new IllegalArgumentException();
		return new Money(amount+other.amount,currency);
	}
	Money plus(Integer more){
		return new Money(amount+more,currency);
	}
	boolean equals(Object other) {
		if(null==other)return false;
		if(!(other instanceof Money))return false;
		if(currency!=other.currency || amount!=other.amount) return false;
		return true;
	}
	int hashCode() {
		return Objects.hash(amount,currency);
	}
}
package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {
	
	int minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		minimumBalance = 1000;	
	}

	public int getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(int minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
	
}

package com.ilp.entity;

import java.util.ArrayList;

public class CurrentAccount extends Product {
	
	private String gstNo;
	
	public CurrentAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	
	
}

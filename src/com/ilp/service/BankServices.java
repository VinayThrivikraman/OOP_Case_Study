package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankServices {

	public static void createService(ArrayList<Service> serviceList) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Service Code: ");
		String serviceCode = scanner.nextLine();
		System.out.println("Enter Service Name: ");
		String serviceName = scanner.nextLine();
		System.out.println("Enter the Rate: ");
		double rate = scanner.nextDouble();
		
		Service service = new Service(serviceCode, serviceName, rate);	
		serviceList.add(service);
	}
	
	public static void displayServices(ArrayList<Service> serviceList) {
		if (serviceList.isEmpty()) {
            System.out.println("No services available.");
        } 
		else {
			System.out.println(serviceList);
        }
	}

	public static void createProduct(ArrayList<Product> productList, ArrayList<Service> serviceList) {
		
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Product Code: ");
        String productCode = scanner.nextLine();
        System.out.println("Enter Product Name: ");
        String productName = scanner.nextLine();
        
        ArrayList<Service> productServiceList = new ArrayList<Service>();
        
        System.out.println("Enter the number of services for this product: ");
        int numberOfServices = scanner.nextInt();
        
        for (int i = 0; i < numberOfServices; i++) {
        	System.out.println("Enter Service Code for Service: ");
            String serviceCode = scanner.next();
            
            Service serviceByCode = findServiceByCode(serviceList, serviceCode);
            
            if (serviceByCode != null) {
                productServiceList.add(serviceByCode);
            } 
            else {
                System.out.println("Service with code " + serviceCode + " not found.");
            }
        }
        if (productName.equalsIgnoreCase("SavingsMaxAccount"))
        {
        	Product product = new SavingsMaxAccount(productCode, productName, productServiceList);
        	productList.add(product);
        }
        else if (productName.equalsIgnoreCase("CurrentAccount"))
        {
        	Product product = new CurrentAccount(productCode, productName, productServiceList);
        	productList.add(product);
        }
        else if (productName.equalsIgnoreCase("LoanAccount"))
        {
        	Product product = new LoanAccount(productCode, productName, productServiceList);
        	productList.add(product);
        }
        System.out.println("Product created successfully.");
	}
	
	private static Service findServiceByCode(ArrayList<Service> serviceList, String serviceCode) {
		for (Service service : serviceList) {
	        if (service.getServiceCode().equals(serviceCode)) {
	            return service;
	        }
	    }
		return null;
	}

	public static void displayProducts(ArrayList<Product> products) {
		if (products.isEmpty()) {
            System.out.println("No products available.");
        } 
		else 
		{
	        System.out.println("List of Products:");
	        for (Product product : products) {
	        	System.out.println("\nProduct Code: " + product.getProductCode());
	            System.out.println("Product Name: " + product.getProductName());
	            
	            ArrayList<Service> productServiceList = product.getServices();

	                if (productServiceList.isEmpty()) {
	                    System.out.println("No services associated with this product.");
	                } 
	                else {
	                    System.out.println("Services:");
	                    for (Service service : productServiceList) {
	                        System.out.println("  Service Code: " + service.getServiceCode());
	                        System.out.println("  Service Name: " + service.getServiceName());
	                        System.out.println("  Rate: " + service.getRate());
	                        System.out.println("  ----------------------");
	                    }
	                }
			System.out.println("----------------------");
            }
        }
	}

//	public static Customer createAccountAndCustomer(ArrayList<Product> productList, ArrayList<Account> accountList) {
//		Scanner scanner = new Scanner(System.in);
//		
//		char createMoreAccountChoice = 'y';
//		
//		do {
//			System.out.println("\nAvailable Accounts Are: ");
//			System.out.println("1.Savings Max Account \n2.Current Account \n3.Loan Account");
//			
//			System.out.println("\nChoose your Option: ");
//			int accountMenuChoice = scanner.nextInt();
//			
//			scanner.nextLine();
//			System.out.println("Enter Account Number: ");
//			String accountNumber = scanner.nextLine();
//			
//			System.out.println("Enter Account Balance: ");
//			double accountBalance = scanner.nextDouble();
//			
//			String accountType = null;
//			Account account = null;
//					
//			switch(accountMenuChoice)
//			{
//				case 1:
//					accountType = "Savings Max Account";
//					account = new Account(accountNumber, accountType, accountBalance, productList.get(accountMenuChoice-1));
//					System.out.println(account);
//					break;
//					
//				case 2:
//					accountType = "Current Account";
//					account = new Account(accountNumber, accountType, accountBalance, productList.get(accountMenuChoice-1));
//					System.out.println(account);
//					break;
//					
//				case 3:
//					accountType = "Loan Account";
//					account = new Account(accountNumber, accountType, accountBalance, productList.get(accountMenuChoice-1));
//					System.out.println(account);
//					break;
//			}
//			accountList.add(account);
//			
//			System.out.println("Do you wish to add more Accounts? (y/n)");
//			createMoreAccountChoice = scanner.next().charAt(0);
//		}
//		while(createMoreAccountChoice == 'y');
//		
//		scanner.nextLine();
//		System.out.println("Enter Customer Code: ");
//		String customerCode = scanner.nextLine();
//		System.out.println("Enter Customer Name: ");
//		String customerName = scanner.nextLine();
//		
//		Customer customer = new Customer(customerCode, customerName, accountList);
//		System.out.println(customer);
//		
//		return customer;
//	}

	public static Customer createAccountAndCustomer(ArrayList<Product> productList, ArrayList<Account> accountList) {
		Scanner scanner = new Scanner(System.in);
		
		char createMoreAccountChoice = 'y';
		
		do {
			System.out.println("\nAvailable Accounts Are: ");
			for (Product product: productList)
			{
				System.out.println(product.getProductName());
			}
//			System.out.println("1.Savings Max Account \n2.Current Account \n3.Loan Account");
			
			scanner.nextLine();
			System.out.println("\nChoose an Account: ");
			String accountMenuChoice = scanner.nextLine();
			
//			scanner.nextLine();
			System.out.println("Enter Account Number: ");
			String accountNumber = scanner.nextLine();
			
			System.out.println("Enter Account Balance: ");
			double accountBalance = scanner.nextDouble();
			
			String accountType = null;
			Account account = null;
					
			switch(accountMenuChoice)
			{
				case "SavingsMaxAccount":
					for (Product product: productList)
					{
						if(product.getProductName().equalsIgnoreCase(accountMenuChoice))
						{
							accountType = product.getProductName();
							account = new Account(accountNumber, accountType, accountBalance, product);
							break;	
						}
					}
					
					
				case "CurrentAccount":
					for (Product product: productList)
					{
						if(product.getProductName().equalsIgnoreCase(accountMenuChoice))
						{
							accountType = product.getProductName();
							account = new Account(accountNumber, accountType, accountBalance, product);
							System.out.println(account);
						}
					}
					break;
					
				case "LoanAccount":
					for (Product product: productList)
					{
						if(product.getProductName().equalsIgnoreCase(accountMenuChoice))
						{
							accountType = product.getProductName();
							account = new Account(accountNumber, accountType, accountBalance, product);
							System.out.println(account);
						}
					}
					break;
			}
			accountList.add(account);
			
			System.out.println("Do you wish to add more Accounts? (y/n)");
			createMoreAccountChoice = scanner.next().charAt(0);
		}
		while(createMoreAccountChoice == 'y');
		
		scanner.nextLine();
		System.out.println("Enter Customer Code: ");
		String customerCode = scanner.nextLine();
		System.out.println("Enter Customer Name: ");
		String customerName = scanner.nextLine();
		
		Customer customer = new Customer(customerCode, customerName, accountList);
		System.out.println(customer);
		
		return customer;
	}
	
	public static void manageAccounts(Customer customer, ArrayList<Account> accountList, ArrayList<Product> productList) {
		
		System.out.println(customer.getCustomerName() + " has the Following Accounts: ");
		for (Account account: accountList)
		{
			System.out.println(account.getAccountType());
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter Your Choice: ");
		String accountChoice = scanner.nextLine();
		
		System.out.println("\n1.Deposit \n2.Withdraw \n3.Display Balance");
		int serviceChoice = scanner.nextInt();
		
		switch (serviceChoice)
		{
			case 1:
				System.out.println("Enter the Amount to Deposit: ");
				double depositAmount = scanner.nextDouble();
				for (Account account: customer.getAccountList())
				{
					if(account.getAccountType().equals(accountChoice))
					{
						if(account.getAccountType().equalsIgnoreCase("LoanAccount"))
						{
							LoanAccount loanAccount = (LoanAccount) account.getProduct();
							System.out.println("Choose a Deposit Type: ");
							System.out.println("1.Cash Deposit \n2.Cheque Deposit");
							int depositChoice = scanner.nextInt();
							
							if(depositChoice == 2)
							{
								account.setBalance(account.getBalance() + (depositAmount - depositAmount * loanAccount.getChequeDeposit()/100));
								System.out.println("Cheque Deposit Completed Successfully \nCurrent Balance: " + account.getBalance());
							}
							else
							{
								account.setBalance(account.getBalance() + depositAmount);
								System.out.println("Cash Deposit Completed Successfully \nCurrent Balance: " + account.getBalance());
							}
						}
						else
						{
							account.setBalance(account.getBalance() + depositAmount);
							System.out.println("Deposit Completed Successfully \nCurrent Balance: " + account.getBalance());
						}
					}
				}
				break;
				
			case 2:
				System.out.println("Enter the Amount to Withdraw: ");
				double withdrawAmount = scanner.nextDouble();
				
				for (Account account: customer.getAccountList())
				{
					if(account.getAccountType().equals(accountChoice))
					{
						if(account.getAccountType().equalsIgnoreCase("SavingsMaxAccount"))
						{
							SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
							if(withdrawAmount > savingsMaxAccount.getMinimumBalance())
							{
								System.out.println("Transaction INCOMPLETE!!! \nPlease Maintain Minimum Balance");
								System.out.println("Current Balance: " + account.getBalance());
							}
							else
							{
								account.setBalance(account.getBalance() - withdrawAmount);
								System.out.println("Transaction Successful!");
								System.out.println("Current Balance: " + account.getBalance());
							}
						}
						else
						{
							account.setBalance(account.getBalance() - withdrawAmount);
							System.out.println("Transaction Successful!");
							System.out.println("Current Balance: " + account.getBalance());
						}
					}
					
				}
				break;
			
			case 3:
		}
	}

	public static void displayCustomer(Customer customer) {
		System.out.println("\n******************************Customer Details******************************");
		System.out.println("Customer Code" + "        " + "Customer Name" + "        " + "Account Type" + "        " + "Balance");
		System.out.println("********************************************************************************");
		
		for (Account account: customer.getAccountList())
		{
			System.out.println(customer.getCustomerCode() + "    " + customer.getCustomerName() + "    " + account.getAccountType() + "    " + account.getBalance());
			System.out.println("Services Available:");
			for(Service service: account.getProduct().getServices())
			{
				System.out.println(service.getServiceName());
			}
		}
		
	}
}

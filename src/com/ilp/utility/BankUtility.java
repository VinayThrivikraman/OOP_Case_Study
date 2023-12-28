package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.BankServices;

public class BankUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Customer customer = null;
		
		
		char goBackChoice;
		do {
			System.out.println("*******Welcome to Bank*******");
			System.out.println("1. Create Service \n2. Create Product \n3. Create Customer \n4. Manage Accounts \n5. Display Customer \n6. Exit \n7. Display Services \n8. Display products");
			Scanner scanner = new Scanner(System.in);
			
			int mainMenuChoice = scanner.nextInt();
			
			switch (mainMenuChoice)
			{
				case 1:
					BankServices.createService(serviceList);
					break;
					
				case 2:
					BankServices.createProduct(productList, serviceList);
					break;
					
				case 3:
					if(customer == null)
					{
						System.out.println("No Customer Exists!! \nCreate a New Account: ");
						customer = BankServices.createAccountAndCustomer(productList, accountList);
					}
					break;
					
				case 4:
					scanner.nextLine();
					System.out.println("Enter Customer Code: ");
					String customerCode = scanner.nextLine();
					if(customer.getCustomerCode().equals(customerCode))
					{
						BankServices.manageAccounts(customer, accountList, productList);
					}
					else
					{
						System.out.println("Invalid Customer Code");
					}
					break;
					
				case 5:
					BankServices.displayCustomer(customer);
					break;
					
				case 6:
					break;
					
				case 7:
					BankServices.displayServices(serviceList);
					break;
				case 8:
					BankServices.displayProducts(productList);
					break;
					
				default:
					break;
			}
			System.out.println("Do you with to Continue? (y/n)");
			goBackChoice = scanner.next().charAt(0);
		}
		while(goBackChoice == 'y');
	}

}

package com.nikhitha;
import java.sql.*;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ATMJdbc {
public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/atm_db";
		String username="root";
		String password="mahi";
		Scanner sc=new Scanner(System.in); 
		
		System.out.println("Enter account number: ");
		int account_no=sc.nextInt();
		System.out.println("Enter PIN: ");
		int pin=sc.nextInt();
		try {
		Connection con=DriverManager.getConnection(url, username, password);
		System.out.println("Connected successfully");
		
		PreparedStatement ps=con.prepareStatement("select * from atm_account where account_no=? and pin=?");
		ps.setInt(1, account_no);
		ps.setInt(2,pin);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			System.out.println("Login successful");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Exit");
			
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			
			switch(choice) 
			{
			case 1:{
				System.out.println("Balance: " + rs.getInt("balance"));
				break;
			       }
			case 2: {
				System.out.println("Enter amount to deposit");
				int amount=sc.nextInt();
				int balance=rs.getInt("balance");
				int newbalance=balance + amount;
				PreparedStatement update=con.prepareStatement("Update ATM_ACCOUNT SET balance = ? where account_no = ?");
				update.setInt(1, balance);
				update.setInt(2, 1001);
				update.executeUpdate();
				System.out.println("Amount deposited successfully");
				System.out.println("Updated Balance: " + newbalance);
				break;
	            	}
			case 3:{
				System.out.println("Enter amount to withdraw");
				int amount=sc.nextInt();
			    break;
		           }
			case 4: {
				System.out.println("Exit");
				break;
 	                }
			default:System.out.println("Invalid choice");
			}
			} else {
			System.out.println("Invalid account number or PIN");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

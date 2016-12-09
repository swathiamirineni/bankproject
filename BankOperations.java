package banking;

import java.sql.*;
import java.util.*;

public class BankOperations  implements BankInterface {
	Scanner sc=new Scanner(System.in);
	Connection con;
	Statement stmt;
	String br,at,ab,b_id,pan,bid,se,l;
	int  accountno;
	double amount,sum2,sub2;
	
	public void connection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
        stmt=con.createStatement();
    }
	//*******************START MENU********************************
	
	public void startMenu() throws Exception   
	{
		Scanner sc1=new Scanner(System.in);
		System.out.println("Welcome to OUR BANK");
		System.out.println("");
		Thread.sleep(3000);
		System.out.println("1:Existing User");
		Thread.sleep(1000);
		System.out.println("2:New User");
		Thread.sleep(1000);
		System.out.println("3:Exit");
		System.out.println("");
		Thread.sleep(1000);
		System.out.println("select one option");
		String n=sc1.nextLine();
		switch(n)
		{
		case "1":loginMenu();
		       break;
		case "2":branchMenu();
		       break;
		case "3":System.out.println("Exited");
	           System.exit(0);
		       break;
		default:System.out.println("PLZZZZZ... ENTER PROPER OPTION");
		        Thread.sleep(2000);
		        System.out.println("");
		        startMenu();
                
		}
	}
	//****************************BRANCH MENU***********************
	
	public void branchMenu() throws Exception {
		Scanner sc2=new Scanner(System.in);
		connection();
		System.out.println("Enter Branch where U wish to have an Account");
		System.out.println("B01:kukatpally");
		System.out.println("B02:lingampally");
		System.out.println("B03:Ameerpet");
		br=sc2.nextLine();
		switch(br)
		{
		case "B01":accountTypeMenu();
		           break;
		case "B02":accountTypeMenu();
		           break;
		case "B03":accountTypeMenu(); 
		           break;
		default:System.out.println("Follow Instructions carefully...Only given values are valid");
			     menu();
		}
	
		con.close();
	}
	
	//**************************ACCOUNT TYPE MENU*****************************//
	
	public void accountTypeMenu() throws Exception {
		Scanner sc3=new Scanner(System.in);
		System.out.println("Enter the Type of Accout to have:");
		System.out.println("AT01:Saving Type:Rs.500");
		System.out.println("AT02:Current Type:Rs.0");
		System.out.println("AT03:Fixed Type:Rs.1000");
		System.out.println("AT04:Recurring Type:Rs.500");
		System.out.println("AT05:Loan Type:Rs.1500");
		at=sc3.nextLine();
		System.out.println("");
		switch(at)
		{
		case "AT01":details();
		       break;
		case "AT02":details();
	       break;
		case "AT03":details();
	           break;
		case "AT04":details();
	       break;
		case "AT05":details();	                       
	       break;
	     default:System.out.println("Plz...Enter proper Option");
	             Thread.sleep(2000);
	             accountTypeMenu();
		}	
	}
	//*************************DETAILS ***************************************
	
	public void details() throws Exception{
		Scanner sc4=new Scanner(System.in);
		connection();
		PreparedStatement ps=con.prepareStatement("insert into customerregistration_tbl(B_id,Acc_id,Account_Name,User_Name,password,PanCard_Number) values(?,?,?,?,?,?)"); 
		ps.setString(1,br );
		ps.setString(2,at);
		System.out.print("Enter Account Name: ");
		ps.setString(3,sc4.nextLine() );
		System.out.print("Enter (for Login) User Name: ");
		String us=sc4.nextLine();
		ps.setString(4,us );
		System.out.print("Enter Password: ");
	    String pas=sc4.nextLine();
	    if(pas.length()>=6)
	    {
		ps.setString(5,pas );
	    }
		
	    else
	    {
	    	System.out.println("enter password minimum of 6 characters");
	    	System.out.println("enter password");
	    	String pas1=sc.nextLine();	
			
			if(pas1.length()>=6)
			{
				ps.setString(5,pas1);
			}
			else{
				System.out.println("password limit is: 6");
				System.out.println("Enter password.... you have only one chance left");
				String pas3=sc4.nextLine();	
			if(pas3.length()>=6)
			{
				ps.setString(5,pas3);
			}
			
			else
			{
				System.out.println("sorry... registration failed");
				System.out.println("Do you have any other type of account to login");
				System.out.println("press 1 for login into your account or 0 for exit");
				String n=sc4.nextLine();
				switch(n)
				{
				case "1":loginMenu();
				       break;
				case "0":System.out.println("Thank u wish to see u again");
				       System.exit(0);
				 default: System.out.println("sry.. there is no such option (time exceeded)");
				          System.exit(0);
				}
			}
			
			}
		}
	    	
		System.out.println("Enter pan number");
		String p=sc4.nextLine();	
		
		
		//
		if(p.length()==10)
		{
			ps.setString(6,p);
		}
		else
		{
			System.out.println("pan limit is: 10");
			System.out.println("Enter pan number... you have only two chances left ");
			String p2=sc4.nextLine();	
			int p3=p2.length();
			if(p3==10)
			{
				ps.setString(6,p2);
			}
			else{
				System.out.println("pan limit is: 10");
				System.out.println("Enter pan number.... you have only one chance left");
				String p4=sc4.nextLine();	
				int p5=p4.length();
			if(p5==10)
			{
				ps.setString(6,p4);
			}
			
			else
			{
				System.out.println("sorry... registration failed");
				System.out.println("Do you have any other type of account to login");
				System.out.println("press 1 for login into your account or 0 for exit");
				String n=sc4.nextLine();
				switch(n)
				{
				case "1":loginMenu();
				       break;
				case "0":System.out.println("Thank u wish to see u again");
				       System.exit(0);
				 default: System.out.println("sry.. there is no such option (time exceeded)");
				          System.exit(0);
				}
			}
			}
				
	
		}
		
		ps.executeUpdate();
		PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=500.00 where Acc_id='AT01'");
		PreparedStatement ps2=con.prepareStatement("update customerregistration_tbl set Mybalance=0.0 where Acc_id='AT02'");
		PreparedStatement ps3=con.prepareStatement("update customerregistration_tbl set Mybalance=1000.00 where Acc_id='AT03'");
		PreparedStatement ps4=con.prepareStatement("update customerregistration_tbl set Mybalance=500.00 where Acc_id='AT04'");
		PreparedStatement ps5=con.prepareStatement("update customerregistration_tbl set Mybalance=1500.00 where Acc_id='AT05'");
		ps1.executeUpdate();
		ps2.executeUpdate();
		ps3.executeUpdate();
		ps4.executeUpdate();
		ps5.executeUpdate();
		System.out.println("");
		System.out.println("Please wait your Account is in process...");
		Thread.sleep(2000);
		System.out.println("");
		System.out.println("Cleark Action is activate");
		Thread.sleep(3000);
		System.out.println("");
		System.out.println("Your Book is being Printed...");
		System.out.println("");
		Thread.sleep(3000);
		PreparedStatement ps6=con.prepareStatement("select * from customerregistration_tbl where User_Name='"+us+"'");
		ResultSet rs=ps6.executeQuery();
		while(rs.next())
		{
			ab=rs.getString(1);
		System.out.println("AccountNumber: "+ab);
		Thread.sleep(1000);
		System.out.println("Account Name: "+rs.getString(4));
		Thread.sleep(1000);
		System.out.println("Branch ID: "+rs.getString(2));
		Thread.sleep(1000);
		System.out.println("AccountType ID: "+rs.getString(3));
		Thread.sleep(1000);
		//System.out.println("Your Present Balance: "+rs.getDouble(7));
		//Thread.sleep(1000);
		System.out.println("Your PanCard Number: "+rs.getString(8));
		Thread.sleep(1000);
		
		}
        Thread.sleep(2000);
        System.out.println("");
        lockers();
        Thread.sleep(2000);
        System.out.println("");
		System.out.println("Enjoy our Services");
		 Thread.sleep(3000);
		System.out.println("For Login PRESS 1 &&&&& For Exit PRESS 0");
		String n=sc4.nextLine();
	    if(n.equals(1))
	    {
	    	loginMenu();
	    }
	    else
	    {
	    	System.out.println("Wish to see You back again");
	    	System.exit(0);
	    }
		
		con.close();
	}
	
	//****************************LOGIN MENU ************************
	
	public void loginMenu() throws Exception{
		Scanner sc5=new Scanner(System.in);
		System.out.println("Type of login");
		System.out.println("1:Employee");
		System.out.println("2:User");
		System.out.println("3:Exit");
	String n=sc5.nextLine();
		switch(n)
		{
		case "1":login();
		     
		       break;
		case "2":userLogin();
		       break;
		case "3":System.out.println("Thank you Wish to see u again back ");
		         System.exit(0);
	    default:System.out.println("Plz...Enter proper Option");
                Thread.sleep(2000);
                loginMenu();
        
		}
	}
	public void login() throws Exception {
		System.out.println("enter username");
		Scanner sc6=new Scanner(System.in);
		String st=sc6.nextLine();
		System.out.println("enter password");
		String st1=sc6.nextLine();
		connection();
		ResultSet rs=stmt.executeQuery("select B_id,Designation,User_Name,password from employee_tbl where user_name='"+st+"' and password='"+st1+"' ");
		while (rs.next()) {
            String user =rs.getString("User_Name");         
            String pass =rs.getString("password");
            String deg=rs.getString("Designation");
            bid=rs.getString("B_id");
     if(st.equals(user)&&st1.equals(pass))
		{ 
			System.out.println("welcome "+user);
		     if(deg.equals("Manager"))
		    	 manager();
		     else if(deg.equals("Cashier"))
		          cashier();
		     else if(deg.equals("Clerk"))
		    	 clerk();
		     
		     System.exit(0);
		}
		}
		System.out.println("enter correct username and password");
		login();
		
		con.close();
		}

	public void userLogin() throws Exception{
		
		System.out.println("");
		System.out.print("enter username: ");
		Scanner sc7=new Scanner(System.in);
		String st=sc7.nextLine();
		System.out.print("\nenter password: ");
		String st1=sc7.nextLine();
		connection();
		
		ResultSet rs=stmt.executeQuery("select Acc_id,Account_Number,User_Name,password,PanCard_Number,B_id from customerregistration_tbl where user_name='"+st+"' and password='"+st1+"' ");
		
		while (rs.next()) {
            String user =rs.getString("User_Name");
            String pass =rs.getString("password");
               accountno = rs.getInt("Account_Number");
           String Acc_id=rs.getString("Acc_id");
            b_id=rs.getString("B_id");
            pan=rs.getString("PanCard_Number");
            
        if(st.equals(user)&&st1.equals(pass))
		{ 
        	System.out.println("");
			System.out.println("welcome "+user);
			System.out.println("");
			System.out.println("Account number is:   "+accountno);
			System.out.println("Account_Type code is   "+Acc_id);		 
			transationsMenu();
		}
		}
       System.out.println("enter correct username and password");
	   userLogin();
		con.close();
        }
		

		
	
	//*******************MENU**********************
	
	public void menu() throws Exception
	{
		Scanner sc8=new Scanner(System.in);
		System.out.println("press 1 for Branch option & press 2 for exit");
		String i=sc8.nextLine();
		switch(i)
		{
		case "1":branchMenu();
				break;
		case "2":System.exit(0);
				break;
	    default: System.out.println("plz... enter proper option");
	            menu();
	    
		}
	}
	
	//********************TRANSACTION MENU *******************************
	
	public void transationsMenu() throws Exception
	{
		Scanner sc9=new Scanner(System.in);
		System.out.println("enter your account type ID again for verification");
		String j=sc9.nextLine();
		
		switch(j)
		{
		case "AT01":System.out.println("verification successful");
			        transations();
		            break;
		case "AT02":System.out.println("verification successful");
                    transations();
                    break;
		case "AT03":System.out.println("verification successful");
		             fixedTransation();
                     break;
		case "AT04":System.out.println("verification successful");
                  recTransation();
                    break;
		case "AT05":System.out.println("verification successful");
                    loanTransaction();
                    break;
        default:System.out.println("verification failed try again");
        transationsMenu();
        break;
		}
	}
		
	//***********TRANSATIONS*****************
	
	public void transations() throws SQLException, ClassNotFoundException, InterruptedException {
		Scanner s1=new Scanner(System.in);
		System.out.println("choose  operations you want");
		System.out.println("1:Deposit");
		System.out.println("2:withdraw");
		System.out.println("3:balance enquiry");
		System.out.println("4:TransactionDetails");
		System.out.println("5:Transfer");
		System.out.println("6:Exit");
		String n=s1.nextLine();
		switch(n)
		{
		case "1":System.out.println("you would like to deposit");
		       deposit();
		       break;
		case "2":System.out.println("you would like to withdraw");
		      withdraw();
		      break;
		case "3": System.out.println("balance enquiry");
		       balanceEnquiry();
		        break;
		case "4": System.out.println("Transaction Details");
		          transactionDetails();
		          break;
		case "6": System.out.println("Thank you wish to see u again");
		        System.exit(0);
		        break;
		case "5":System.out.println("Transfer of money");
			transferOfFunds();
			break;
		default:System.out.println("Follow the instructions carefully");
		         transations();
		         break;
                 
		}
		
	}
	//***************DEPOSIT *******************
	
	
	public void deposit() throws SQLException, ClassNotFoundException
	{
		Scanner s2=new Scanner(System.in);
		try{
		connection();
	ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
	
		 System.out.println("Enter the form of deposit");
		 System.out.println("1.Cash");
		 System.out.println("2.Cheque");
		 System.out.println("3.DD");
		 System.out.println("4.Exit");
		 String n=s2.nextLine();
		 switch(n)
		 {
		 case "1":break;
		 case "2":break;
		 case "3":break;
		 case "4":System.out.println("Exited");
		        System.exit(0);
		 default:System.out.println("Kindly follw instructions carefully");
		                 transations();
		 }
		
			while(rs.next()){
				 double mybalance=rs.getDouble("Mybalance");
		System.out.println("enter amount deposit");
		amount=s2.nextDouble();
		
		if(amount>=100000)
		{
			cashierDeposit();
		 mybalance+= amount;
		}
		else 
		{ 
			mybalance+= amount;	
		}
		 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
		 PreparedStatement ps=con.prepareStatement(s);
		 ps.executeUpdate();
		 String amount1="credited:"+amount;
		 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
		 PreparedStatement ps1=con.prepareStatement(s1);
		 ps1.executeUpdate();
		 System.out.println("");
		 System.out.println("your balance is:" +mybalance);
		 branchDeposit();
		 }
}
	catch(Exception e)
	{
	//System.out.println(e);	
	}
	 con.close();
	}
public void branchDeposit() throws Exception
	{
		try{
	connection();
		 ResultSet rs1=stmt.executeQuery("select Cash_details from Branch_tbl where B_id='"+b_id+"'");
		 while(rs1.next())
		 {
		sum2=rs1.getDouble("Cash_details");
		sum2+=amount;
		String s2="update Branch_tbl set Cash_details='"+sum2+"' where B_id='"+b_id+"'";
		 PreparedStatement ps=con.prepareStatement(s2);
		 ps.executeUpdate();
		}
		 menu1();
		 
		}
		
		 catch(Exception e)
		 {
		
		 }
		con.close();
	}
	
	//****************CASHEIR DEPOSIT ******************
	
	public void cashierDeposit() throws InterruptedException, ClassNotFoundException, SQLException {
		Scanner s3=new Scanner(System.in);
		System.out.println("cashier action is activivated");
		System.out.println("");
		System.out.println("enter pan number");
		String st=sc.nextLine();	
		System.out.println("");
		Thread.sleep(1000);
		System.out.println("verifying ");	
		Thread.sleep(3000);
		if(st.equals(pan))
		{
			System.out.println("successful");
		}
		else
		{
			System.out.println("Pancard Mis-match Deposit unsuccessful");
			System.out.println("");
			transations();
		}
		}
	//*************MENU1***********************
	
	 public void menu1() throws ClassNotFoundException, SQLException, InterruptedException
		{
		 Scanner s3=new Scanner(System.in);
			 System.out.println("press 1 for more actions  & exit for 0");
		     String b=s3.nextLine();
			 switch(b)
			 {
			 case "1":transations();
			        System.out.println(" ");
			         break;
			 case "0":System.out.println("Thank you wish to see u again");
			        System.out.println(" ");
			        System.exit(0);
			 default:System.out.println("Follow  the instructions carefully");
			          System.out.println(" ");
			          transations();
			 }
		}
	 //*****************WITHDRAW****************************//
	 public void withdraw() throws ClassNotFoundException, SQLException, InterruptedException
	 {
		 Scanner s4=new Scanner(System.in);
	 	try
	 {
	 	connection();
	 	double mybalance=0;
	 ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
	 while(rs.next())
	 {
		 
		 System.out.println("Enter the form of withdraw");
		 System.out.println("1.Cash");
		 System.out.println("2.Cheque");
		 System.out.println("3.Exit");
		 String n=s4.nextLine();
		 switch(n)
		 {
		 case "1":break;
		 case "2":break;
		 case "3":System.out.println("Exited");
		        System.exit(0);
		 default:System.out.println("Kindly follw instructions carefully");
		                 transations();
		 }
		 
	 mybalance=rs.getDouble("Mybalance");
	 	System.out.println("enter amount withdraw");
	 	amount=s4.nextDouble();
	 	if(amount>mybalance)
	 	{
	 		System.out.println("sorry insufficient funds..in your account");
	 		System.out.println("");
	 		Thread.sleep(2000);
	 		System.out.println("we can try to withdraw again below "+mybalance+" from  your account");
	 		transations();
	 	}
	 	else {
	 		
	 	
	 		if(amount>=50000)
	 	{
	 			cashierWithdraw();
	 		mybalance-= amount;
	 		System.out.println("withdraw successfull");
	 		 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
		 	 PreparedStatement ps=con.prepareStatement(s);
		 	 ps.executeUpdate();
		 	 String amount1="Debited:"+amount;
		 	 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
		 	 PreparedStatement ps1=con.prepareStatement(s1);
		 	 ps1.executeUpdate();
		 	 branchWithdraw();
		 	transations();
	 	}
	 	else 
	 	{ 
	 		mybalance-= amount;
	 		System.out.println("withdraw successfull");
	 		
	 	
	 	 
	 	 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
	 	 PreparedStatement ps=con.prepareStatement(s);
	 	 ps.executeUpdate();
	 	 String amount1="Debited:"+amount;
	 	 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
	 	 PreparedStatement ps1=con.prepareStatement(s1);
	 	 ps1.executeUpdate();
	 	 branchWithdraw();
	 	transations();
	 	 
	 }
	 	}
	 }

	 }
	 
	 catch(Exception e)
	 {
	 }
	 	con.close();
	 }

	 public void branchWithdraw() throws ClassNotFoundException, SQLException
	 {
		 
	 connection();
	 	 ResultSet rs1=stmt.executeQuery("select Cash_details from Branch_tbl where B_id='"+b_id+"'");
	 	 while(rs1.next()){
	 	sub2=rs1.getDouble("Cash_details");
	 	sub2-=amount;
	 	String s2="update Branch_tbl set Cash_details='"+sub2+"' where B_id='"+b_id+"'";
	 	 PreparedStatement ps=con.prepareStatement(s2);
	 	 ps.executeUpdate();
	 	
	 	 }
con.close();
	 }
	 public void cashierWithdraw() throws InterruptedException, ClassNotFoundException, SQLException
	 {
		 Scanner s5=new Scanner(System.in);
	 	System.out.println("cashier action is activivated");
	 	System.out.println("");
	 	System.out.println("enter pan number");
	 	String st=s5.nextLine();	
	 	System.out.println("");
	 	Thread.sleep(1000);
	 	System.out.println("verifying ");		
	 	Thread.sleep(3000);
	 	if(st.equals(pan))
	 	{
	 		System.out.println("successful");
	 	}
	 	else
	 	{
	 		System.out.println("Pancard Mis-match withdraw unsuccessful");
	 		System.out.println("");
	 		transations();
	 	}
	 }
	 
	 //*******************BALANCE INquiry********************
	 
	public  void balanceEnquiry() throws ClassNotFoundException, SQLException, InterruptedException
	 {
	 connection();
	 ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
	 while(rs.next()){
	 	double bal=rs.getDouble("Mybalance");
	 	System.out.println("current your balance is:"+bal);
	 }
	 transations();
	 con.close();
	 }	
	 	
	 //*********FIXED TRANSATIONS*******************
	 
	 public void fixedTransation() throws Exception 
		{
		 try
		 {
			connection();
	     Scanner s6=new Scanner(System.in);
	     System.out.println("Enter account number for verification");
	   int ano=s6.nextInt();
	     if(ano==accountno)
	     {
	    System.out.println("enter principal amount");
	    double P=s6.nextDouble();
	    System.out.println(" Rate of interest is 0.075 percetage");
	       double r=0.075;
	     System.out.println("enter time period");
	     double t=s6.nextDouble();
	      System.out.println("enter years to be compounded");
	      double n=s6.nextDouble();
	      double A,branch_amount=0;
	      double  temp=(1+r/n);  
	      A=P*Math.pow(temp,(n*t));
	     System.out.println("Fixed deposit: "+Math.round(A) );
	     PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");
	   ps1.setDouble(1,A);
	   ps1.setDouble(2,accountno);
	   ps1.executeUpdate();
	   ResultSet rs1=stmt.executeQuery("select Cash_Details from Branch_tbl where B_id='"+b_id+"'");
       while(rs1.next())
       {
    	    branch_amount=rs1.getDouble("Cash_Details");
       }
      branch_amount+=P;
       PreparedStatement ps2=con.prepareStatement("update branch_tbl set Cash_Details=? where B_id='"+b_id+"'");     
        ps2.setDouble(1, branch_amount);
        ps2.executeUpdate();
        PreparedStatement ps3=con.prepareStatement("insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+P+"','"+b_id+"')");
        ps3.executeUpdate();
		}
	     else
	     {
	    	 System.out.println("Autherization failed try again");
	    	 fixedTransation();
	     }
	     System.out.println("For Login PRESS 1 &&&&& For Exit PRESS 0");
			int n=s6.nextInt();
		    if(n==1)
		    {
		    	loginMenu();
		    }
		    else
		    {
		    	System.out.println("Wish to see You back again");
		    	System.exit(0);
		    }
		 }
		 catch(Exception e)
		 {
			 System.out.println("plz enter correct value");
			 fixedTransation();
		 }
	     con.close();
		}	
	 //**************recuring Transactions************
	 
public void recTransation() throws Exception{	   
		        Scanner s7=new Scanner(System.in);
	            System.out.println("Please enter principle amount :");
	            try{
		        Double amountre = s7.nextDouble();
		      
		        System.out.println("Enter time in months : ");
		        int timere = s7.nextInt();
		      
		        System.out.println("the annual rate is 0.085 : ");
		        Double ratere = 0.85;
		          connection();
		          PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");     
		          
			        ps1.setDouble(1,amountre);
				    ps1.setInt(2,accountno);
				    ps1.executeUpdate();   
			      Double interest = simpleInterest(amountre, ratere, timere);
			        double branch_amount=0.0;
			       Double tamount=(amountre)+interest;
			       System.out.println("the total amount is "+tamount);
			       ResultSet rs1=stmt.executeQuery("select Cash_Details from Branch_tbl where B_id='"+b_id+"'");
			       while(rs1.next())
			       {
			    	    branch_amount=rs1.getDouble("Cash_Details");
			       }
			      branch_amount+=amountre;
			       PreparedStatement ps2=con.prepareStatement("update branch_tbl set Cash_Details=? where B_id='"+b_id+"'");     
                    ps2.setDouble(1, branch_amount);
                    ps2.executeUpdate();
                    PreparedStatement ps3=con.prepareStatement("insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amountre+"','"+b_id+"')");
                    ps3.executeUpdate();
                    System.out.println("For Login PRESS 1 &&&&& For Exit PRESS 0");
            		String n=s7.nextLine();
            	    if(n.equals(1))
            	    {
            	    	loginMenu();
            	    }
            	    else 
            	    {
            	    	System.out.println("Wish to see You back again");
            	    	System.exit(0);
            	    }
                    }
		        catch(Exception e)
		        {
		        	System.out.println("Kindly Enter correct value");
		        	recTransation();
		        	
		        }
                    con.close();
                    
                 }
	       public static  Double simpleInterest( Double principle, Double rate, int time){
		        Double interest = (principle*rate*time)/100;
		        System.out.println("interest is" +interest);
				return interest;
		    }


		public void loanTransaction() throws Exception
		{
			Scanner s8=new Scanner(System.in);
			System.out.println("1: Existing loan ");
			System.out.println("2: New loan ");
			String n=s8.nextLine();
			switch(n)
			{
			case "1":loanType1();
			       break;
			case "2":loanType();
			       break;
			default: System.out.println("Go through instructions carefully");    
			         loanTransaction();
			}
		}
		public void loanType1() throws ClassNotFoundException, SQLException, InterruptedException
		{
			Scanner s9=new Scanner(System.in);
			System.out.println("which action do you to perform");
			System.out.println("1:withdraw");
			System.out.println("2:balanceEnquiry");
			System.out.println("3:Transactions Details");
			System.out.println("4:Exit");
			String s1=s9.nextLine();
			switch(s1)
			{
				case "1":withdraw();
				
				         break;
				case "2":balanceEnquiry();
				          break;
				case "3":transactionDetails();
				         break;
				case "4": System.out.println("you are exited");
				          System.exit(0);
				          break;
				  default: System.out.println("Go through instructions carefully");    
				             loanType1();
			}
		}
	public void loanType() throws Exception 
	{
		try{
			Scanner s10=new Scanner(System.in);
			connection();				  		  
	         System.out.println("Please enter loan amount :");
	        Double amount = s10.nextDouble();	      
	        System.out.println("Enter time in months : ");
	        int time = s10.nextInt();	      
	        System.out.println("the annual rate is 0.010 : ");
	        Double rate = 0.85,branch_amount=0.0;        
	        Double interest = simpleInterest1(amount, rate, time);
	        PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");
		     ps1.setDouble(1,amount);
		     ps1.setInt(2,accountno);
		    ps1.executeUpdate();
		    ResultSet rs1=stmt.executeQuery("select Cash_Details from Branch_tbl where B_id='"+b_id+"'");
		       while(rs1.next())
		       {
		    	    branch_amount=rs1.getDouble("Cash_Details");
		       }
		      branch_amount-=amount;
		       PreparedStatement ps2=con.prepareStatement("update branch_tbl set Cash_Details=? where B_id='"+b_id+"'");     
		        ps2.setDouble(1, branch_amount);
		        ps2.executeUpdate();
	       Double tamount=(amount)+interest;
	       System.out.println("the total amount is "+tamount);
			}
		 catch(Exception e){    	
	    	System.out.println(e);
		 }
		loginMenu();
		con.close();
		}
	public static Double simpleInterest1( Double principle, Double rate, int time)
	{
				        Double interest = (principle*rate*time)/100;
				        System.out.println("annual interest is" +interest);
						return interest;
				    }
	public void manager() throws Exception
	{
		Scanner s11=new Scanner(System.in);
		System.out.println("Manager Action is activate");
		Thread.sleep(3000);
		System.out.println("which details you want to check");
		System.out.println("1:Employee");
		System.out.println("2:User");
		System.out.println("3:Bank balance");
		System.out.println("4:Exit");
		
		String n=s11.nextLine();
		switch(n)
		{
		case "1":employee();
		       break;
		case "2":user();
	           break;
		case "3":balanceEnquiryManager();
		       break;
		case "4":System.out.println("your are exited");
			   System.exit(0);
			   startMenu();
		default:System.out.println("Plz...Enter proper Option");
                Thread.sleep(2000);
                manager();
       }
		
}
	
	public void balanceEnquiryManager() throws Exception {
		Scanner s12=new Scanner(System.in);
	
		System.out.println("Manager Action is activate");
		Thread.sleep(3000);
		System.out.println(bid);
		connection();
      System.out.println("Enter branch code for verification");
      //String bid1=sc.nextLine();
		ResultSet rs=stmt.executeQuery("select * from Branch_tbl where B_id='"+bid+"'");
        ResultSetMetaData rsmd=rs.getMetaData();
        
        
        int c=rsmd.getColumnCount();
        for(int i=1;i<=c;i++)
        {
         	System.out.print(rsmd.getColumnName(i)+"    ");
         	
        }
         System.out.println();
         System.out.println(" ");
     	
         while(rs.next())
         	{
        	 if(rs.getString("B_id").equals(bid))
             {
         		
         		System.out.print(rs.getString(1)+",   "+rs.getString(2)+",  "+rs.getString(3)+",  "+rs.getString(4));
               }
        	 else{
             	System.out.println("There is no such user available");
             }
         	System.out.println();
         	}
        System.out.println("you want to exit y/n");
        String s1=s12.nextLine();
        if(s1.equals("y")||s1.equals("Y"))
        {
        	System.out.println("you are exited,please login you want");
        	loginMenu();
        	System.exit(0);
        }
        else if(s1.equals("n")||(s1.equals("N")))
        {
        	manager();
        	
        }
    con.close();     	
	}
private void user() throws Exception {
	Scanner s13=new Scanner(System.in);
		System.out.println("Manager Action is activate");
		Thread.sleep(3000);
		connection();
        System.out.println("Enter account number");
        String accno=s13.nextLine();
        
		ResultSet rs=stmt.executeQuery("select Account_Number,B_id,Acc_id,Account_Name,Mybalance,PanCard_Number from customerregistration_tbl where Account_Number='"+accno+"'");
        ResultSetMetaData rsmd=rs.getMetaData();
        int c=rsmd.getColumnCount();
        
        for(int i=1;i<=c;i++)
       {
        	System.out.print(rsmd.getColumnName(i)+":");
        	
       }
        System.out.println();
        System.out.println(" ");
    	
        while(rs.next())
        	{
        	
                 if(rs.getString("B_id").equals(bid))
                 {
         
            		 System.out.print(rs.getString(1)+",  "+rs.getString(2)+",  "+rs.getString(3)+",  "+rs.getString(4)+",  "+rs.getDouble(5)+",  "+rs.getString(6));
                    
                    }
                    else{
                    	System.out.println("There is no such user available");
                    }
        	}
                  
            	System.out.println();      	
        System.out.println("you want to exit y/n");
        String s1=s13.nextLine();
        if(s1.equals("y")||s1.equals("Y"))
        {
        	System.out.println("you are exited,please login you want");
        	loginMenu();
        	System.exit(0);
        }
        else if(s1.equals("n")||(s1.equals("N")))
        {
        	manager();
        	
        }
        	
        con.close();
}
	public void employee() throws Exception
	{
		Scanner s14=new Scanner(System.in);
		System.out.println("Manager Action is activate");
		Thread.sleep(3000);
		connection();        
		System.out.println("Enter Employee id");
        se=s14.nextLine();
        
       
       ResultSet rs=stmt.executeQuery("select E_ID,B_id,E_Name,Designation from employee_tbl where E_id='"+se+"'");
        ResultSetMetaData rsmd=rs.getMetaData();
        int c=rsmd.getColumnCount();
        System.out.println(bid);
       
        for(int i=1;i<=c;i++)
       {
        	System.out.print(rsmd.getColumnName(i)+"  ");
        	
       }
        System.out.println();
        System.out.println(" ");
    	
        while(rs.next())
        	{
        	 
                if(rs.getString("B_id").equals(bid)){
        		 System.out.print(rs.getString(1)+",  "+rs.getString(2)+",  "+rs.getString(3)+",  "+rs.getString(4));
               
                }
                else{
                	System.out.println("There is no such employee available");
                	
                
                }
        
        	}
        	System.out.println();
        	
        System.out.println("you want to exit y/n");
        String s1=s14.nextLine();
        if(s1.equals("y")||s1.equals("Y"))
        {
        	System.out.println("you are exited,please login you want");
        	loginMenu();
        	System.exit(0);
        }
        else if(s1.equals("n")||(s1.equals("N")))
        {
        	manager();
        	
        }
        user();
        con.close();
	}
	//*********************CASHEIR < CLERK ***************************
	
	public void cashier() throws Exception
	{
		Scanner s15=new Scanner(System.in);
		System.out.println("cashier action is activated");
		Thread.sleep(3000);
		connection();
        ResultSet rs=stmt.executeQuery("select B_name,Cash_details from branch_tbl where B_id='"+bid+"'");
        ResultSetMetaData rsmd=rs.getMetaData();
        int c=rsmd.getColumnCount();
        for(int i=1;i<=c;i++)
        {
         	System.out.print(rsmd.getColumnName(i)+"    ");
         	
        }
         System.out.println();
         System.out.println(" ");
     	
         while(rs.next())
         	{
        	 if(rs.getString("B_id").equals(bid))
        	 {
         		
         		System.out.print(rs.getString(1)+",  "+rs.getDouble(2));
         		
               }
        	 else{
             	System.out.println("There is no such branch details ");
             	
             
             }
         	System.out.println();
         	}
        System.out.println("you want to exit y/n");
        String s1=s15.nextLine();
        if(s1.equals("y")||s1.equals("Y"))
        {
        	System.out.println("you are exited,please login you want");
        	loginMenu();
        	System.exit(0);
        }
        else if(s1.equals("n")||(s1.equals("N")))
        {
        	exit();
        	
        }
		con.close();
	}
	private void exit() {
		System.out.println("you are exited");
		System.exit(0);
}

	public void clerk() throws Exception
	{
		Scanner s16=new Scanner(System.in);
		System.out.println("clerk action is activated");
		connection();
		System.out.println("enter account type");
		String acctype=s16.nextLine();
		ResultSet rs=stmt.executeQuery("select Account_name,Acc_id,Account_Number from customerregistration_tbl where Acc_id='"+acctype+"'");
	    ResultSetMetaData rsmd=rs.getMetaData();
        int c=rsmd.getColumnCount();
        
        for(int i=1;i<=c;i++)
       {
        	System.out.print(rsmd.getColumnName(i)+"    ");
        	
       }
        System.out.println();
        System.out.println(" ");
    	
        while(rs.next())
        	{ if(rs.getString("B_id").equals(bid))
       	 {
        	 
        		System.out.print(rs.getString(1)+",  "+rs.getString(2)+",  "+rs.getString(3));
        		
              }
        else{
         	System.out.println("There is no such branch details ");
         }
        	System.out.println();
        	}
        System.out.println("you want to exit y/n");
        String s1=s16.nextLine();
        if(s1.equals("y")||s1.equals("Y"))
        {
        	System.out.println("you are exited,please login you want");
        	loginMenu();
        	System.exit(0);
        }
        else if(s1.equals("n")||(s1.equals("N")))
        {
        	clerk();
        	
        }
	con.close();
	}
public void lockers() throws ClassNotFoundException, SQLException {
	Scanner s17=new Scanner(System.in);
		connection();
		System.out.println("Do u wish to have Locker y/n");
		  l=s17.nextLine();
		switch(l)
		
		{
		case "y":System.out.println("Your request is considered ... we will once inform you after processing done");
		PreparedStatement ps7=con.prepareStatement("update customerregistration_tbl set Locker='y' where Account_Number='"+ab+"'");
		ps7.executeUpdate();
		         break;
		case "n":System.out.println("Soon see you back with locker request");
		PreparedStatement ps8=con.prepareStatement("update customerregistration_tbl set Locker='N' where Account_Number='"+ab+"'");
		ps8.executeUpdate();
		         break;
		 default:System.out.println("Enter correct option");
		         lockers();
		}
con.close();		
}
public void transactionDetails() throws ClassNotFoundException, SQLException, InterruptedException
{
	connection();
	ResultSet rs3=stmt.executeQuery("select * from transactions_tbl where Account_Number='"+accountno+"'");
	 ResultSetMetaData rsmd=rs3.getMetaData();
	  int c=rsmd.getColumnCount();
	  System.out.println(accountno);
     
      for(int i=1;i<=c;i++)
     {
      	System.out.print(rsmd.getColumnName(i)+"  ");
      	
     }
      System.out.println("");
      while(rs3.next())
    	  
      {
    	  //if(rs.getString("Account_Number").equals(accountno))
	 System.out.println(rs3.getString(1)+",   "+rs3.getString(2)+",   "+rs3.getString(3)+",  "+rs3.getString(4)+",  "+rs3.getObject(5)+",  ");
    	  }
      System.out.println("");
      transations();
      con.close();
 }


private void transferOfFunds() throws SQLException, ClassNotFoundException, InterruptedException {
	// TODO Auto-generated method stub
	try{


	Scanner s=new Scanner(System.in);
	 connection(); 
	boolean acno=true;
	double amount;
	System.out.println("enter To user account number");
    String n1=s.nextLine();
    
    System.out.println("enter To Account branch id");
    String st2=s.nextLine();
     
    System.out.println("enter amount to transfer");
    amount=s.nextDouble();
    ResultSet rs= stmt.executeQuery("select Account_Number from customerregistration_tbl");
    //System.out.println(b_id);  
    //int count = 1;
     
      while (rs.next()) {
        
       int i=1;
        
        while(i<=6)
	     {
	    	acno=((rs.getString(i)).equals(n1));
	    	System.out.println(acno); 
	     }
        i++;
        System.out.println(b_id);
      }
	if(acno==true)
	  {
	    PreparedStatement ps=con.prepareStatement("call sp_transfor3(?,?,?,?,?)");
	    ps.setInt(1, accountno);
	    ps.setString(2, n1);
	    ps.setDouble(3,amount);
	  
	   ps.setString(5,b_id);
	   ps.setString(4,st2);
	    ps.executeUpdate();
	   System.out.println("Transfer successfully");
	   transations();
	  }
	  /*else if(acno==false)
	  {
	  System.out.println("not");
	  }*/
	}
	catch(Exception e)
	{
		System.out.println(e);
		transations();
	}
	
	
	 con.close();  
     

}
}

  
      	           
      	           
	




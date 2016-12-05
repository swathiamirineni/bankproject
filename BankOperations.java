package kbr.banking;

import java.util.*;
import java.lang.*;
import java.sql.*;

 class BankOperations implements BankInterface  {
	Scanner sc=new Scanner(System.in);
	
	String br,at;
	int accountno;
	String b_id;
	double sum2,sub2;
	double amount;
	public void startMenu() throws Exception   
	{
	
		System.out.println("Welcome to OUR BANK");
		System.out.println("1:Existing User");
		System.out.println("2:New User");
		System.out.println("3:Exit");
		System.out.println("select one option");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:loginMenu();
		       break;
		case 2:branchMenu();
		       break;
		case 3:System.out.println("Exited");
	           System.exit(0);
		       break;
		default:System.out.println("PLZZZZZ... ENTER PROPER OPTION");
		        Thread.sleep(2000);
		        System.out.println("");
		        startMenu();
                
		}
	}
	
	public void branchMenu() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
		
		System.out.println("Enter Branch where U wish to have an Account");
		System.out.println("B01:kukatpally");
		System.out.println("B02:lingampally");
		System.out.println("B03:Ameerpet");
		br=sc.next();
		switch(br)
		{
		case "B01":accountTypeMenu();
		           break;
		case "B02":accountTypeMenu();
		           break;
		case "B03":accountTypeMenu(); 
		           break;
		default:menu();
		}
	
		con.close();
	}	
	public void accountTypeMenu() throws Exception {
		System.out.println("Enter the Type of Accout to have:");
		System.out.println("AT01:Saving Type:Rs.500");
		System.out.println("AT02:Current Type:Rs.0");
		System.out.println("AT03:Fixed Type:Rs.1000");
		System.out.println("AT04:Recurring Type:Rs.500");
		System.out.println("AT05:Loan Type:Rs.1500");
		at=sc.next();
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
	public void details() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
		
		PreparedStatement ps=con.prepareStatement("insert into customerregistration_tbl(B_id,Acc_id,Account_Name,User_Name,password) values(?,?,?,?,?)"); 
		ps.setString(1,br );
		ps.setString(2,at);
		System.out.print("Enter Account Name: ");
		ps.setString(3,sc.next() );
		System.out.print("Enter (for Login) User Name: ");
		String us=sc.next();
		ps.setString(4,us );
		System.out.print("Enter Password: ");
		
		ps.setString(5,sc.next() );
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
		System.out.println("AccountNumber: "+rs.getInt(1));
		Thread.sleep(1000);
		System.out.println("Account Name: "+rs.getString(4));
		Thread.sleep(1000);
		System.out.println("Branch ID: "+rs.getString(2));
		Thread.sleep(1000);
		System.out.println("AccountType ID: "+rs.getString(3));
		Thread.sleep(1000);
		System.out.println("Your Present Balance: "+rs.getDouble(7));
		}
        Thread.sleep(2000);
        System.out.println("");
		System.out.println("Enjoy our Services");
		 Thread.sleep(3000);
		System.out.println("For Login PRESS 1 &&&&& For Exit PRESS 0");
		int n=sc.nextInt();
	    if(n==1)
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

	
	public void loginMenu() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Type of login");
		System.out.println("1:Employee");
		System.out.println("2:User");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:login();
		     
		       break;
		case 2:userLogin();
		      
	           break;
	    default:System.out.println("Plz...Enter proper Option");
                Thread.sleep(2000);
                loginMenu();
        
		}
	}
	public void login() throws SQLException,ClassNotFoundException {
		System.out.println("enter username");
		Scanner s=new Scanner(System.in);
		String st=s.nextLine();
		System.out.println("enter password");
		String st1=s.nextLine();
		Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select Designation,User_Name,password from employee_tbl where user_name='"+st+"' and password='"+st1+"' ");
		while (rs.next()) {
            String user =rs.getString("User_Name");         
            String pass =rs.getString("password");
            String deg=rs.getString("Designation");
		
           /* if(!st.equals(user)&&st1.equals(pass))
    		{
    			System.out.println("enter correct username and password");
    			login();
    		}*/
    		
       
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
		}
	
	public void userLogin() throws Exception{
		System.out.println("check");
		System.out.println("enter username");
		Scanner s=new Scanner(System.in);
		String st=s.nextLine();
		System.out.println("enter password");
		String st1=s.nextLine();
		Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
//System.out.println("connected");
Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select Acc_id,Account_Number,User_Name,password,B_id from customerregistration_tbl where user_name='"+st+"' and password='"+st1+"' ");
		
		while (rs.next()) {
            String user =rs.getString("User_Name");
            String pass =rs.getString("password");
              accountno=rs.getInt("Account_Number");
            String Acc_id=rs.getString("Acc_id");
            b_id=rs.getString("B_id");
            
        if(st.equals(user)&&st1.equals(pass))
		{ 
			System.out.println("welcome "+user);
			System.out.println("Account number is   "+accountno);
			System.out.println("Account number is   "+Acc_id);
			System.out.println(b_id);
			transations();
			System.exit(0);
			rs.close();
		}
       
		}
		System.out.println("enter correct username and password");
	userLogin();
		
	}
		
	

	public void activityMenu() {
	}
	
public void clerk() {
		// TODO Auto-generated method stub
		System.out.println("clerk");
	}

public void cashier() {
		// TODO Auto-generated method stub
		System.out.println("casheir");
	}

public void manager() {
		// TODO Auto-generated method stub
		System.out.println("manger");
	}

 
	public void transations() throws SQLException, ClassNotFoundException {
		System.out.println("chose one operations you want");
		System.out.println("1:Deposit");
		System.out.println("2:withdraw");
		System.out.println("3:balance enquiry");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:System.out.println("you would like to deposit");
		       deposit();
		       break;
		case 2:System.out.println("you would like to withdraw");
		      withdraw();
		      break;
		case 3: System.out.println("balance enquiry");
		        balanceEnquiry();
		        break;
		default:System.out.println("Exited");
                 System.exit(0);
		}
		
		
	}
	public void menu() throws Exception
	{
		System.out.println("press 1 for Branch option & press 2 for exit");
		int i=sc.nextInt();
		switch(i)
		{
		case 1:branchMenu();
				break;
		case 2:System.exit(0);
				break;
	    default: System.out.println("plz... enter proper option");
	            menu();
	    
		}
	}
public void deposit() throws SQLException, ClassNotFoundException
{
	try{
	System.out.println("check"+accountno);
	Class.forName("com.mysql.jdbc.Driver");
	
	//String bal="select Mybalance from customerregistration_tbl where Account_Number="+accountno;
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
	System.out.println("connected");
ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
while(rs.next()){
double mybalance=rs.getDouble("Mybalance");
	System.out.println("enter amount deposit");
	amount=sc.nextDouble();
	 mybalance+= amount;
	 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
	 PreparedStatement ps=con.prepareStatement(s);
	 ps.executeUpdate();
	 String amount1="credited:"+amount;
	 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
	 PreparedStatement ps1=con.prepareStatement(s1);
	 ps1.executeUpdate();
	 branchDeposit();
	 rs.close();
	 con.close();
}
	}
catch(Exception e)
{
//System.out.println(e);	
}
}

public void branchDeposit() throws Exception
{
	try{
Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
	 ResultSet rs1=stmt.executeQuery("select Cash_details from Branch_tbl where B_id='"+b_id+"'");
	 while(rs1.next()){
	sum2=rs1.getDouble("Cash_details");
	 System.out.println("checksss");
	sum2+=amount;
	String s2="update Branch_tbl set Cash_details='"+sum2+"' where B_id='"+b_id+"'";
	 PreparedStatement ps=con.prepareStatement(s2);
	 ps.executeUpdate();
	 con.close();
	 }
	 System.out.println("press 1 for main menu & exit for 0");
	 
	}
	 catch(Exception e)
	 {
	// System.out.println(e);	
	 }
	 startMenu();
}


public void withdraw() throws ClassNotFoundException, SQLException
{
	System.out.println("check"+accountno);
	Class.forName("com.mysql.jdbc.Driver");
	
	//String bal="select Mybalance from customerregistration_tbl where Account_Number="+accountno;
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
	System.out.println("connected");
ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
while(rs.next()){
double mybalance=rs.getDouble("Mybalance");
	System.out.println("enter amount withdraw");
	amount=sc.nextDouble();
	 mybalance-= amount;
	 System.out.println(mybalance);
	 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
	 PreparedStatement ps=con.prepareStatement(s);
	 ps.executeUpdate();
	 String amount1="Debited:"+amount;
	 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
	 PreparedStatement ps1=con.prepareStatement(s1);
	 ps1.executeUpdate();
	 branchWithdraw();
	 rs.close();
	 con.close();
}
}
public void branchWithdraw() throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
	 ResultSet rs1=stmt.executeQuery("select Cash_details from Branch_tbl where B_id='"+b_id+"'");
	 while(rs1.next()){
	sub2=rs1.getDouble("Cash_details");
	sub2-=amount;
	String s2="update Branch_tbl set Cash_details='"+sub2+"' where B_id='"+b_id+"'";
	 PreparedStatement ps=con.prepareStatement(s2);
	 ps.executeUpdate();
	 con.close();
	 }
}
public  void balanceEnquiry() throws ClassNotFoundException, SQLException
{
	System.out.println("check"+accountno);
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
	System.out.println("connected");
ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
while(rs.next()){
	double bal=rs.getDouble("Mybalance");
	System.out.println("your balance is:"+bal);
}
}	
	public void fixedTransation() {
		
		
	}
	public void LoanTransation2()
	{
		
	}

	@Override
	public void fixedTransation2() {
		// TODO Auto-generated method stub
		
	}
	
	}
		
		
	



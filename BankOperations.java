package kbr.banking;

import java.util.*;
import java.lang.*;
import java.sql.*;

  class BankOperations implements BankInterface  {
	Scanner sc=new Scanner(System.in);
	
	String br,at,l,ab;
	int accountno;
	String b_id,pan;
	double sum2,sub2;
	double amount;
	public void startMenu() throws Exception   
	{
	
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
		String n=sc.next();
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
		default:System.out.println("Follow Instructions carefully...Only given values are valid");
			     menu();
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
		
		PreparedStatement ps=con.prepareStatement("insert into customerregistration_tbl(B_id,Acc_id,Account_Name,User_Name,password,PanCard_Number) values(?,?,?,?,?,?)"); 
		ps.setString(1,br );
		ps.setString(2,at);
		System.out.print("Enter Account Name: ");
		ps.setString(3,sc.next() );
		System.out.print("Enter (for Login) User Name: ");
		String us=sc.next();
		ps.setString(4,us );
		System.out.print("Enter Password: ");
		ps.setString(5,sc.next() );
		System.out.println("Enter pan number");
		String p=sc.next();	
		
		
		int p1=p.length();
		if(p1==10)
		{
			ps.setString(6,p);
		}
		else{
			System.out.println("pan limit is: 10");
			System.out.println("Enter pan number... you have only two chances left ");
			String p2=sc.next();	
			int p3=p2.length();
			if(p3==10)
			{
				ps.setString(6,p2);
			}
			else{
				System.out.println("pan limit is: 10");
				System.out.println("Enter pan number.... you have only one chance left");
				String p4=sc.next();	
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
				int n=sc.nextInt();
				switch(n)
				{
				case 1:loginMenu();
				       break;
				case 0:System.out.println("Thank u wish to see u again");
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
	public void login() throws SQLException,ClassNotFoundException, InterruptedException {
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
		System.out.println("");
		System.out.print("enter username: ");
		Scanner s=new Scanner(System.in);
		String st=s.next();
		System.out.print("\nenter password: ");
		String st1=s.next();
		Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
//System.out.println("connected");
Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select Acc_id,Account_Number,User_Name,password,PanCard_Number,B_id from customerregistration_tbl where user_name='"+st+"' and password='"+st1+"' ");
		
		while (rs.next()) {
            String user =rs.getString("User_Name");
            String pass =rs.getString("password");
              accountno=rs.getInt("Account_Number");
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
        
        else
        {
	      System.out.println("enter correct username and password");
	      userLogin();
        }
		}
	
		
	con.close();
		
	}
	public void transationsMenu() throws ClassNotFoundException, SQLException, InterruptedException
	{
		System.out.println("enter your account type ID again for verification");
		String j=sc.next();
		
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
		}
	}
		
	

	
	
public void clerk() {
		// TODO Auto-generated method stub
		System.out.println("clerk");
	}

public void cashierDeposit() throws InterruptedException, ClassNotFoundException, SQLException {
	System.out.println("cashier action is activivated");
	System.out.println("");
	System.out.println("enter pan number");
	String st=sc.next();	
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
public void cashierWithdraw() throws InterruptedException, ClassNotFoundException, SQLException
{
	System.out.println("cashier action is activivated");
	System.out.println("");
	System.out.println("enter pan number");
	String st=sc.next();	
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
public void manager() {
		// TODO Auto-generated method stub
		System.out.println("manger");
	}

 
	public void transations() throws SQLException, ClassNotFoundException, InterruptedException {
		System.out.println("choose  operations you want");
		System.out.println("1:Deposit");
		System.out.println("2:withdraw");
		System.out.println("3:balance enquiry");
		System.out.println("4:Exit");
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
		case 4: System.out.println("Thank you wish to see u again");
		        System.exit(0);
		default:System.out.println("Follow the instructions carefully");
		         transations();
                 
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
	 public void menu1() throws ClassNotFoundException, SQLException, InterruptedException
	{
		 System.out.println("press 1 for more actions  & exit for 0");
	     int b=sc.nextInt();
		 switch(b)
		 {
		 case 1:transations();
		        System.out.println(" ");
		         break;
		 case 0:System.out.println("Thank you wish to see u again");
		        System.out.println(" ");
		        System.exit(0);
		 default:System.out.println("Follow  the instructions carefully");
		          System.out.println(" ");
		          transations();
		 }
	}
public void deposit() throws SQLException, ClassNotFoundException
{
	try{
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
while(rs.next()){
double mybalance=rs.getDouble("Mybalance");
	System.out.println("enter amount deposit");
	amount=sc.nextDouble();
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
	 while(rs1.next())
	 {
	sum2=rs1.getDouble("Cash_details");
	sum2+=amount;
	String s2="update Branch_tbl set Cash_details='"+sum2+"' where B_id='"+b_id+"'";
	 PreparedStatement ps=con.prepareStatement(s2);
	 ps.executeUpdate();
	}
	 menu1();
	 con.close();
	}
	
	 catch(Exception e)
	 {
	// System.out.println(e);	
	 }
	
}


public void withdraw() throws ClassNotFoundException, SQLException, InterruptedException
{
	try
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select Mybalance from customerregistration_tbl where Account_Number='"+accountno+"'");
while(rs.next())
{
double mybalance=rs.getDouble("Mybalance");
	System.out.println("enter amount withdraw");
	amount=sc.nextDouble();
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
		transations();
	}
	else 
	{ 
		mybalance-= amount;
		System.out.println("withdraw successfull");
		transations();
	}
	}
	 System.out.println("your balance is:"+mybalance);
	 String s="update customerregistration_tbl set Mybalance='"+mybalance+"' where Account_Number='"+accountno+"'";
	 PreparedStatement ps=con.prepareStatement(s);
	 ps.executeUpdate();
	 String amount1="Debited:"+amount;
	 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount1+"','"+b_id+"')";
	 PreparedStatement ps1=con.prepareStatement(s1);
	 ps1.executeUpdate();
	 branchWithdraw();
	 con.close();
}
}
catch(Exception e)
{
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
	public void fixedTransation() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
     Scanner s=new Scanner(System.in);
     System.out.println("Enter account number for verification");
   int ano=sc.nextInt();
     if(ano==accountno)
     {
    System.out.println("enter principal amount");
    double P=s.nextDouble();
    System.out.println(" Rate of interest is 0.075 percetage");
       double r=0.075;
     System.out.println("enter time period");
     double t=s.nextDouble();
      System.out.println("enter years to be compounded");
      double n=s.nextDouble();
      double A;
      double  temp=(1+r/n);  
      A=P*Math.pow(temp,(n*t));
      System.out.println("Fixed deposit: "+Math.round(A) );
      PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");
   ps1.setDouble(1,A);
   ps1.setDouble(2,accountno);
	}
     else
     {
    	 System.out.println("Autherization failed try again");
    	 fixedTransation();
     }
	}	
	public void recTransation(){
	}
	
	
	
	public void loanTransaction() throws ClassNotFoundException, SQLException, InterruptedException
	{
		System.out.println("1: Existing loan ");
		System.out.println("2: New loan ");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:loanType1();
		       break;
		case 2:loanType();
		       break;
		default: System.out.println("Go through instructions carefully");    
		         loanTransaction();
		}
	}
	public void loanType1() throws ClassNotFoundException, SQLException, InterruptedException
	{
		System.out.println("Do you want to withdraw y/n");
		String s1=sc.next();
		switch(s1)
		{
			case "y":withdraw();
			
			         break;
			case "n":
			          balanceEnquiry();
			           break;
			  default: System.out.println("Go through instructions carefully");    
			             loanType1();
		}
	}
	public void loanType() {
		try{
			
		    Class.forName("com.mysql.jdbc.Driver");

		    Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
		    
		     Scanner sc = new Scanner(System.in);
	         System.out.println("Please enter loan amount :");
		        Double lamount = sc.nextDouble();
		        PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");
			     ps1.setDouble(1,lamount);
				    ps1.setInt(2,accountno);
				    ps1.executeUpdate(); 
				    String amount="Loan:"+lamount;
					 String s1="insert into transactions_tbl(Account_Number,Balance,B_id)values('"+accountno+"','"+amount+"','"+b_id+"')";
					 PreparedStatement ps2=con.prepareStatement(s1);
					 ps2.executeUpdate();
		        System.out.println("Enter time in months : ");
		        int time = sc.nextInt();
		      
		        System.out.println("the annual rate is 0.010 : ");
		        Double rate = 0.85;
		        
		        
		        Double interest = simpleInterest(lamount, rate, time);
		      
		       Double tamount=(lamount)+interest;
		   
		       System.out.println("the total amount is "+tamount);
		       System.out.println("");
		       Thread.sleep(3000);
		       System.out.println("verfying....");
		       Thread.sleep(2000);
		       System.out.println("");
		       System.out.println("Manager action is activated");
		       Thread.sleep(3000);
		       System.out.println("Loan Granted successfully");
		       System.out.println("Press 1 to continue && 0 to exit");
		       int p=sc.nextInt();
		       switch(p)
		       {
		       case 1:loanType1();
		               break;
		       case 0:System.out.println("Thank u...Wish to see u back again");
		              System.exit(0);
		       default:System.out.println("Follow instructions carefully");
		              System.out.println("Press 1 to continue && 0 to exit");
		               p=sc.nextInt();
		       
		}
		}
		
		 catch(Exception e){
		    	
		    	System.out.println(e);
		 }
		    }

		  
		    public static Double simpleInterest( Double principle, Double rate, int time){
		        Double interest = (principle*rate*time)/100;
		        System.out.println("annual interest is" +interest);
				return interest;
		    }

	
		
		public double calcMInterest(  double interest )
		{
		    double mInterest;

		    mInterest = (interest / 12);

		    return mInterest; 

		}
		public  double calcMPayment (double mInterest, double lamount, int time)
		{
		    double mPayment;
		    mPayment = (mInterest * lamount)*time;

		    return mPayment;
		}
		public void loanStatement(double lamount, double interest, int time, double mPayment)
		{
		   System.out.println(" time is" +time);
		   
		}
	
	
	@Override
	public void cashier() {
		// TODO Auto-generated method stub
		System.out.println("cahier");
	}

	public void lockers() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
		System.out.println("Do u wish to have Locker y/n");
		  l=sc.next();
		switch(l)
		
		{
		case "y":System.out.println("Your request is considered ... we will once inform you after processing done");
		PreparedStatement ps7=con.prepareStatement("update customerregistration_tbl set Locker='y' where Account_Number='"+ab+"'");
		ps7.executeUpdate();
		         break;
		case "n":System.out.println("Soon see you back with locker request");
		         break;
		 default:System.out.println("Enter correct option");
		         lockers();
		}
		
		
	}
	
	}
		
		
	
 
 
 
 
 



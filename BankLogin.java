package kbr.banking;

import java.sql.*;
import java.util.Scanner;

public class BankLogin  implements BankOperation {

	int accountno;
	
	
	@Override
	public void startMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void branchMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountTypeMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activityMenu() {
		// TODO Auto-generated method stub

	}
	public void loginMenu() throws ClassNotFoundException, SQLException, InterruptedException{
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Type of login");
		System.out.println("1:Employee");
		System.out.println("2:User");
		System.out.println("3.Exit");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:login();
		       break;
		case 2:userLogin();
	           break;
		case 3:System.out.println("you are exited,please login");
		       loginMenu();
		default:System.out.println("Plz...Enter proper Option");
                Thread.sleep(2000);
                loginMenu();
        
		}
		}
		catch(Exception e)
		{
			System.out.println("enter correct input");
			loginMenu();
		}
	}

	@Override	
	
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
	
      
	

	@Override
	public void registration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void transactions() throws ClassNotFoundException, SQLException, InterruptedException {
		System.out.println("for deposite");
		
		loginMenu();

	}
	public void manager() throws InterruptedException
	{
		System.out.println("Manager Action is activate");
		Thread.sleep(3000);
		System.out.println("Verification is going on...Plz wait");
		Thread.sleep(3000);
		System.out.println("Verifying User Details");
		
	}
	public void cashier()
	{
		System.out.println("cashier");
	}
	public void clerk()
	{
		System.out.println("clerk");
	}
	public void userLogin() throws ClassNotFoundException, SQLException, InterruptedException{
		
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
		
		ResultSet rs=stmt.executeQuery("select Account_name,Acc_id,Account_Number,User_Name,password from customerregistration_tbl where user_name='"+st+"' and password='"+st1+"' ");
		
		while (rs.next()) {
            String user =rs.getString("User_Name");
            String pass =rs.getString("password");
            accountno=rs.getInt("Account_Number");
            String acc_id=rs.getString("Acc_id");
            String name=rs.getString("Account_name");
            
        if(st.equals(user)&&st1.equals(pass))
		{ 
			System.out.println("welcome "+name);
			System.out.println("Account number is   "+accountno);
			System.out.println("Account type is   "+acc_id);
			
			if(acc_id.equals("AT01"))
				transactions();
			else if(acc_id.equals("AT02"))
				transactions();
			else if(acc_id.equals("AT03"))
				fixedTransaction();
			else if(acc_id.equals("AT04"))
				recuType();
			else if(acc_id.equals("AT05"))
				loanType();
			System.exit(0);
		}
        
		}
		System.out.println("enter correct username and password");
		userLogin();
		loanType();
		}
	
	
	public void fixedTransaction()
	{
	System.out.println("fixed");	
	}
public void	recuType(){System.out.println("rec");}
public void loanType()
{
	System.out.println("loan");
	 double interest; 
	    double mInterest;
	    int time;
	    double amount; 
	    double mPayment;
	    double loan;
	    try
	    {
	    Scanner s= new Scanner(System.in);
	    System.out.println("Hi, Please enter the loan amount here:");
	    amount = s.nextDouble();

	    System.out.println("Thanks,the annual interest rate in this bank is 10.45"); 
	            
	    interest =0.10;

	    System.out.println("now please put in the number of months the loan was taken out for");
	    time = s.nextInt();
	    
	    mInterest = calcMInterest( interest );
	    mPayment = calcMPayment (mInterest, amount, time);
	    System.out.println("my amount is"+amount);
	    System.out.println("monthlyinterest is"+mInterest);
	    System.out.println("no of months "+time);
	    System.out.println("month interest"+mPayment);
	    Class.forName("com.mysql.jdbc.Driver");

	    Connection con=DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrbankdb?user=sysbank&password=@sys123");
	    
	   // String query="update customerregistration_tbl set Mybalance='+amount+' where Account_Number='+accountno+'";
	    
	    PreparedStatement ps1=con.prepareStatement("update customerregistration_tbl set Mybalance=? where Account_Number=?");
	    /*System.out.println(amount);
	    System.out.println(accountno);*/
	    ps1.setDouble(1,amount);
	    ps1.setInt(2,accountno);
	    ps1.executeUpdate();
	    }
	    catch(Exception e){
	    	//System.out.println("enter correct value");
	    	//loanType();
	    	System.out.println(e);
	    }
	    
}
	
	public double calcMInterest(  double interest )
	{
	    double mInterest;

	    mInterest = (interest / 12);

	    return mInterest; 

	}
	public  double calcMPayment (double mInterest, double amount, int time)
	{
	    double mPayment;
	    mPayment = (mInterest * amount)*time;

	    return mPayment;
	}
	public void loanStatement(double amount, double interest, int time, double mPayment)
	{
	   System.out.println(" time is" +time);
	   
	}

	
}		
	




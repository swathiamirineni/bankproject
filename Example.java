package banking;



	
		// TODO Auto-generated method stub
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		import java.util.Scanner;
		public class Example {
	
			Double amount,bal,am,am1;
	static boolean acno;
			
		public void transferOfFunds throws ClassNotFoundException, SQLException 
			{
				Scanner s=new Scanner(System.in);
				 
				
			  
			      System.out.println("enter To user account number");
			      String n1=s.nextLine();
			      System.out.println("enter To Account branch id");
			      String st2=s.next();
			    connection();
			  	        PreparedStatement ps1=con.prepareStatement("select Account_Number from customerregistration_tbl");
			      		//PreparedStatement ps=con.prepareStatement("select count(Account_Number) from customerregistration_tbl"); 
			    		ResultSet rs1=ps1.executeQuery();
			    		/*while(rs1.next())
			    		{
			    			int i=1;
			    			acno=rs1.getInt(i);
			    			i++;
			    			System.out.println(acno);
			    		}*/
			    		
			      		//int c=rs.getInt(1);
			    		//int c=rs.executeUpdate()
			      ResultSet rs= stmt.executeQuery("select Account_Number from customerregistration_tbl");
			      int count = 1;
			      int i=1;
			      while (rs.next()) {
			        count++;
			      
			        
			        while(i<=count)
				     {
				    	acno=((rs.getString(i)).equals(n1));
				    	 
				    break;
				     }
			      }
			        
			     if(acno==true)
				  {
				    PreparedStatement ps=con.prepareStatement("call sp_transfor3(2,'"+n1+"',500)");
				    ps.executeUpdate();
				  }
				  else if(acno==false)
				  {
				  System.out.println("not");
				  }
				   
			     //System.out.println(count); 

}
		}
		
			  
			      	           
			      	           
			      
		

		
			
		
			      		
			      		


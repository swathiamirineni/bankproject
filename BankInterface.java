package banking;
import java.sql.SQLException;
public interface BankInterface {
			
			public void startMenu() throws SQLException, ClassNotFoundException, InterruptedException, Exception ;
			public void branchMenu() throws InterruptedException, ClassNotFoundException, SQLException, Exception;
			public void accountTypeMenu()throws InterruptedException, ClassNotFoundException, SQLException, Exception;
		
			public void login() throws ClassNotFoundException, SQLException, InterruptedException, Exception;
			//public void registration() ;
			public void transations() throws SQLException, ClassNotFoundException, InterruptedException;
			public void menu() throws InterruptedException, Exception;
			public void fixedTransation() throws ClassNotFoundException, SQLException, Exception;
		
			public void branchDeposit() throws ClassNotFoundException, SQLException, Exception;
			}


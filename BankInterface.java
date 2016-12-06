package kbr.banking;

import java.sql.SQLException;

public interface BankInterface {
		String s = null;
		public void startMenu() throws SQLException, ClassNotFoundException, InterruptedException, Exception ;
		public void branchMenu() throws InterruptedException, ClassNotFoundException, SQLException, Exception;
		public void accountTypeMenu()throws InterruptedException, ClassNotFoundException, SQLException, Exception;
		public void lockers() throws ClassNotFoundException, SQLException;
		public void login() throws ClassNotFoundException, SQLException, InterruptedException;
		//public void registration() ;
		public void transations() throws SQLException, ClassNotFoundException, InterruptedException;
		public void menu1() throws InterruptedException, Exception;
		public void fixedTransation() throws ClassNotFoundException, SQLException;
		public void branchDeposit() throws ClassNotFoundException, SQLException, Exception;
		public void cashier();
}
		



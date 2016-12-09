package banking;

import java.sql.*;

public class BankMainNew {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, Exception {
		BankInterface b2=new BankOperations();
		b2.startMenu();
	}

}

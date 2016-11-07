package project;


public class InfoManager {
	public static void main(String[] args) 
    {
    	SqlManager sql=new SqlManager();
    	sql.getConnection();
    	sql.query();
    	Login win=new Login();
    }

}

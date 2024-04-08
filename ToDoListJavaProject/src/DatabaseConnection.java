import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {
    Connection conn = null;		
    Statement stmt = null;
	ResultSet rs = null;
    Configuration config = new Configuration();
    public DatabaseConnection(){
        
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/todolist_java?"+
		                                   "user="+config.user()+"&password="+config.password());

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
    }
    
    public void insertTask(ToDoListComponent currentToDoList) {

    }

    public void insertToDoList() {
        
    }

    public void updateDatabase(ArrayList<ToDoListComponent> td) {
        
    }
}

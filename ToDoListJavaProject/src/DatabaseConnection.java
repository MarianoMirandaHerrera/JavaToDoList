import java.sql.Statement;
import java.security.AllPermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    public void insertTask(ToDoListComponent currentToDoList, TaskComponent taskComponent) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO todolist_java.tasks (idtasks, tasktext, todolistid) VALUES (?, ?, ?)");
            pstmt.setInt(1, taskComponent.taskId); // Set the value of the first parameter (id)
            pstmt.setString(2, ""); // Set the value of the second parameter (todolisttext)
            pstmt.setInt(3, currentToDoList.id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            release();
        }
    }

    public void insertToDoList(int i) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO todolist_java.todolists (id, todolisttext) VALUES (?, ?)");
            pstmt.setInt(1, i); // Set the value of the first parameter (id)
            pstmt.setString(2, ""); // Set the value of the second parameter (todolisttext)
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            release();
        }

    }

    public void updateDatabase(ArrayList<ToDoListComponent> td) {
        
    }

    public HashMap<Integer, String> addComponents() {
        HashMap<Integer, String> hm = new HashMap<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM todolist_java.todolists");

            if (stmt.execute("SELECT * FROM todolist_java.todolists")) {
                rs = stmt.getResultSet();
            }

            while (rs.next()) {
                int todolist_id = rs.getInt("id");
                String todolist_todolisttext = rs.getString("todolisttext");
                hm.put(todolist_id,todolist_todolisttext);                
            }
        } catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
            release();
        }
        return hm;  
    }

    public void release() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore
                stmt = null;
        }
    }

    public HashMap<Integer,String> addTaskComponent(int key) {
        HashMap<Integer, String> hm = new HashMap<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM todolist_java.tasks");

            if (stmt.execute("SELECT * FROM todolist_java.tasks")) {
                rs = stmt.getResultSet();
            }

            while (rs.next()) {
                if (key == rs.getInt("todolistid")) {    
                    int todolist_id = rs.getInt("idtasks");
                    String todolist_todolisttext = rs.getString("tasktext");
                    hm.put(todolist_id,todolist_todolisttext);
                }                
            }
        } catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
            release();
        }
        return hm;  
    } 
}

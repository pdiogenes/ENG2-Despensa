package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysql {
    public Connection conecta(){
            try {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/despensa", "root", "");
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmazonUserDAO {
    public static final String sqlite_url = "jdbc:sqlite:C:/My DataBase/FinalProject.db";
    public static final Connection con;


    static {
        try {
            con = DriverManager.getConnection(sqlite_url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNewDataBase() {
        String url = "jdbc:sqlite:C:/My DataBase/FinalProject.db";
        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void create() {
        String url = "jdbc:sqlite:C:/My DataBase/FinalProject.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                String sql = "CREATE TABLE IF NOT EXISTS amazon_users(id INTEGER , first_name TEXT NOT NULL,last_name TEXT NOT NULL,email TEXT NOT NULL, password TEXT NOT NULL, address TEXT, city TEXT  )";
                Statement stm = con.createStatement();
                stm.executeUpdate(sql);
                System.out.println("create table =");
                System.out.println("the table  is created ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
    public static void insertInto() throws SQLException{
        String query = "INSERT INTO amazon_users (id,first_name, last_name, email, password, address, city) VALUES (2, 'Avi', 'Biton', 'example2@gmail.com', 'example2223', 'Yaakov', 'Rehovot')";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
        System.out.println("Insert Into table...");
        System.out.println("The table  is done !");
    }

    public static void updateTable(int id, String firstName, String lastName, String email, String password, String address, String city) throws SQLException {
        String query = "UPDATE amazon_users SET first_name=?, last_name=?, email=?, password=?, address=?, city=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, firstName);
        pst.setString(2, lastName);
        pst.setString(3, email);
        pst.setString(4, password);
        pst.setString(5, address);
        pst.setString(6, city);
        pst.setInt(7, id);
        int numRowsUpdated = pst.executeUpdate();
        if (numRowsUpdated > 0) {
            System.out.println("Record updated successfully");
        } else {
            System.out.println("No records were updated");
        }
    }


    public static void deleteFromTable() throws SQLException{
        String query = "DELETE FROM amazon_users WHERE id = 3";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
        System.out.println("Record deleted from the table...");
        System.out.println("The table is updated!");
    }
    public static List<AmazonUser> getAll() throws SQLException {
        List<AmazonUser> users = new ArrayList<>();
        String query = "SELECT * FROM amazon_users";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String city = rs.getString("city");
            AmazonUser user = new AmazonUser(id, firstName, lastName, email, password, address, city);
            users.add(user);
        }

        return users;
    }
}

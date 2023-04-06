import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class AmazonUserTest {
    public static String sqlite_url = "jdbc:sqlite:C:/My DataBase/FinalProject.db";
    public static Connection con ;
    static {
        try {
            con = DriverManager.getConnection(sqlite_url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeClass
    public static void setUp() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:C:/My DataBase/FinalProject.db");
    }
    @After
    public void tearDown() throws SQLException {
        // Close the database connection
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
    @Test //Test for creating table
    void testCreateTable() {
        String url = "jdbc:sqlite:C:/My DataBase/FinalProject.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                String sql = "DROP TABLE IF EXISTS amazon_users"; // Drop the table if it exists
                Statement stm = con.createStatement();
                stm.executeUpdate(sql);

                // Call the create method
                AmazonUserDAO.create();

                // Verify that the table was created
                ResultSet rs = con.getMetaData().getTables(null, null, "amazon_users", null);
                assertTrue(rs.next());
                assertEquals("amazon_users", rs.getString("TABLE_NAME"));
                assertFalse(rs.next());
            }
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test //Update table test
    public void testUpdateTable() throws SQLException {
        // insert a new record into the test database
        String query = "INSERT INTO amazon_users (id, first_name, last_name, email, password, address, city) VALUES (1, 'Avi', 'Biton', 'Avi@example.com', 'password', 'Sea', 'Rishon')";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);

        // update the record using the updateTable method
        String first_name = "Yoni";
        String last_name = "Rish";
        String email = "Yoni@example.com";
        String password = "newpassword";
        String address = "MagenDavid";
        String city = "TLV";
        int id = 1;
        AmazonUserDAO.updateTable(id, first_name, last_name, email, password, address, city);

        // retrieve the updated record from the test database
        String selectQuery = "SELECT * FROM amazon_users WHERE id = 1";
        PreparedStatement selectPst = con.prepareStatement(selectQuery);
        ResultSet rs = selectPst.executeQuery();
        rs.next();
        assertEquals(first_name, rs.getString("first_name"));
        assertEquals(last_name, rs.getString("last_name"));
        assertEquals(email, rs.getString("email"));
        assertEquals(password, rs.getString("password"));
        assertEquals(address, rs.getString("address"));
        assertEquals(city, rs.getString("city"));

        // clean up the test database by deleting the record
        String deleteQuery = "DELETE FROM amazon_users WHERE id = 1";
        PreparedStatement deletePst = con.prepareStatement(deleteQuery);
        deletePst.executeUpdate();
    }
    @Test // Delete record test
    public void testDeleteFromTable() throws SQLException {
        // Delete a record from the table
        AmazonUserDAO.deleteFromTable();

        // Check if the record has been deleted
        String query = "SELECT COUNT(*) FROM amazon_users WHERE id = 3";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        assertEquals(0, count);
    }
    @Test // Select all records test
    public void testGetAll() throws SQLException {
        List<AmazonUser> users = AmazonUserDAO.getAll();
        assertNotNull(users); // check that the list is not null
        assertTrue(users.size() > 0); // check that the list is not empty
    }
    @Test
   public void TestPasswordLength(){
        Assert.assertEquals(true, AmazonUser.isValid("Example223"));
    }
    @Test
    public void testIsPasswordValid() {
        AmazonUser user = new AmazonUser(2, "example3@gmail.com", "Example223", "Shalom", "Koen", "Weizman", "Rehovot");
        assertFalse(user.isPasswordValid());
    }
}



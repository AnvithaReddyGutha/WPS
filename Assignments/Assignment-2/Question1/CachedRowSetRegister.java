import java.sql.*;
import javax.sql.rowset.*;
import javax.sql.rowset.spi.*;
import java.io.*;
 
public class CachedRowSetRegister {
    static Console console = System.console();
    static String answer;
    static boolean quit = false;
 
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/Form";
        Integer username = "mobileno";
        try (Connection conn = DriverManager.getConnection(url, username)) {
            conn.setAutoCommit(false);
            String sql = "SELECT * FROM Registration";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            rowset.setTableName("Registration");
            rowset.populate(result);
            while (!quit) {
                if (!readStudent(rowset)) continue;
                updateRegistration(rowset);
                deleteRegistration(rowset);
                insertRegistration(rowset);
                saveChanges(rowset, conn);
                askToQuit();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    static void readStudentInfo(String position, ResultSet result)
            throws SQLException {
        String name = result.getString("name");
        String email = result.getString("email");
        Integer mobileno = result.getInteger("mobileno");
        Integer date_of_birth = result.getInteger("date_of_birth");
        String RegistrationInfo = "%s: %s - %s - %d - %d\n";
        System.out.format(RegistrationInfo, position, name, email, mobileno,date_of_birth);
    }
    static boolean readStudent(ResultSet result) throws SQLException {
        int row = Integer.parseInt(console.readLine("Enter Registration number: "));
        if (result.absolute(row)) {
            readStudentInfo("Student at row " + row + ": ", result);
            return true;
        } else {
            System.out.println("There's no detail at row " + row);
            return false;
        }
    } 
    static void updateRegistration(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to update this Registration (Y/N)?: ");
        if (answer.equalsIgnoreCase("Y")) {
            String email = console.readLine("\tUpdate email: ");
            if (!email.equals("")) result.updateString("email", email);
            result.updateRow();
            System.out.println("Registration details has been updated.");
        }
    }
    static void deleteRegistration(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to delete this Registration (Y/N)?: ");
        if (answer. equalsIgnoreCase("Y")) {
            result.deleteRow();
            System.out.println("Registration details has been removed.");
        }
    }
    static void insertStudent(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to insert a new Registration (Y/N)?: ");
        if (answer.equalsIgnoreCase("Y")) {
            String name = console.readLine("\tEnter name: ");
            String email = console.readLine("\tEnter email: ");
            Integer mobileno = console.readLine("\tEnter mobileno: ");
            Integer date_of_birth = console.readLine("\tEnter date_of_birth: ");
            result.moveToInsertRow();
            result. updateNull("Registration Id");
            result.updateString("name", name);
            result.updateString("email", email);
            result.updateInteger("mobileno", mobileno);
            result.updateInteger("date_of_birth", date_of_birth);
            result.insertRow();
            result.moveToCurrentRow();
            System.out.println("Registration Details has been added.");
        }
    }
    static void saveChanges(CachedRowSet rowset, Connection conn) {
        answer = console.readLine("Do you want to save changes (Y/N)?: ");
        if (answer.equalsIgnoreCase("Y")) {
            try {
                rowset.acceptChanges(conn);
            } catch (SyncProviderException ex) {
                System.out.println("Error committing changes to the database: " + ex);
            }
        }
    }
    static void askToQuit() {
        answer = console.readLine("Do you want to quit (Y/N)?: ");
        quit = answer.equalsIgnoreCase("Y");
    }
} 
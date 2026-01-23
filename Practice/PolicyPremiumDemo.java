// You are using Java
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class PolicyPremiumDemo {
    
      public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try (Connection con = getConnection();) {
            processMenu(con, sc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost/ri_db";
        String username = "test";
        String password = "test123";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }


    public static void insertPolicy(Connection con, Scanner sc) {
        
    }
    public static void displayAll(Connection con) {
        
    }
    
//Update the minimum_premium to 4000 for all policies that currently have the highest premium value in the policy table.
    private static void modifyPolicyPremium(Connection con, Scanner sc){
        try {
            // 2. First SQL: get the maximum premium
            String selectSql = "SELECT MAX(minimum_premium)  FROM policy";
            PreparedStatement ps1 = con.prepareStatement(selectSql);
            ResultSet rs = ps1.executeQuery();

            int maxPremium = 0;
            if (rs.next()) {
                maxPremium = rs.getInt(1);
            }
            // 3. Second SQL: update all policies with that premium
            String updateSql = "UPDATE policy SET minimum_premium = 4000 WHERE minimum_premium = ?";
            PreparedStatement ps2 = con.prepareStatement(updateSql);
            ps2.setInt(1, maxPremium);

            int rowsUpdated = ps2.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public static void processMenu(Connection con, Scanner sc) {
        System.out.println("Enter choice: (1/2)");
        int choice = Integer.parseInt(sc.nextLine());
        try {
            switch (choice) {
                case 1:
                    insertPolicy(con, sc);
                    break;
                case 2:
                    modifyPolicyPremium(con,sc);
                    break;
                case 3:
                    displayAll(con);
                    break;    
                default:
                    System.out.println("Invalid Choice");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
}

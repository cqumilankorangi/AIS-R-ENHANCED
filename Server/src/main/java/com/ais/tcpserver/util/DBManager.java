package com.ais.tcpserver.util;

import com.ais.model.AdminModel;
import com.ais.model.ManagementModel;
import com.ais.model.RecruitModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String url = "jdbc:mysql://localhost:3306/car";
    private static final String user = "root";
    private static final String password = "kina";

    private Connection connection;

    public DBManager() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the MySQL database!");
    }

    public Connection getConnection() {
        return connection;
    }

    public void insertAdmin(AdminModel admin) throws SQLException {
        String sql = "INSERT INTO admin (fullName, address, phoneNo, email, userName, password, level, branch) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getFullName());
            pstmt.setString(2, admin.getAddress());
            pstmt.setString(3, admin.getPhoneNo());
            pstmt.setString(4, admin.getEmail());
            pstmt.setString(5, admin.getUserName());
            pstmt.setString(6, admin.getPassword());
            pstmt.setString(7, admin.getLevel());
            pstmt.setString(8, admin.getBranch());

            pstmt.executeUpdate();
            System.out.println("Admin inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateAdmin(AdminModel admin) throws SQLException {
        String sql = "UPDATE admin SET fullName = ?, address = ?, phoneNo = ?, email = ?, password = ?, level = ?, branch = ?, userName = ? WHERE id = ?";
        boolean isUpdated = false;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getFullName());
            pstmt.setString(2, admin.getAddress());
            pstmt.setString(3, admin.getPhoneNo());
            pstmt.setString(4, admin.getEmail());
            pstmt.setString(5, admin.getPassword());
            pstmt.setString(6, admin.getLevel());
            pstmt.setString(7, admin.getBranch());
            pstmt.setString(8, admin.getUserName());
            pstmt.setInt(9, admin.getId());

            int rowsAffected = pstmt.executeUpdate();
            isUpdated = (rowsAffected > 0);

            System.out.println("Update query executed: " + pstmt);
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    public List<AdminModel> getAdmin() {
        List<AdminModel> admins = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT * FROM admin";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String address = rs.getString("address");
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String userName = rs.getString("userName");
                String pwd = rs.getString("password");
                String level = rs.getString("level");
                String branch = rs.getString("branch");
                admins.add(new AdminModel(id, fullName, address, phoneNo, email, userName, pwd, level, branch));
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AdminModel getAdminByUserNameAndPassword(String paramUserName, String password) {
        AdminModel admin = null;
        String sql = "SELECT * FROM admin WHERE userName = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paramUserName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String address = rs.getString("address");
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String userName = rs.getString("userName");
                String pwd = rs.getString("password");
                String level = rs.getString("level");
                String branch = rs.getString("branch");
                admin = new AdminModel(id, fullName, address, phoneNo, email, userName, pwd, level, branch);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public ManagementModel getManagementByUserNameAndPassword(String paramUserName, String password) {
        ManagementModel management = null;
        String sql = "SELECT * FROM management WHERE userName = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paramUserName);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String phoneNo = rs.getString("phoneNo");
                    String email = rs.getString("email");
                    String userName = rs.getString("userName");
                    String pwd = rs.getString("password");
                    String position = rs.getString("position");
                    management = new ManagementModel(id, fullName, address, phoneNo, email, userName, pwd, position);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return management;
    }

    public RecruitModel getRecruitByUserNameAndPassword(String paramUserName, String password) {
        RecruitModel recruit = null;
        String sql = "SELECT * FROM recruit WHERE userName = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paramUserName);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String phoneNo = rs.getString("phoneNo");
                    String email = rs.getString("email");
                    String userName = rs.getString("userName");
                    String pwd = rs.getString("password");
                    String interviewDate = rs.getString("interviewDate");
                    String qualificationLevel = rs.getString("qualificationLevel");
                    String department = rs.getString("department");
                    recruit = new RecruitModel(id, fullName, address, phoneNo, email, 
                            userName, pwd, interviewDate, qualificationLevel,department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruit;
    }

    public RecruitModel getRecruitByUserName(String paramUserName) {
        RecruitModel recruit = null;
        String sql = "SELECT * FROM recruit WHERE userName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paramUserName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String address = rs.getString("address");
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String userName = rs.getString("userName");
                String pwd = rs.getString("password");
                String interviewDate = rs.getString("interviewDate");
                String qualificationLevel = rs.getString("qualificationLevel");
                String department = rs.getString("department");
                recruit = new RecruitModel(id, fullName, address, phoneNo, email, 
                        userName, pwd, interviewDate, qualificationLevel, department);
                return recruit;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertManagement(ManagementModel management) throws SQLException {
        String sql = "INSERT INTO management (fullName, address, phoneNo, email, userName, password, position) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, management.getFullName());
            pstmt.setString(2, management.getAddress());
            pstmt.setString(3, management.getPhoneNo());
            pstmt.setString(4, management.getEmail());
            pstmt.setString(5, management.getUserName());
            pstmt.setString(6, management.getPassword());
            pstmt.setString(7, management.getPosition());

            pstmt.executeUpdate();
            System.out.println("Management inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateManagement(ManagementModel management) throws SQLException {
        String sql = "UPDATE management SET fullName = ?, address = ?, phoneNo = ?, email = ?, password = ?, position = ? WHERE userName = ? AND id = ?";
        boolean isUpdated = false;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, management.getFullName());
            pstmt.setString(2, management.getAddress());
            pstmt.setString(3, management.getPhoneNo());
            pstmt.setString(4, management.getEmail());
            pstmt.setString(5, management.getPassword());
            pstmt.setString(6, management.getPosition());
            pstmt.setString(7, management.getUserName());  // Assuming userName is unique and used as a condition
            pstmt.setInt(8, management.getId());  // Assuming id is unique and used as a condition

            int rowsAffected = pstmt.executeUpdate();
            isUpdated = (rowsAffected > 0);

            System.out.println("Update query executed: " + pstmt);
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    public void insertRecruit(RecruitModel recruit) throws SQLException {
        String sql = "INSERT INTO recruit (fullName, address, phoneNo, email, userName, password, interviewDate, qualificationLevel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recruit.getFullName());
            pstmt.setString(2, recruit.getAddress());
            pstmt.setString(3, recruit.getPhoneNo());
            pstmt.setString(4, recruit.getEmail());
            pstmt.setString(5, recruit.getUserName());
            pstmt.setString(6, recruit.getPassword());
            pstmt.setString(7, recruit.getInterviewDate());
            pstmt.setString(8, recruit.getQualificationLevel());

            pstmt.executeUpdate();
            System.out.println("Recruit inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRecruit(RecruitModel recruit) {
        String sql = "UPDATE recruit SET fullName = ?, address = ?, phoneNo = ?, email = ?, "
                + "userName =?, password = ?, interviewDate = ?, qualificationLevel = ?, department = ? WHERE id = ?";
        boolean isUpdated = false;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recruit.getFullName());
            pstmt.setString(2, recruit.getAddress());
            pstmt.setString(3, recruit.getPhoneNo());
            pstmt.setString(4, recruit.getEmail());
            pstmt.setString(5, recruit.getUserName());
            pstmt.setString(6, recruit.getPassword());
            pstmt.setString(7, recruit.getInterviewDate());
            pstmt.setString(8, recruit.getQualificationLevel());
            pstmt.setString(9, recruit.getDepartment());
            pstmt.setInt(10, recruit.getId());

            int rowsAffected = pstmt.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean addOTP(int recruitId, String provideOTP) {
        String sql = "UPDATE recruit SET otp = ? WHERE id = ?";
        boolean isUpdated = false;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, provideOTP);
            pstmt.setInt(2, recruitId);

            int rowsAffected = pstmt.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}

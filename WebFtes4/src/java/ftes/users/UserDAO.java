/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ftes.users;

import ftes.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYENSE190290
 */
public class UserDAO {
    private static final String SEARCH = "SELECT userID, fullName, gender, dob, email, phone FROM BeooUsers";
    private static final String INSERT = "INSERT INTO BeooUsers(fullName, gender, dob, email, phone) VALUES(?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM BeooUsers WHERE userID=?";
    private static final String GET_USER_BY_ID = "SELECT userID, fullName, gender, dob, email, phone FROM BeooUsers WHERE userID=?";
    private static final String UPDATE = "UPDATE BeooUsers SET fullName=?, gender=?, dob=?, email=?, phone=? WHERE userID=?";

  public List<UserDTO> getListUser() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ptm = null; ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new UserDTO(rs.getInt("userID"), rs.getString("fullName"), 
                            rs.getString("gender"), rs.getDate("dob"), rs.getString("email"), rs.getString("phone")));
                }
            }
        } catch (Exception e) { e.printStackTrace();
        } finally {
            if (rs != null) rs.close(); if (ptm != null) ptm.close(); if (conn != null) conn.close();
        }
        return list;
    }

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null; PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getGender());
                ptm.setDate(3, user.getDob());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPhone());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close(); if (conn != null) conn.close();
        }
        return check;
    }

    public boolean delete(int userID) throws SQLException {
        boolean check = false;
        Connection conn = null; PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, userID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close(); if (conn != null) conn.close();
        }
        return check;
    }

    public UserDTO getUserById(int userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null; PreparedStatement ptm = null; ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_BY_ID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    user = new UserDTO(rs.getInt("userID"), rs.getString("fullName"), 
                            rs.getString("gender"), rs.getDate("dob"), rs.getString("email"), rs.getString("phone"));
                }
            }
        } catch (Exception e) { e.printStackTrace();
        } finally {
            if (rs != null) rs.close(); if (ptm != null) ptm.close(); if (conn != null) conn.close();
        }
        return user;
    }

    public boolean update(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null; PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getGender());
                ptm.setDate(3, user.getDob());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPhone());
                ptm.setInt(6, user.getUserID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close(); if (conn != null) conn.close();
        }
        return check;
    }

}

package services;

import helpers.ConnectionToDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserService {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String makeDigest(String st) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.update(st.getBytes());
        byte[] digest = messageDigest.digest();

        StringBuffer hexString = new StringBuffer();
        for (byte b : digest) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }

    public boolean validatingUser(String login, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement ps = connection.prepareStatement("select * from users where login = ? and password = ? ");
        ps.setString(1, login);
        ps.setString(2, makeDigest(password));
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void updatePassword(String newpassword, int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update users set password = ? where id = ?");
        ps.setString(1, newpassword);
        ps.setInt(2, id);
        ps.execute();
    }

    public void updateProfile(String firstname, String lastname, String login, int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update users set firstname = ?, lastname = ?, login = ? where id = ?");
        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setString(3, login);
        ps.setInt(4, id);
        ps.execute();
    }


}

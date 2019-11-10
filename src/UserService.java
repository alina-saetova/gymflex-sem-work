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
        for (int i = 0;i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
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

    public void updatePassword(String newpassword, String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update users set password = ? where id = ?");
        ps.setString(1, newpassword);
        ps.setInt(2, Integer.parseInt(id));
        ps.execute();
    }

    public void updateProfile(String firstname, String lastname, String login, String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update users set firstname = ?, lastname = ?, login = ? where id = ?");
        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setString(3, login);
        ps.setInt(4, Integer.parseInt(id));
        ps.execute();
    }
}

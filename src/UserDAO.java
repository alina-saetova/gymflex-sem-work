import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;

public class UserDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String idUser) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
        ps.setInt(1, Integer.parseInt(idUser));
        ResultSet rs = ps.executeQuery();
        User u = null;
        while (rs.next()) {
            u = new User(rs.getString("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("login"),
                    rs.getString("password"), rs.getString("photo"));
        }
        return u;

    }

    public User getUserByLogin(String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from users where login = ?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        User u = null;
        while (rs.next()) {
            u = new User(rs.getString("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("login"),
                    rs.getString("password"), rs.getString("photo"));
        }
        return u;

    }

    public void insert(String firstName, String lastName, String login, String password, String photo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into users (firstname, lastname, login, password, photo) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, login);
        ps.setString(4, password);
        ps.setString(5, photo);
        ps.execute();
    }

//    public boolean checkLogin(String login) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement("select * from users where login = ?");
//        ps.setString(1, login);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            System.out.println(rs.getString("login"));
//            return true;
//        }
//        return false;
//    }


}

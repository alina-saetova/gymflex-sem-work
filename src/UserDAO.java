import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String idUser) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from users where id=" + idUser);
        User u = null;
        while (rs.next()) {
            u = new User(rs.getString("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("login"),
                    rs.getString("password"));
        }
        return u;

    }

    public User getUserByLogin(String login) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from users where login = '" + login + "'");
        User u = null;
        while (rs.next()) {
            u = new User(rs.getString("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("login"),
                    rs.getString("password"));
        }
        return u;

    }
}

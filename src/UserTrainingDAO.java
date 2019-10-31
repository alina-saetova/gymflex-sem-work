import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserTrainingDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int createTraining(String user_id, String name) throws SQLException {
        stmnt.executeUpdate("insert into utrainings (user_id, name) " +
                "VALUES (" + user_id + ", '" + name + "');");
        ResultSet rs = stmnt.executeQuery("select * from utrainings where user_id=" + user_id + ";");
        int id = 1;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        System.out.println(id);
        return id;
    }

    public List<UserTraining> getUserTrainings(String user_id) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from utrainings where user_id=" + user_id);
        List<UserTraining> ut = new ArrayList<>();
        while (rs.next()) {
            ut.add(new UserTraining(rs.getString("id"), rs.getString("user_id"), rs.getString("name")));
        }
        return ut;
    }
}

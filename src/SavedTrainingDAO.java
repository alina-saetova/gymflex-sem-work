import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SavedTrainingDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String checkLike(String user_id, String training_id) throws SQLException {
        if (user_id == null) {
            return "no_auth";
        }
        ResultSet rs = stmnt.executeQuery("select * from fav_training_user " +
                "where training_id = " + training_id + " AND user_id = " + user_id + ";");
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<String> getSavedTrainingsId(User user) throws SQLException {
        List<String> ids = new ArrayList<>();
        ResultSet rs = stmnt.executeQuery("select * from fav_training_user where user_id = " + user.getId());
        while (rs.next()) {
            ids.add(rs.getString("training_id"));
        }
        return ids;
    }

    public void insert(String training_id, String user_id) throws SQLException {
        stmnt.executeUpdate("insert into fav_training_user values (" + training_id + ", " + user_id+ ");");
    }
}

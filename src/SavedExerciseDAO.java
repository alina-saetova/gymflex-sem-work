import org.apache.taglibs.standard.tlv.JstlBaseTLV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedExerciseDAO {

    UserDAO ud = new UserDAO();
    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String checkLike(String user_id, String exercise_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_exercise_user " +
                "where exercise_id = ? AND user_id = ?");
        ps.setInt(1, Integer.parseInt(exercise_id));
        ps.setInt(2, Integer.parseInt(user_id));
        ResultSet rs = ps.executeQuery();
        if (ud.getUserById(user_id) == null) {
            return "no_auth";
        }
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<String> getSavedExercisesId(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_exercise_user where user_id = ?");
        ps.setInt(1, Integer.parseInt(user.getId()));
        List<String> ids = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getString("exercise_id"));
        }
        return ids;
    }

    public void insert(String exercise_id, String user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_exercise_user values (?, ?)");
        ps.setInt(1, Integer.parseInt(exercise_id));
        ps.setInt(2, Integer.parseInt(user_id));
        ps.execute();
    }
}

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

    public String checkLike(int user_id, int exercise_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_exercise_user " +
                "where exercise_id = ? AND user_id = ?");
        ps.setInt(1, exercise_id);
        ps.setInt(2, user_id);
        if (ud.getUserById(user_id) == null) {
            return "true";
        }
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<Integer> getSavedExercisesId(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_exercise_user where user_id = ?");
        ps.setInt(1, user.getId());
        List<Integer> ids = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("exercise_id"));
        }
        return ids;
    }

    public void insert(int exercise_id, int user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_exercise_user values (?, ?)");
        ps.setInt(1, exercise_id);
        ps.setInt(2, user_id);
        ps.execute();
    }
}

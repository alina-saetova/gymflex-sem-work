import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedTrainingDAO {

    private UserDAO ud = new UserDAO();
    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String checkLike(int user_id, int training_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_training_user " +
                "where training_id = ? AND user_id = ?");
        ps.setInt(1, training_id);
        ps.setInt(2, user_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<Integer> getSavedTrainingsId(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_training_user where user_id = ?");
        ps.setInt(1, user.getId());
        List<Integer> ids = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("training_id"));
        }
        return ids;
    }

    public void insert(int training_id, int user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_training_user values (?, ?)");
        ps.setInt(1, training_id);
        ps.setInt(2, user_id);
        ps.execute();
    }
}

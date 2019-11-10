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

    public String checkLike(String user_id, String training_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_training_user " +
                "where training_id = ? AND user_id = ?");
        ps.setInt(1, Integer.parseInt(training_id));
        ps.setInt(2, Integer.parseInt(user_id));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<String> getSavedTrainingsId(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from fav_training_user where user_id = ?");
        ps.setInt(1, Integer.parseInt(user.getId()));
        List<String> ids = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getString("training_id"));
        }
        return ids;
    }

    public void insert(String training_id, String user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_training_user values (?, ?)");
        ps.setInt(1, Integer.parseInt(training_id));
        ps.setInt(2, Integer.parseInt(user_id));
        ps.execute();
    }
}

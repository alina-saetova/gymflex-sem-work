package dao;

import helpers.ConnectionToDatabase;
import java.sql.*;

public class SavedExerciseDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(int exercise_id, int user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_exercise_user values (?, ?)");
        ps.setInt(1, exercise_id);
        ps.setInt(2, user_id);
        ps.execute();
    }
}

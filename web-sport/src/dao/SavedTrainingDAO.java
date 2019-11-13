package dao;

import helpers.ConnectionToDatabase;
import models.User;

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

    public void insert(int training_id, int user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fav_training_user values (?, ?)");
        ps.setInt(1, training_id);
        ps.setInt(2, user_id);
        ps.execute();
    }
}

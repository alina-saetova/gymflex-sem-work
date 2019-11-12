package services;

import dao.TrainingDAO;
import helpers.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainingService {

    TrainingDAO td = new TrainingDAO();
    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateLikes(int trainings_id) throws SQLException {
        int likes = td.getTrainingById(trainings_id).getCnt_likes() + 1;
        PreparedStatement ps = connection.prepareStatement("update trainings set cnt_likes = ? where id = ?");
        ps.setInt(1, likes);
        ps.setInt(2, trainings_id);
        ps.execute();
    }
}

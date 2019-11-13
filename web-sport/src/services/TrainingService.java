package services;

import dao.TrainingDAO;
import helpers.ConnectionToDatabase;
import models.Exercise;
import models.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Training> getLastAddedTrainings() throws SQLException {
        List<Training> trainings = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM trainings ORDER BY id DESC LIMIT 6");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            trainings.add(new Training(rs.getInt("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"),
                    rs.getString("location"), rs.getString("photo")));
        }
        return trainings;
    }
}

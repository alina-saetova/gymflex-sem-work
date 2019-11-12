package services;

import dao.ExerciseDAO;
import dao.TrainingDAO;
import helpers.ConnectionToDatabase;
import models.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseService {

    ExerciseDAO ed = new ExerciseDAO();
    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Exercise> getExercisesFromTraining(int training_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from exercise_training where training_id = ?");
        ps.setInt(1, training_id);
        ResultSet rs = ps.executeQuery();
        List<Exercise> exercises = new ArrayList<>();
        while (rs.next()) {
            exercises.add(ed.getExerciseById(rs.getInt("exercise_id")));
        }
        return exercises;
    }

    public void updateLikes(int exercise_id) throws SQLException {
        int likes = ed.getExerciseById(exercise_id).getCnt_likes() + 1;
        PreparedStatement ps = connection.prepareStatement("update exercises set likes = ?  where id = ?");
        ps.setInt(1, likes);
        ps.setInt(2, exercise_id);
        ps.execute();
    }
}

package dao;

import helpers.ConnectionToDatabase;
import models.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Exercise getExerciseById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from exercises where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Exercise e = null;
        while (rs.next()) {
            e = new Exercise(rs.getInt("id"), rs.getString("name"),
                    rs.getString("info"), rs.getInt("likes"),
                    rs.getString("photo"), rs.getString("type"));
        }
        return e;
    }

    public List<Exercise> getAllExercises() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from exercises");
        ResultSet rs = ps.executeQuery();
        List<Exercise> ex = new ArrayList<>();
        while (rs.next()) {
            ex.add(new Exercise(rs.getInt("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("likes")),
                    rs.getString("photo"), rs.getString("type")));
        }
        return ex;
    }

    public List<Exercise> getExercisesByType(String type) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from exercises where type = ?");
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        List<Exercise> exercises = new ArrayList<>();
        while (rs.next()) {
            exercises.add(new Exercise(rs.getInt("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("likes")),
                    rs.getString("photo"), rs.getString("type")));
        }
        return exercises;
    }
}

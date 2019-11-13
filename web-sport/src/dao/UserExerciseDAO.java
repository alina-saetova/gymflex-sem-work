package dao;

import helpers.ConnectionToDatabase;
import models.UserExercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserExerciseDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(int utraining_id, String name, String reps) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into uexercises (utraining_id, name, reps) " +
                "VALUES (?, ?, ?)");
        ps.setInt(1, utraining_id);
        ps.setString(2, name);
        ps.setString(3, reps);
        ps.execute();
    }

    public List<UserExercise> get(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from uexercises where utraining_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<UserExercise> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new UserExercise(rs.getInt("id"), rs.getInt("utraining_id"),
                    rs.getString("name"), rs.getString("reps")));
        }
        return list;
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from uexercises where utraining_id = ?");
        ps.setInt(1, id);
        ps.execute();
    }
}

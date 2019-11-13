package dao;

import helpers.ConnectionToDatabase;
import models.UserTraining;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTrainingDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int insert(int user_id, String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into utrainings (user_id, name) " +
                "VALUES (?, ?)");
        ps.setInt(1, user_id);
        ps.setString(2, name);
        ps.execute();
        PreparedStatement ps1 = connection.prepareStatement("select * from utrainings where user_id = ?");
        ps1.setInt(1, user_id);
        ResultSet rs = ps1.executeQuery();
        int id = 1;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        System.out.println(id);
        return id;
    }

    public List<UserTraining> getAll(int user_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from utrainings where user_id = ?");
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        List<UserTraining> ut = new ArrayList<>();
        while (rs.next()) {
            ut.add(new UserTraining(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name")));
        }
        return ut;
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from utrainings where id = ?");
        ps.setInt(1, id);
        ps.execute();
    }
}

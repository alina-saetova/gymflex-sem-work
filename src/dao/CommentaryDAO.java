package dao;

import helpers.ConnectionToDatabase;
import models.Commentary;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentaryDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Commentary> getAll(int id, String type) throws SQLException, ParseException {
        PreparedStatement ps = connection.prepareStatement("select * from commentaries where article_id = ? AND type = ?");
        ps.setInt(1, id);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        List<Commentary> comms = new ArrayList<>();
        while (rs.next()) {
            comms.add(new Commentary(rs.getInt("id"), rs.getInt("user_id"),
                    rs.getInt("article_id"),
                    new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY").parse(rs.getString("date")),
                    rs.getString("content"), rs.getString("type")));
        }
        return comms;
    }

    public int insert(int user_id, int article_id, Date date, String content, String type) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into commentaries (user_id, article_id, date, content, type) VALUES (?, ?, ?, ?, ?)");
        ps.setInt(1, user_id);
        ps.setInt(2, article_id);
        ps.setString(3, String.valueOf(date));
        ps.setString(4, content);
        ps.setString(5, type);
        ps.execute();
        PreparedStatement ps1 = connection.prepareStatement("select * from commentaries where article_id = ?");
        ps1.setInt(1, article_id);
        ResultSet rs = ps1.executeQuery();
        int id = 1;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }
}

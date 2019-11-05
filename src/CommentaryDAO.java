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

    public Commentary getCommentaryById(String id) throws SQLException, ParseException {
        PreparedStatement ps = connection.prepareStatement("select * from commentaries where id = ?");
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        Commentary e = null;
        while (rs.next()) {
            e = new Commentary(rs.getString("id"), rs.getString("user_id"),
                    rs.getString("article_id"),
                    new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY").parse(rs.getString("date")),
                    rs.getString("content"), rs.getString("type"));
        }
        return e;
    }
    public List<Commentary> getArticleCommentaries(String id, String type) throws SQLException, ParseException {
        PreparedStatement ps = connection.prepareStatement("select * from commentaries where article_id = ? AND type = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        List<Commentary> comms = new ArrayList<>();
        while (rs.next()) {
            comms.add(new Commentary(rs.getString("id"), rs.getString("user_id"),
                    rs.getString("article_id"),
                    new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY").parse(rs.getString("date")),
                    rs.getString("content"), rs.getString("type")));
        }
        return comms;
    }

    public String insert(String user_id, String article_id, Date date, String content, String type) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into commentaries (user_id, article_id, date, content, type) VALUES (?, ?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(user_id));
        ps.setInt(2, Integer.parseInt(article_id));
        ps.setString(3, String.valueOf(date));
        ps.setString(4, content);
        ps.setString(5, type);
        ps.execute();
        PreparedStatement ps1 = connection.prepareStatement("select * from commentaries where article_id = ?");
        ps1.setInt(1, Integer.parseInt(article_id));
        ResultSet rs = ps1.executeQuery();
        String id = "1";
        while (rs.next()) {
            id = rs.getString("id");
        }
        return id;
    }
}

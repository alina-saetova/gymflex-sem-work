import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentaryDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Commentary getCommentaryById(String id) throws SQLException, ParseException {
        ResultSet rs = stmnt.executeQuery("select * from commentaries where id=" + id);
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
        ResultSet rs = stmnt.executeQuery("select * from commentaries where article_id=" + id + " AND type='" + type + "';");
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
        stmnt.executeUpdate("insert into commentaries (user_id, article_id, date, content, type) VALUES ('" +
                user_id +"', '" + article_id + "', '" + date + "', '" + content + "', '" + type + "')");
        ResultSet rs = stmnt.executeQuery("select * from commentaries where article_id=" + article_id);
        String id = "1";
        while (rs.next()) {
            id = rs.getString("id");
        }
        return id;
    }
}

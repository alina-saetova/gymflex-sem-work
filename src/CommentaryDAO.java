import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                    rs.getString("content"));
        }
        return e;
    }
    public List<Commentary> getArticleCommentaries(String id) throws SQLException, ParseException {
        ResultSet rs = stmnt.executeQuery("select * from commentaries where article_id=" + id);
        List<Commentary> comms = new ArrayList<>();
        while (rs.next()) {
            comms.add(new Commentary(rs.getString("id"), rs.getString("user_id"),
                    rs.getString("article_id"),
                    new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY").parse(rs.getString("date")),
                    rs.getString("content")));
        }
        return comms;
    }
}

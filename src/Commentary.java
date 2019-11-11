import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commentary {

    private int id;
    private User user;
    private int article_id;
    private String type;
    private Date date;
    private String content;
    private String dateString;
    private UserDAO ud = new UserDAO();

    public String getDateString() {
        return dateString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(int user_id) throws SQLException {
        user = ud.getUserById(user_id);
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id='" + id + '\'' +
                ", user_id='" + user.getId() + '\'' +
                ", article_id='" + article_id + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Commentary(int id, int user_id, int article_id, Date date, String content, String type) throws ParseException, SQLException {
        this.id = id;
        user = ud.getUserById(user_id);
        this.article_id = article_id;
        this.date = date;
        this.content = content;
        this.type = type;
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        dateString = f.format(date);
    }

}

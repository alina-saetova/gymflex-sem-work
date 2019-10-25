import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commentary {

    private String id;
    private String user_id;
    private String article_id;

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
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
                ", user_id='" + user_id + '\'' +
                ", article_id='" + article_id + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Commentary(String id, String user_id, String article_id, Date date, String content) throws ParseException {
        this.id = id;
        this.user_id = user_id;
        this.article_id = article_id;
        this.date = date;
        this.content = content;
    }



}

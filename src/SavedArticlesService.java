import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavedArticlesService {

    ExerciseDAO ed = new ExerciseDAO();
    TrainingDAO td = new TrainingDAO();
    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteArticle(int article_id, int user_id, String type) throws SQLException {
        PreparedStatement ps;
        if (type.equals("exercise")) {
            ps = connection.prepareStatement(
                    "delete from fav_exercise_user where exercise_id = ? and user_id = ?"
            );
        }
        else {
            ps = connection.prepareStatement(
                    "delete from fav_training_user where training_id = ? and user_id = ?"
            );
        }
        ps.setInt(1, article_id);
        ps.setInt(2, user_id);
        ps.execute();
        updateLikes(article_id, type);
    }

    public void updateLikes(int article_id, String type) throws SQLException {
        int likes;
        PreparedStatement ps;
        if (type.equals("exercise")) {
            likes = ed.getExerciseById(article_id).getCnt_likes() - 1;
            ps = connection.prepareStatement("update exercises set likes = ?  where id = ?");
        }
        else {
            likes = td.getTrainingById(article_id).getCnt_likes() - 1;
            ps = connection.prepareStatement("update trainings set cnt_likes = ?  where id = ?");
        }
        ps.setInt(1, likes);
        ps.setInt(2, article_id);
        ps.execute();
    }
}

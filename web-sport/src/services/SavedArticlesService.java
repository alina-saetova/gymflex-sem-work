package services;

import dao.ExerciseDAO;
import dao.TrainingDAO;
import helpers.ConnectionToDatabase;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public String checkLike(int user_id, int id, String type) throws SQLException {
        PreparedStatement ps;
        if (type.equals("training")) {
            ps = connection.prepareStatement("select * from fav_training_user " +
                    "where training_id = ? AND user_id = ?");
        }
        else {
            ps = connection.prepareStatement("select * from fav_exercise_user " +
                    "where exercise_id = ? AND user_id = ?");
        }
        ps.setInt(1, id);
        ps.setInt(2, user_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<Integer> getSavedIds(User user, String type) throws SQLException {
        PreparedStatement ps;
        if (type.equals("training")) {
            ps = connection.prepareStatement("select * from fav_training_user where user_id = ?");
        }
        else {
            ps = connection.prepareStatement("select * from fav_exercise_user where user_id = ?");
        }
        ps.setInt(1, user.getId());
        List<Integer> ids = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        if (type.equals("training")) {
            while (rs.next()) {
                ids.add(rs.getInt("training_id"));
            }
        }
        else {
            while (rs.next()) {
                ids.add(rs.getInt("exercise_id"));
            }
        }
        return ids;
    }
}

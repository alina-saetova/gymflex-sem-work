import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Training getTrainingById(String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from trainings where id = ?");
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        Training t = null;
        while (rs.next()) {
            t = new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location"));
        }
        return t;
    }


    public List<Training> getAllTrainings() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from trainings");
        ResultSet rs = ps.executeQuery();
        List<Training> trs = new ArrayList<>();
        while (rs.next()) {
            trs.add(new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location")));
        }
        return trs;
    }

    public List<Training> getTrainingsByType(String gender, String purpose, String location) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from trainings where gender = ? AND purpose = ? AND location = ?");
        ps.setString(1, gender);
        ps.setString(2, purpose);
        ps.setString(3, location);
        ResultSet rs = ps.executeQuery();
        List<Training> trs = new ArrayList<>();
        while (rs.next()) {
            trs.add(new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location")));
        }
        return trs;
    }

    public void updateLikes(String trainings_id) throws SQLException {
        int likes = getTrainingById(trainings_id).getCnt_likes() + 1;
        PreparedStatement ps = connection.prepareStatement("update trainings set cnt_likes = ? where id = ?");
        ps.setInt(1, likes);
        ps.setInt(2, Integer.parseInt(trainings_id));
        ps.execute();
    }
}

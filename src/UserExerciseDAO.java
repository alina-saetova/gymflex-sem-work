import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserExerciseDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionToDatabase.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createExercise(String utraining_id, String name, String reps) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into uexercises (utraining_id, name, reps) " +
                "VALUES (?, ?, ?)");
        ps.setInt(1, Integer.parseInt(utraining_id));
        ps.setString(2, name);
        ps.setString(3, reps);
        ps.execute();
    }

    public List<UserExercise> getExercisesFromUserTraining(String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from uexercises where utraining_id = ?");
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        List<UserExercise> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new UserExercise(rs.getString("id"), rs.getString("utraining_id"),
                    rs.getString("name"), rs.getString("reps")));
        }
        return list;
    }

    public void deleteUserExercisesFromTraining(String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from uexercises where utraining_id = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.execute();
    }
}

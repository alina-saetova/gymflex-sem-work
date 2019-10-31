import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserExerciseDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createExercise(String utraining_id, String name, String reps) throws SQLException {
        stmnt.executeUpdate("insert into uexercises (utraining_id, name, reps) " +
                "VALUES (" + utraining_id + ", '" + name + "', '" + reps + "');");
    }

    public List<UserExercise> getExercisesFromUserTraining(String id) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from uexercises where utraining_id=" + id + ";");
        List<UserExercise> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new UserExercise(rs.getString("id"), rs.getString("utraining_id"),
                    rs.getString("name"), rs.getString("reps")));
        }
        return list;
    }
}

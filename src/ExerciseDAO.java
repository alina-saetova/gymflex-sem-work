import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Exercise getExerciseById(String id) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from exercises where id=" + id);
        Exercise e = null;
        while (rs.next()) {
            e = new Exercise(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("likes")),
                    rs.getString("photo"), rs.getString("type"));
        }
        return e;
    }

    public List<Exercise> getAllExercises() throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from exercises");
        List<Exercise> ex = new ArrayList<>();
        while (rs.next()) {
            ex.add(new Exercise(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("likes")),
                    rs.getString("photo"), rs.getString("type")));
        }
        return ex;
    }

    public List<Exercise> getExercisesByType(String type) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from exercises where type = '" + type + "'");
        List<Exercise> exercises = new ArrayList<>();
        while (rs.next()) {
            exercises.add(new Exercise(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("likes")),
                    rs.getString("photo"), rs.getString("type")));
        }
        return exercises;
    }
}

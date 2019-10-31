import java.sql.SQLException;
import java.sql.Statement;

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
}

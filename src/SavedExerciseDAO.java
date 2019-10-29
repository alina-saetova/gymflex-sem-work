import org.apache.taglibs.standard.tlv.JstlBaseTLV;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SavedExerciseDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String checkLike(String user_id, String exercise_id) throws SQLException {
        if (user_id == null) {
            return "no_auth";
        }
        ResultSet rs = stmnt.executeQuery("select * from fav_exercise_user " +
                "where exercise_id = " + exercise_id + " AND user_id = " + user_id + ";");
        if (rs.next()) {
            return "true";
        }
        return "false";
    }

    public List<String> getSavedExercisesId(User user) throws SQLException {
        List<String> ids = new ArrayList<>();
        ResultSet rs = stmnt.executeQuery("select * from fav_exercise_user where user_id = " + user.getId());
        while (rs.next()) {
            ids.add(rs.getString("exercise_id"));
        }
        return ids;
    }
}

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {

    private Statement stmnt;

    {
        try {
            stmnt = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Training getTrainingById(String id) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from trainings where id=" + id);
        Training t = null;
        while (rs.next()) {
            t = new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location"));
        }
        return t;
    }


    public List<Training> getAllTrainings() throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from trainings");
        List<Training> trs = new ArrayList<>();
        while (rs.next()) {
            trs.add(new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location")));
        }
        return trs;
    }

    public List<Training> getTrainingsByType(String gender, String purpose, String location) throws SQLException {
        ResultSet rs = stmnt.executeQuery("select * from trainings where gender = '" + gender + "' AND purpose = '" + purpose
                + "' AND location = '" + location + "'");
        List<Training> trs = new ArrayList<>();
        while (rs.next()) {
            trs.add(new Training(rs.getString("id"), rs.getString("name"),
                    rs.getString("info"), Integer.parseInt(rs.getString("cnt_likes")),
                    rs.getString("gender"), rs.getString("purpose"), rs.getString("location")));
        }
        return trs;
    }
}

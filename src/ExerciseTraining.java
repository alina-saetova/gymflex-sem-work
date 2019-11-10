import java.sql.SQLException;

public class ExerciseTraining {

    private Exercise exercise;
    private Training training;
    private TrainingDAO td = new TrainingDAO();
    private ExerciseDAO ed = new ExerciseDAO();

    @Override
    public String toString() {
        return "ExerciseTrainingServlet{" +
                "exercise=" + exercise +
                ", training=" + training +
                '}';
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(int exercise_id) throws SQLException {
        exercise = ed.getExerciseById(exercise_id);
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(int training_id) throws SQLException {
        training = td.getTrainingById(training_id);
    }

    public ExerciseTraining(int exercise_id, int training_id) throws SQLException {
        exercise = ed.getExerciseById(exercise_id);
        training = td.getTrainingById(training_id);
    }
}

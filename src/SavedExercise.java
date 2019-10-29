public class SavedExercise {

    private String exercise_id;
    private String user_id;

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SavedExercise{" +
                "exercise_id='" + exercise_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public SavedExercise(String exercise_id, String user_id) {
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }
}

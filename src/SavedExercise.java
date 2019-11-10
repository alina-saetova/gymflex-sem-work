public class SavedExercise {

    private int exercise_id;
    private int user_id;

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SavedExercise{" +
                "exercise_id='" + exercise_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public SavedExercise(int exercise_id, int user_id) {
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }
}

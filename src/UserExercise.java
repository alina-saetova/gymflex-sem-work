public class UserExercise {

    private String id;
    private String utraining_id;
    private String name;
    private String reps;

    public UserExercise(String id, String utraining_id, String name, String reps) {
        this.id = id;
        this.utraining_id = utraining_id;
        this.name = name;
        this.reps = reps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUtraining_id() {
        return utraining_id;
    }

    public void setUtraining_id(String utraining_id) {
        this.utraining_id = utraining_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "UserExercise{" +
                "id='" + id + '\'' +
                ", utraining_id='" + utraining_id + '\'' +
                ", name='" + name + '\'' +
                ", reps='" + reps + '\'' +
                '}';
    }


}

package models;

public class UserExercise {

    private int id;
    private int utraining_id;
    private String name;
    private String reps;

    public UserExercise(int id, int utraining_id, String name, String reps) {
        this.id = id;
        this.utraining_id = utraining_id;
        this.name = name;
        this.reps = reps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtraining_id() {
        return utraining_id;
    }

    public void setUtraining_id(int utraining_id) {
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
        return "models.UserExercise{" +
                "id='" + id + '\'' +
                ", utraining_id='" + utraining_id + '\'' +
                ", name='" + name + '\'' +
                ", reps='" + reps + '\'' +
                '}';
    }


}

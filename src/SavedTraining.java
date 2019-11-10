public class SavedTraining {

    private int training_id;
    private int user_id;

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SavedTraining{" +
                "training_id='" + training_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public SavedTraining(int training_id, int user_id) {
        this.training_id = training_id;
        this.user_id = user_id;
    }

}

public class SavedTraining {

    private String training_id;
    private String user_id;

    public String getTraining_id() {
        return training_id;
    }

    public void setTraining_id(String training_id) {
        this.training_id = training_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SavedTraining{" +
                "training_id='" + training_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public SavedTraining(String training_id, String user_id) {
        this.training_id = training_id;
        this.user_id = user_id;
    }

}

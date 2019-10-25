public class Training {

    String id;
    String name;
    String info;
    int cnt_likes;
    String gender;
    String purpose;
    String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCnt_likes() {
        return cnt_likes;
    }

    public void setCnt_likes(int cnt_likes) {
        this.cnt_likes = cnt_likes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ifo='" + info + '\'' +
                ", cnt_likes='" + cnt_likes + '\'' +
                ", gender='" + gender + '\'' +
                ", purpose='" + purpose + '\'' +
                ", location='" + location + '\'' +
                '}';
    }



    public Training(String id, String name, String info, int cnt_likes, String gender, String purpose, String location) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.cnt_likes = cnt_likes;
        this.gender = gender;
        this.purpose = purpose;
        this.location = location;
    }

}

package models;

public class Training {

    private int id;
    private String name;
    private String info;
    private int cnt_likes;
    private String gender;
    private String purpose;
    private String location;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
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
        return "models.Training{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ifo='" + info + '\'' +
                ", cnt_likes='" + cnt_likes + '\'' +
                ", gender='" + gender + '\'' +
                ", purpose='" + purpose + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Training(int id, String name, String info, int cnt_likes, String gender, String purpose, String location, String photo) {
        this.id = id;
        this.name = name;
//        formatInfo(info);
        this.info = info;
        this.cnt_likes = cnt_likes;
        this.gender = gender;
        this.purpose = purpose;
        this.location = location;
        this.photo = photo;
    }

//    public void formatInfo(String input) {
//        String f = "";
//        int cur = 0;
//        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i) == '$') {
//                f += input.substring(cur, i) + "\n";
//                cur = i + 1;
//            }
//        }
//        info = f;
//        System.out.println(info);
//    }

}

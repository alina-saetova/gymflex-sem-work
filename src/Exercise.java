public class Exercise {

    private int id;
    private String name;
    private String info;
    private int cnt_likes;
    private String photo;
    private String type;

    @Override
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", cnt_likes=" + cnt_likes +
                ", photo='" + photo + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Exercise(int id, String name, String info, int cnt_likes, String photo, String type) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.cnt_likes = cnt_likes;
        this.photo = photo;
        this.type = type;
    }

}

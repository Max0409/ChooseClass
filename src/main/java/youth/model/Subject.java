package youth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author:MAX
*/
@Entity
@Table(name = "subject")
public class Subject {


    @Column(name = "C_ID")
    @Id
    private String id;
    @Column(name = "C_NAME")
    private String name;
    @Column(name = "C_TIME")
    private String time;
    @Column(name = "SCORE")
    private String score;
    @Column(name = "TEACHER")
    private String teacher;
    @Column(name = "LOCATION")
    private String location;


    public Subject(){

    }

    public Subject(String id, String name, String time, String score, String teacher, String location) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.score = score;
        this.teacher = teacher;
        this.location = location;

    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public char getShare() {
//        return share;
//    }
//
//    public void setShare(char share) {
//        this.share = share;
//    }
//}

}
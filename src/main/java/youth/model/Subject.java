package youth.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* @author:MAX
*/
@Entity
@Table(name = "subject")
@XmlRootElement(name = "b:课程")
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

    @Column(name = "C_SHARE_N")
    private String share;


    @Column(name = "CHOOSEN")
    private String isChosen;


    public Subject(){

    }

    public Subject(String id, String name, String time, String score, String teacher, String location,String share) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.score = score;
        this.teacher = teacher;
        this.location = location;
        this.share=share;


    }

    public String isChosen() {
        return isChosen;
    }

    public void setChosen(String chosen) {
        isChosen = chosen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @XmlElement(name="b:名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name="b:课时")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @XmlElement(name="b:学分")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    @XmlElement(name="b:老师")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    @XmlElement(name="b:地点")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @XmlElement(name="b:共享")
    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }
}


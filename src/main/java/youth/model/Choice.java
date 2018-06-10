package youth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author:MAX
*/
@Entity
@Table(name = "choice")
public class Choice {


    @Id
    @Column(name = "S_ID")
    private String sId;

    @Column(name = "C_ID")
    private String cId;

    @Column(name = "SCORE")
    private String score;

    @Column(name = "SCHOOL")
    private String school;

    public Choice(){

    }
    public Choice(String sId, String cId) {
        this.sId = sId;
        this.cId = cId;
    }

    public Choice(String sId, String cId, String score, String school) {
        this.sId = sId;
        this.cId = cId;
        this.score = score;
        this.school = school;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}


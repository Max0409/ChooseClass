package youth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author:MAX
*/
@Entity
@Table(name = "student")
public class Student {
    @Column(name = "S_ID")
    @Id
    private String sId;
    @Column(name = "S_NAME")
    private String sName;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "MAJOR")
    private String major;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "S_LEVEL")
    private String sLevel;
    @Column(name = "U_NAME")
    private String uName;

    public Student(String sId, String sName, String gender, String major, String password, String level, String userName) {
        this.sId = sId;
        this.sName = sName;
        this.gender = gender;
        this.major = major;
        this.password = password;
        this.sLevel = level;
        this.uName = userName;


    }


    public Student(){

    }


    public Student(String sId, String sName, String gender, String major, String password) {
        this.sId = sId;
        this.sName = sName;
        this.gender = gender;
        this.major = major;
        this.password = password;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return sLevel;
    }

    public void setLevel(String level) {
        this.sLevel = level;
    }

    public String getUserName() {
        return uName;
    }

    public void setUserName(String userName) {
        this.uName = userName;
    }

}


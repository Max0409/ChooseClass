package youth.bean;

import youth.model.Subject;

import javax.persistence.Column;

public class SubjectVO {

/*
* @author:MAX
*/

    private String id;


    private String name;

    private String time;

    private String score;

    private String teacher;

    private String location;


    private String share;



    private boolean isChosen;


    public SubjectVO(String id, String name, String time, String score, String teacher, String location,String share) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.score = score;
        this.teacher = teacher;
        this.location = location;
        this.share=share;


    }



    public SubjectVO(){}


    public void toSubjectVO(Subject subject){

        this.id=subject.getId();
        this.name=subject.getName();
        this.time=subject.getTime();
        this.score=subject.getScore();
        this.teacher=subject.getTeacher();
        this.location=subject.getLocation();
        this.share=subject.getShare();
        if(subject.getIsChosen().equals("false")){
            this.isChosen=false;
        }else {
            this.isChosen=true;

        }

    }


}


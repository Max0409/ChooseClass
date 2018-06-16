package youth.bean;

import org.springframework.beans.factory.annotation.Autowired;
import youth.dao.ChoiceRepository;
import youth.model.Subject;

public class SubjectCount {

/*
* @author:MAX
*/

    @Autowired
    private final ChoiceRepository choiceRepository;


Subject subject;
public  int count=0;

    public SubjectCount(ChoiceRepository choiceRepository, Subject subject) {
        this.choiceRepository = choiceRepository;

        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }



    public void  getCount(){
        this.count=choiceRepository.countAllByCId(subject.getId());
    }


}


package youth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.model.Choice;
import youth.model.Subject;

import javax.transaction.Transactional;

/*
* @author:MAX
*/
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

    @Transactional
    void deleteBySIdAndCId(String sid,String cid);

    Choice findBySIdAndCId(String sid,String cid);







}


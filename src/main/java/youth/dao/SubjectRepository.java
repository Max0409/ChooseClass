package youth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.model.Subject;

import javax.transaction.Transactional;

/*
* @author:MAX
*/
public interface SubjectRepository extends JpaRepository<Subject, Integer> {


    @Transactional
    void deleteById(String id);






}


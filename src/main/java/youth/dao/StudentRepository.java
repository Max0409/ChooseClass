package youth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.model.Student;
import youth.model.Subject;

/*
* @author:MAX
*/
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findBySId(String id);
    Student findBySIdAndPassword(String sid,String password);






}


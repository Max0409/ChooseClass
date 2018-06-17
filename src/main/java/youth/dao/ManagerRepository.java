package youth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.model.Manager;
import youth.model.Student;
/*
* @author:MAX
*/

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findByName(String name);




}


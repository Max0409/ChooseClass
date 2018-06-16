package youth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/*
* @author:MAX
*/
@Entity
@Table(name = "MANAGER")
public class Manager {
    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;


    public Manager(){

    }

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


package panda.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Feng on 02-Mar-15.
 */
@Entity
@Table(name = "test")
public class Test {
    
    @Id
    @Column(name = "test")
    private String test;
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
}
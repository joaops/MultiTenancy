package panda.repo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Feng on 02-Mar-15.
 */
@Entity
public class Test {
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Id
    private String test;
}

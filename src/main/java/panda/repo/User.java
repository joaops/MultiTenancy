package panda.repo;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Feng on 01-Mar-15.
 */

@Entity
public class User {

    @Id
    private String username;

    private String password;
    private boolean enabled;
    private String role;
    private String db;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

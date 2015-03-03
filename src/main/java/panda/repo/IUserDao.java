package panda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Feng on 02-Mar-15.
 */
public interface IUserDao extends JpaRepository<User, String> {
}

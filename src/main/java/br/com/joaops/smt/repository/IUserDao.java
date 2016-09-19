package br.com.joaops.smt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joaops.smt.model.User;

public interface IUserDao extends JpaRepository<User, String> {
    
}
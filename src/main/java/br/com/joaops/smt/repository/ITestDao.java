package br.com.joaops.smt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joaops.smt.model.Test;

public interface ITestDao extends JpaRepository<Test, String> {
    
}
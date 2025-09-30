package com.auriel.auriel_financas.repository;
import com.auriel.auriel_financas.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {

}

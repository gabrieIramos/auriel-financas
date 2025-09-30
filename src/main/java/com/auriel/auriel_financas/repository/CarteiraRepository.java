package com.auriel.auriel_financas.repository;
import com.auriel.auriel_financas.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    List<Carteira> findByUsuarioId(Long usuarioId);


}

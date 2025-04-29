package co.edu.usco.backendparcial.repository;

import co.edu.usco.backendparcial.model.AsignatureEntity;
import co.edu.usco.backendparcial.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignatureRepository extends JpaRepository<AsignatureEntity, Long> {
    List<AsignatureEntity> findByProfessor(UserEntity professor);
}

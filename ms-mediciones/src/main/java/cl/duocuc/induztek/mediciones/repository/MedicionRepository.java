package cl.duocuc.induztek.mediciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duocuc.induztek.mediciones.model.Medicion;

public interface MedicionRepository extends JpaRepository<Medicion, Long> {

	List<Medicion> findByProtocoloId(Long protocoloId);
}

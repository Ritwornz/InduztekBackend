package cl.duocuc.induztek.protocolos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duocuc.induztek.protocolos.model.Protocolo;

public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

	Optional<Protocolo> findByCodigoProtocolo(String codigoProtocolo);

	boolean existsByCodigoProtocolo(String codigoProtocolo);

	boolean existsByCodigoProtocoloAndIdNot(String codigoProtocolo, Long id);

	List<Protocolo> findByClienteId(Long clienteId);
}

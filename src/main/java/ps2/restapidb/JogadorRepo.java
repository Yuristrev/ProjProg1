package ps2.restapidb;

import org.springframework.data.repository.CrudRepository;

public interface JogadorRepo extends CrudRepository<Jogador, Long> {
	Iterable<Jogador> findByCampeonatoId(Long campeonatoId);
}
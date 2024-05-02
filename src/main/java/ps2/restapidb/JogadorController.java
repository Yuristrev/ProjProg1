package ps2.restapidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class JogadorController {
    @Autowired
	private JogadorRepo jogadorRepo;

	public JogadorController() {

	}

	@GetMapping("/api/jogador")
	Iterable<Jogador> getJogador(@RequestParam Optional<Long> campeonatoId) {
		if (campeonatoId.isEmpty()) {
			return jogadorRepo.findAll();
		}
		return jogadorRepo.findByCampeonatoId(campeonatoId.get());
	}
	
	@GetMapping("/api/jogador/{id}")
	Optional<Jogador> getJogador(@PathVariable long id) {
		return jogadorRepo.findById(id);
	}
	
	@PostMapping("/api/jogador")
	Jogador createJogador(@RequestBody Jogador j) {
		Jogador createdJoga = jogadorRepo.save(j);
		return createdJoga;
	}
	
	@PutMapping("/api/jogador/{jogadorId}")
	Optional<Jogador> updateJogador(@RequestBody Jogador jogadorRequest, @PathVariable long jogadorId) {
		Optional<Jogador> opt = jogadorRepo.findById(jogadorId);
		if (opt.isPresent()) {
			if (jogadorRequest.getId() == jogadorId) {
				jogadorRepo.save(jogadorRequest);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do jogador com id " + jogadorId);	
	}	
	
	@DeleteMapping(value = "/api/jogador/{id}")
	void deleteJogador(@PathVariable long id) {
		jogadorRepo.deleteById(id);
	}		
}



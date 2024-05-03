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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CampeonatoController {

	@Autowired
	private CampeonatoRepo campeonatoRepo;

	@GetMapping("/api/campeonatos")
	Iterable<Campeonato> getCampeonatos() {
		return campeonatoRepo.findAll();
	}
	
	@GetMapping("/api/campeonatos/{id}")
	Optional<Campeonato> getCampeonato(@PathVariable long id) {
		return campeonatoRepo.findById(id);
	}
	
	@PostMapping("/api/campeonatos")
	Campeonato createCampeonato(@RequestBody Campeonato f) {
		Campeonato createdFac = campeonatoRepo.save(f);
		return createdFac;
	}
	
	@PutMapping("/api/campeonatos/{campeonatoId}")
	Optional<Campeonato> updateCampeonato(@RequestBody Campeonato campeonatoReq, @PathVariable long campeonatoId) {
		Optional<Campeonato> opt = campeonatoRepo.findById(campeonatoId);
		if (opt.isPresent()) {
			if (campeonatoReq.getId() == campeonatoId) {
				campeonatoRepo.save(campeonatoReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da campeonato com id " + campeonatoId);
	}	
	
	@DeleteMapping("/api/campeonatos/{id}")
	void deleteCampeonato(@PathVariable long id) {
		campeonatoRepo.deleteById(id);
	}	
	
}

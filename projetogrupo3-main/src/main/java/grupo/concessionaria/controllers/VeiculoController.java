package grupo.concessionaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo.concessionaria.entities.Veiculo;
import grupo.concessionaria.services.VeiculoService;

@RequestMapping(value = "/Veiculo")
@RestController

public class VeiculoController {
	@Autowired
	private VeiculoService service;

	@GetMapping
	public List<Veiculo> procurarTodos() {
		return service.procurarTodos();
	}

	@GetMapping(value = "/{id}")
	public Veiculo procurarPortodos(@PathVariable Integer id) {
		return service.procurarPorId(id);
	}

	@PostMapping
	public String adicionarVeiculo(@RequestBody Veiculo veiculo) {
		String response = service.adicionarVeiculo(veiculo);
		return response;
	}

	@PutMapping(value = "/{id}")
	public String editarrVeiculo(@PathVariable Integer id, @RequestBody Veiculo veiculo) {
		String response = service.editarVeiculo(id, veiculo);
		return response;
	}

	@DeleteMapping("/{id}")
	public void excluirVeiculo(@PathVariable Integer id) {
		service.excluirVeiculo(id);

	}

}

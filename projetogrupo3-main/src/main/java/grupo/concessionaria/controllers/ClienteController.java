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

import grupo.concessionaria.entities.Cliente;
import grupo.concessionaria.services.ClienteService;

@RequestMapping(value = "/Cliente")
@RestController

public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping
	public List<Cliente> procurarTodos() {
		return service.procurarTodos();
	}

	@GetMapping(value = "/{id}")
	public Cliente procurarPortodos(@PathVariable Integer id) {
		return service.procurarPorId(id);
	}

	@PostMapping
	public String adicionarCliente(@RequestBody Cliente cliente) {
		String response = service.adicionarCliente(cliente);
		return response;
	}

	@PutMapping(value = "/{id}")
	public String editarrCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
		String response = service.editarCliente(id, cliente);
		return response;
	}

	@DeleteMapping("/{id}")
	public void excluirCliente(@PathVariable Integer id) {
		service.excluirCliente(id);

	}

}

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

import grupo.concessionaria.entities.Vendedor;
import grupo.concessionaria.services.VendedorService;

@RequestMapping(value = "/Vendedor")
@RestController

public class VendedorController {
	@Autowired
	private VendedorService service;

	@GetMapping
	public List<Vendedor> procurarTodos() {
		return service.procurarTodos();
	}

	@GetMapping(value = "/{id}")
	public Vendedor procurarPortodos(@PathVariable Integer id) {
		return service.procurarPorId(id);
	}

	@PostMapping
	public String adicionarVendedor(@RequestBody Vendedor vendedor) {
		String response = service.adicionarVendedor(vendedor);
		return response;
	}

	@PutMapping(value = "/{id}")
	public String editarrVendedor(@PathVariable Integer id, @RequestBody Vendedor vendedor) {
		String response = service.editarVendedor(id, vendedor);
		return response;
	}

	@DeleteMapping("/{id}")
	public void excluirVendedor(@PathVariable Integer id) {
		service.excluirVendedor(id);

	}

}

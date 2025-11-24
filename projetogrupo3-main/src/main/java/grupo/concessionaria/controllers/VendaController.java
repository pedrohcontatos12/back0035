package grupo.concessionaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo.concessionaria.entities.Venda;
import grupo.concessionaria.services.VendaService;

@RequestMapping(value = "/Venda")
@RestController

public class VendaController {
	@Autowired
	private VendaService service;

	@GetMapping
	public List<Venda> procurarTodos() {
		return service.procurarTodos();
	}

	@GetMapping(value = "/{id}")
	public Venda procurarPortodos(@PathVariable Integer id) {
		return service.procurarPorId(id);
	}

	@PostMapping
	public ResponseEntity<VendaService.VendaResponseDTO> registrar(
			@RequestBody VendaService.RegistrarVendaRequestDTO dto) {
		var resposta = service.registrarVenda(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}

	@PutMapping("/vendas/{id}")
	public ResponseEntity<VendaService.VendaResponseDTO> editarVenda(@PathVariable Integer id,
			@RequestBody VendaService.AtualizarVendaRequestDTO dto) {

		VendaService.VendaResponseDTO response = service.editarVenda(id, dto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public void excluirVenda(@PathVariable Integer id) {
		service.excluirVenda(id);

	}

}

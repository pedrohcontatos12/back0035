package grupo.concessionaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo.concessionaria.entities.Cliente;
import grupo.concessionaria.repositories.ClienteRepository;

@Service
public class ClienteService {
	
@Autowired
private ClienteRepository repository;

public List<Cliente> procurarTodos(){
	return repository.findAll();
}
public Cliente procurarPorId (Integer id) {
	return repository.findById(id).get();
}

public String adicionarCliente(Cliente cliente) {
	repository.save (cliente);
	return "Véiculo alugado com sucesso";
}

public String editarCliente(Integer id,Cliente cliente){ 
		Cliente response= repository.findById (id).get();
	
		response.setNome(cliente.getNome());
		response.setCpf(cliente.getCpf());
		response.setTelefone(cliente.getTelefone());
		response.setEmail(cliente.getEmail());

		repository.save(response);
		return" Cliente editado com sucesso!";		
}
public String excluirCliente(Integer id) {
	Cliente response = repository.findById(id).get();
	repository.delete(response);
	return "Cliente excluido com sucesso!";
	}

}

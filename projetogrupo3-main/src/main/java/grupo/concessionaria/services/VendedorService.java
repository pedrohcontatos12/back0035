package grupo.concessionaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo.concessionaria.entities.Vendedor;
import grupo.concessionaria.repositories.VendedorRepository;

@Service
public class VendedorService {
	
@Autowired
private VendedorRepository repository;

public List<Vendedor> procurarTodos(){
	return repository.findAll();
}
public Vendedor procurarPorId (Integer id) {
	return repository.findById(id).get();
}

public String adicionarVendedor(Vendedor vendedor) {
	repository.save (vendedor);
	return "Véiculo alugado com sucesso";
}

public String editarVendedor(Integer id,Vendedor vendedor){ 
		Vendedor response= repository.findById (id).get();
		response.setNome(vendedor.getNome());
		response.setCpf(vendedor.getCpf());
		response.setMatricula(vendedor.getMatricula());
		response.setPorcentualComissao(vendedor.getPorcentualComissao());

		repository.save(response);
		return" Vendedor editado com sucesso!";		
}
public String excluirVendedor(Integer id) {
	Vendedor response = repository.findById(id).get();
	repository.delete(response);
	return "Vendedor excluido com sucesso!";
	}

}

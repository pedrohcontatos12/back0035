package grupo.concessionaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo.concessionaria.entities.Veiculo;
import grupo.concessionaria.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
@Autowired
private VeiculoRepository repository;

public List<Veiculo> procurarTodos(){
	return repository.findAll();
}
public Veiculo procurarPorId (Integer id) {
	return repository.findById(id).get();
}
public String adicionarVeiculo(Veiculo veiculo) {
	repository.save (veiculo);
	return "Véiculo alugado com sucesso";
}

public String editarVeiculo(Integer id,Veiculo veiculo){ 
		Veiculo response= repository.findById (id).get();
		
		response.setModelo(veiculo.getModelo());
		response.setPreco(veiculo.getPreco());
		response.setCor(veiculo.getCor());
		repository.save(response);
		return" Veiculo editado com sucesso!";		
}
public String excluirVeiculo(Integer id) {
	Veiculo response = repository.findById(id).get();
	repository.delete(response);
	return "veiculo excluido com sucesso!";
	}

}

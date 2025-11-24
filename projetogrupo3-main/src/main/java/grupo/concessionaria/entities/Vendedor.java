package grupo.concessionaria.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Vendedor")
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 100) // length é igual a quantidade de caracterer ou quantidade de digitos
	private String nome;
	@Column(nullable = false, length = 11)
	private String cpf;
	@Column(nullable = false, length = 11)
	private String matricula;
	@Column(nullable = false, length = 100)
	private Integer porcentualComissao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getPorcentualComissao() {
		return porcentualComissao;
	}

	public void setPorcentualComissao(Integer porcentualComissao) {
		this.porcentualComissao = porcentualComissao;
	}

}


package grupo.concessionaria.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo.concessionaria.entities.Cliente;
import grupo.concessionaria.entities.Venda;
import grupo.concessionaria.entities.Veiculo;
import grupo.concessionaria.entities.Vendedor;
import grupo.concessionaria.repositories.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final ClienteService clienteService;
	private final VendedorService vendedorService;
	private final VeiculoService veiculoService;

	public VendaService(VendaRepository vendaRepository, ClienteService clienteService, VendedorService vendedorService,
			VeiculoService veiculoService) {
		this.vendaRepository = vendaRepository;
		this.clienteService = clienteService;
		this.vendedorService = vendedorService;
		this.veiculoService = veiculoService;
	}

	public List<Venda> procurarTodos() {
		return vendaRepository.findAll();
	}

	public Venda procurarPorId(Integer id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Venda não encontrada para o ID: " + id));
	}

	@Transactional
	public VendaResponseDTO registrarVenda(RegistrarVendaRequestDTO dto) {
		Venda venda = registrarVendaUseCase(dto);
		return toResponseDTO(venda);
	}

	@Transactional
	public VendaResponseDTO editarVenda(Integer id, AtualizarVendaRequestDTO dto) {
		Venda venda = procurarPorId(id);

		if (dto.getValorFinal() != null) {
			venda.setValorFinal(dto.getValorFinal());
		}
		if (dto.getDataVenda() != null) {
			venda.setDataVenda(dto.getDataVenda());
		}

		venda = vendaRepository.save(venda);
		return toResponseDTO(venda);
	}

	@Transactional
	public void excluirVenda(Integer id) {
		Venda venda = procurarPorId(id);
		vendaRepository.delete(venda);
	}

	private Venda registrarVendaUseCase(RegistrarVendaRequestDTO dto) {

		Cliente cliente = clienteService.procurarPorId(dto.getClienteId());
		Vendedor vendedor = vendedorService.procurarPorId(dto.getVendedorId());
		Veiculo veiculo = veiculoService.procurarPorId(dto.getVeiculoId());

		if (!veiculo.getStatus()) {
			throw new IllegalStateException("Veículo não está disponível para venda.");
		}

		LocalDateTime dataVenda = dto.getDataVenda() != null ? dto.getDataVenda() : LocalDateTime.now();

		Venda venda = new Venda(dataVenda, dto.getValorFinal(), cliente, vendedor, veiculo);

		veiculo.setStatus(false);
		veiculoService.editarVeiculo(veiculo.getId(), veiculo);

		return vendaRepository.save(venda);
	}

	public static class RegistrarVendaRequestDTO {

		private Integer clienteId;
		private Integer vendedorId;
		private Integer veiculoId;
		private BigDecimal valorFinal;
		private LocalDateTime dataVenda;

		public Integer getClienteId() {
			return clienteId;
		}

		public void setClienteId(Integer clienteId) {
			this.clienteId = clienteId;
		}

		public Integer getVendedorId() {
			return vendedorId;
		}

		public void setVendedorId(Integer vendedorId) {
			this.vendedorId = vendedorId;
		}

		public Integer getVeiculoId() {
			return veiculoId;
		}

		public void setVeiculoId(Integer veiculoId) {
			this.veiculoId = veiculoId;
		}

		public BigDecimal getValorFinal() {
			return valorFinal;
		}

		public void setValorFinal(BigDecimal valorFinal) {
			this.valorFinal = valorFinal;
		}

		public LocalDateTime getDataVenda() {
			return dataVenda;
		}

		public void setDataVenda(LocalDateTime dataVenda) {
			this.dataVenda = dataVenda;
		}
	}

	public static class AtualizarVendaRequestDTO {

		private BigDecimal valorFinal;
		private LocalDateTime dataVenda;

		public BigDecimal getValorFinal() {
			return valorFinal;
		}

		public void setValorFinal(BigDecimal valorFinal) {
			this.valorFinal = valorFinal;
		}

		public LocalDateTime getDataVenda() {
			return dataVenda;
		}

		public void setDataVenda(LocalDateTime dataVenda) {
			this.dataVenda = dataVenda;
		}
	}

	public static class VendaResponseDTO {

		private Integer id;
		private LocalDateTime dataVenda;
		private BigDecimal valorFinal;
		private Integer clienteId;
		private String nomeCliente;
		private Integer vendedorId;
		private String nomeVendedor;
		private Integer veiculoId;
		private String modeloVeiculo;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public LocalDateTime getDataVenda() {
			return dataVenda;
		}

		public void setDataVenda(LocalDateTime dataVenda) {
			this.dataVenda = dataVenda;
		}

		public BigDecimal getValorFinal() {
			return valorFinal;
		}

		public void setValorFinal(BigDecimal valorFinal) {
			this.valorFinal = valorFinal;
		}

		public Integer getClienteId() {
			return clienteId;
		}

		public void setClienteId(Integer clienteId) {
			this.clienteId = clienteId;
		}

		public String getNomeCliente() {
			return nomeCliente;
		}

		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}

		public Integer getVendedorId() {
			return vendedorId;
		}

		public void setVendedorId(Integer vendedorId) {
			this.vendedorId = vendedorId;
		}

		public String getNomeVendedor() {
			return nomeVendedor;
		}

		public void setNomeVendedor(String nomeVendedor) {
			this.nomeVendedor = nomeVendedor;
		}

		public Integer getVeiculoId() {
			return veiculoId;
		}

		public void setVeiculoId(Integer veiculoId) {
			this.veiculoId = veiculoId;
		}

		public String getModeloVeiculo() {
			return modeloVeiculo;
		}

		public void setModeloVeiculo(String modeloVeiculo) {
			this.modeloVeiculo = modeloVeiculo;
		}
	}

	private VendaResponseDTO toResponseDTO(Venda venda) {
		VendaResponseDTO dto = new VendaResponseDTO();

		dto.setId(venda.getId());
		dto.setDataVenda(venda.getDataVenda());
		dto.setValorFinal(venda.getValorFinal());

		if (venda.getCliente() != null) {
			dto.setClienteId(venda.getCliente().getId());
			dto.setNomeCliente(venda.getCliente().getNome());
		}

		if (venda.getVendedor() != null) {
			dto.setVendedorId(venda.getVendedor().getId());
			dto.setNomeVendedor(venda.getVendedor().getNome());
		}

		if (venda.getVeiculo() != null) {
			dto.setVeiculoId(venda.getVeiculo().getId());
			dto.setModeloVeiculo(venda.getVeiculo().getModelo());
		}

		return dto;

	}
}

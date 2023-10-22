package com.api.ecommerce.services;

import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dto.RelatorioPedidoDTO;
import com.api.ecommerce.entities.Pedido;
import com.api.ecommerce.entities.PedidoItem;
import com.api.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PedidoRepository pedidoR;

	@Autowired
	EmailService emailService;

	public List<Pedido> listarPedidos() {
		return pedidoR.findAll();
	}

	// Este método retorna uma lista de RelatorioPedidoDTO com base nos pedidos
	// encontrados no repositório
	public List<RelatorioPedidoDTO> listarRelatorioPedidoDTO() {
		// Recupera a lista de pedidos do repositório
		List<Pedido> pedidos = pedidoR.findAll();
		// Inicializa uma lista para armazenar os DTOs do relatório
		List<RelatorioPedidoDTO> relatorioDTO = new ArrayList<>();

		// Itera sobre a lista de pedidos
		for (Pedido pedido : pedidos) {
			// Usa o ModelMapper para mapear o objeto Pedido para um RelatorioPedidoDTO
			RelatorioPedidoDTO dto = modelMapper.map(pedido, RelatorioPedidoDTO.class);
			// Adiciona o DTO mapeado à lista de relatório
			relatorioDTO.add(dto);
		}

		// Retorna a lista de DTOs do relatório
		return relatorioDTO;
		/*
		 * OBS sobre o código anterior: No código anterior, os DTOs do relatório eram
		 * criados manualmente, copiando os campos do objeto Pedido para o objeto
		 * RelatorioPedidoDTO. No novo código, a conversão é automatizada usando o
		 * ModelMapper, o que torna o código mais simples e eficiente. Em vez de
		 * escrever manualmente a lógica de cópia, o ModelMapper lida com a tarefa de
		 * mapear os campos, reduzindo a quantidade de código necessário e minimizando a
		 * chance de erros. Isso melhora a legibilidade, a manutenção e a eficiência do
		 * código.
		 * 
		 */

	}

	public Pedido buscarIdPedido(Long id) {
		return pedidoR.findById(id).orElse(null);
	}

	public Pedido salvarPedido(Pedido pedido) {
		// Calcula os valores brutos e líquidos antes de salvar o pedido
		calcularValoresBrutoELiquido(pedido);

		// Prepara o relatório do pedido
		RelatorioPedidoDTO relatorio = new RelatorioPedidoDTO(pedido.getIdPedido(), pedido.getDataPedido(),
				pedido.getValorTotal());

		// Realiza o envio de email
		emailService.enviarEmail("emailhipotetico@gmail.com", "Novo pedido cadastrado", relatorio.toString());

		// Salva o pedido no banco de dados
		return pedidoR.save(pedido);
	}

	private void calcularValoresBrutoELiquido(Pedido pedido) {
		for (PedidoItem item : pedido.getItens()) {
			BigDecimal precoVenda = item.getProduto().getValorUnitario();
			int quantidade = item.getQuantidade();

			// Cálculo do valor bruto
			BigDecimal valorBruto = precoVenda.multiply(BigDecimal.valueOf(quantidade));

			// Verifica se a forma de pagamento é "À Vista" (considere fazer isso insensível
			// a maiúsculas/minúsculas)

			if (pedido.getFormaPagamento().equalsIgnoreCase("À Vista")) {
				// Cálculo do valor líquido com desconto de 10% se a forma de pagamento for "À
				// Vista"
				BigDecimal valorDesconto = valorBruto.multiply(BigDecimal.valueOf(0.10));
				BigDecimal valorLiquido = valorBruto.subtract(valorDesconto);
				item.setValorBruto(valorBruto);
				item.setValorLiquido(valorLiquido);
			} else {
				// Caso contrário, não aplicar desconto
				item.setValorBruto(valorBruto);
				item.setValorLiquido(valorBruto);

			}
		}
	}

	public Pedido atualizarPedido(Pedido pedido) {
		if (pedido == null || buscarIdPedido(pedido.getIdPedido()) == null) {
			return null;
		}

		return pedidoR.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null || buscarIdPedido(pedido.getIdPedido()) == null) {
			return false;
		}

		pedidoR.delete(pedido);

		return buscarIdPedido(pedido.getIdPedido()) == null;

	}
}

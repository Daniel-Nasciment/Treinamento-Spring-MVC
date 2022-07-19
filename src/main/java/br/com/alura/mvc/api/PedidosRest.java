package br.com.alura.mvc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.enuns.StatusPedido;
import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;
import br.com.alura.mvc.response.PedidoResponse;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidosRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(value = "/aguardando")
	// @RequestParam INDICA AO SPRING QUE ESSES ATRIBUTOS VAO VIR NA URL E SÃO OBRIGATÓRIOS
	public ResponseEntity<Page<PedidoResponse>> getPedidosAguardandoOfertas(@RequestParam int pg, @RequestParam int qtd) {
		
		Sort sort = Sort.by("id").descending();
		Pageable paginacao = PageRequest.of(pg, qtd, sort);
		
		Page<Pedido> list = pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
		
		return ResponseEntity.ok(PedidoResponse.toResponse(list));
	}
	
	@GetMapping
	// @RequestParam INDICA AO SPRING QUE ESSES ATRIBUTOS VAO VIR NA URL E SÃO OBRIGATÓRIOS
	public ResponseEntity<Page<PedidoResponse>> getPedidos(@RequestParam int pg, @RequestParam int qtd) {
		
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(pg, qtd, sort);
		
		Page<Pedido> list = pedidoRepository.findAll(paginacao);
		
		return ResponseEntity.ok(PedidoResponse.toResponse(list));
	}

	
}

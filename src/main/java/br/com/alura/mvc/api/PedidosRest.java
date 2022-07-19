package br.com.alura.mvc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<PedidoResponse>> getPedidosAguardandoOfertas() {
		
		
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(0, 2, sort);
		
		List<Pedido> list = pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
		
		return ResponseEntity.ok(PedidoResponse.toResponse(list));
	}

	
}

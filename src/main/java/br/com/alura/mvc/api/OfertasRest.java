package br.com.alura.mvc.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.model.Oferta;
import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.PedidoRepository;
import br.com.alura.mvc.request.OfertaRequest;
import br.com.alura.mvc.response.OfertaResponse;

@RestController
@RequestMapping(value = "/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping(value = "/teste")
	public String getMethodName() {
		return "deu certo";
	}

	@PostMapping
	public ResponseEntity<OfertaResponse> criarOferta(@RequestBody OfertaRequest request) {
		Optional<Pedido> possivelPedido = pedidoRepository.findById(request.getIdPedido());

		if (!possivelPedido.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Oferta oferta = request.toModel();

		Pedido pedido = possivelPedido.get();

		// EM UM RELACIONAMENTO BI DIRECIONAL TENHO QUE FAZER OS OBJETOS SE CONHECEREM ANTES DE SALVAR NO BANCO DE DADOS
		oferta.setPedido(pedido);
		pedido.adicionaOferta(oferta);

		// O RELACIONAMENTO COM OFERTA ESTA COMO CASCADE ALL, NESSE CASO SALVARA
		// AUTOMATICAMENTE A OFERTA
		pedidoRepository.save(pedido);

		return ResponseEntity.ok().build();
	}

}

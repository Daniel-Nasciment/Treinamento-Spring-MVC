package br.com.alura.mvc.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.mvc.model.Oferta;
import br.com.alura.mvc.model.Pedido;
import br.com.alura.mvc.repository.OfertaRepository;
import br.com.alura.mvc.repository.PedidoRepository;
import br.com.alura.mvc.request.OfertaRequest;
import br.com.alura.mvc.response.OfertaResponse;

@RestController
@RequestMapping(value = "/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private OfertaRepository ofertaRepository;


	@PostMapping
	public ResponseEntity<OfertaResponse> criarOferta(@RequestBody @Valid OfertaRequest request) {
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

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OfertaResponse> buscarOfertaPorId(@PathVariable Integer id) {
		
		Optional<Oferta> oferta = ofertaRepository.findById(id);
		
		return ResponseEntity.ok(new OfertaResponse(oferta.get()));
	}

	
	
	@PostMapping(value = "/novaOferta")
	// UriComponentsBuilder É INJETADO AUTOMETICAMENTE PELO SPRING
	public ResponseEntity<OfertaResponse> novaOferta(@RequestBody @Valid OfertaRequest request, UriComponentsBuilder uriBuilder) {
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
		// AUTOMATICAMENTE A OFERTA, POREM O ID DA NOVA OFERTA SALVA NÃO FICA DISPONIVEL(NULL) NESSA TRANSAÇÃO.
		// SE EFETURAR O SAVE DA OFERTA, AI SIM O ID DELA FICA DISPONIVEL
		pedidoRepository.save(pedido);
		ofertaRepository.save(oferta);
		
		URI uri = uriBuilder.path("/api/ofertas/{id}").buildAndExpand(oferta.getId()).toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
	
}

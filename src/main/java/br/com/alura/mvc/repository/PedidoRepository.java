package br.com.alura.mvc.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.enuns.StatusPedido;
import br.com.alura.mvc.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Cacheable(value = "pedidos") // FUNCIONA COMO ID DO CACHE
	Page<Pedido> findByStatus(StatusPedido status, Pageable pageable);

	@Query("select p from Pedido p join p.user u where u.username = :name")
	List<Pedido> findAllByUsername(@Param("name") String name);

	@Query("select p from Pedido p join p.user u where u.username = :name and p.status = :status")
	List<Pedido> findByStatusAndUsername(@Param("status") StatusPedido status,@Param("name") String name);
	
	@Override
	@Cacheable(value = "pedidosAll")
	Page<Pedido> findAll(Pageable pageable);

	
	// É UMA BOA PRATICA USAR CACHE EM TABLEAS EM QUE NUNCA/RARAMENTE É ATUALIZADO ALGUMA INFORMAÇÃO
}

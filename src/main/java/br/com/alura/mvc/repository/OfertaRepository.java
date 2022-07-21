package br.com.alura.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.mvc.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Integer>{

}

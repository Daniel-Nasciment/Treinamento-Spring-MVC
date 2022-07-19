package br.com.alura.mvc.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

// HandlerInterceptorAdapter -> DEPRECATED CLASS

public class InterceptorAccess implements HandlerInterceptor {

	// É IDEAL SALVAR ISSO EM BANCO DE DADOS, POIS AQUI ESTA SALVO EM MEMORIA
	public static List<Acesso> acessos = new ArrayList<>();

	// ANTES DE COMEÇAR O PROCESSAMENTO, ANTES DE CHEGAR NO CONTROLLER/REST
	// CONTROLLER
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Acesso acesso = new Acesso();
		acesso.path = request.getRequestURI();
		acesso.data = LocalDateTime.now();

		request.setAttribute("acesso", acesso);

		return true;

	}

	// DEPOIS QUE A RESPOSTA FOI GERADA
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.duracao = Duration.between(acesso.data, LocalDateTime.now());
		acessos.add(acesso);
		
	}

	// COM ISSO CONSIGO IMPORTAR ESSA CLASSE EM OUTRO LUGAR
	public static class Acesso {
		private String path;
		private LocalDateTime data;
		private Duration duracao;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public LocalDateTime getData() {
			return data;
		}

		public void setData(LocalDateTime data) {
			this.data = data;
		}

		public Duration getDuracao() {
			return duracao;
		}

		public void setDuracao(Duration duracao) {
			this.duracao = duracao;
		}

	}

	
	
}

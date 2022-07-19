package br.com.alura.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.mvc.interceptor.InterceptorAccess;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

	// É NECESSÁRIO ESSA CLASSE PARA "HABILITAR O INTERCEPTOR"
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptorAccess()).addPathPatterns("/**");
	}
	
}

package br.com.alura.mvc;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.mvc.interceptor.InterceptorAccess;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurationSupport{

	// É NECESSÁRIO ESSA CLASSE PARA "HABILITAR O INTERCEPTOR"
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptorAccess()).addPathPatterns("/**");
	}
	
	// PARA HABILITAR O PAGEABLE
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
    }
	
}

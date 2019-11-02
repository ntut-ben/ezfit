package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "shopping.controller", "home.controller", "createAccount.controller", "login.controller" })
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("messages");
		return resourceBundleMessageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("createAccount/pic/**").addResourceLocations("/WEB-INF/views/createAccount/pic/");
		registry.addResourceHandler("createAccount/js/**").addResourceLocations("/WEB-INF/views/createAccount/js/");
		registry.addResourceHandler("createAccount/css/**").addResourceLocations("/WEB-INF/views/createAccount/css/");
		registry.addResourceHandler("login/**").addResourceLocations("/WEB-INF/views/login/");
		registry.addResourceHandler("index/img/**").addResourceLocations("/WEB-INF/views/index/img/");
		registry.addResourceHandler("index/js/**").addResourceLocations("/WEB-INF/views/index/js/");
		registry.addResourceHandler("index/css/**").addResourceLocations("/WEB-INF/views/index/css/");
		registry.addResourceHandler("/productImage/**").addResourceLocations("/WEB-INF/views/productImage/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/");
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/views/img/");
	}

}

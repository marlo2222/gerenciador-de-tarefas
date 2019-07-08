package br.com.teste.twgerenciadortarefas;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String userQuery;
	@Value("${spring.queries.roles-query}")
	private String roleQuery;

}

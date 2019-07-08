package br.com.teste.twgerenciadortarefas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.twgerenciadortarefas.models.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	
		Usuario findByEmail(String email);

}

package br.com.teste.twgerenciadortarefas.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.teste.twgerenciadortarefas.models.Usuario;
import br.com.teste.twgerenciadortarefas.repositorios.RepositorioUsuario;

@Service
public class ServicoUsuario {
	
	@Autowired
	private RepositorioUsuario repositoriousuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario encontrarPorEmail(String email){
		return repositoriousuario.findByEmail(email);
	}
	
	public void salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositoriousuario.save(usuario);
	}
	
}

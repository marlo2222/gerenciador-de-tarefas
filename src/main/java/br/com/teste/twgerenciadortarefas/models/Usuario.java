package br.com.teste.twgerenciadortarefas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usr_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "isr_id")
	private long id;
	
	@Column(name = "usr_email", nullable = false, length = 100)
	@NotNull(message = "O e-mail é obrigatorio.")
	@Length(min = 5, max = 100, message = "O e-mail deve conter entre 5 e 100 caracteres")
	private String email;
	
	@Column(name = "usr_senha", nullable = false, length = 100)
	@NotNull(message = "A senha é obrigatoria")
	private String senha;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}

package br.com.teste.twgerenciadortarefas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.twgerenciadortarefas.models.Tarefa;

public interface Repositoriotarefa extends JpaRepository<Tarefa, Long> {

}

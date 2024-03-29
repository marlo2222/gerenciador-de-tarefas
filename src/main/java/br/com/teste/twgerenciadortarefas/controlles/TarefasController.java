package br.com.teste.twgerenciadortarefas.controlles;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.teste.twgerenciadortarefas.models.Tarefa;
import br.com.teste.twgerenciadortarefas.models.Usuario;
import br.com.teste.twgerenciadortarefas.repositorios.RepositorioUsuario;
import br.com.teste.twgerenciadortarefas.repositorios.Repositoriotarefa;
import br.com.teste.twgerenciadortarefas.servicos.ServicoUsuario;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {
	
	@Autowired
	private Repositoriotarefa repositoriotarefa;
	@Autowired
	private ServicoUsuario servicoUsuario;

	@GetMapping("/listar")
	public ModelAndView listar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/listar");
		String emailUsuario = request.getUserPrincipal().getName();//aqui estamos acessando o email do usuario na seção atual, definido no spring security
		mv.addObject("tarefas", repositoriotarefa.carregarTarefasPorUsuario(emailUsuario));
		return mv;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de expiração é obrigatorioa");
		}else {
			if(tarefa.getDataExpiracao().before(new Date())) {
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de não pode referi-se ao passado");
			}
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject(tarefa);
		} else {
			String emailUsuario = request.getUserPrincipal().getName();
			Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
			tarefa.setUsuario(usuarioLogado);
			repositoriotarefa.save(tarefa);
			mv.setViewName("redirect:/tarefas/listar");
		}
		return mv;
	}
	@GetMapping("/alterar/{id}")
	public  ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Tarefa tarefa = repositoriotarefa.getOne(id);
		mv.addObject("tarefa", tarefa);
		mv.setViewName("tarefas/alterar");
		return mv;
		
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if(tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de expiração é obrigatorioa");
		}else {
			if(tarefa.getDataExpiracao().before(new Date())) {
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de não pode referi-se ao passado");
			}
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/alterar");
			mv.addObject(tarefa);
		} else {
			mv.setViewName("redirect:/tarefas/listar");
			repositoriotarefa.save(tarefa);
		}
		return mv;
	}
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") long id){
		repositoriotarefa.deleteById(id);
		return "redirect:/tarefas/listar";
	}
	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id") long id) {
		Tarefa tarefa = repositoriotarefa.getOne(id);
		tarefa.setConcluida(true);
		repositoriotarefa.save(tarefa);
		return "redirect:/tarefas/listar";
	}

}

package controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TarefasController {

    @RequestMapping("novaTarefa")
    public String form() {
        return "tarefa/formulario";
    }

    
    // Método instancia e seta o objeto a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping("adicionaTarefa")
    public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
    	
    	// Verifica se tem algum erro com o campo descricao
    	if(result.hasFieldErrors("descricao")) {
    		return "tarefa/formulario";
    	}
    	
    	// Verifica algum erro geral
    	if(result.hasErrors()) {
            return "tarefa/formulario";
        }
    	
        //JdbcTarefaDao dao = new JdbcTarefaDao();
        //dao.adiciona(tarefa);
        return "tarefa/adicionada";
    }
    
    
    // Recebe o modelo, adiciona um atributo a ele e o retorno redireciona para o JSP
    @RequestMapping("listaTarefas")
	public String lista(Model model) {
	    //JdbcTarefaDao dao = new JdbcTarefaDao();
	    //model.addAttribute("tarefas", dao.lista());
	    return "tarefa/lista";
    }
    
    
    /*
    // Instancia um Objeto ModelAndView passando o caminho do JSP, adiciona um objeto a ele e retorna esse ModelAndView
    @RequestMapping("listaTarefas")
    public ModelAndView lista() {
        JdbcTarefaDao dao = new JdbcTarefaDao();
        List<Tarefa> tarefas = dao.lista();

        ModelAndView mv = new ModelAndView("tarefa/lista");
        mv.addObject("tarefas", tarefas);
        return mv;
    } */
    
}
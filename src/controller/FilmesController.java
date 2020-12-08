package controller;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bean.Filme;
import dao.JdbcCategoriaDao;
import dao.JdbcFilmeDao;
import dao.JdbcGeneroDao;


@MultipartConfig
@Controller
public class FilmesController {
	
	private JdbcFilmeDao filmeDao;  
	private JdbcGeneroDao generoDao;
	private JdbcCategoriaDao categoriaDao;
	 
	
	@Autowired
	public FilmesController(JdbcFilmeDao filmeDao, JdbcGeneroDao generoDao, JdbcCategoriaDao categoriaDao) {
		this.filmeDao = filmeDao; 
		this.generoDao = generoDao;
		this.categoriaDao = categoriaDao;
	}
	

    
    @RequestMapping(value = "filmes", method = RequestMethod.GET)
	public String lista(Model model) {
	    
    	// Recebe o modelo, adiciona um atributo a ele e o retorno redireciona para o JSP
    	model.addAttribute("filmes", filmeDao.selectAll());
	    return "filme/lista";
    }
    
    
    
    @RequestMapping(value="buscaFilmes")
    public String busca(String busca, Model model) {
	    
    	model.addAttribute("filmes", filmeDao.search(busca));
	    return "filme/lista";
    }
    
 

    @RequestMapping("formularioFilme")
    public String form(/*Filme filme,*/ Model model) {   	
    	
    	// Nao pode receber um objeto pq impede a visualizacao da validacao do Insert/Update
    	model.addAttribute("generos", generoDao.selectAll());
    	model.addAttribute("categorias", categoriaDao.selectAll());
        return "filme/cadastro";
    }

    
    
    // Metodo instancia e seta o objeto Filme a partir dos campos de mesmo nome dos atributos da Classe
    @RequestMapping(value = {"/insertFilme"}, method = RequestMethod.POST)
    public String adiciona(@Valid Filme filme , BindingResult result, @RequestParam("arquivo") MultipartFile arquivo) {
    		
    	// Verifica algum erro geral
    	if(result.hasErrors()) {	
            return "forward:formularioFilme";
        }

    	try {
    		byte[] bytes = arquivo.getBytes();
    		filme.setImagem(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        filmeDao.insert(filme);
        return "redirect:filmes";
    }
    
    
    
    @RequestMapping("editFilme")
    public String edit(Filme filme, Model model) {
    	    
    	// Recebe o id do filme, consulta ele no BD, add ao atributo do Model e repassa para o formulario de cadastro/edicao
    	model.addAttribute("filme", filmeDao.select(filme.getId()));
        return "forward:formularioFilme";
    }
    
    
    
    //@RequestMapping(value = "/updateFilme", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestMapping("updateFilme")
    public String update( @Valid Filme filme, BindingResult result, @RequestParam("arquivo") MultipartFile arquivo) {
    	
    	// Verifica algum erro geral
    	if(result.hasErrors()) {
            return "forward:formularioFilme";
        }
    	
    	if(arquivo.isEmpty()) {
    		filme.setImagem(filmeDao.select(filme.getId()).getImagem());
    	} else {
    		
		    try {
	    		byte[] bytes = arquivo.getBytes();
	    		filme.setImagem(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}

    	
		filmeDao.update(filme);
		return "redirect:filmes";
    }
    
    
    
    @RequestMapping(value="deleteFilme", method=RequestMethod.GET)
    public String remove(Filme filme) {
        filmeDao.delete(filme.getId());
        return "redirect:filmes";
    }
    
    
 
    // Imagens precisam ser enviadas em todo o corpo da resposta
    @ResponseBody
    @RequestMapping(value="imagemFilme", method=RequestMethod.GET)
    public byte[] buscaImagem(Filme filme) {   
        return filmeDao.select(filme.getId()).getImagem();
    }
    
}
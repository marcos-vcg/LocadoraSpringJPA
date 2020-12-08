package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.Usuario;
import dao.DataSource;
import dao.UsuarioDao;

@Controller
public class LoginController {
	
	

	@RequestMapping("loginForm")
	public String loginForm() {
	  return "login/formulario-login";
	}
	
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if(new UsuarioDao(new DataSource()).validar(usuario)) {
          session.setAttribute("usuarioLogado", usuario);
          return "menu";
		}
		return "redirect:index.jsp";
	}
}

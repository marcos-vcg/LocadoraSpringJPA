package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Usuario;

public class UsuarioDao {
	private DataSource datasource;
	private String tabela;
	
	public UsuarioDao(DataSource datasource){
		this.datasource = datasource;
		this.tabela = "usuarios";
	}
	
	
	// 
	public boolean validar(Usuario usuario){
		
		boolean valido = false;
		Usuario usuarioBD;
		
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE login = ?";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.setString(1, usuario.getLogin());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				usuarioBD = new Usuario();
				usuarioBD.setLogin(rs.getString("login"));
				usuarioBD.setSenha(rs.getString("senha"));
				
				if(usuario.getLogin() == usuarioBD.getLogin()) {
					if(usuario.getSenha() == usuarioBD.getSenha()) {
						valido = true;
					}	
				}
				
			}
			
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Verificar USUARIO " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		

		
		return valido;
	}
	


}

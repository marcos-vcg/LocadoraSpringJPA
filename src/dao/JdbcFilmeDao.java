package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import bean.Filme;



@Repository
public class JdbcFilmeDao {
	
	private String tabela;
	private JdbcGeneroDao generoDao;
	private JdbcCategoriaDao categoriaDao;
	private Connection connection;
	
	
	
	@Autowired
	public JdbcFilmeDao(DataSource dataSource, JdbcGeneroDao generoDao, JdbcCategoriaDao categoriaDao){
		
		try {
			this.connection = dataSource.getConnection();
			this.generoDao = generoDao;
			this.categoriaDao = categoriaDao;
			
		} catch (Exception ex) {
			System.err.println("Erro ao instanciar FilmeDao " + ex.getMessage());
		}
		this.tabela = "filme";
	}
	
	
	public Filme select(Integer id){
		
		Filme filme = null;
		
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = ?;";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(generoDao.select(rs.getInt("genero")));
				filme.setCopias(rs.getInt("copias"));
				filme.setSinopse(rs.getString("sinopse"));
				filme.setDuracao(rs.getString("duracao"));
				filme.setLancamento(rs.getString("lancamento"));
				filme.setImagem(rs.getBytes("imagem"));
				filme.setCategoria(categoriaDao.select(rs.getInt("categoria")));
				
				System.out.println("Filme lido");
				
			}
			
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar filme " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return filme;
	}
	
	
	public ArrayList<Filme> selectAll(){
		
		ArrayList<Filme> lista = new ArrayList<Filme>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + "  ORDER BY titulo;";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
	
			while(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(generoDao.select(rs.getInt("genero")));
				filme.setCopias(rs.getInt("copias"));
				filme.setSinopse(rs.getString("sinopse"));
				filme.setDuracao(rs.getString("duracao"));
				filme.setLancamento(rs.getString("lancamento"));
				filme.setImagem(rs.getBytes("imagem"));
				filme.setCategoria(categoriaDao.select(rs.getInt("categoria")));
				System.out.println("Filme lido");
				
				if(filme.getId() != null ) {
					lista.add(filme);
				}
				
			}
			ps.close();
			
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar filme " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		
		return lista;
	}
	

	
	// Search - Aplica um filtro que busca todos pelo parametro passado
	public ArrayList<Filme> search(String busca){
			
		ArrayList<Filme> lista = new ArrayList<Filme>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + " where titulo LIKE ? OR genero LIKE ? ORDER BY titulo;";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			busca = "%"+busca+"%";
			ps.setString(1, busca);
			ps.setString(2, busca);
	
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(generoDao.select(rs.getInt("genero")));
				filme.setCopias(rs.getInt("copias"));
				filme.setSinopse(rs.getString("sinopse"));
				filme.setDuracao(rs.getString("duracao"));
				filme.setLancamento(rs.getString("lancamento"));
				filme.setImagem(rs.getBytes("imagem"));
				filme.setCategoria(categoriaDao.select(rs.getInt("categoria")));
				System.out.println("Filme lido");
				
				if(filme.getId() != null ) {
					lista.add(filme);
				}
				
			}
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar cliente " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return lista;
	}
	
	
	public Boolean insert(Filme f) {
		
		Boolean rowInserted = false;
		
		try {
			
			String SQL = "INSERT INTO filme (titulo, genero, copias, sinopse, duracao, lancamento, imagem , categoria) VALUES (?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			//ps.setString(1, tabela);
			ps.setString(1, f.getTitulo());
			ps.setInt(2, f.getGenero().getId());
			ps.setInt(3, f.getCopias());
			ps.setString(4, f.getSinopse());
			ps.setString(5, f.getDuracao());
			ps.setString(6, f.getLancamento());
			ps.setBytes(7, f.getImagem());
			ps.setInt(8, f.getCategoria().getId());
			
			rowInserted = ps.executeUpdate() > 0;						
			ps.close();
			
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowInserted;
	}
	
	
	
	public boolean update(Filme f) {
		
		boolean rowUpdated = false;
		
		try {
			
			String SQL = "UPDATE " + tabela + " SET titulo = ?, genero = ?, copias = ?, sinopse = ?, duracao = ?, lancamento = ?, imagem = ?, categoria = ? WHERE id = ?;" ;	
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setString(1, f.getTitulo());
			ps.setInt(2, f.getGenero().getId());
			ps.setInt(3, f.getCopias());
			ps.setString(4, f.getSinopse());
			ps.setString(5, f.getDuracao());
			ps.setString(6, f.getLancamento());
			ps.setBytes(7, f.getImagem());
			ps.setInt(8, f.getCategoria().getId());
			ps.setInt(9, f.getId());
			
			
			rowUpdated = ps.executeUpdate() > 0;
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowUpdated;
	}
	
	
	
	public boolean delete(Integer id) {
		
		boolean rowDeleted = false;
		
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = ?;" ;			
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			rowDeleted = ps.executeUpdate() > 0;								
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowDeleted;
	}
	
	
	// Trata os erros de todas as excessões das chamadas SQL
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t.getCause();
				}
			}
		}
	}
	
	
}

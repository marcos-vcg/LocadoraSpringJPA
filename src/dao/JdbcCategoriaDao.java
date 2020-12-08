package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bean.Categoria;


@Repository
public class JdbcCategoriaDao {
	private Connection connection;
	private String tabela;
	

	
	@Autowired
	public JdbcCategoriaDao(DataSource dataSource){
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.tabela = "categoria";
	}
	
	
	// Select By Id
	public Categoria select(int id) {
		
		Categoria categoria = null;
		
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = ?";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNome(rs.getString("nome"));
				categoria.setPreco(rs.getString("preco"));
			}
			
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar categoria " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return categoria;
	}
	
	
	// Select All
	public ArrayList<Categoria> selectAll(){
		
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + " ORDER BY nome";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Categoria categ = new Categoria();
				categ.setId(rs.getInt("id"));
				categ.setNome(rs.getString("nome"));
				categ.setPreco(rs.getString("preco"));
		
				lista.add(categ);
			}
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return lista;
	}
	
	
	// Insert
	public void insert(Categoria categoria) {
		try {
			
			String SQL = "INSERT INTO " + tabela + " (nome, preco) VALUES ( ?, ?);";
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getPreco());
			ps.executeUpdate();						// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	
	// Update
	public boolean update(Categoria categoria) {
		
		boolean rowUpdated = false;
		
		try {
			
			String SQL = "UPDATE " + tabela + " SET nome = ?, preco = ? WHERE id = ?;" ;			
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getPreco());
			ps.setInt(3, categoria.getId());
			
			rowUpdated = ps.executeUpdate() > 0;
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowUpdated;
	}
	
	
	// Delete
	public boolean delete(Integer id) {
		
		boolean rowDeleted = false;
		
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = ?;" ;			
			java.sql.PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			rowDeleted = ps.executeUpdate() > 0;											// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowDeleted;
	}
	
	
	
	// Trata os erros de todas as excess�es das chamadas SQL
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

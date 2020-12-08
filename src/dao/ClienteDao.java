package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Cliente;

public class ClienteDao {
	private DataSource dataSource;
	private String tabela;
	//private DependenteDAO dependenteDao;

	
	public ClienteDao(DataSource datasource){
		this.dataSource = datasource;
		this.tabela = "cliente";
		//dependenteDao = new DependenteDAO(dataSource);
	}
	
	
	public int selectLast(){
		
		int ultimoId = 0;
		
		try {
			String SQL = "SELECT MAX(id) FROM " + tabela + ";";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {			
				ultimoId = rs.getInt(1);
			}
			ps.close();
			
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar Id do Ultimo cliente  " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return ultimoId;
	}
	
	
	public Cliente select(Integer id){
		
		Cliente cliente = new Cliente();
		
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = ?;";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setNascimento(rs.getString("nascimento"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setImagem(rs.getBytes("imagem"));
				
				System.out.println("Cliente lido");
				
			}
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar filme " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return cliente;
	}
	
	
	public ArrayList<Cliente> selectAll(){
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + "  ORDER BY nome;";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setNascimento(rs.getString("nascimento"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setImagem(rs.getBytes("imagem"));
				
				System.out.println("Cliente lido");
				
				
				if(cliente.getId() != null) {
					lista.add(cliente);
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
	

	// Search - Aplica um filtro que busca todos pelo parametro passado
	public ArrayList<Cliente> search(String busca){
			
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + " where nome LIKE ? OR cpf LIKE ? ORDER BY nome;";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			
			busca = "%"+busca+"%";
			ps.setString(1, busca);
			ps.setString(2, busca);
	
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setNascimento(rs.getString("nascimento"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setImagem(rs.getBytes("imagem"));
				
				System.out.println("Cliente lido");
				
				
				if(cliente.getId() != null) {
					lista.add(cliente);
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
	
	
	public boolean insert(Cliente c) {
		boolean rowInserted = false;
		try {
			
			String SQL = "INSERT INTO " + tabela + " VALUES (DEFAULT,  ?, ?, ?, ?, ?, ?, ?);";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);

			ps.setString(1, c.getNome());
			ps.setString(2, c.getCpf());
			ps.setString(3, c.getTelefone());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getNascimento());
			ps.setString(6, c.getEndereco());
			ps.setBytes(7, c.getImagem());			

			rowInserted = ps.executeUpdate() > 0;	
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return rowInserted;
	}
	
	
	
	public boolean update(Cliente c) {
		
		boolean rowUpdated = false;
		
		try {
			
			String SQL = "UPDATE " + tabela + " SET nome = ?, cpf = ?, telefone = ?, email = ?, nascimento = ?, endereco = ?, imagem = ? WHERE id = ?;" ;	
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCpf());
			ps.setString(3, c.getTelefone());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getNascimento());
			ps.setString(6, c.getEndereco());
			ps.setBytes(7, c.getImagem());			
			ps.setInt(8, c.getId());
			
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
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			rowDeleted = ps.executeUpdate() > 0;
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

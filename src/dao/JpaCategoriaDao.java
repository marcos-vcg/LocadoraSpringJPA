package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import bean.Categoria;

@Repository
public class JpaCategoriaDao {

	@PersistenceContext
    private EntityManager manager;
	
	
	
	
	public void insert(Categoria categoria) {
		manager.persist(categoria);
	}
	
	public void update(Categoria categoria) {
	    manager.merge(categoria);
	}
	
	public List<Categoria> selectAll() {
	    return manager.createQuery("select genero from Genero genero").getResultList();
	}
	
	public Categoria select(Integer id) {
	   return manager.find(Categoria.class, id);
	}
	
	public void delete(Categoria categoria) {
		Categoria categoriaARemover = select(categoria.getId());
	    manager.remove(categoriaARemover);
	}
	
	  
	public List<Categoria> search(String palavra) {
		palavra = "%"+palavra+"%";
		Query query = manager.createQuery("select categoria from Categoria where categoria.nome like :palavra");
		query.setParameter("palavra", palavra);
		return query.getResultList();
	}  
	  
	
	
}

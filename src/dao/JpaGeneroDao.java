package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import bean.Genero;

@Repository
public class JpaGeneroDao {

	@PersistenceContext
    private EntityManager manager;
	
	
	
	
	public void insert(Genero genero) {
		manager.persist(genero);
	}
	
	public void update(Genero genero) {
	    manager.merge(genero);
	}
	
	public List<Genero> selectAll() {
	    return manager.createQuery("select genero from Genero genero").getResultList();
	}
	
	public Genero select(Integer id) {
	   return manager.find(Genero.class, id);
	}
	
	public void delete(Genero genero) {
		Genero generoARemover = select(genero.getId());
	    manager.remove(generoARemover);
	}
	
	  
	public List<Genero> search(String palavra) {
		palavra = "%"+palavra+"%";
		Query query = manager.createQuery("select genero from Genero where genero.nome like :palavra");
		query.setParameter("palavra", palavra);
		return query.getResultList();
	}  
	  
	
	
}

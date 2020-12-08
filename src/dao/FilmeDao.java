package dao;

import java.util.List;

import bean.Filme;

public interface FilmeDao {

	Filme select(Integer id);
    List<Filme> selectAll();
    void insert(Filme filme);
    void update(Filme filme);
    void delete(Filme filme);
    List<Filme> search(String busca);
}

package bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name="filme")
public class Filme implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message="Favor Informar o Título")
	@Size(max=30, message="No máximo 30 caracteres")
	private String titulo;
	
	
	/**
     * The {@link Genero} that edited this version. 
     */
	
    @OneToOne(cascade= CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="genero")
	@NotNull(message="Favor selecionar um Gênero")
	private Genero genero;
	
	@Positive
	private Integer copias;
	
	private String sinopse;
	private String duracao;
	private String lancamento;
	
	//@Lob
	private byte[] imagem;
	
	
	//@OneToMany
	
	@OneToOne(cascade= CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
    @JoinColumn(name = "categoria")
	@NotNull(message="Favor selecionar uma Categoria")
	private Categoria categoria;


	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Integer getCopias() {
		return copias;
	}

	public void setCopias(Integer copias) {
		this.copias = copias;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getLancamento() {
		return lancamento;
	}

	public void setLancamento(String lancamento) {
		this.lancamento = lancamento;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}

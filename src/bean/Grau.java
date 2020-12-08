package bean;

import java.util.ArrayList;
import java.util.List;

public enum Grau {

	Selecione(""), AVO("Avo"), PAI("Pai"), FILHO("Filho"), TIO("Tio"), SOBRINHO("Sobrinho"), CONJUGE("Conjuge");
	
	private String descricao;
	
	Grau(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() { return descricao; }
	
	public static ArrayList<Grau> getGraus(){
		ArrayList<Grau> lista = new ArrayList<Grau>(); 
		for(Grau g : Grau.values()){
			lista.add(g);
		}
		return lista;
	}
	
}

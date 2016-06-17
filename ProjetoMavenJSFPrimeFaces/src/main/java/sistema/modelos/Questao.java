package sistema.modelos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Questao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	int dificuldade;
	int tempoEstimado; //em minutos
	@ManyToMany
	List<Prova> provas;
	String img;
	String enunciado, enunimg;
	String resolucao, resolimg;
	String tipoPergunta;
	@ManyToOne
	Conteudo conteudo;
	
	
	public String getEnunimg() {
		return enunimg;
	}
	public void setEnunimg(String enunimg) {
		this.enunimg = enunimg;
	}
	public String getResolimg() {
		return resolimg;
	}
	public void setResolimg(String resolimg) {
		this.resolimg = resolimg;
	}
	public void criaResolucao(String img){
		if(img.isEmpty()){
			
		}
	}
	public void criaEnunciado(String img){
		if(img.isEmpty()){
			
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}
	public int getTempoEstimado() {
		return tempoEstimado;
	}
	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public String getResolucao() {
		return resolucao;
	}
	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}
	public String getTipoPergunta() {
		return tipoPergunta;
	}
	public void setTipoPergunta(String tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}
	public Conteudo getConteudo() {
		return conteudo;
	}
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
	
	public void addProva(Prova prova){
		this.provas.add(prova);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}

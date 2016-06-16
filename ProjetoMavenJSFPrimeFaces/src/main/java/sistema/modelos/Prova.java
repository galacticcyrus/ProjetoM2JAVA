package sistema.modelos;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.derby.client.am.DateTime;

@Entity
public class Prova implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToMany
	List<Questao> questoes;
	int dificuldade;
	int tempo; //em minutos
	Date dataDeAplicacao;
	@ManyToOne
	Disciplina disciplina;
	String Faculdade;
	String Turma;
	List<Conteudo> conteudos;
	List<String> tipos;
	
	//manda o numero da questao que quer remover e procura outra questao quer nao seja igual, pra entao remover
	public boolean trocaQuestao(int i){
		//Questao q = questoes.get(i);
		if(questoes.get(i).conteudo.selecionaQuestao(questoes.get(i).dificuldade, questoes.get(i).tipoPergunta, this, false))
		{
			questoes.get(i).provas.remove(this);
			questoes.remove(i);
			tempo -= questoes.get(i).tempoEstimado;
			return true;
		}
		else
		{
			System.out.println("Não existe outra questao desse conteudo que esteja com uma dificuldade aproximada e não seja essa.");
			return false;
		}
	}
	
	public void addQuestao(Questao q){
		questoes.add(q);
	}
	public void moverQuestao(int id1, int id2){
		Collections.swap(questoes, id1, id2);
	}

	public Disciplina getDisciplina() {
	
		return this.disciplina;
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setDisciplina(Disciplina disciplina2) {
		// TODO Auto-generated method stub
		this.disciplina = disciplina2;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public Date getDataDeAplicacao() {
		return dataDeAplicacao;
	}

	public void setDataDeAplicacao(Date dataDeAplicacao) {
		this.dataDeAplicacao = dataDeAplicacao;
	}

	public String getFaculdade() {
		return Faculdade;
	}

	public void setFaculdade(String faculdade) {
		Faculdade = faculdade;
	}

	public String getTurma() {
		return Turma;
	}

	public void setTurma(String turma) {
		Turma = turma;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	
}

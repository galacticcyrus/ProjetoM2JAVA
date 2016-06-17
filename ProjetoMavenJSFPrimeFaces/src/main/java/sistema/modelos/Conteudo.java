package sistema.modelos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import sistema.modelos.Questao;
import sistema.modelos.Prova;
@Entity
public class Conteudo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6493480562655431382L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany
	List<Questao> questoes;
	
	@ManyToOne
	private Disciplina disciplina;

	public boolean selecionaQuestao(int dificuldade, String tipo, Prova p, boolean jafoi){
		List<Questao> ret = new ArrayList<Questao>();
		for(Questao q: questoes){
			if(!p.questoes.contains(q) && q.tipoPergunta == tipo && q.dificuldade==dificuldade){
					ret.add(q);    //adiciona todos os possiveis dentro dessas condicoes				
			}
		}
		if(ret.size() != 0)
		{	
			int frequencia = ret.get(0).provas.size();
			int id = 0;
			for(int i = 1; i < ret.size() ; i++)    //escolhe os menos frequentes
			{
				if(ret.get(i).provas.size() < frequencia)
				{
					frequencia = ret.get(i).provas.size();
					id = i;
				}
			}
			p.addQuestao(ret.get(id)); //adicionou o que foi menos utilizado e ainda esta dentro das condicoes
			p.tempo += ret.get(id).tempoEstimado;
			//disciplina.addProva(p)
			ret.get(id).addProva(p);
			return true;

		}
		else if(!jafoi)
		{ 	
			if(Math.random()<0.5 && dificuldade >=2)
				selecionaQuestao(dificuldade-1, tipo, p, true);
			else 
				if(dificuldade <=9)
					selecionaQuestao(dificuldade+1, tipo, p, true);
				else //caso o random d maior que 0.5 e dificuldade for 10
					selecionaQuestao(dificuldade-1, tipo, p, true);
		}
		return false;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public void addQuestao(Questao questao) {
		// TODO Auto-generated method stub
		this.questoes.add(questao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Conteudo other = (Conteudo) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
}

package sistema.modelos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	String professor;
	String nome; 
	@OneToMany(mappedBy="disciplina")
	List<Conteudo> conteudos;
	
	@OneToMany
	List<Prova> provas;
	
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Conteudo> getConteudos() {
		return conteudos;
	}
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	
	//*********************************************
	
	public Prova geraProva(int tempo, int qtd, int dificuldade, List<Conteudo> cons, List<String> tipos, String curso, String faculdade, String turma, Date data){

		Prova ret = new Prova();
		int c = 0, t = 0;
		

		for(int i = 0; i < qtd; i++)
		{

			if(c == cons.size())
				c = 0;
			if(t == tipos.size())
				t = 0;
			if(!cons.get(c).selecionaQuestao(dificuldade, tipos.get(t), ret, false))
				i--; //ele n�o achou com esse conteudo e esse tipo, tenta de novo com outro c e outro t
			c++; //passa por todos conteudos, em ordem (ex: cont 1, cont 2, cont 3, cont 1, cont 2, cont 3, cont 1,...)
			t++; //passa por todos tipos, em ordem (ex: tipo 1, tipo 2, tipo 1, tipo 2, tipo 1, ...)

		}
		while(ret.tempo > tempo)
		{
			int aux=-1, taux=0;

			for(int i = 0; i < qtd; i++)
			{

				if(taux < ret.questoes.get(i).tempoEstimado)
				{
					taux = ret.questoes.get(i).tempoEstimado;
					aux = i;
				}

			} 
			if(!ret.trocaQuestao(aux))
				break; //n�o achou outra pra substituir, deixa assim mesmo

		}
			

		return ret;
	}

	//*********************************************

	public void addConteudo(Conteudo c){
		c.setId(conteudos.size()); //o id come�a do zero
		conteudos.add(c);
	}

	public void moverConteudo(int id1, int id2){
		Collections.swap(conteudos, id1, id2);
		conteudos.get(id1).setId(id1); //novo id1 recebe seu id atualizado
		conteudos.get(id2).setId(id2); //novo id2 recebe seu id atualizado
	}
	public void addProva(Prova prova) {
		this.provas.add(prova);
		
	}
	
}

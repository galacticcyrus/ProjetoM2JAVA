package sistema.modelos;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int matricula;
	private String nome;
	Disciplina disciplina;
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public Disciplina getDisciplina(){
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina){
		this.disciplina = disciplina; 
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

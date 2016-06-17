package sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.DisciplinaDAO;
import sistema.dao.ConteudoDAO;
import sistema.modelos.Disciplina;
import sistema.modelos.Disciplina;
import sistema.modelos.Produto;
import sistema.modelos.Conteudo;
import sistema.modelos.Prova;


public class DisciplinaService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Disciplina salvar(Disciplina disciplina) {

		disciplina = disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();
		return disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		List<Disciplina> list = disciplinaDAO.getAll(Disciplina.class);
		disciplinaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Disciplina disciplina) {

		disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();

	}

	public void remover(Disciplina disciplina) {

		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getid());
		disciplinaDAO.remove(disciplina);
		disciplinaDAO.closeEntityManager();
	}

	public Disciplina pesquisar(Disciplina disciplina) {

		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getid());
		disciplinaDAO.closeEntityManager();
		return disciplina;
	}

	public List<Prova> pesquisarProvasDisciplina(Disciplina disciplina) {

		List<Prova> provas;

		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getid());
		provas = disciplina.getProvas();

		return provas;
	}
	
	public List<Conteudo> pesquisarConteudosDisciplina(Disciplina disciplina) {

		ConteudoDAO conteudoDAO = new ConteudoDAO();
		List<Conteudo> conteudos;
		if(disciplina != null){
		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getid());
		conteudos = disciplina.getConteudos();
		}
		else{
			conteudos = conteudoDAO.getAll(Conteudo.class);
		}

		return conteudos;
	}


}

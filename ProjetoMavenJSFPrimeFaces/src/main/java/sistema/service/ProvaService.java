package sistema.service;

import java.util.List;

import sistema.dao.ConteudoDAO;
import sistema.dao.ProvaDAO;
import sistema.dao.DisciplinaDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Fornecedor;
import sistema.modelos.Produto;
import sistema.modelos.Prova;
import sistema.modelos.Questao;

public class ProvaService {
	ProvaDAO provaDAO = new ProvaDAO();
 	
	public Prova salvar(Prova prova)
	{
		prova = provaDAO.save(prova);
		provaDAO.closeEntityManager();
		return prova;
	
	}

	public List <Prova> getProvas()
	{
		List <Prova> list = provaDAO.getAll(Prova.class);
		provaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Prova prova) {
		provaDAO.save(prova);
		provaDAO.closeEntityManager();
	}


	public void remover(Prova prova) {
	
		prova = provaDAO.getById(Prova.class, prova.getId());
		if(prova.getDisciplina() == null){
		provaDAO.remove(prova);
		provaDAO.closeEntityManager();
		}
	}
	public List<Conteudo> pesquisarProvasConteudos(Prova prova) {

		ConteudoDAO conteudoDAO = new ConteudoDAO();
		List<Conteudo> conteudos;
		if(prova != null){
		prova = provaDAO.getById(Prova.class, prova.getId());
		conteudos = prova.getConteudos();
		}
		else{
			conteudos = conteudoDAO.getAll(Conteudo.class);
		}

		return conteudos;
	}

	public List<Questao> pesquisaProvaQuestao(Prova prova){
		List<Questao> questoes;

		prova = provaDAO.getById(Prova.class, prova.getId());
		questoes = prova.getQuestoes();

		return questoes;
	}
	
	@SuppressWarnings("null")
	public List<Disciplina> pesquisarProvasDisciplina(Prova prova) {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		List<Disciplina> disciplinas = null;
		if(prova != null){
		prova = provaDAO.getById(Prova.class, prova.getId());
		disciplinas.add(prova.getDisciplina());
		}
		else{
			disciplinas = disciplinaDAO.getAll(Disciplina.class);
		}

		return disciplinas;
	}
}

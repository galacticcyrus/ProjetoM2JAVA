package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.ConteudoDAO;
import sistema.dao.QuestaoDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Fornecedor;
import sistema.modelos.Produto;
import sistema.modelos.Prova;
import sistema.modelos.Questao;

public class QuestaoService {
	QuestaoDAO questaoDAO = new QuestaoDAO();
 	
	public Questao salvar(Questao questao)
	{
		questao = questaoDAO.save(questao);
		questaoDAO.closeEntityManager();
		return questao;
	
	}

	public List <Questao> getQuestoes()
	{
		List <Questao> list = questaoDAO.getAll(Questao.class);
		questaoDAO.closeEntityManager();
		return list;
	}

	public void alterar(Questao questao) {
		questaoDAO.save(questao);
		questaoDAO.closeEntityManager();
	}


	public void remover(Questao questao) {
	
		questao = questaoDAO.getById(Questao.class, questao.getId());
		if(questao.getConteudo() == null){
		questaoDAO.remove(questao);
		questaoDAO.closeEntityManager();
		}
	}
	
	@SuppressWarnings("null")
	public List<Conteudo> pesquisarQuestoesConteudos(Questao questao) {

		ConteudoDAO conteudoDAO = new ConteudoDAO();
		List<Conteudo> conteudos=null;
		if(questao != null){
		questao = questaoDAO.getById(Questao.class, questao.getId());
		conteudos.add(questao.getConteudo());
		}
		else{
			conteudos = conteudoDAO.getAll(Conteudo.class);
		}

		return conteudos;
	}
}

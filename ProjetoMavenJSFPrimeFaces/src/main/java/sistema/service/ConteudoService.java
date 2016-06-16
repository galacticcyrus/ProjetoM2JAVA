package sistema.service;

import java.util.List;

import sistema.dao.ConteudoDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;

public class ConteudoService {
	ConteudoDAO conteudoDAO = new ConteudoDAO();
 	
		public Conteudo salvar(Conteudo conteudo)
		{
			conteudo = conteudoDAO.save(conteudo);
			conteudoDAO.closeEntityManager();
			return conteudo;
		
		}
	
		public List <Conteudo> getConteudos()
		{
			List <Conteudo> list = conteudoDAO.getAll(Conteudo.class);
			conteudoDAO.closeEntityManager();
			return list;
		}

		public void alterar(Conteudo conteudo) {
			conteudoDAO.save(conteudo);
			conteudoDAO.closeEntityManager();
		}

	
		public void remover(Conteudo conteudo) {
		
			conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getId());
			if(conteudo.getDisciplina() == null){
			conteudoDAO.remove(conteudo);
			conteudoDAO.closeEntityManager();
			}
		}
		
		public Disciplina pesquisarDisciplinaConteudo(Conteudo conteudo) {

			Disciplina disciplinas;

			conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getId());
			disciplinas = conteudo.getDisciplina();

			return disciplinas;
		}
}

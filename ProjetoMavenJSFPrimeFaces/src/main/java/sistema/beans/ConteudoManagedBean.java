package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import sistema.modelos.Disciplina;
import sistema.modelos.Conteudo;
import sistema.modelos.Prova;
import sistema.modelos.Questao;
import sistema.service.ConteudoService;
import sistema.service.DisciplinaService;
import sistema.dao.ConteudoDAO;

@ManagedBean(name = "conteudoManagedBean")
@ViewScoped
public class ConteudoManagedBean {
	private Conteudo conteudo = new Conteudo();
	private Disciplina disciplina;
	private ConteudoDAO contDAO = new ConteudoDAO();
	private ConteudoService contService = new ConteudoService();
	private DisciplinaService disciplinaService = new DisciplinaService();
	private List<Questao> questoes;
	private List<Conteudo> conteudos;
	private Conteudo conteudoSelecionada;
	
	
	public List<Questao> getQuestoes() {
		return questoes;
	}


	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}


	public void salvar() {
		if(disciplina != null){
		disciplina.addConteudo(conteudo);
		conteudo.setDisciplina(disciplina);
		}
		else{
			conteudo.setDisciplina(new Disciplina());
		}
		conteudo = contDAO.save(conteudo);

		if (conteudos != null)
			conteudos.add(conteudo);

		conteudo = new Conteudo();
		disciplina = null;

	}


	public Disciplina getDisciplina() {
		return disciplina;
	}

	/*public void remove(Disciplina conteudo) {
		prodService.remover(conteudo);
		conteudos.remove(conteudo);
	}*/

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public List<Conteudo> getConteudos() {
		if (conteudos == null)
			conteudos = contService.getConteudos();

		return conteudos;
	}
	
	public List<Disciplina> getDisciplinasConteudos() {
		if (conteudoSelecionada != null) {
			return (List<Disciplina>)contService.pesquisarDisciplinaConteudo(conteudoSelecionada);
		} else
			return null;
	}

	public void onRowEdit(RowEditEvent event) {

		Conteudo c = ((Conteudo) event.getObject());
		contService.alterar(c);
	}
}

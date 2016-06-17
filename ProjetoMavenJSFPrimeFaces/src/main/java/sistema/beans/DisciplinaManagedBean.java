package sistema.beans;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import sistema.beans.datamodel.DisciplinaDataModel;
import sistema.modelos.Disciplina;
import sistema.modelos.Prova;
import sistema.modelos.Conteudo;
import sistema.service.ConteudoService;
import sistema.service.DisciplinaService;
import sistema.service.ProvaService;

@ManagedBean(name = "disciplinaManagedBean")
@ViewScoped
public class DisciplinaManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina = new Disciplina();
	private Disciplina disciplinaSelecionada;
	private DisciplinaService servico = new DisciplinaService();
	private List<Disciplina> disciplinas;
	private List<Prova> provas;
	private List<Conteudo> conteudos;
	private ConteudoService contService = new ConteudoService();
	private ProvaService provaServices = new ProvaService();
	
	
	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	/**
	 * @return the disciplinaSelecionada
	 */
	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	/**
	 * @param disciplinaSelecionado
	 *            the disciplinaSelecionada to set
	 */
	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public void salvar() {
		disciplina = servico.salvar(disciplina);

		if (disciplinas != null)
			disciplinas.add(disciplina);

		disciplina = new Disciplina();

	}

	public DataModel<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = servico.getDisciplinas();

		return new DisciplinaDataModel(disciplinas);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void remove(Disciplina disciplina) {
		if (servico.pesquisarProvasDisciplina(disciplina).size() > 0 || servico.pesquisarConteudosDisciplina(disciplina).size()>0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "N縊 � poss咩el remover disciplina",
					"Disciplina possui produtos!"));
		} else {
			servico.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Prova> getProvasDisciplina() {
		return provaServices.getProvas();
	}
	
	public List<Conteudo> getConteudosDisciplina() {
		return contService.getConteudos();
	}


	public void onRowEdit(RowEditEvent event) {

		Disciplina d = ((Disciplina) event.getObject());
		servico.alterar(d);
	}
}

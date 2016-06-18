package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.dao.ProvaDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Prova;
import sistema.modelos.Questao;
import sistema.service.DisciplinaService;
import sistema.service.ConteudoService;
import sistema.service.ProvaService;



@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class ProvaManagedBean {
	private Prova prova = new Prova();
	private ProvaDAO provaDAO = new ProvaDAO();
	private ProvaService provaService = new ProvaService();
	private ConteudoService contService = new ConteudoService();
	private DisciplinaService discService = new DisciplinaService();
	private List<Prova> provas;
	//private List<Questao> questoes;
	//private List<Disciplina> disciplinas;
	private List<Conteudo> conteudos;
	private Disciplina disciplina = new Disciplina();
	private Prova provaSelecionada = null;
	public void salvar() {
		disciplina.addProva(prova);
		prova.setDisciplina(disciplina);
		prova.setConteudos(conteudos);

		prova = provaService.salvar(prova);

		if (provas != null)
			provas.add(prova);

		prova = new Prova();
		disciplina = null;

	}

	public void setDisciplina(Disciplina disciplina){
		this.disciplina=disciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public List<Conteudo> getConteudos(){
		return conteudos;
	}
	public void setConteudos(List<Conteudo> conteudos){
		this.conteudos = conteudos;
	}
	/*public void remove(Disciplina conteudo) {
		prodService.remover(conteudo);
		conteudos.remove(conteudo);
	}*/

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Prova> getProvas() {
		if (provas == null)
			provas = provaService.getProvas();

		return provas;
	}

	public void onRowEdit(RowEditEvent event) {

		Prova p = ((Prova) event.getObject());
		provaService.alterar(p);
	}
	
	public List<Conteudo> getProvasConteudo() {
		return contService.getConteudos();
	}
	
	
	public List<Disciplina> getProvasDisciplina() {
		return discService.getDisciplinas();
	}

}

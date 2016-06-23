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

//import sistema.beans.datamodel.QuestaoDataModel;
import sistema.dao.QuestaoDAO;
import sistema.modelos.Prova;
import sistema.modelos.Questao;
import sistema.modelos.Conteudo;
import sistema.service.ConteudoService;
import sistema.service.QuestaoService;
import sistema.beans.converter.QuestaoConverter;

@ManagedBean(name = "questaoManagedBean")
@ViewScoped
public class QuestaoManagedBean {
	private Questao questao = new Questao();
	private Questao questaoSelecionada;
	private QuestaoDAO questaoDAO = new QuestaoDAO();
	private QuestaoService questaoService = new QuestaoService();
	private ConteudoService conteudoService = new ConteudoService();
	private List<Questao> questoes;
	private List<Conteudo> conteudos;
	private List<Prova> provas;
	private Conteudo conteudo = new Conteudo();
	
	public void salvar() {
		conteudo.addQuestao(questao);
		questao.setConteudo(conteudo);
		questao = questaoService.salvar(questao);
		conteudo = conteudoService.salvar(conteudo);

		if (questoes != null)
			questoes.add(questao);

		questao = new Questao();
		conteudo = null;

	}


	public Conteudo getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(Conteudo conteudo){
		this.conteudo = conteudo;
	}

	/*public void remove(Disciplina conteudo) {
		prodService.remover(conteudo);
		conteudos.remove(conteudo);
	}*/

	public Questao getQuestao() {
		return questao;
	}

	public int getFrequenciaUso()
	{
		if(provas != null){
		return provas.size();
		}
		return 0;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoes() {
		if (questoes == null)
			questoes = questaoService.getQuestoes();

		return questoes;
	}

	public void onRowEdit(RowEditEvent event) {

		Questao q = ((Questao) event.getObject());
		questaoService.alterar(q);
	}
	
	public List<Conteudo> getQuestoesConteudo() {
		return conteudoService.getConteudos();
	}
	
}

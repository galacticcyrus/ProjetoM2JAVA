package sistema.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.itextpdf.text.DocumentException;

import sistema.dao.ProvaDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Produto;
import sistema.modelos.Prova;
import sistema.modelos.Questao;
import sistema.service.DisciplinaService;
import sistema.service.ConteudoService;
import sistema.service.ProvaService;



@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class ProvaManagedBean implements Serializable{
	private Prova prova = new Prova();
	private ProvaDAO provaDAO = new ProvaDAO();
	private ProvaService provaService = new ProvaService();
	private ConteudoService contService = new ConteudoService();
	private DisciplinaService discService = new DisciplinaService();
	private List<Prova> provas;
	//private List<Questao> questoes;
	//private List<Disciplina> disciplinas;
	private Questao questao = new Questao();
	private List<Conteudo> conteudos;
	private Disciplina disciplina = new Disciplina();
	private Prova provaSelecionada = null;
	public void salvar() {
		prova.setQuestoes(new ArrayList<Questao>());
		disciplina.addProva(prova);
		prova.setDisciplina(disciplina);
		prova.setConteudos(conteudos);
		disciplina = discService.salvar(disciplina);
		
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
		{
			provas = provaService.getProvas();
		}

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
	
	public void Imprimir(int aux) throws DocumentException, IOException
	{
		for(int i = 0; i<provas.size(); i++)
		{
			if(provas.get(i).getId() == aux){
				provas.get(i).imprimir();
				System.out.println("Foi");
			}
		}		
	}
	
	public void remove() throws DocumentException, IOException{
		
		
		//provaService.remover(prova);
		//provas.remove(prova);
	}
	public void geraProva(int aux) throws DocumentException, IOException{
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		 //(dateFormat.format(cal.getTime())) para pegar o tempo atual
		for(int i = 0; i<provas.size(); i++)
		{
			if(provas.get(i).getId() == aux){
				String s = new String();
				s = provas.get(i).getTiposQuestoes();
				String sx[] = s.split(",");
				List<String> tipos = new ArrayList<String>();
				for(int j = 0;j< sx.length; j++){
					tipos.add(sx[j]);
				}
				System.out.println(provas.get(i).getConteudos());
				Prova p = provas.get(i).getDisciplina().geraProva(provas.get(i).getTempo(), provas.get(i).getQtdQuestoes(), provas.get(i).getDificuldade(), provas.get(i).getConteudos(), tipos, provas.get(i).getCurso(), provas.get(i).getFaculdade(), provas.get(i).getTurma(),cal.getTime(),provas.get(i).getId());
				System.out.println("foi");
				p = provaService.salvar(p);
			}
			}	
	}

}

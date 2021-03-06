package sistema.modelos;
import java.io.Serializable;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.derby.client.am.DateTime;

import java.io.FileOutputStream;
import java.io.IOException;
 

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Entity
public class Prova implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToMany
	List<Questao> questoes;
	int dificuldade;
	int tempo; //em minutos
	Date dataDeAplicacao;
	@ManyToOne
	Disciplina disciplina;
	String Faculdade;
	String Turma;
	int qtdQuestoes;
	String tiposQuestoes;
	List<Conteudo> conteudos;
	String Curso;
	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	

	public String getTiposQuestoes() {
		return tiposQuestoes;
	}

	public void setTiposQuestoes(String tiposQuestoes) {
		this.tiposQuestoes = tiposQuestoes;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public Date getDataDeAplicacao() {
		return dataDeAplicacao;
	}

	public void setDataDeAplicacao(Date dataDeAplicacao) {
		this.dataDeAplicacao = dataDeAplicacao;
	}

	public String getFaculdade() {
		return Faculdade;
	}

	public void setFaculdade(String faculdade) {
		Faculdade = faculdade;
	}

	public String getTurma() {
		return Turma;
	}

	public void setTurma(String turma) {
		Turma = turma;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	

	
	//manda o numero da questao que quer remover e procura outra questao quer nao seja igual, pra entao remover
	public boolean trocaQuestao(int id){
		//Questao q = questoes.get(i);
		int i=-1;
		for(int j =0;j<questoes.size();j++){
			if(questoes.get(j).getId() == id)
				i = j;
		}
		if(i!=-1){
		if(questoes.get(i).conteudo.selecionaQuestao(questoes.get(i).dificuldade, questoes.get(i).tipoPergunta, this, false))
		{
			questoes.get(i).provas.remove(this);
			questoes.remove(i);
			tempo -= questoes.get(i).tempoEstimado;
			return true;
		}
		else
		{
			System.out.println("N�o existe outra questao desse conteudo que esteja com uma dificuldade aproximada e n�o seja essa.");
			return false;
		}
		}
		else{
			System.out.println("Questao nao achada com esse id!");
			return false;
		}
	}
	
	public void addQuestao(Questao q){
		questoes.add(q);
	}
	public void moverQuestaoUp(int id1){
		int id2;		
		try{
		for(int i =0;i<questoes.size();i++){
			if(questoes.get(i).getId() == id1 && questoes.get(i-1)!= null){
				id2 = questoes.get(i-1).getId();
				Collections.swap(questoes, i, i-1);
				//questoes.get(i).setId(questoes.get(i-1).getId()) ;
				//questoes.get(i-1).setId(id1); 
			}
		}
		}
		catch(Exception e){
			System.out.println("There is no question on top of the selected");
		}
	}
	public void moverQuestaoDown(int id1){
		int id2;		
		try{
		for(int i =0;i<questoes.size();i++){
			if(questoes.get(i).getId() == id1 && questoes.get(i+1)!= null){
				id2 = questoes.get(i+1).getId();
				Collections.swap(questoes, i, i+1);
				//questoes.get(i).setId(questoes.get(i+1).getId()) ;
				//questoes.get(i+1).setId(id1); 

			}
		}
		}
		catch(Exception e){
			System.out.println("There is no question below the selected");
		}
	}

	public Disciplina getDisciplina() {
	
		return this.disciplina;
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setDisciplina(Disciplina disciplina2) {
		// TODO Auto-generated method stub
		this.disciplina = disciplina2;
	}
	
	public void imprimir()throws DocumentException, IOException 
	{
	 
		createPdf("C:/Users/111310/Desktop/java/POO-II-1Sem2016/ProjetoMavenJSFPrimeFaces/" + disciplina.getNome() + " " + Turma + ".pdf", false);	
		createPdf("C:/Users/111310/Desktop/java/POO-II-1Sem2016/ProjetoMavenJSFPrimeFaces/" + disciplina.getNome() + " " + Turma + " Gabarito.pdf", true);
		
	}
	
	public void setId(int id){
		this.id = id;
	}
		
	public int getQtdQuestoes() {
		return qtdQuestoes;
	}

	public void setQtdQuestoes(int qtdQuestoes) {
		this.qtdQuestoes = qtdQuestoes;
	}

	public void createPdf(String filename, boolean gabarito) throws DocumentException, IOException {
		        // step 1
		        Document document = new Document();
		        // step 2
		        PdfWriter.getInstance(document, new FileOutputStream(filename));
		        // step 3
		        document.open();
		        // step 4
		        document.add((new Paragraph((disciplina.getNome()), new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD))));
		        document.add((new Paragraph(("" + dataDeAplicacao + "\n\n\n\n"), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD))));
		        System.out.println(questoes.size());
		        for(int i = 0; i < questoes.size(); i++)
		        {
		        	if(gabarito)
		        	{
		        		if(!questoes.get(i).resolimg.equals(""))
		        		{
		        			document.add(Image.getInstance(questoes.get(i).resolimg));
		        		}
		        		document.add(new Paragraph(""+(i+1)+" - "+questoes.get(i).getResolucao() + "\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
		        	}
		        	else
		        	{
		        		if(!questoes.get(i).enunimg.equals(""))
		        		{
		        			document.add(Image.getInstance(questoes.get(i).enunimg));
		        		}
		        		document.add(new Paragraph(""+(i+1)+" "+questoes.get(i).getResolucao() + "\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
		        	}
		        }
		        document.add(new Paragraph("Boa prova!"));
		        // step 5
		        System.out.println(filename);
		        document.close();
		   }

	
	
	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Turma == null) ? 0 : Turma.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prova other = (Prova) obj;
		if (Turma == null) {
			if (other.Turma != null)
				return false;
		} else if (!Turma.equals(other.Turma))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
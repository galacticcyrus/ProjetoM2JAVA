package sistema.beans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import sistema.modelos.Disciplina;
import sistema.service.DisciplinaService;

public class DisciplinaDataModel  extends ListDataModel<Disciplina> implements SelectableDataModel<Disciplina> {
private DisciplinaService servico = new DisciplinaService();
	
	public DisciplinaDataModel()
	{
		
	}

	public DisciplinaDataModel(List <Disciplina> list)
	{
	   super(list);	
	}

	
	@Override
	public Disciplina getRowData(String rowKey) {
		
		for(Disciplina d: servico.getDisciplinas())
		   if(Integer.parseInt(rowKey) ==  d.getid())
			   return d;
		
		return null;
	}

	@Override
	public Object getRowKey(Disciplina disciplina) {
		return disciplina.getid();
	}
}

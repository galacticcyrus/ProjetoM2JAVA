package sistema.beans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import sistema.modelos.Conteudo;
import sistema.service.ConteudoService;

public class ConteudoDataModel extends ListDataModel<Conteudo> implements SelectableDataModel<Conteudo> {
	private ConteudoService servico = new ConteudoService();
	public ConteudoDataModel()
	{
		
	}

	public ConteudoDataModel(List <Conteudo> list)
	{
	   super(list);	
	}

	
	@Override
	public Conteudo getRowData(String rowKey) {
		
		for(Conteudo c: servico.getConteudos())
		   if(Integer.parseInt(rowKey) ==  c.getId())
			   return c;
		
		return null;
	}

	@Override
	public Object getRowKey(Conteudo conteudo) {
		return conteudo.getId();
	}
}

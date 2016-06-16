package sistema.beans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sistema.modelos.Questao;
import sistema.service.QuestaoService;

@FacesConverter("converterQuestao")
public class QuestaoConverter implements Converter{
	private QuestaoService servico = new QuestaoService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			
			  for(Questao q : servico.getQuestoes()){
				  String s = "" + q.getId();
				 if(s.equals(value))
				  	return q;
			  }
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object questao) {
		if (questao == null || questao.equals("")) {
			return null;
		} else {
			String s = "" + ((Questao) questao).getId();
			return (s);

		}
	}
}

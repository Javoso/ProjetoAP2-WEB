package br.com.projetoWEB.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projetoWEB.model.Turma;

@FacesConverter(forClass = Turma.class, value = "generoConverter")
public class TurmaConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.isEmpty())
			return null;
		return arg1.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Turma genero = (Turma) object;
		if (genero == null || genero.getNomeDaTurma() == null)
			return null;
		arg1.getAttributes().put(String.valueOf(genero.getNomeDaTurma()), genero);
		return String.valueOf(genero.getNomeDaTurma());
	}
}

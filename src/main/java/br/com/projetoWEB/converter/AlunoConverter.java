package br.com.projetoWEB.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projetoWEB.model.Aluno;

@FacesConverter(forClass = Aluno.class, value = "atorConverter")
public class AlunoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.isEmpty())
			return null;
		return arg1.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Aluno ator = (Aluno) object;
		if (ator == null || ator.getNomeAluno() == null)
			return null;
		arg1.getAttributes().put(String.valueOf(ator.getNomeAluno()), ator);
		return String.valueOf(ator.getNomeAluno());
	}
}

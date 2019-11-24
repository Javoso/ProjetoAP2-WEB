package br.com.projetoWEB.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projetoWEB.model.Genero;

@FacesConverter(forClass = Genero.class, value = "generoConverter")
public class GeneroConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.isEmpty())
			return null;
		return arg1.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Genero genero = (Genero) object;
		if (genero == null || genero.getNome() == null)
			return null;
		arg1.getAttributes().put(String.valueOf(genero.getNome()), genero);
		return String.valueOf(genero.getNome());
	}
}

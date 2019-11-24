package br.com.projetoWEB.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projetoWEB.model.Permissao;

@FacesConverter(forClass = Permissao.class, value = "permissaoConverter")
public class PermissaoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.isEmpty())
			return null;
		return arg1.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Permissao permissao = (Permissao) object;
		if (permissao == null || permissao.getDescricao() == null)
			return null;
		arg1.getAttributes().put(String.valueOf(permissao.getDescricao()), permissao);
		return String.valueOf(permissao.getDescricao());
	}
}

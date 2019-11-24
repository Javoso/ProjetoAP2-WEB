package br.com.projetoWEB.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Permissao;
import br.com.projetoWEB.model.Usuario;
import br.com.projetoWEB.util.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		GenericDAO<Usuario> usuarios = CDIServiceLocator.getBean(GenericDAO.class);
		Usuario usuario = usuarios.findByAtributeSingle(Usuario.class, "email", email);
		SystemUser user = null;
		if (usuario != null) {
			user = new SystemUser(usuario, getAuthority(usuario));
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getAuthority(Usuario usuario) {
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		for (Permissao permissao : usuario.getPermissoes()) {
			authority.add(new SimpleGrantedAuthority(permissao.getDescricao().toUpperCase()));
		}
		return authority;
	}

}

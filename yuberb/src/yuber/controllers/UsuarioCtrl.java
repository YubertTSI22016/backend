package yuber.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import yuber.interfaces.IUsuario;
import yuber.interfaces.UsuarioLocalApi;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

/**
 * Session Bean implementation class UsuarioSrv
 */
@Stateless
public class UsuarioCtrl implements IUsuario {

	@EJB(lookup = "java:app/yuberdb/UsuarioSrv!yuber.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;

 
	@Override
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
	  
		return srvUsuario.crearUsuario(usuario, tenant);
	}

	 
}

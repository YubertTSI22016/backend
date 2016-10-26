package yuber.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.UsuarioLocalApi;
import yuber.models.Usuario;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
/**
 * Session Bean implementation class UsuarioSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class UsuarioSrv implements UsuarioLocalApi {
	@Inject
	EntityManager em;

	public UsuarioSrv() {

	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataUsuario> usuarios = new ArrayList();
		// obtengo todos los usuarios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Usuario> listUsu = new ArrayList<Usuario>(new LinkedHashSet(criteria.list()));

		listUsu.stream().forEach((usu) -> {
			usuarios.add(usu.getDatatype(true));
		});
		return usuarios;
	}

	public DataUsuario loginUsuario(String mailUsuario, String clave, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() == 1) {
			DataUsuario usuario = listUsu.get(0).getDatatype(true);
			if(usuario.getEliminado())
				return null;
			if (usuario.getClave().equals(clave))
				return usuario;
		}
		return null;
	}
	
	public DataUsuario loginFacebook(String mailUsuario, String uid, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() == 1) {
			DataUsuario usuario = listUsu.get(0).getDatatype(true);
			if(usuario.getEliminado())
				return null;
			if (usuario.getRedSocialUsada().equals("Facebook") && usuario.getIdRedSocial().equals(uid))
				return usuario;
		}
		return new DataUsuario();
	}

	public DataUsuario modificarUsuario(DataUsuario usu, DataTenant tenant) {
		Usuario realObj = new Usuario(usu);
		if (em.find(Usuario.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El usuario no existe");
		}
		em.merge(realObj);
		return realObj.getDatatype(true);
	}

	public DataUsuario getUsuario(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Usuario realObj = (Usuario) session.get(Usuario.class, id);
		return realObj.getDatatype(true);
	}

	public DataUsuario crearUsuario(DataUsuario usu, DataTenant tenant) {
		Usuario realObj = new Usuario(usu);
		realObj.setEliminado(false);
		// guardo el usuario en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaUsuario(String idUsuario, DataTenant tenant) {
		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setEliminado(true);
		this.modificarUsuario(usu, tenant);
	}

	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() > 0) {
			DataUsuario usuario = listUsu.get(0).getDatatype(false);
			return usuario;
		}
		return null;
	}
	
	//PAGOS
	//GUARDO EL TOKEN EN EL USUARIO
	public void guardarToken(String idUsuario, String token, DataTenant tenant){

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(token);
		this.modificarUsuario(usu, tenant);
	}

	//ELIMINO EL TOKEN DEL USUARIO
	public void eliminarToken(String idUsuario, DataTenant tenant){

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(null);
		this.modificarUsuario(usu, tenant);
	}	

	//CARGO LA TARJETA DEL USUARIO
	public void cargarTarjetaUsuario (String idUsuario, Float cargo, DataTenant tenant) {
		// Set your secret key: remember to change this to your live secret key in production
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.apiKey = "sk_test_0GH1eXDiz1lErEC4qT7PW658";
	
		// Get the credit card details submitted by the form
		//String token = request.getParameter("stripeToken");
		DataUsuario usu = getUsuario(idUsuario, tenant);
		String token = usu.getTokenTarjeta();
		String emailUsuario = usu.getEmail().getEmail();
	
		// Create a charge: this will charge the user's card
		try {
		  Map<String, Object> chargeParams = new HashMap<String, Object>();
		  chargeParams.put("amount", cargo); // Amount in cents
	 	  chargeParams.put("currency", "usd");
		  chargeParams.put("source", token);
		  chargeParams.put("description", "Cargo para: " + emailUsuario);
	
	 	  //Charge charge = Charge.create(chargeParams);
	 	  Charge.create(chargeParams);
	 	  
		} catch (Exception e) {
			  // Something else happened, completely unrelated to Stripe
		}

	}

}
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;

import ar.edu.um.dao.MensajeDao;
import ar.edu.um.dao.TemaDao;
import ar.edu.um.dao.TipoMensajeDao;
import ar.edu.um.dao.TipoUsuarioDao;
import ar.edu.um.dao.UsuarioDao;
import ar.edu.um.dominio.MensajeDominio;
import ar.edu.um.dominio.UsuarioDominio;
import ar.edu.um.entidad.Mensaje;
import ar.edu.um.entidad.Tema;
import ar.edu.um.entidad.TipoMensaje;
import ar.edu.um.entidad.TipoUsuario;
import ar.edu.um.entidad.Usuario;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Twitt");
	    EntityManager em = emf.createEntityManager();
	    
	    	    
	    Endpoint.publish("http://localhost:9898/MensajeDominio", new MensajeDominio());
	    Endpoint.publish("http://localhost:9898/UsuarioDominio", new UsuarioDominio());
        System.out.println("HelloWeb service is ready");
	    
	}

}

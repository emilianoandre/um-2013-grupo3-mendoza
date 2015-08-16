import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceProvider;

import ar.edu.um.dominio.MensajeDominio;
import ar.edu.um.dominio.UsuarioDominio;

@WebServiceProvider
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

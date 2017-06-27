package conec;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAConnection {
	
	private EntityManagerFactory conection;

	
	private EntityManagerFactory conect() {
		
		
		try {
			
			if (conection != null && conection.isOpen()) {
		
				return conection;
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		conection = Persistence.createEntityManagerFactory("TREASY");
		return conection;
		
		
	}

	
	protected EntityManager getEntityManager() {
		
		return conect().createEntityManager();
		
	}
	

	protected Query getQuery(String jpql) {
		
		return this.getEntityManager().createQuery(jpql);
		
	}

}

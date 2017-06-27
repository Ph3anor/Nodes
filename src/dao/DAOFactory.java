package dao;

public interface DAOFactory {

	
	@SuppressWarnings("rawtypes")
	
	public static Object getInstanceOf(Class c) {
		
		if (c.equals(NodeDAO.class)) {
			
			return new NodeJPADAO();
			
		}
		
		return null;
		
	}
	
}
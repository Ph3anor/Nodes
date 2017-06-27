package dao;

import pojo.Node;


public interface NodeDAO {

	
	Object edit = null;


	public void include(Node node);
	
	public Node searchForId(int id);
	
	public void delete(Node result);
	
	public void edit(Node node);
	
	
}

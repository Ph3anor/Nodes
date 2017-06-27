package dao;

import pojo.Node;

public class NodeJPADAO extends JPAAbstract<Node> implements NodeDAO {

	@Override
	public String getEntityName() {
		
		return Node.class.getSimpleName();
		
	}

}

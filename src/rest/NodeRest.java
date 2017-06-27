package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import dao.DAOFactory;
import dao.NodeDAO;
import dto.NodeDTO;
import pojo.Node;

@Path("/node")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class NodeRest extends UtilRest {
	public NodeRest() {
	}

	private NodeDAO nodeDao = (NodeDAO) DAOFactory.getInstanceOf(NodeDAO.class);

	@POST
	public Response addNode(String nodeParam) {
		try {
			NodeDTO nodeDTO = new ObjectMapper().readValue(nodeParam, NodeDTO.class);

			Node parent = null;
			if (nodeDTO.getParent() != null) {
				parent = nodeDao.searchForId(nodeDTO.getParent());
				if (parent == null) {
					return this.buildErrorResponse("Failed");
				}
			}

			Node node = new Node();
			node.setCode(nodeDTO.getCode());
			node.setDescription(nodeDTO.getDescription());
			node.setDetail(nodeDTO.getDetail());
			node.setParent(parent);

			nodeDao.include(node);

			return this.buildResponse("Sucess.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/{parentId}")
	public Response searchNodeForId(@PathParam("parentId") Integer parentId) {
		try {
			Node node = nodeDao.searchForId(parentId);
			return this.buildResponse(node);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	

	@POST
	@Path("/{name}")
	public Response searchNodeForName(@PathParam("name") String name) {
		try {
			List<Node> nodes = new ArrayList<Node>();

			return this.buildResponse(nodes); // método build response
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}



	@GET
	@Path("/all")
	public Response all() {
		try {
			Node node = nodeDao.searchForId(1);
			return this.buildResponse(node);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteNode(@PathParam("id") int id) {
		try {
			Node result = nodeDao.searchForId(id);
			nodeDao.delete(result);

			return this.buildResponse("Sucess.");
		} catch (Exception e) {
			System.out.println("Error class NodeRest method process: " + e);
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	

	@PUT
	@Path("/{id}")
	public Response editNode(@PathParam("id") int id ,String nodeParam) {
		try {
			Node node = new ObjectMapper().readValue(nodeParam, Node.class);
			node.setId(id);
			nodeDao.edit(node);

			return this.buildResponse("Sucess.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Calculation;
import response.ErrorResponse;
import response.Result;

@Stateless
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculationBean {
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	@POST
	@Path("calc")
	public Response addCalculation(Calculation calculation) {
		if (calculation == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse("Please provide a valid calculation")).build();
		}
		
		int n1 = calculation.getNumber1();
		int n2 = calculation.getNumber2();
		
		float result = 0;
		
		switch (calculation.getOperation()) {
		case "/":
			result = (float) n1 / n2;
			break;
		case "*":
			result = n1 * n2;
			break;
		case "+":
			result = n1 + n2;
			break;
		case "-":
			result = n1 - n2;
			break;
		default:
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse("Invalid operation")).build();
		}
		
		entityManager.persist(calculation);
		
		return Response.ok(new Result(result)).build();
	}
	
	@GET
	@Path("calculations")
	public List<Calculation> getCalculations() {
		TypedQuery<Calculation> query = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class);
		
		return query.getResultList();
	}
}
package com.redhat.c0dered.math.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.redhat.c0dered.math.MathOperations;
import com.redhat.c0dered.math.MathResult;

@Path("/math")
public class MathService {

	private Response buildResponse(MathResult res) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return Response.status(200).entity(mapper.writeValueAsString(res)).build();
		} catch (IOException e) {
			//TODO: Log the stack traces with errors instead of printing to console
			e.printStackTrace();
		}
		
		return Response.status(200).entity("{\"error\":\"Error Processing Request\"}").build();
	}
	
	@GET
	@Produces("application/json")
	@Path("/verify")
	public Response verify() {
		return buildResponse(new MathResult("REST API Verification Success!","0"));
	}
	
	@GET
	@Produces("application/json")
	@Path("/add")
	public Response add(@QueryParam("a") String a, @QueryParam("b") String b) {
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			return buildResponse(new MathResult("Both Operands 'a' and 'b' Must Be Provided","0"));
		}
		return buildResponse(MathOperations.add(a, b));
	}
	
	@GET
	@Produces("application/json")
	@Path("/subtract")
	public Response subtract(@QueryParam("a") String a, @QueryParam("b") String b) {
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			return buildResponse(new MathResult("Both Operands 'a' and 'b' Must Be Provided","0"));
		}
		return buildResponse(MathOperations.subtract(a, b));
	}
	
	@GET
	@Produces("application/json")
	@Path("/multiply")
	public Response multiply(@QueryParam("a") String a, @QueryParam("b") String b) {
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			return buildResponse(new MathResult("Both Operands 'a' and 'b' Must Be Provided","0"));
		}
		return buildResponse(MathOperations.multiply(a, b));
	}
	
	@GET
	@Produces("application/json")
	@Path("/divide")
	public Response divide(@QueryParam("a") String a, @QueryParam("b") String b) {
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			return buildResponse(new MathResult("Both Operands 'a' and 'b' Must Be Provided","0"));
		}
		return buildResponse(MathOperations.divide(a, b));
	}

	@GET
	@Produces("application/json")
	@Path("/")
	public Response invalid() {
		return buildResponse(new MathResult("Invalid API Call: 'null'","0"));
	}
	
	@GET
	@Produces("application/json")
	@Path("/{invalid}")
	public Response invalid(@PathParam("invalid") String req) {
		return buildResponse(new MathResult("Invalid API Call: '"+ req + "'","0"));
	}
}

package com.Rest.Jersey_Rest;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {
	
	
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User create(User user) {
		UserRepository ur = new UserRepository();
		if(ur.createUser(user))
		{
			ur.closeConnection();
			return user;
		}
			
		return null;
	}
	
	
	@GET
	@Path("/getUsers")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<User> getAll() {
		UserRepository ur = new UserRepository();
		List<User> users = ur.getAllUsers();
		ur.closeConnection();
		return users;
		
	}
	
	@GET
	@Path("/getUsers/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User getUser(@PathParam("id") int id) {
		
		UserRepository ur = new UserRepository();
		User user = ur.getUserById(id);
		ur.closeConnection();
		return user;
		
	}
	
	
	@PUT
	@Path("/updateUsers")
	public User updateUser(User user) {
		UserRepository ur = new UserRepository();
		if(ur.getUserById(user.getId())==null)
			 ur.createUser(user);
		else
			ur.updateUser(user);
		ur.closeConnection();
		return user;
		
	}
	
	
	@DELETE
	@Path("/deletUsers")
	public User deleteUser(User user) {
		UserRepository ur = new UserRepository();
		
		ur.deleteUser(user);
		ur.closeConnection();
		return user;
	}

}

package com.example.helloworld.resources;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.helloworld.core.Player;
import com.example.helloworld.db.PlayerDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
	private final Logger logger = LoggerFactory.getLogger(PlayerResource.class); 
	
	private final PlayerDAO playerDAO;
	public PlayerResource(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

    @POST
    @UnitOfWork(value="hibernate-A")
    public Response addPlayer(@Valid Player player) throws IOException {
    	Optional<Player> per = playerDAO.findById(player.getId()) ;
		if(per.isPresent()) {
			return Response.ok(playerDAO.update(player)).build();
			
		} else {
			return Response.ok(playerDAO.create(player)).build();
		}
    }

    @GET
    @Path("/{id}")
    @UnitOfWork(value="hibernate-A")
    public Response getPlayer(@PathParam("id") Long id) {
    	Optional<Player> player = playerDAO.findById(id);
    	if (player.isPresent())
            return Response.ok(player).build();
        else
            return Response.status(Status.NOT_FOUND).build();

    }

    /*@DELETE
    @Path("/{id}")
    public Response deletePlayer(@PathParam("id") Long id) {
        playerService.deleteById(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/find")
    public List<Player> getPlayerByName(@QueryParam("name") String name) {
        return playerService.findPlayerByName(Optional.ofNullable(name));
    }*/

    @GET
    @UnitOfWork(value="hibernate-A")
    public List<Player> getPlayerList() {
    	logger.info("Inside Player Resource ");
        return playerDAO.findAll();
    }
    
    /*@DELETE
    @RolesAllowed("ADMIN")
    public Response removeAllPlayer() throws Exception {
        playerService.removeAll();
        
        return Response.noContent().build();
    }*/
    
}

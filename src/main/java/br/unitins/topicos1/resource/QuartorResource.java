package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.QuartoHotel;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/quartoHotel")
public class QuartorResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <QuartoHotel> todosQuartoHotels(){
        return QuartoHotel.listAll();
    }
}

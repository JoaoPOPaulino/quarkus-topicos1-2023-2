package br.unitins.topicos1.resource;

import java.io.File;
import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.form.QuartoImageForm;
import br.unitins.topicos1.model.TipoQuarto;
import br.unitins.topicos1.service.quarto.QuartoFileService;
import br.unitins.topicos1.service.quarto.QuartoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/quartos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartoResource {

    @Inject
    QuartoService service;

    @Inject
    QuartoFileService fileService;

    @POST
    public Response insert(@Valid QuartoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid QuartoDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        QuartoResponseDTO response = service.findById(id);
        return Response.ok(response).build();
    }

    @GET
    @Path("/serach/tipoQuarto/{tipoQuarto}")
    public Response findByTipo(@PathParam("tipoQuarto") TipoQuarto tipoQuarto) {
        return Response.ok(service.findByTipo(tipoQuarto)).build();
    }

    @PATCH
    @Path("{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagemQuarto(@PathParam("id") Long id, @MultipartForm QuartoImageForm form) {
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("406", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        QuartoResponseDTO response = service.updateNomeImagem(id, nomeImagem);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).entity("Quarto não encontrado").build();
        }

        return Response.ok(response).build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        File file = fileService.obter(nomeImagem);
        if (file == null || !file.exists()) {
            return Response.status(Status.NOT_FOUND).entity("Imagem não encontrada.").build();
        }

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
        return response.build();
    }

}

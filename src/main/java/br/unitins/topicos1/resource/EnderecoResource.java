package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/enderecos")
public class EnderecoResource {

    @Inject
    EnderecoService service;

    // Métodos CRUD
}

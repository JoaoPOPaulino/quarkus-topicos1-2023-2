package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TipoQuartoDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.QuartoService;
import br.unitins.topicos1.service.ReservaService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ReservaResourceTest {

        @Inject
        ReservaService reservaService;

        @Inject
        UsuarioService usuarioService;

        @Inject
        QuartoService quartoService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/reservas")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                UsuarioDTO usuarioTest = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoTest = new QuartoDTO(
                                1,
                                150.0,
                                true,
                                tipo);

                QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);

                ReservaDTO dto = new ReservaDTO(dataInicio, dataFim, 1, 200.0, quarto.id(), usuario.id());

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/reservas")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "dataInicio", is(dataInicio.toString()),
                                                "dataFim", is(dataFim.toString()),
                                                "preço", is(200.0f));
        }

        @Test
        public void testUpdate() {

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);
                UsuarioDTO usuarioTest = new UsuarioDTO("Mark Zuckerberg Update", "marquinho", "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoTest = new QuartoDTO(1, 150.0, true, tipo);
                QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim, 1, 200.0, quarto.id(), usuario.id());
                ReservaResponseDTO reserva = reservaService.insert(reservaDTO);

                LocalDate newDataFim = dataFim.plusDays(3);
                reservaDTO = new ReservaDTO(dataInicio, newDataFim, 1, 250.0, quarto.id(), usuario.id());

                given()
                                .contentType(ContentType.JSON)
                                .body(reservaDTO)
                                .when().put("/reservas/")
                                .then()
                                .statusCode(204);
                given()
                                .when().get("/reservas/")
                                .then()
                                .statusCode(200)
                                .body(
                                                "id", is(reserva.id().intValue()),
                                                "dataInicio", is(dataInicio.toString()),
                                                "dataFim", is(newDataFim.toString()),
                                                "preço", is(250.0f));
        }

        @Test
        public void testDelete() {

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);
                UsuarioDTO usuarioTest = new UsuarioDTO("Mark Zuckerberg Delete", "marquinho", "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoTest = new QuartoDTO(1, 150.0, true, tipo);
                QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim, 1, 200.0, quarto.id(), usuario.id());
                ReservaResponseDTO reserva = reservaService.insert(reservaDTO);
                given()
                                .when().delete("/reservas/")
                                .then()
                                .statusCode(204);

                given()
                                .when().get("/reservas/")
                                .then()
                                .statusCode(404);
        }
}

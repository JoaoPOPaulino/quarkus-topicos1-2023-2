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
                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);
                UsuarioDTO usuarioTest = new UsuarioDTO("Novo Usuário", "novoUsuario", "senha123", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoTest = new QuartoDTO(1, 150.0, true, tipo);
                QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dto = new ReservaDTO(dataInicio, dataFim, 1, quarto.id(), quarto.preco(),
                                usuario.id());

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
                                                "preco", is(200.0f));
        }

        @Test
        public void testUpdate() {
                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);
                UsuarioDTO usuarioTest = new UsuarioDTO("Mark Zuckerberg Update", "ok", "senha123", 1,
                                telefones, endereco);

                UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");

                QuartoDTO quartoTest = new QuartoDTO(1, 150.0, true, tipo);

                QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, 1, quarto.id(), quarto.preco(),
                                usuario.id());

                ReservaResponseDTO reservaTest = reservaService.insert(dtoInsert);

                Long id = reservaTest.id();

                LocalDate newDataFim = dataFim.plusDays(3);
                ReservaDTO dtoUpdate = new ReservaDTO(dataInicio, newDataFim, 1, quarto.id(), quarto.preco(),
                                usuario.id());

                                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().post("/reservas")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "dataInicio", is(dataInicio.toString()),
                                                "dataFim", is(dataFim.toString()),
                                                "preco", is(200.0f));given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/reservas/" + id)
                                .then()
                                .statusCode(204);
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
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, 1, quarto.id(), quarto.preco(),
                                usuario.id());
                ReservaResponseDTO reservaTest = reservaService.insert(dtoInsert);

                Long id = reservaTest.id();
                given()
                                .when().delete("/reservas/" + id)
                                .then()
                                .statusCode(204);

                given()
                                .when().get("/reservas/" + id)
                                .then()
                                .statusCode(404);
        }
}

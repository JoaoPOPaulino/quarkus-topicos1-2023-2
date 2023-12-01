package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.quarto.QuartoService;
import br.unitins.topicos1.service.reserva.ReservaService;
import br.unitins.topicos1.service.usuario.UsuarioService;
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
                // List<TelefoneDTO> telefones = new ArrayList<>();
                // telefones.add(new TelefoneDTO("63", "5555-5555"));
                // EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                // 123);
                // UsuarioDTO usuarioTest = new UsuarioDTO("Novo Usuário", "novoUsuario",
                // "senha123", 1, telefones,
                // endereco);
                // UsuarioResponseDTO usuario = usuarioService.insert(usuarioTest);

                // TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                // QuartoDTO quartoTest = new QuartoDTO(1, 150.0, true, tipo);
                // QuartoResponseDTO quarto = quartoService.insert(quartoTest);

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dto = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/reservas")
                                .then()
                                .statusCode(201)
                                .body("dataInicio", notNullValue())
                                .body("dataFim", notNullValue())
                                .body("preco", equalTo(150.0F));
        }

        @Test
        public void testUpdate() {

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);

                ReservaResponseDTO reservaTest = reservaService.insert(dtoInsert);
                Long id = reservaTest.id();

                LocalDate newDataFim = dataFim.plusDays(3);
                ReservaDTO dtoUpdate = new ReservaDTO(dataInicio, newDataFim, idQuarto,
                                idUsuario);
                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/reservas/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);
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

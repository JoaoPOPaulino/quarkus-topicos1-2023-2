package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ServicoDTO;
import br.unitins.topicos1.dto.ServicoResponseDTO;
import br.unitins.topicos1.service.ServicoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ServicoResourceTest {

        @Inject
        ServicoService servicoService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/servicos")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                LocalDateTime horaInicio = LocalDateTime.of(2023, 1, 1, 8, 0);
                LocalDateTime horaFim = LocalDateTime.of(2023, 1, 1, 11, 30);

                ServicoDTO dto = new ServicoDTO(
                                "Café da Manhã",
                                "Serviço de café da manhã",
                                horaInicio,
                                horaFim);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/servico")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Café da Manhã"),
                                                "descricao", is("Serviço de café da manhã"));
        }

        @Test
        public void testUpdate() {
                LocalDateTime horaInicio = LocalDateTime.of(2023, 1, 1, 8, 0);
                LocalDateTime horaFim = LocalDateTime.of(2023, 1, 1, 11, 30);
                ServicoDTO dtoInsert = new ServicoDTO(
                                "Café da Manhã",
                                "Descrição Original",
                                horaInicio,
                                horaFim);

                ServicoResponseDTO servicoTest = servicoService.insert(dtoInsert);
                Long id = servicoTest.id();

                LocalDateTime horaInicioUpdate = LocalDateTime.of(2023, 1, 1, 8, 0);
                LocalDateTime horaFimUpdate = LocalDateTime.of(2023, 1, 1, 11, 0);
                ServicoDTO dtoUpdate = new ServicoDTO(
                                "Café da Manhã Atualizado",
                                "Descrição Atualizada",
                                horaInicioUpdate,
                                horaFimUpdate);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/servico/" + id)
                                .then()
                                .statusCode(204);
        }

}

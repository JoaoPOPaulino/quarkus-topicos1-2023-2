package br.unitins.topicos1;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.service.ReservaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ReservaResourceTest {

    @Inject
    ReservaService reservaService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/reservas")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim = dataInicio.plusDays(7);

        ReservaDTO dto = ReservaDTO();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/reservas")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "dataInicio", notNullValue(),
                        "dataFim", notNullValue(),
                        "quartoId", is(2) // ajuste conforme necessário
                );
    }
}

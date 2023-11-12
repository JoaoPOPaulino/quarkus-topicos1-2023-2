package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.service.QuartoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class QuartoResourceTest {

    @Inject
    QuartoService quartoService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/quartos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        QuartoDTO dto = new QuartoDTO(1, 101, 150.0, true);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/quartos")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "tipoQuarto", is("CASUAL"),
                        "numero", is(101),
                        "preco", is(150.0f),
                        "disponivel", is(true));
    }

    @Test
    public void testUpdate() {

        QuartoDTO dtoInsert = new QuartoDTO(1, 101, 150.0, true);

        QuartoResponseDTO quartoTest = quartoService.insert(dtoInsert);

        Long id = quartoTest.id();

        QuartoDTO dtoUpdate = new QuartoDTO(1, 101, 300.0, true);

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                .put("/quartos/" + id)
                .then()
                .statusCode(204);

        // Verifica se o quarto foi atualizado corretamente
        given()
                .when()
                .get("/quartos/" + id)
                .then()
                .statusCode(200)
                .body("preco", is(300.0f));
    }

    @Test
    public void testDelete() {

        QuartoDTO dto = new QuartoDTO(1, 101, 150.0, true);

        QuartoResponseDTO quartoTest = quartoService.insert(dto);

        Long id = quartoTest.id();

        given()
                .when()
                .delete("/quartos/" + id)
                .then()
                .statusCode(204);

        // Verifica se o quarto foi excluído corretamente
        given()
                .when()
                .get("/quartos/" + id)
                .then()
                .statusCode(404);
    }
}

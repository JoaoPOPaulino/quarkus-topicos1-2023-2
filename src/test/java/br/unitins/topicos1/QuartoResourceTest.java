package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.dto.TipoQuartoDTO;
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
                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
                QuartoDTO dto = new QuartoDTO(
                                1,
                                150.0,
                                true,
                                tipo);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/quartos")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "numero", is(101),
                                                "preco", is(150.0f),
                                                "disponivel", is(true),
                                                "tipoQuarto.id", is(1),
                                                "tipoQuarto.label", is("Casual"));
        }

        @Test
        public void testUpdate() {
                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");

                QuartoDTO dto = new QuartoDTO(
                                101,
                                150.0,
                                true,
                                tipo);

                QuartoResponseDTO quartoTest = quartoService.insert(dto);
                Long id = quartoTest.id();

                QuartoDTO dtoUpdate = new QuartoDTO(
                                101,
                                200.0,
                                false,
                                tipo);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when()
                                .put("/quartos/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {
                TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");

                QuartoDTO dto = new QuartoDTO(
                                101,
                                150.0,
                                true,
                                tipo);

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

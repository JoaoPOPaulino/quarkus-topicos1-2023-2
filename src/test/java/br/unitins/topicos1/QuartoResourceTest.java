package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto.TipoQuarto;
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
                .when().get("/quarto")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        QuartoDTO dto = createSampleQuartoDTO(101, TipoQuarto.LUXO, 350.0, true);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/quarto")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "numero", is(101),
                        "tipo", is("LUXO"),
                        "preco", is(350.0f),
                        "disponivel", is(true));
    }

    @Test
    public void testUpdate() {
        QuartoDTO dtoInsert = createSampleQuartoDTO(102, TipoQuarto.CASAL, 300.0, false);

        QuartoResponseDTO quartoTest = quartoService.insert(dtoInsert);

        QuartoDTO dtoUpdate = createSampleQuartoDTO(102, TipoQuarto.SIMPLES, 250.0, true);

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/quarto/" + quartoTest.getId())
                .then()
                .statusCode(204);
    }

    private QuartoDTO createSampleQuartoDTO(int numero, TipoQuarto tipo, double preco, boolean disponivel) {
        return new QuartoDTO(numero, tipo, preco, disponivel);
    }

    @Test
    public void testDelete() {
        QuartoDTO dto = createSampleQuartoDTO(103, TipoQuarto.LUXO, 200.0, true);

        QuartoResponseDTO quartoTest = quartoService.insert(dto);

        given()
                .when().delete("/quarto/" + quartoTest.getId())
                .then()
                .statusCode(204);
    }
}

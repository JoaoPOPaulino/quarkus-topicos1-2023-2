package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.dto.ComentarioResponseDTO;
import br.unitins.topicos1.service.ComentarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ComentarioResourceTest {

    @Inject
    ComentarioService comentarioService;

    @Test
    void testFindAll() {
        given()
                .when().get("/comentarios")
                .then()
                .statusCode(200);
    }

    @Test
    void testInsert() {

        ComentarioDTO dto = new ComentarioDTO(
                "Este é um comentário de teste",
                LocalDateTime.now(),
                1L);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/comentarios")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "conteudo", is("Este é um comentário de teste"));
    }

    @Test
    public void testUpdate() {
        ComentarioDTO dtoInsert = new ComentarioDTO(
                "Comentário para Update",
                LocalDateTime.now(),
                1L);

        ComentarioResponseDTO comentarioTest = comentarioService.insert(dtoInsert);
        Long id = comentarioTest.id();

        ComentarioDTO dtoUpdate = new ComentarioDTO(
                "Comentário Atualizado",
                LocalDateTime.now(),
                1L);

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/comentarios/" + id)
                .then()
                .statusCode(204);
    }

    @Test
    public void testDelete() {
        ComentarioDTO dto = new ComentarioDTO(
                "Comentário para Delete",
                LocalDateTime.now(),
                1L);

        ComentarioResponseDTO comentarioTest = comentarioService.insert(dto);
        Long id = comentarioTest.id();
        given()
                .when().delete("/comentarios/" + id)
                .then()
                .statusCode(204);
    }

}

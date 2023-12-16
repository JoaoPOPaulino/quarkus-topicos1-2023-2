package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.comentario.ComentarioService;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ComentarioResourceTest {

        @Inject
        ComentarioService comentarioService;

        @Inject
        UsuarioService usuarioService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/comentarios")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {

                UsuarioDTO dtoInsert = new UsuarioDTO(
                                "Mark Zuckerberg Update",
                                "morkos",
                                "mark@zuckerberg.com",
                                "333");

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste");
                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/comentarios")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "conteudo", is("Comentário de teste"));
        }

        @Test
        public void testUpdate() {

                String loginUnico = "usuario_" + UUID.randomUUID().toString();

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Mark Zuckerberg Update",
                                loginUnico,
                                loginUnico + "@zuckerberg.com",
                                "senha123");

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoUsuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste");
                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto, usuarioTest);

                ComentarioDTO dtoUpdate = new ComentarioDTO("Comentário Atualizado");
                Long id = comentarioTest.id();

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/comentarios/" + id)
                                .then()
                                .statusCode(200)
                                .body("conteudo", is("Comentário Atualizado"));
        }

        @Test
        public void testDelete() {

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Teste",
                                "TestComentario",
                                "teste@hotmail.com",
                                "333");
                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoUsuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste");

                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto, usuarioTest);

                Long id = comentarioTest.id();
                given()
                                .when().delete("/comentarios/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testFindComentariosByData() {
                LocalDateTime dataTeste = LocalDateTime.now().minusDays(1);
                given()
                                .queryParam("data", dataTeste.toString())
                                .when().get("/comentarios/data")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testFindById() {

                UsuarioDTO usuario = new UsuarioDTO(
                                "Teste",
                                "TestComentario",
                                "teste.com",
                                "333");
                UsuarioResponseDTO usuarioTest = usuarioService.insert(usuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste");

                ComentarioResponseDTO comentario = comentarioService.insert(dto, usuarioTest);
                Long id = comentario.id();
                given()
                                .when().get("/comentarios/" + id)
                                .then()
                                .statusCode(200)
                                .body("id", is(id.intValue()));
        }

}

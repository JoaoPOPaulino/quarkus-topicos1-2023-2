package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {

        @Inject
        UsuarioService usuarioService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/usuarios")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "mark@zuckerberg.com",
                                "333");

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Mark Zuckerberg Insert"),
                                                "login", is("marquinho"),
                                                "email", is("mark@zuckerberg.com"));
        }

        @Test
        public void testUpdate() {
                UsuarioDTO novoUsuario = new UsuarioDTO(
                                "Usuário Teste", "usuario_teste", "usuario@hotmail.com", "senha123");
                UsuarioResponseDTO usuarioInserido = usuarioService.insert(novoUsuario);

                UsuarioDTO usuarioAtualizado = new UsuarioDTO(
                                "Usuário Teste Atualizado", "usuario_teste", "usuario@hotmail.com", "senha123");
                given()
                                .contentType(ContentType.JSON)
                                .body(usuarioAtualizado)
                                .when().put("/usuarios/" + usuarioInserido.id())
                                .then()
                                .statusCode(204);
        }

        @Test
        void testDelete() {

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Delete",
                                "marquinho",
                                "marquinho2hotmail.com",
                                "333");

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
                Long id = usuarioTest.id();

                given()
                                .when().delete("/usuarios/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testFindById() {
                Long id = 1L;
                given()
                                .when()
                                .get("/usuarios/" + id)
                                .then()
                                .statusCode(200)
                                .body("id", is(id.intValue()));
        }
}
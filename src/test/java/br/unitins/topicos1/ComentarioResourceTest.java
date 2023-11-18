package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.dto.ComentarioResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.ComentarioService;
import br.unitins.topicos1.service.UsuarioService;
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

                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                String loginDinamico = "comentarioTeste" + System.currentTimeMillis();

                UsuarioDTO dtoInsert = new UsuarioDTO(
                                "Comentario Test",
                                loginDinamico,
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

                ComentarioDTO dto = new ComentarioDTO(
                                "Este é um comentário de teste",
                                LocalDateTime.now(),
                                usuarioTest.id());

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
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                String loginDinamico = "comentarioTeste" + System.currentTimeMillis();

                UsuarioDTO dtoInsert = new UsuarioDTO(
                                "Comentario Test",
                                loginDinamico,
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

                ComentarioDTO dto = new ComentarioDTO(
                                "Comentário para Update",
                                LocalDateTime.now(),
                                usuarioTest.id());

                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto);
                Long id = comentarioTest.id();

                ComentarioDTO dtoUpdate = new ComentarioDTO(
                                "Comentário Atualizado",
                                LocalDateTime.now(),
                                usuarioTest.id());

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/comentarios/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                String loginDinamico = "comentarioTeste" + System.currentTimeMillis();

                UsuarioDTO dtoInsert = new UsuarioDTO(
                                "Comentario Test",
                                loginDinamico,
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

                ComentarioDTO dto = new ComentarioDTO(
                                "Comentário para Delete",
                                LocalDateTime.now(),
                                usuarioTest.id());

                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto);
                Long id = comentarioTest.id();
                given()
                                .when().delete("/comentarios/" + id)
                                .then()
                                .statusCode(204);
        }

}

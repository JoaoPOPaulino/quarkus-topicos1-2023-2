package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
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
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);
                UsuarioResponseDTO usuarioCriado = usuarioService.insert(dtoUsuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste", LocalDateTime.now(),
                                usuarioCriado.id());
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
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "oi",
                                "333",
                                1,
                                telefones,
                                endereco);
                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoUsuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste", LocalDateTime.now(),
                                usuarioTest.id());

                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto);

                ComentarioDTO dtoUpdate = new ComentarioDTO(
                                "Comentário Atualizado",
                                LocalDateTime.now(),
                                usuarioTest.id());

                Long id = comentarioTest.id();

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

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);
                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoUsuario);

                ComentarioDTO dto = new ComentarioDTO("Comentário de teste", LocalDateTime.now(),
                                usuarioTest.id());

                ComentarioResponseDTO comentarioTest = comentarioService.insert(dto);

                Long id = comentarioTest.id();
                given()
                                .when().delete("/comentarios/" + id)
                                .then()
                                .statusCode(204);
        }

}

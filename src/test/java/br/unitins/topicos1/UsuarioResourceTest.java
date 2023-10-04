package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
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
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                telefones,
                                endereco);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Mark Zuckerberg Insert"),
                                                "login", is("marquinho"));
        }

        @Test
        public void testUpdate() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                UsuarioDTO dtoInsert = new UsuarioDTO(
                                "Mark Zuckerberg Update",
                                "morkos",
                                "333",
                                telefones,
                                endereco);

                // Inserir um usuário
                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

                // Obter o ID do usuário inserido
                Long id = usuarioTest.getId();

                UsuarioDTO dtoUpdate = new UsuarioDTO(
                                "Mark Zuckerberg",
                                "mark",
                                "555",
                                telefones,
                                endereco);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/usuarios/" + id) // Use o ID obtido para formar o URL de atualização
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Delete",
                                "marquinho",
                                "333",
                                telefones,
                                endereco);

                // Inserindo um usuário
                UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
                Long id = usuarioTest.getId();

                given()
                                .when().delete("/usuarios/" + id)
                                .then()
                                .statusCode(204);
        }
}

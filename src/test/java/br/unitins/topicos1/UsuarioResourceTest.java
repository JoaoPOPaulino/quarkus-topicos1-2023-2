package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
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
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                1,
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
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Update",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
                Long id = usuarioTest.id();

                UsuarioDTO dtoUpdate = new UsuarioDTO(
                                "Mark Zuckerberg",
                                "mark",
                                "555",
                                1,
                                telefones,
                                endereco);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/usuarios/" + id)
                                .then()
                                .statusCode(204);

                // verificando a alteracao

                System.out.println(id);
                System.out.println(id);
                System.out.println(id);
                System.out.println(id);
                UsuarioResponseDTO usu = usuarioService.findById(4l);
                assertThat(usu.nome(), is("Mark Zuckerberg"));
                assertThat(usu.login(), is("mark"));

        }

        @Test
        void testDelete() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Delete",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
                Long id = usuarioTest.id();

                given()
                                .when().delete("/usuarios/" + id)
                                .then()
                                .statusCode(204);
        }
}

// @Test
// public void testUpdate() {
// List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
// telefones.add(new TelefoneDTO("63", "5555-5555"));

// UsuarioDTO dto = new UsuarioDTO(
// "Mark Zuckerberg Update",
// "marquinho",
// "333",
// telefones
// );

// // inserindo um usuario
// UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
// Long id = usuarioTest.id();

// UsuarioDTO dtoUpdate = new UsuarioDTO(
// "Mark Zuckerberg",
// "mark",
// "555",
// telefones
// );

// given()
// .contentType(ContentType.JSON)
// .body(dtoUpdate)
// .when().put("/usuarios/"+ id)
// .then()
// .statusCode(204);

// // verificando a alteracao

// System.out.println(id);
// System.out.println(id);
// System.out.println(id);
// System.out.println(id);
// UsuarioResponseDTO usu = usuarioService.findById(4l);
// assertThat(usu.nome(), is("Mark Zuckerberg"));
// assertThat(usu.login(), is("mark"));

// }
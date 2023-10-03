package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Endereco;
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
        UsuarioDTO dto = createSampleUsuarioDTO(
                "Mark Zuckerberg Insert",
                "marquinho",
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
                        "login", is("marquinho"));
    }

    @Test
    public void testUpdate() {
        UsuarioDTO dtoInsert = createSampleUsuarioDTO(
                "Mark Zuckerberg Update",
                "marquinho",
                "333");

        // inserindo um usuario
        UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoInsert);

        UsuarioDTO dtoUpdate = createSampleUsuarioDTO(
                "Mark Zuckerberg",
                "mark",
                "555");

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/usuarios/" + usuarioTest.id())
                .then()
                .statusCode(204);
    }

    private UsuarioDTO createSampleUsuarioDTO(String nome, String login, String senha) {
        List<TelefoneDTO> telefones = new ArrayList<>();
        telefones.add(new TelefoneDTO("63", "5555-5555"));

        Endereco endereco = new Endereco();
        endereco.setEstado("Tocantins");
        endereco.setCidade("Palmas");
        endereco.setRua("Rua Principal");
        endereco.setNumero("123A");
        endereco.setQuadra("Quadra 5");

        return new UsuarioDTO(nome, login, senha, telefones, endereco);
    }
}

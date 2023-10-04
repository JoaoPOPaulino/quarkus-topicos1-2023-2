package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Pagamento.TipoPagamento;
import br.unitins.topicos1.service.PagamentoService;
import br.unitins.topicos1.service.ReservaService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

        @Inject
        PagamentoService pagamentoService;

        @Inject
        ReservaService reservaService;

        @Inject
        UsuarioService usuarioService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/pagamentos")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                PagamentoDTO dto = new PagamentoDTO(
                                TipoPagamento.CARTAO_CREDITO,
                                null); // Não associamos o pagamento a uma reserva neste teste

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/pagamentos")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "tipoPagamento", is("CARTAO_CREDITO"),
                                                "reservaId", is(nullValue())); // Usamos is(nullValue()) para verificar
                                                                               // se o campo é nulo
        }

        @Test
        public void testUpdate() {
                // Crie um usuário fictício
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123);

                UsuarioDTO dtoUsuario = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dtoUsuario);
                Long idUsuario = usuarioTest.getId();

                // Crie uma reserva fictícia
                ReservaDTO dtoReserva = new ReservaDTO(
                                idUsuario, // Use o ID do usuário criado anteriormente
                                "2023-12-01",
                                "2023-12-10");

                ReservaResponseDTO reservaTest = reservaService.insert(dtoReserva);
                Long idReserva = reservaTest.getId();

                // Inserir um pagamento fictício
                PagamentoDTO dtoInsert = new PagamentoDTO(
                                TipoPagamento.CARTAO_CREDITO,
                                idReserva);

                PagamentoResponseDTO pagamentoTest = pagamentoService.insert(dtoInsert);
                Long idPagamento = pagamentoTest.getId();

                // Atualizar o pagamento fictício
                PagamentoDTO dtoUpdate = new PagamentoDTO(
                                TipoPagamento.BOLETO,
                                idReserva); // Use o mesmo ID de reserva para a atualização

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/pagamentos/" + idPagamento)
                                .then()
                                .statusCode(204);
        }

}

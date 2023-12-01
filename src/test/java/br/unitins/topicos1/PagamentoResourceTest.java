package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.dto.tipo.TipoPagamentoDTO;
import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.service.pagamento.PagamentoService;
import br.unitins.topicos1.service.quarto.QuartoService;
import br.unitins.topicos1.service.reserva.ReservaService;
import br.unitins.topicos1.service.usuario.UsuarioService;
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
    QuartoService quartoService;

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
        TipoQuartoDTO tipo = new TipoQuartoDTO(1, "Casual");
        QuartoDTO quartoInsert = new QuartoDTO(
                1,
                150.0,
                true,
                tipo);
        QuartoResponseDTO quartoTest = quartoService.insert(quartoInsert);

        QuartoResponseDTO quartoRecuperado = quartoService.findById(quartoTest.id());
        double precoQuarto = quartoRecuperado.preco();

        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("63", "5555-5555"));
        EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                123);

        UsuarioDTO usuarioInsert = new UsuarioDTO(
                "Mark Zuckerberg Update",
                "marquinho",
                "333",
                1,
                telefones,
                endereco);

        UsuarioResponseDTO usuarioTest = usuarioService.insert(usuarioInsert);

        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataInicio.plusDays(5);
        ReservaDTO reservaInsert = new ReservaDTO(dataInicio, dataFim, quartoTest.id(),
                usuarioTest.id());

        ReservaResponseDTO reservaTest = reservaService.insert(reservaInsert);

        TipoPagamentoDTO tipoP = new TipoPagamentoDTO(1, "Cartão de Crédito");
        LocalDateTime dataPagamento = LocalDateTime.now();

        PagamentoDTO dto = new PagamentoDTO(dataPagamento, reservaTest.id(), tipoP);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/pagamentos")
                .then()
                .statusCode(201)
                .body("dataPagamento", notNullValue())
                .body("reserva.id", is(reservaTest.id()))
                .body("valor", equalTo(precoQuarto));
    }
}
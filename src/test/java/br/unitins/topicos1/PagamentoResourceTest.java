package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.dto.tipo.TipoPagamentoDTO;
import br.unitins.topicos1.service.pagamento.PagamentoService;
import br.unitins.topicos1.service.reserva.ReservaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

    // @Inject
    // PagamentoService pagamentoService;

    // @Inject
    // ReservaService reservaService;

    // @Test
    // public void testFindAll() {
    // given()
    // .when().get("/pagamentos")
    // .then()
    // .statusCode(200);
    // }

    // @Test
    // public void testInsert() {
    // ReservaDTO reservaInsert = createReserva();

    // TipoPagamentoDTO pagamento = new TipoPagamentoDTO(1, "Pix");

    // PagamentoDTO dto = new PagamentoDTO(dto.dataPagamento(), reserva.id(),
    // pagamento.id(), 100.0);
    // given()
    // .contentType(ContentType.JSON)
    // .body(dto)
    // .when().post("/pagamentos")
    // .then()
    // .statusCode(201)
    // .body("id", notNullValue());
    // }

    // @Test
    // public void testUpdate() {
    // PagamentoResponseDTO pagamento = createPagamento();

    // PagamentoDTO dto = new PagamentoDTO(pagamento.getIdReserva(),
    // pagamento.getTipoPagamentoId(), 200.0);
    // given()
    // .contentType(ContentType.JSON)
    // .body(dto)
    // .when().put("/pagamentos/" + pagamento.getId())
    // .then()
    // .statusCode(204);
    // }

    // @Test
    // public void testDelete() {
    // PagamentoResponseDTO pagamento = createPagamento();

    // given()
    // .when().delete("/pagamentos/" + pagamento.getId())
    // .then()
    // .statusCode(204);
    // }
}

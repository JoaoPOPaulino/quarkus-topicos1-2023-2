package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import br.unitins.topicos1.dto.servico.ServicoResponseDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.jwt.JwtService;
import br.unitins.topicos1.service.servico.ServicoService;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ServicoResourceTest {
        @Inject
        UsuarioService usuarioService;

        @Inject
        JwtService jwtService;

        @Inject
        ServicoService servicoService;

        @Test
        public void testFindAll() {
                String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImpwIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjc3NjEwMSwiaWF0IjoxNzAyNjg5NzAxLCJqdGkiOiI5NDllYTIzMi1hZTBmLTRkZmItYWI3OC0yNDM1NDJjZTU1ZDMifQ.f6yh_GLr2wmfWORiahfeypOCBh_CgyswZ_LvDRs-uel3zb2ebZvqHOVutyGzNcZpEdRKSlkmE2g8s_uso6o0wkERNecaA2_xUCRiJ3SFrs1FFsi7FtX5ZeKDFW4MT083ezhyDWvJQmgH92-ofxr2tjNxh2e1vBIT6nsK9YUg6htsVi1MKzxeyDbO_GgeEEY736b4GOUAUwBzNbIyaqPLn-wjEp_1Cos5v75xLRG0HERHJgm43H1fv7bE_j-OBp0Pz5Rp-wXnCVodx23ZyF6Kh1kIN5-yV5Vs";
                given()
                                .auth()
                                .oauth2(token)
                                .when().get("/servico")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                LocalTime horaInicio = LocalTime.of(7, 30);
                LocalTime horaFim = LocalTime.of(10, 45);

                ServicoDTO dto = new ServicoDTO(
                                "Café da Manhã",
                                "Serviço de café da manhã",
                                horaInicio,
                                horaFim);

                given()
                                .auth()
                                .oauth2("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImpwIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjc3NjEwMSwiaWF0IjoxNzAyNjg5NzAxLCJqdGkiOiI5NDllYTIzMi1hZTBmLTRkZmItYWI3OC0yNDM1NDJjZTU1ZDMifQ.f6yh_GLr2wmfWORiahfeypOCBh_CgyswZ_LvDRs-uel3zb2ebZvqHOVutyGzNcZpEdRKSlkmE2g8s_uso6o0wkERNecaA2_xUCRiJ3SFrs1FFsi7FtX5ZeKDFW4MT083ezhyDWvJQmgH92-ofxr2tjNxh2e1vBIT6nsK9YUg6htsVi1MKzxeyDbO_GgeEEY736b4GOUAUwBzNbIyaqPLn-wjEp_1Cos5v75xLRG0HERHJgm43H1fv7bE_j-OBp0Pz5Rp-wXnCVodx23ZyF6Kh1kIN5-yV5Vs")
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/servico")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Café da Manhã"),
                                                "descricao", is("Serviço de café da manhã"));
        }

        @Test
        public void testUpdate() {
                LocalTime horaInicio = LocalTime.of(7, 30);
                LocalTime horaFim = LocalTime.of(10, 45);

                ServicoDTO dtoInsert = new ServicoDTO(
                                "Café da Manhã",
                                "Descrição Original",
                                horaInicio,
                                horaFim);

                ServicoResponseDTO servicoTest = servicoService.insert(dtoInsert);
                Long id = servicoTest.id();

                LocalTime horaInicioUpdate = LocalTime.of(8, 30);
                LocalTime horaFimUpdate = LocalTime.of(11, 00);

                ServicoDTO dtoUpdate = new ServicoDTO(
                                "Café da Manhã Atualizado",
                                "Descrição Atualizada",
                                horaInicioUpdate,
                                horaFimUpdate);

                given()
                                .auth()
                                .oauth2("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImpwIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjc3NjEwMSwiaWF0IjoxNzAyNjg5NzAxLCJqdGkiOiI5NDllYTIzMi1hZTBmLTRkZmItYWI3OC0yNDM1NDJjZTU1ZDMifQ.f6yh_GLr2wmfWORiahfeypOCBh_CgyswZ_LvDRs-uel3zb2ebZvqHOVutyGzNcZpEdRKSlkmE2g8s_uso6o0wkERNecaA2_xUCRiJ3SFrs1FFsi7FtX5ZeKDFW4MT083ezhyDWvJQmgH92-ofxr2tjNxh2e1vBIT6nsK9YUg6htsVi1MKzxeyDbO_GgeEEY736b4GOUAUwBzNbIyaqPLn-wjEp_1Cos5v75xLRG0HERHJgm43H1fv7bE_j-OBp0Pz5Rp-wXnCVodx23ZyF6Kh1kIN5-yV5Vs")
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/servico/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        void testDelete() {
                LocalTime horaInicio = LocalTime.of(8, 30);
                LocalTime horaFim = LocalTime.of(11, 00);

                ServicoDTO dto = new ServicoDTO(
                                "Café da Manhã",
                                "Serviço de café da manhã",
                                horaInicio,
                                horaFim);

                ServicoResponseDTO servicoTest = servicoService.insert(dto);
                Long id = servicoTest.id();

                given()
                                .auth()
                                .oauth2("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImpwIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjc3NjEwMSwiaWF0IjoxNzAyNjg5NzAxLCJqdGkiOiI5NDllYTIzMi1hZTBmLTRkZmItYWI3OC0yNDM1NDJjZTU1ZDMifQ.f6yh_GLr2wmfWORiahfeypOCBh_CgyswZ_LvDRs-uel3zb2ebZvqHOVutyGzNcZpEdRKSlkmE2g8s_uso6o0wkERNecaA2_xUCRiJ3SFrs1FFsi7FtX5ZeKDFW4MT083ezhyDWvJQmgH92-ofxr2tjNxh2e1vBIT6nsK9YUg6htsVi1MKzxeyDbO_GgeEEY736b4GOUAUwBzNbIyaqPLn-wjEp_1Cos5v75xLRG0HERHJgm43H1fv7bE_j-OBp0Pz5Rp-wXnCVodx23ZyF6Kh1kIN5-yV5Vs")
                                .when().delete("/servico/" + id)
                                .then()
                                .statusCode(204);
        }

}

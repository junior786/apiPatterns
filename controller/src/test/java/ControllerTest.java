import com.fasterxml.jackson.databind.ObjectMapper;
import com.junior.modules.*;
import com.junior.modules.dto.PessoaPostDto;
import com.junior.modules.exception.FacadeValid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PessoaController.class, PessoaService.class, Facade.class, FacadeValid.class})
@AutoConfigureMockMvc
@EnableWebMvc
@ExtendWith(SpringExtension.class)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PessoaRepository pessoaRepository;

    @MockBean
    EnderecoRepository enderecoRepository;

    @MockBean
    GetCep getCep;

    @Test
    public void getAll_test() throws Exception {
        mockMvc.perform(get("/v1/pessoas"))
                .andExpect(status().isOk());
    }

    @Test
    public void post_test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PessoaPostDto pessoaPostDto = PessoaPostDto.builder()
                .nome("teste")
                .sexo("teste")
                .numero(100)
                .cep("100")
                .build();
        Pessoa pessoa = Pessoa.builder()
                .nome("teste")
                .sexo("teste")
                .build();
        Endereco endereco = Endereco.builder()
                .cep("100")
                .complemento("teste")
                .bairro("teste")
                .localidade("teste")
                .build();

        Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        Mockito.when(getCep.getEndereco("100")).thenReturn(endereco);
        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        mockMvc.perform(post("/v1/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaPostDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_byId() throws Exception {
        Endereco endereco = Endereco.builder()
                .cep("1000")
                .id(1L)
                .bairro("teste")
                .localidade("")
                .numero(10)
                .build();

        Optional<Pessoa> pessoa = Optional.of(new Pessoa(3L, "teste", "teste", endereco));
        Mockito.when(pessoaRepository.findById(3L)).thenReturn(pessoa);
        mockMvc.perform(get("/v1/pessoa/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void put_byId() throws Exception {
        PessoaPostDto pessoa = PessoaPostDto.builder()
                .nome("Teste test")
                .sexo("Teste")
                .build();
        Pessoa pessoa1 = Pessoa.builder()
                .nome("Teste")
                .sexo("Teste")
                .id(1L)
                .endereco(new Endereco(1L, "100", "teste",
                        "teste", "teste", "teste", 10, null))
                .build();

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa1));
        Mockito.when(pessoaRepository.save(pessoa1)).thenReturn(pessoa1);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/v1/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_byId() throws Exception {
        mockMvc.perform(delete("/v1/pessoa/3"))
                .andExpect(status().isOk());

    }

    @Test
    public void put_byId_failed() throws Exception {
        PessoaPostDto pessoa = PessoaPostDto.builder()
                .nome("Teste test")
                .sexo("Teste")
                .cep("100")
                .build();

        Pessoa pessoa1 = Pessoa.builder()
                .nome("Teste")
                .sexo("Teste")
                .id(1L)
                .endereco(new Endereco(1L, "100", "teste",
                        "teste", "teste", "teste", 10, null))
                .build();

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa1));
        Mockito.when(pessoaRepository.save(pessoa1)).thenReturn(pessoa1);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/v1/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void get_by_id_failed() throws Exception {
        Mockito.when(pessoaRepository.findById(3L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/v1/pessoa/3"))
                .andExpect(status().isNotFound());
    }
}

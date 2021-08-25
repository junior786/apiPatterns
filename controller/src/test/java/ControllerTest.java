import com.fasterxml.jackson.databind.ObjectMapper;
import com.junior.modules.*;
import com.junior.modules.dto.PessoaPostDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PessoaController.class)
@AutoConfigureMockMvc
@EnableWebMvc
@ExtendWith(SpringExtension.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private PessoaService pessoaService;


    ObjectMapper objectMapper;

    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
    }

    @Test
    public void getAll_test() throws Exception {
        mockMvc.perform(get("/v1/pessoas"))
                .andExpect(status().isOk());

    }

    @Test
    public void post_test() throws Exception {
        PessoaPostDto pessoa = new PessoaPostDto();

        mockMvc.perform(post("/v1/pessoa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());

    }

}

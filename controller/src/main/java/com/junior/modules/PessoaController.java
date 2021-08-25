package com.junior.modules;

import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PostDtoPessoa;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor
public class PessoaController {

    final PessoaService pessoaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pessoa")
    public PessoaDto savePessoa(@Valid @RequestBody PostDtoPessoa pessoa) {
        return pessoaService.savePessoa(pessoa);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pessoas")
    public List<PessoaDto> findAll() {
        return pessoaService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pessoa/{id}")
    public PessoaDto getById(@PathVariable Long id) {
        return pessoaService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/pessoa/{id}")
    public void deletrById(@PathVariable Long id){
         pessoaService.deleteById(id);
    }
}

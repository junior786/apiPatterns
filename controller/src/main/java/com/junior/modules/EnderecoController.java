package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class EnderecoController {

    EnderecoService enderecoService;

    @GetMapping("/endereco")
    public EnderecoDto getEndereco(@RequestParam("pessoaId") Long id) {
        return enderecoService.getEndereco(id);
    }
    @PutMapping("/endereco")
    public EnderecoDto putEndereco(@RequestParam("pessoaId") Long id, @Valid @RequestBody EnderecoDto enderecoDto) {
        return enderecoService.putEndereco(id, enderecoDto);
    }
}

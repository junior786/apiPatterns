package com.junior.modules;

import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;

import java.util.List;

public interface FacadePessoas {
    PessoaDto cadasterPessoa(PessoaPostDto pessoa);

    List<PessoaDto> listAll();

    PessoaDto getById(Long id);

    void deleteById(Long id);

    PessoaDto putPessoa(PessoaPostDto pessoaDto, Long id);

}

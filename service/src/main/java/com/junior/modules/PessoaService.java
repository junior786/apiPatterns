package com.junior.modules;

import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService {

    FacadePessoas impFacade;

    public PessoaDto savePessoa(PessoaPostDto pessoa) {
        return impFacade.cadasterPessoa(pessoa);
    }

    public List<PessoaDto> getAll() {
        return impFacade.listAll();
    }

    public PessoaDto getById(Long id) {
        return impFacade.getById(id);
    }

    public void deleteById(Long id) {
        impFacade.deleteById(id);
    }

    public PessoaDto putById(PessoaPostDto pessoaPostDto, Long id) {
        return impFacade.putPessoa(pessoaPostDto, id);
    }

}

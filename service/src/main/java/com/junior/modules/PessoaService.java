package com.junior.modules;

import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PostDtoPessoa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService {

    Facade facade;

    public PessoaDto savePessoa(PostDtoPessoa pessoa) {
        return facade.cadasterPessoa(pessoa);
    }

    public List<PessoaDto> getAll() {
        return facade.listAll();
    }

    public PessoaDto getById(Long id){
        return facade.getById(id);
    }

    public void deleteById(Long id){
         facade.deleteById(id);
    }
}

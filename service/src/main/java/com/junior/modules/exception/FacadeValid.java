package com.junior.modules.exception;

import com.junior.modules.Pessoa;
import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;

import java.util.Optional;

public class FacadeValid {

    public static PessoaDto isPresent(Optional<Pessoa> pessoa) {
        if (pessoa.isPresent()) {
            Pessoa pessoaExist = pessoa.get();
            return PessoaDto.getInstance(pessoaExist, EnderecoDto.getInstance(pessoaExist.getEndereco()));
        } else {
            throw new ExceptionPessoaNotFound("ID não encontrado");
        }
    }
}

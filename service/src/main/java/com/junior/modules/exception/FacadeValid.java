package com.junior.modules.exception;

import com.junior.modules.Pessoa;
import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacadeValid {

    public PessoaDto isPresent(Optional<Pessoa> pessoa) {
        if (pessoa.isPresent()) {
            Pessoa pessoaExist = pessoa.get();
            return PessoaDto.getInstance(pessoaExist, EnderecoDto.getInstance(pessoaExist.getEndereco()));
        } else {
            throw new ExceptionPessoaNotFound("ID não encontrado");
        }
    }

    public Pessoa putPessoaValid(Pessoa pessoa, PessoaPostDto pessoaPostDto) {
        if (pessoaPostDto.getCep()!= null)
            throw new HttpMessageNotReadableException("Campo CEP não pode ser atribuido no corpo");
        if (pessoaPostDto.getNome() != null) {
            pessoa.setNome(pessoaPostDto.getNome());
        }
        if (pessoaPostDto.getSexo() != null) {
            pessoa.setSexo(pessoaPostDto.getSexo());
        }

        return pessoa;
    }

}

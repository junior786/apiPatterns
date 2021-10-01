package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;
import com.junior.modules.exception.ExceptionPessoaNotFound;
import com.junior.modules.exception.FacadeValid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.BiConsumer;

@AllArgsConstructor
@Component
public class ImpFacadeEndereco implements FacadeEndereco {
    private EnderecoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;

    private FacadeValid facadeValid;

    public EnderecoDto getEndereco(Long id) {
        Optional<Pessoa> byId = pessoaRepository.findById(id);
        PessoaDto present = facadeValid.isPresent(byId);
        return present.getEndereco();
    }

    public EnderecoDto putEndereco(EnderecoDto enderecoDto, Long id) {
        Optional<Pessoa> byId = pessoaRepository.findById(id);
        facadeValid.isPresent(byId);
        Pessoa pessoa = byId.orElseThrow();
        Endereco endereco = enderecoRepository.findById(pessoa.getEndereco().getId())
                .orElseThrow(() -> new ExceptionPessoaNotFound("Pessoa nao encontrada"));
        BiConsumer<Endereco, EnderecoDto> consumer = (e, d) -> {
            e.setNumero(d.getNumero());
            e.setBairro(d.getBairro());
            e.setCep(d.getCep());
            e.setComplemento(d.getComplemento());
            e.setLocalidade(d.getLocalidade());
            e.setUf(d.getUf());
        };
        consumer.accept(endereco, enderecoDto);
        enderecoRepository.save(endereco);
        return enderecoDto;
    }
}

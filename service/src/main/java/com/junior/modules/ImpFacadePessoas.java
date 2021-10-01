package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;
import com.junior.modules.exception.ExceptionPessoaNotFound;
import com.junior.modules.exception.FacadeValid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImpFacadePessoas implements FacadePessoas {

    private EnderecoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;

    private GetCep getCep;

    private FacadeValid facadeValid;

    public PessoaDto cadasterPessoa(PessoaPostDto pessoa) {
        Pessoa p1 = Pessoa.builder()
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo()).build();

        Endereco endereco = getCep.getEndereco(pessoa.getCep());
        Pessoa pessoaSalva = pessoaRepository.save(p1);

        endereco.setNumero(pessoa.getNumero());
        endereco.setPessoa(pessoaSalva);
        pessoaSalva.setEndereco(endereco);

        pessoaSalva.setSexo(pessoaSalva.getSexo().toLowerCase(Locale.ROOT));
        enderecoRepository.save(endereco);
        pessoaRepository.save(pessoaSalva);

        return PessoaDto.getInstance(pessoaSalva, EnderecoDto.getInstance(endereco));

    }

    public List<PessoaDto> listAll() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> PessoaDto.getInstance(pessoa, EnderecoDto.getInstance(pessoa.getEndereco())))
                .sorted(Comparator.comparing(PessoaDto::getNome))
                .collect(Collectors.toList());
    }

    public PessoaDto getById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return facadeValid.isPresent(pessoa);
    }

    public void deleteById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        pessoa.ifPresent(p -> pessoaRepository.delete(p));
    }

    public PessoaDto putPessoa(PessoaPostDto pessoaDto, Long id) {
        Optional<Pessoa> findId = pessoaRepository.findById(id);
        facadeValid.isPresent(findId);
        Pessoa pessoa = findId.orElseThrow(() -> new ExceptionPessoaNotFound("Pessoa não encontrada"));
        pessoa = facadeValid.putPessoaValid(pessoa, pessoaDto);
        pessoaRepository.save(pessoa);
        return PessoaDto.getInstance(pessoa, EnderecoDto.getInstance(pessoa.getEndereco()));
    }
}

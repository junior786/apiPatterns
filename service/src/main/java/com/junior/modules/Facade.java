package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;
import com.junior.modules.exception.FacadeValid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Facade {

    private EnderecoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;

    private GetCep getCep;

    private FacadeValid facadeValid;

    public PessoaDto cadasterPessoa(PessoaPostDto pessoa) {
        Pessoa p1 = Pessoa.builder()
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo()).build();

        Pessoa pessoaSalva = pessoaRepository.save(p1);
        Endereco endereco = getCep.getEndereco(pessoa.getCep());
        endereco.setNumero(pessoa.getNumero());
        endereco.setPessoa(pessoaSalva);
        pessoaSalva.setEndereco(endereco);

        enderecoRepository.save(endereco);
        pessoaRepository.save(pessoaSalva);

        return PessoaDto.getInstance(pessoaSalva, EnderecoDto.getInstance(endereco));

    }

    public List<PessoaDto> listAll() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> PessoaDto.getInstance(pessoa, EnderecoDto.getInstance(pessoa.getEndereco())))
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
        Pessoa pessoa = findId.get();
        pessoa = facadeValid.putPessoaValid(pessoa, pessoaDto);
        pessoaRepository.save(pessoa);
        return PessoaDto.getInstance(pessoa, EnderecoDto.getInstance(pessoa.getEndereco()));
    }

    public EnderecoDto getEndereco(Long id) {
        Optional<Pessoa> byId = pessoaRepository.findById(id);
        PessoaDto present = facadeValid.isPresent(byId);
        return present.getEndereco();
    }

    public EnderecoDto putEndereco(EnderecoDto enderecoDto, Long id) {
        Optional<Pessoa> byId = pessoaRepository.findById(id);
        facadeValid.isPresent(byId);
        Pessoa pessoa = byId.get();
        Endereco endereco = enderecoRepository.findById(pessoa.getEndereco().getId()).get();
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

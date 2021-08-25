package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import com.junior.modules.dto.PessoaDto;
import com.junior.modules.dto.PessoaPostDto;
import com.junior.modules.exception.FacadeValid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Facade {

    private EnderecoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;

    private GetCep getCep;

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
        return FacadeValid.isPresent(pessoa);
    }

    public void deleteById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        pessoa.ifPresent(p -> pessoaRepository.delete(p));
    }
}

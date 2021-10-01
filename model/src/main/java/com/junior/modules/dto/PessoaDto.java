package com.junior.modules.dto;

import com.junior.modules.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    private Long id;
    private String nome;
    private String sexo;
    private EnderecoDto endereco;

//FACTORY METHOD
    public static PessoaDto getInstance(Pessoa pessoa, EnderecoDto endereco){
      return new PessoaDto(pessoa.getId(),pessoa.getNome(),pessoa.getSexo(),endereco);
    }
}

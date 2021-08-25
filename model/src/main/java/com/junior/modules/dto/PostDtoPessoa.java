package com.junior.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoPessoa {
    private String nome;
    private String sexo;
    private String cep;
    private int numero;
    private EnderecoDto endereco;
}

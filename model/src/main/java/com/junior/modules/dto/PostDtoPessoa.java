package com.junior.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoPessoa {
    @NotBlank
    private String nome;
    @NotBlank
    private String sexo;
    @NotBlank
    private String cep;
    @DecimalMin("1")
    @NotNull
    private int numero;
    private EnderecoDto endereco;
}

package com.junior.modules.dto;

import com.junior.modules.validation.SexoValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PessoaPostDto {
    @NotBlank
    private String nome;
    @NotBlank
    @SexoValid
    private String sexo;
    @NotBlank
    private String cep;
    @DecimalMin("1")
    @NotNull
    private int numero;
}

package com.junior.modules.dto;

import com.junior.modules.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {
    @NotBlank
    private String cep;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String localidade;
    @NotBlank
    private String uf;
    @DecimalMin("0")
    private Integer numero;

    public static EnderecoDto getInstance(Endereco endereco) {
        return new EnderecoDto(endereco.getCep(), endereco.getComplemento(), endereco.getBairro(), endereco.getLocalidade(),
                endereco.getUf(), endereco.getNumero());
    }
}

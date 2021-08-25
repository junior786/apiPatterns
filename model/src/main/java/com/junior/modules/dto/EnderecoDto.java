package com.junior.modules.dto;

import com.junior.modules.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {
    private String cep;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private int numero;

    public static EnderecoDto getInstance(Endereco endereco) {
        return new EnderecoDto(endereco.getCep(), endereco.getComplemento(), endereco.getBairro(), endereco.getLocalidade(),
                endereco.getUf(), endereco.getNumero());
    }
}

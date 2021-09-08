package com.junior.modules;

import com.junior.modules.exception.ExceptionCepNotFound;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetCep {
    private static final String URL = "https://viacep.com.br/ws/";

    public Endereco getEndereco(String cep) {
        RestTemplate rs = RestTemplatGet.getRestTemplate();
        try {
            Endereco endereco = rs.getForObject(URL + cep + "/json/", Endereco.class);
            if (endereco.getCep() == null) {
                throw new ExceptionCepNotFound("Erro ao encontrar o cep");
            }
            return endereco;
        } catch (Exception e) {
            throw new ExceptionCepNotFound("Cep errado");
        }
    }
}

package com.junior.modules;

import com.junior.modules.exception.ExceptionCepNotFound;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCep {
    private static final String URL = "https://viacep.com.br/ws/";

    public Endereco getEndereco(String cep) {
        RestTemplate rs = new RestTemplate();
        System.out.println(URL + cep + "/json/");
        try{
            Endereco endereco = rs.getForObject(URL + cep + "/json/", Endereco.class);
            if (endereco.getCep() == null){
                throw new ExceptionCepNotFound("Erro ao encontrar o cep");
            }
            return endereco;
        }catch (Exception e){
            throw new ExceptionCepNotFound("Cep errado");
        }
    }
}

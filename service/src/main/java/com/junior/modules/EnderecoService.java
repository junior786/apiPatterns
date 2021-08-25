package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    Facade facade;

    public EnderecoDto getEndereco(Long id) {
        return facade.getEndereco(id);
    }
    public EnderecoDto putEndereco(Long id, EnderecoDto enderecoDto){
        return facade.putEndereco(enderecoDto,id);
    }
}

package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    FacadeEndereco impFacade;

    public EnderecoDto getEndereco(Long id) {
        return impFacade.getEndereco(id);
    }

    public EnderecoDto putEndereco(Long id, EnderecoDto enderecoDto) {
        return impFacade.putEndereco(enderecoDto, id);
    }
}

package com.junior.modules;

import com.junior.modules.dto.EnderecoDto;

public interface FacadeEndereco {
    EnderecoDto getEndereco(Long id);

    EnderecoDto putEndereco(EnderecoDto enderecoDto, Long id);
}

package com.junior.modules.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ErrorDetailsValid extends ErrorDetails {
    private Map errors;

}

package com.descomplicommerce.carrinho.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AdicionarItemRequest {
    @NotNull public Long produtoId;
    @Min(1) public int quantidade = 1;
}

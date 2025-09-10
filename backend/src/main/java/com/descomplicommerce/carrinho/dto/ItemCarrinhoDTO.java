package com.descomplicommerce.carrinho.dto;

import java.math.BigDecimal;

public class ItemCarrinhoDTO {
    public Long produtoId;
    public String nome;
    public BigDecimal precoUnitario;
    public int quantidade;
    public BigDecimal totalLinha;
}

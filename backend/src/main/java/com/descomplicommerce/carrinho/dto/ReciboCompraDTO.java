package com.descomplicommerce.carrinho.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReciboCompraDTO {
    public List<ItemCarrinhoDTO> itens;
    public BigDecimal total;
    public String mensagem;
}

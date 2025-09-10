package com.descomplicommerce.produto.entidade;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal preco;

    private String imagem;

    public Produto() {}

    public Produto(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id; }

    public String getNome() {
        return nome; }

    public void setNome(String nome) {
        this.nome = nome; }

    public BigDecimal getPreco() {
        return preco; }

    public void setPreco(BigDecimal preco) {
        this.preco = preco; }

    public String getImagem() {
        return imagem; }

    public void setImagem(String imagem) {
        this.imagem = imagem; }
}

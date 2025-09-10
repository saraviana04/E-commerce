package com.descomplicommerce.carrinho.entidade;

import com.descomplicommerce.produto.entidade.Produto;
import jakarta.persistence.*;

@Entity
@Table(name = "item_carrinho",
        uniqueConstraints = @UniqueConstraint(name="uk_item_carrinho_cart_prod", columnNames = {"carrinho_id","produto_id"}))
public class ItemCarrinho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @ManyToOne(optional = false) @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    public ItemCarrinho() {

    }
    public ItemCarrinho(Produto produto, int quantidade) { this.produto = produto; this.quantidade = quantidade; }

    public Long getId() {
        return id; }

    public Carrinho getCarrinho() {
        return carrinho; }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho; }

    public Produto getProduto() {
        return produto; }

    public void setProduto(Produto produto) {
        this.produto = produto; }

    public int getQuantidade() {
        return quantidade; }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade; }
}

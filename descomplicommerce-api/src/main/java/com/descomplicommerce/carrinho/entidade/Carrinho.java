package com.descomplicommerce.carrinho.entidade;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho", uniqueConstraints = @UniqueConstraint(name = "uk_carrinho_chave", columnNames = "chave"))
public class Carrinho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chave;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho() {}
    public Carrinho(String chave) { this.chave = chave; }

    public Long getId() { return id; }
    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }
    public List<ItemCarrinho> getItens() { return itens; }

    public void adicionar(ItemCarrinho item) { itens.add(item); item.setCarrinho(this); }
    public void remover(ItemCarrinho item) { itens.remove(item); item.setCarrinho(null); }
}

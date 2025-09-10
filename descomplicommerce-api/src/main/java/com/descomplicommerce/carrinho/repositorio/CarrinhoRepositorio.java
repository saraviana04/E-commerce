package com.descomplicommerce.carrinho.repositorio;

import com.descomplicommerce.carrinho.entidade.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepositorio extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByChave(String chave);
}

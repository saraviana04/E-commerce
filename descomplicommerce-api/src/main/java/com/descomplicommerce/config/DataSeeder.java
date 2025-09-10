package com.descomplicommerce.config;

import com.descomplicommerce.produto.entidade.Produto;
import com.descomplicommerce.produto.repositorio.ProdutoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(ProdutoRepositorio repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.saveAll(List.of(
                        new Produto("Camiseta", new BigDecimal("79.90")),
                        new Produto("Fone de ouvido", new BigDecimal("199.00")),
                        new Produto("Teclado", new BigDecimal("299.00")),
                        new Produto("Mouse Gamer", new BigDecimal("149.90")),
                        new Produto("Monitor 24", new BigDecimal("899.00")),
                        new Produto("Notebook", new BigDecimal("3999.00"))

                ));
            }
        };
    }
}

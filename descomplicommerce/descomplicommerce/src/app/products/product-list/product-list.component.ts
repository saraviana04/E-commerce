import { Component } from '@angular/core';
import { ProdutoService } from '../../services/produto.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  products: Product[] = [];

  constructor(
    private produtoSvc: ProdutoService,
    private carrinho: CarrinhoService
  ) {
    this.produtoSvc.listar().subscribe(ps => this.products = ps);
  }

  add(p: Product) {
    this.carrinho.adicionar(p);
  }
}

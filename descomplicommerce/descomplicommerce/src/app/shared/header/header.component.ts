import { Component } from '@angular/core';
import { CarrinhoService } from '../../services/carrinho.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  qtd$ = this.carrinho.itens$.pipe(map(itens => itens.reduce((s,i)=>s+i.quantity,0)));
  total$ = this.carrinho.itens$.pipe(map(itens => itens.reduce((s,i)=>s+i.product.preco*i.quantity,0)));
  constructor(private carrinho: CarrinhoService) {}
}

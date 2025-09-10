import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { CarrinhoService } from '../../services/carrinho.service';
import { CartItem } from '../../models/cart-item';

interface ReciboItem {
  nome: string;
  quantidade: number;
  precoUnitario: number;
}
interface Recibo {
  mensagem: string;
  total: number;
  itens: ReciboItem[];
}

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent implements OnDestroy {
  items: CartItem[] = [];
  total = 0;
  private sub?: Subscription;

  // Toast/recibo
  toastMsg = '';
  recibo?: Recibo;

  constructor(private carrinho: CarrinhoService) {
    this.sub = this.carrinho.itens$.subscribe((items: CartItem[]) => {
      this.items = items;
      this.total = items.reduce((s, ci) => s + ci.product.preco * ci.quantity, 0);
    });
  }

  ngOnDestroy() { this.sub?.unsubscribe(); }

  remove(id: number) { this.carrinho.remover(id); }
  changeQty(id: number, q: string | number) { this.carrinho.atualizarQuantidade(id, Number(q)); }
  clear() { this.carrinho.limpar(); }

  finalizar() {
    this.carrinho.finalizar().subscribe({
      next: (recibo: Recibo) => {
        this.recibo = recibo;
        this.toastMsg = recibo?.mensagem ?? 'Compra finalizada com sucesso!';

        const el = document.getElementById('toastCompra');
        if (el) {
          const bsToast = new (window as any).bootstrap.Toast(el, {
            autohide: true,
            delay: 4000
          });
          bsToast.show();
        }
      },
      error: () => {
        this.recibo = undefined;
        this.toastMsg = '❌ Falha ao finalizar compra.';

        const el = document.getElementById('toastCompra');
        if (el) {
          const bsToast = new (window as any).bootstrap.Toast(el, {
            autohide: true,
            delay: 4000
          });
          bsToast.show();
        }
      }
    });
  }
}

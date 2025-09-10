import { Injectable } from '@angular/core';
import { BehaviorSubject, tap, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Product } from '../models/product';
import { CartItem } from '../models/cart-item';

export interface ReciboCompra {
  itens: {
    produtoId: number;
    nome: string;
    precoUnitario: number;
    quantidade: number;
    totalLinha: number;
  }[];
  total: number;
  mensagem: string;
}

@Injectable({ providedIn: 'root' })
export class CarrinhoService {
  private baseUrl = `${environment.apiBase}/carrinho`;

  private itensSubject = new BehaviorSubject<CartItem[]>([]);
  itens$ = this.itensSubject.asObservable();

  constructor(private http: HttpClient) {
    this.carregar().subscribe();
  }

  private carregar() {
    return this.http.get<any[]>(this.baseUrl).pipe(
      map(lista =>
        lista.map(dto => ({
          product: {
            id: dto.produtoId,
            nome: dto.nome,
            preco: dto.precoUnitario,
            imagem: null
          } as Product,
          quantity: dto.quantidade
        }))
      ),
      tap(itens => this.itensSubject.next(itens))
    );
  }

  adicionar(produto: Product) {
    this.http.post<void>(`${this.baseUrl}/adicionar`, { produtoId: produto.id, quantidade: 1 })
      .pipe(tap(() => this.carregar().subscribe()))
      .subscribe();
  }

  remover(produtoId: number) {
    this.http.delete<void>(`${this.baseUrl}/${produtoId}`)
      .pipe(tap(() => this.carregar().subscribe()))
      .subscribe();
  }

  atualizarQuantidade(produtoId: number, quantidade: number) {
    this.http.patch<void>(`${this.baseUrl}/${produtoId}`, null, { params: { quantidade } })
      .pipe(tap(() => this.carregar().subscribe()))
      .subscribe();
  }

  limpar() {
    this.http.delete<void>(this.baseUrl)
      .pipe(tap(() => this.carregar().subscribe()))
      .subscribe();
  }

  finalizar() {
    return this.http
      .post<ReciboCompra>(`${this.baseUrl}/finalizar`, {})
      .pipe(tap(() => this.carregar().subscribe()));
  }

  total(): number {
    return this.itensSubject.value.reduce(
      (soma, ci) => soma + ci.product.preco * ci.quantity,
      0
    );
  }
}

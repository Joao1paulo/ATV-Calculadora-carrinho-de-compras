package com.example.atv1_carrinho_compras.data

import com.example.atv1_carrinho_compras.domain.Produto
import com.example.atv1_carrinho_compras.domain.ItemCarrinho

val catalogoProdutos = listOf(
    Produto("Notebook Dell Inspiron 15 3000 Series Ultrafino", 3499.00, "Um notebook rápido para estudos e trabalho diário.", 5.0),
    Produto("Mouse sem fio", 89.90, null, 0.0), // Produto sem descrição[cite: 6].
    Produto("Teclado mecânico RGB", 349.90, "Switch azul, ABNT2 com iluminação customizável", 0.0),
    Produto("Monitor LG 29 Ultrawide", 1200.00, "Monitor ideal para produtividade e multitarefas", 10.0), // Segundo produto com desconto[cite: 6].
    Produto("Headset Gamer", 450.00, "Som surround 7.1", 0.0),
    Produto("Mousepad Gigante 90x40", 50.00, "Superfície speed", 0.0)
)

val carrinhoDeValidacao = listOf(
    ItemCarrinho(catalogoProdutos[0], 2),
    ItemCarrinho(catalogoProdutos[1], 1),
    ItemCarrinho(catalogoProdutos[2], 1)
)
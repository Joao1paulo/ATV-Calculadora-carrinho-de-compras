package com.example.atv1_carrinho_compras.domain

interface Pagavel {
    fun calcularTotal(): Double
}

data class Produto(
    val nome: String,
    val preco: Double,
    val descricao: String? = null,
    val descontoPercentual: Double = 0.0
)

data class ItemCarrinho(
    val produto: Produto,
    val quantidade: Int
) : Pagavel {

    // Lógica do cálculo na camada de domínio
    override fun calcularTotal(): Double {
        val precoComDesconto = produto.preco * (1 - (produto.descontoPercentual / 100))
        return precoComDesconto * quantidade
    }

    fun calcularDescontoTotal(): Double {
        val valorDesconto = produto.preco * (produto.descontoPercentual / 100)
        return valorDesconto * quantidade
    }

    fun calcularSubtotalBruto(): Double {
        return produto.preco * quantidade
    }
}
package com.example.atv1_carrinho_compras.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.atv1_carrinho_compras.domain.ItemCarrinho
import com.example.atv1_carrinho_compras.ui.components.CartItemRow
import com.example.atv1_carrinho_compras.ui.components.formatarMoeda

@Composable
fun CartScreen(itens: List<ItemCarrinho>, modifier: Modifier = Modifier) {

    LaunchedEffect(itens) {
        val itensComDesconto = itens
            .filter { it.produto.descontoPercentual > 0 }
            .sortedByDescending { it.calcularTotal() }

        Log.d("CarrinhoApp", "--- Relatório Logcat: Produtos com Desconto ---")
        itensComDesconto.forEach {
            Log.d("CarrinhoApp", "Produto: ${it.produto.nome} | Valor Final: ${formatarMoeda(it.calcularTotal())}")
        }
    }

    val subtotalBruto = itens.sumOf { it.calcularSubtotalBruto() }
    val descontos = itens.sumOf { it.calcularDescontoTotal() }
    val totalFinal = itens.sumOf { it.calcularTotal() }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "🛒 Meu Carrinho",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(itens) { item ->
                CartItemRow(item)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Subtotal", style = MaterialTheme.typography.bodyLarge)
            Text(formatarMoeda(subtotalBruto), style = MaterialTheme.typography.bodyLarge)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Descontos", style = MaterialTheme.typography.bodyLarge)
            Text("-${formatarMoeda(descontos)}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("TOTAL", style = MaterialTheme.typography.titleLarge)
            Text(formatarMoeda(totalFinal), style = MaterialTheme.typography.titleLarge)
        }
    }
}
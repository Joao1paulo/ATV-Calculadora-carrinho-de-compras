package com.example.atv1_carrinho_compras.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Cabeçalho
        Text(
            text = "🛒 Meu Carrinho",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)
        )

        // Lista de Produtos
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(itens) { item ->
                CartItemRow(item)
            }
        }

        // Rodapé de Resumo com estilo "BottomSheet"
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal", style = MaterialTheme.typography.bodyLarge)
                    Text(formatarMoeda(subtotalBruto), style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Descontos", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
                    Text(
                        text = "-${formatarMoeda(descontos)}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TOTAL", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                    Text(
                        text = formatarMoeda(totalFinal),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
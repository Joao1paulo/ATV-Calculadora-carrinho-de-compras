package com.example.atv1_carrinho_compras.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.atv1_carrinho_compras.domain.ItemCarrinho
import java.text.NumberFormat
import java.util.Locale

fun formatarMoeda(valor: Double): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(valor)
}

@Composable
fun CartItemRow(item: ItemCarrinho) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = item.produto.nome,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.produto.descricao ?: "Sem descrição",
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${formatarMoeda(item.produto.preco)}   x${item.quantidade}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = formatarMoeda(item.calcularTotal()),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
package com.seuapp.vipme2.presentation.finance

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.smarttoolfactory.compose.charts.bar.BarChart
import com.github.smarttoolfactory.compose.charts.bar.BarChartData
import com.github.smarttoolfactory.compose.charts.bar.BarChartStyle
import com.github.smarttoolfactory.compose.charts.bar.BarData
import com.seuapp.vipme2.data.local.model.CashEntry
import java.text.SimpleDateFormat
import java.util.*

// Dados de exemplo para o gráfico
private fun sampleEntries(): List<CashEntry> {
    val today = Date()
    val calendar = Calendar.getInstance()
    return listOf(
        CashEntry(id = 1, amount = 250.0, entryDate = today, description = "Venda 1", type = "income"),
        CashEntry(id = 2, amount = 80.0, entryDate = today, description = "Fornecedor", type = "expense"),
        CashEntry(id = 3, amount = 400.0, entryDate = today, description = "Venda 2", type = "income"),
        CashEntry(id = 4, amount = 120.0, entryDate = today, description = "Aluguel", type = "expense"),
    ).apply {
        // Adiciona dados para dias anteriores
        calendar.time = today
        calendar.add(Calendar.DATE, -1)
        add(CashEntry(id = 5, amount = 180.0, entryDate = calendar.time, description = "Venda 3", type = "income"))
        calendar.add(Calendar.DATE, -1)
        add(CashEntry(id = 6, amount = 200.0, entryDate = calendar.time, description = "Venda 4", type = "income"))
        add(CashEntry(id = 7, amount = 50.0, entryDate = calendar.time, description = "Material", type = "expense"))
    }
}

@Composable
fun FinanceChartScreen() {
    val entries = sampleEntries()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Fluxo de Caixa", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        val groupedByDate = entries.groupBy { SimpleDateFormat("dd/MM", Locale.getDefault()).format(it.entryDate) }

        val bars = groupedByDate.map { (date, entriesOnDate) ->
            val income = entriesOnDate.filter { it.type == "income" }.sumOf { it.amount }.toFloat()
            val expense = entriesOnDate.filter { it.type == "expense" }.sumOf { it.amount }.toFloat()

            listOf(
                BarData(label = "Entrada", value = income, color = Color(0xFF4CAF50)),
                BarData(label = "Saída", value = expense, color = Color(0xFFF44336))
            )
        }.flatten()

        val chartData = BarChartData(bars = bars)

        BarChart(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            barChartData = chartData,
            style = BarChartStyle(barWidth = 32.dp)
        )
    }
}

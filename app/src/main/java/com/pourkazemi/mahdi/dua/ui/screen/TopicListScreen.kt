package com.pourkazemi.mahdi.dua.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.component.TopicItem


@Composable
fun TopicListScreen(
    modifier: Modifier = Modifier,
    prayers: List<Prayers>,
    onItemClick: (Int) -> Unit,
) {
    val listState= rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()) {
        items(prayers) { prayer ->
            TopicItem(
                modifier = modifier,
                prayers = prayer,
                onClick = { onItemClick(prayer.id) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopicListScreenPreview() {
    val context = LocalContext.current // دسترسی به کانتکست

    val samplePrayers = listOf(
        Prayers(1,"دعای اول", "توضیحات دعای اول"),
        Prayers(2,"دعای دوم", "توضیحات دعای دوم")
    )
    TopicListScreen(prayers = samplePrayers) {
        Toast.makeText(context, "Hello, Toast! $it", Toast.LENGTH_SHORT).show()
    }
}
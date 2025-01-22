package com.pourkazemi.mahdi.dua.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.component.TopicItem


@Composable
fun TopicListScreen(
    prayers: List<Prayers>,
    fontSize:Int=18,
    modifier: Modifier = Modifier,
    onItemClick: (Prayers) -> Unit,
) {
    val listState= rememberLazyListState() //dose not needed

    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,//dose not needed
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(prayers) { prayer ->
                TopicItem(
                    modifier = modifier,
                    prayers = prayer,
                    fontSize = fontSize,
                    onClick = { onItemClick(prayer) }
                )
            }
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
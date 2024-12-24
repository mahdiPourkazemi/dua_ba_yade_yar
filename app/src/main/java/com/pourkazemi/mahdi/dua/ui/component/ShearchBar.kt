package com.pourkazemi.mahdi.dua.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",//Todo customize placeholder in resource
    onSearch: (String) -> Unit = {}
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        textStyle = LocalTextStyle.current.copy(
            textDirection = TextDirection.Rtl,
            textAlign = TextAlign.Right,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "آیکون جستجو",
                tint = MaterialTheme.colorScheme.primary,
                )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDirection = TextDirection.Rtl,
                    textAlign = TextAlign.Right,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                ),
                color = MaterialTheme.colorScheme.outline
                )
        },
        maxLines = 1,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            // رنگ‌های بهبود یافته متریال دیزاین
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.outline,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.outline
        ),
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.None // تغییر به None برای فارسی
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch(searchQuery) }
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp) //Todo customize padding for topAppBar
    )
}


/*@Preview
@Composable
fun SearchBarPreview(){
    SearchBar("ئشاهی",,onSearch = {})
}*/

/*var searchQuery by remember { mutableStateOf("") }

SearchBar(
searchQuery = searchQuery,
onQueryChange = { searchQuery = it },
onSearch = { query ->
    // Handle search here
}
)*/


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MaterialTheme {
            var searchQuery by remember { mutableStateOf("") }

            SearchBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                //onSearch = { /* Handle search */ }
            )

    }
}

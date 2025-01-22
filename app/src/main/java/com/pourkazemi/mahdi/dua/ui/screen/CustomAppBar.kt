package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.ui.theme.MyTypography


// کامپوننت اپ‌بار سفارشی
@Composable
fun CustomAppBar(
    title: String,
    isSwitchChecked: Boolean,
    onSwitchChange: (Boolean) -> Unit = {},
    currentTextSize: TextStyle,
    onTextSizeChange: (TextStyle) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .height( 56.dp)
            .fillMaxWidth(),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "ترجمه",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isSwitchChecked,
                    onCheckedChange = {
                        onSwitchChange(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.surface//.copy(alpha = 0.5f)
                    ),
                )

            }


            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }

        }
    }

    if (showDialog) {
        TextSizeDialog(
            onDismiss = { showDialog = false },
            currentTextSize = currentTextSize,
            onTextSizeChange = { newTextStyle ->
                onTextSizeChange (newTextStyle)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    MaterialTheme {
        /*CustomAppBar(
            title = "عنوان صفحه",
            onSettingsClick = {},
            onSwitchChange = {}
        )*/
    }
}

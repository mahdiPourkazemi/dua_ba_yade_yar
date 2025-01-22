package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pourkazemi.mahdi.dua.R
import androidx.compose.ui.unit.sp


@Composable
fun TextSizeDialog(
    onDismiss: () -> Unit,
    currentTextSize: TextStyle,
    onTextSizeChange: (TextStyle) -> Unit
) {
    var selectedTextSize by remember { mutableStateOf(currentTextSize) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DialogHeader()
                Spacer(modifier = Modifier.height(16.dp))
                TextSizeSlider(
                    currentTextSize = selectedTextSize,
                    onTextSizeChange = { fontSize ->
                        selectedTextSize = selectedTextSize.copy(fontSize = fontSize.sp)
                    }
                )
                DialogButtons(
                    onConfirm = {
                        onTextSizeChange(selectedTextSize)
                        onDismiss()
                    },
                    onCancel = onDismiss
                )
            }
        }
    }
}

@Composable
private fun DialogHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = adaptiveIconPainterResource(id = R.drawable.dar_name),
            contentDescription = stringResource(id = R.string.developer_and_etc),
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(110.dp)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.developer_and_etc),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textDirection = TextDirection.ContentOrRtl,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    }
}


@Composable
private fun TextSizeSlider(
    currentTextSize: TextStyle,
    onTextSizeChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "اندازه فعلی: ${currentTextSize.fontSize.value.toInt()}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textDirection = TextDirection.ContentOrRtl,
                fontSize = currentTextSize.fontSize
            )
        )
        Slider(
            value = currentTextSize.fontSize.value,
            onValueChange = onTextSizeChange,
            valueRange = 14f..32f,
            steps = 11,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun DialogButtons(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = onCancel) {
            Text("لغو")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onConfirm) {
            Text("تایید")
        }
    }
}
@Preview(showBackground = true, name = "TextSizeDialog Preview")
@Composable
fun PreviewTextSizeDialog() {
    MaterialTheme {
        TextSizeDialog(
            onDismiss = {},
            currentTextSize = TextStyle(fontSize = 16.sp),
            onTextSizeChange = {}
        )
    }
}
package com.pourkazemi.mahdi.dua_bayadyar.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class TranslationState(
    initialGlobalState: Boolean = false,
    initialIndividualStates: Map<Int, Boolean> = emptyMap()
) {
    var globalTranslationEnabled by mutableStateOf(initialGlobalState)
        private set

    var individualStates by mutableStateOf(initialIndividualStates)
        private set

    fun toggleGlobalTranslation(enabled: Boolean) {
        globalTranslationEnabled = enabled
        // Reset individual states when global state changes
        individualStates = mapOf()
    }

    fun toggleIndividualTranslation(itemId: Int) {
        val isCurrentlyVisible = isTranslationVisible(itemId)
        individualStates = individualStates + (itemId to !isCurrentlyVisible)
    }

    fun isTranslationVisible(itemId: Int): Boolean {
        return individualStates[itemId] ?: globalTranslationEnabled
    }

    companion object {
        val Saver: Saver<TranslationState, *> =  mapSaver(
            save = { mapOf("global" to it.globalTranslationEnabled, "individual" to it.individualStates) },
            restore = {saved ->
                TranslationState(
                    initialGlobalState =saved["global"] as Boolean,
                    initialIndividualStates = saved["individual"] as Map<Int, Boolean>
                )
            }
        )
    }
}

@Composable
fun rememberTranslationState(): TranslationState {
    return rememberSaveable(saver = TranslationState.Saver) {
        TranslationState()
    }
}

@Composable
fun DuaItem(
    modifier: Modifier = Modifier,
    itemId: Int,
    translationState: TranslationState,
    text: String = "default",
    traText: String = "translate",
    textStyle: TextStyle,
    traTextStyle: TextStyle
) {
    val showTranslation = translationState.isTranslationVisible(itemId)

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = textStyle.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        textDirection = TextDirection.ContentOrRtl
                    ),
                    modifier = modifier.fillMaxWidth()
                )

                AnimatedVisibility(
                    visible = showTranslation,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Text(
                        text = traText,
                        style = traTextStyle.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textDirection = TextDirection.Rtl
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }

            FilledTonalIconButton(
                onClick = { translationState.toggleIndividualTranslation(itemId) },
                modifier = Modifier.align(Alignment.BottomStart),
                shape = CircleShape,
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    imageVector = if (showTranslation)
                        Icons.Rounded.KeyboardArrowUp
                    else
                        Icons.Rounded.KeyboardArrowDown,
                    contentDescription = if (showTranslation)
                        "Hide translation"
                    else
                        "Show translation",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DuaItemPreview() {
    val previewTranslationState = rememberTranslationState()
    DuaItem(
        itemId = 1,
        translationState = previewTranslationState,
        text = "بسم الله الرحمن الرحيم",
        traText = "In the name of Allah, the Most Gracious, the Most Merciful",
        textStyle = MaterialTheme.typography.bodyMedium,
        traTextStyle = MaterialTheme.typography.bodySmall
    )
}
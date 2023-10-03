package com.tellme.core_ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.R
import com.tellme.core_ui.components.button.PrimaryButton
import com.tellme.core_ui.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppMultilineTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (hasFocus: Boolean) -> Unit = {},
    hint: String = "Answer here...",
    sendButton: @Composable () -> Unit
) {

    var state by remember { mutableStateOf(TextFieldValue(text = "")) }
    val interactionSource = remember { MutableInteractionSource() }
    var hasFocus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = value) {
        val diff: Int = value.length - state.text.length
        val selection: TextRange = if (diff > 1) TextRange(value.length) else state.selection
        state = state.copy(text = value, selection = selection)
    }

    Surface(
        modifier = modifier
            .border(3.dp, AppTheme.colors.onPrimary, AppTheme.shapes.large),
        shape = AppTheme.shapes.large
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(36.dp)
                    .onFocusChanged { focusState ->
                        hasFocus = focusState.hasFocus
                        onFocusChanged(hasFocus)
                    },
                value = state,
                onValueChange = remember {
                    { textFieldValue ->
                        state = textFieldValue
                        onValueChange(state.text)
                    }
                },
                enabled = enabled,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier, contentAlignment = Alignment.TopStart) {
                        TextFieldDecorationBox(
                            value = value,
                            innerTextField = innerTextField,
                            enabled = enabled,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource,
                            placeholder = {
                                Text(text = hint, textAlign = TextAlign.Start)
                            },
                            contentPadding = PaddingValues(0.dp)
                        )
                    }
                },
                visualTransformation = VisualTransformation.None,
                singleLine = false,

            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 36.dp)
            ) {
                sendButton()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AppMultilineTextFieldPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppMultilineTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                onFocusChanged = {},
                sendButton = {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        buttonText = "Button Text",
                        iconPainter = painterResource(id = R.drawable.ic_fire_emoji)
                    )
                }
            )
        }
    }
}
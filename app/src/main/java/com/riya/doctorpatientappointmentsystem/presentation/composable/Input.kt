package com.riya.doctorpatientappointmentsystem.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor
import com.riya.doctorpatientappointmentsystem.presentation.theme.headlineSmall

@Composable
fun Input(
    title: String,
    value: String,
    hint: String,
    transformation: TransformationType = TransformationType.SimpleText,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: ImageVector? = null,
    onValueChange: (String) -> Unit
) {
    Text(text = title, style = headlineSmall)
    Spacer(modifier = Modifier.height(8.dp))
    TextFieldInput(
        value = value,
        hint = hint,
        transformation = transformation,
        keyboardType = keyboardType,
        leadingIcon = leadingIcon,
        onValueChange = onValueChange
    )
}

enum class TransformationType {
    SimpleText, Password, Date, CreditCard, ExpireDate
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldInput(
    value: String,
    hint: String,
    transformation: TransformationType = TransformationType.SimpleText,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: ImageVector? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = SystemColor.textGray,
                    textAlign = TextAlign.Start
                )
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = when (transformation) {
            TransformationType.SimpleText -> VisualTransformation.None
            TransformationType.Password -> PasswordVisualTransformation('*')
            TransformationType.Date -> visualTransformationDate()
            TransformationType.ExpireDate -> visualTransformationExpireDate()
            TransformationType.CreditCard -> visualTransformationCreditCard(value)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = leadingIcon?.let {
            {
                Icon(imageVector = it, contentDescription = null, tint = SystemColor.textGray)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(SystemColor.backgroundGray)
            .border(1.dp, SystemColor.textGray, RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SystemColor.backgroundGray,
            unfocusedContainerColor = SystemColor.backgroundGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = SystemColor.primary,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray
        )

    )
}

private fun visualTransformationDate(): VisualTransformation = VisualTransformation { text ->
    val trimmed = text.text.take(8)
    val formatted = trimmed.chunked(2).joinToString("/")
    val offset = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = offset + offset / 2
        override fun transformedToOriginal(offset: Int): Int = offset - offset / 3
    }
    TransformedText(AnnotatedString(formatted), offset)
}

private fun visualTransformationExpireDate(): VisualTransformation = VisualTransformation { text ->
    val trimmed = text.text.take(4)
    val formatted = trimmed.chunked(2).joinToString("/")
    val offset = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = if (offset <= 2) offset else offset + 1
        override fun transformedToOriginal(offset: Int): Int = if (offset <= 2) offset else offset - 1
    }
    TransformedText(AnnotatedString(formatted), offset)
}

private fun visualTransformationCreditCard(cardNumber: String): VisualTransformation = VisualTransformation { text ->
    val trimmed = text.text.take(16)
    val formatted = trimmed.chunked(4).joinToString("-")
    val offset = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = offset + offset / 4
        override fun transformedToOriginal(offset: Int): Int = offset - offset / 5
    }
    TransformedText(AnnotatedString(formatted), offset)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InputPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(20.dp)) {
        Input("Email", email, "Enter your email") { email = it }
        Spacer(modifier = Modifier.height(16.dp))
        Input("Password", password, "Enter password", TransformationType.Password) { password = it }
    }
}


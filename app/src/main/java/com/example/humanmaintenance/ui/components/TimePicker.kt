package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalTime

@Composable
fun TimeInput(
  label: String,
  time: LocalTime?,
  onTimeChange: (LocalTime?) -> Unit,
  required: Boolean = true
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(4.dp)
  ) {
    Text(label)

    if (!required) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Checkbox(
          checked = time == null,
          onCheckedChange = { checked ->
            if (checked) {
              onTimeChange(null)
            } else {
              onTimeChange(LocalTime.now().withSecond(0).withNano(0))
            }
          }
        )

        Text("No end time")
      }
    }

    if (required || time != null) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        NumberField(
          value = time!!.hour,
          range = 0..23,
          onValueChange = {
            onTimeChange(time.withHour(it))
          }
        )

        Text(":")

        NumberField(
          value = time.minute,
          range = 0..59,
          onValueChange = {
            onTimeChange(time.withMinute(it))
          }
        )
      }
    }
  }
}

@Composable
private fun NumberField(
  value: Int,
  range: IntRange,
  onValueChange: (Int) -> Unit
) {
  var text by remember {
    mutableStateOf(
      TextFieldValue(
        text = value.toString().padStart(2, '0'),
        selection = TextRange(2)
      )
    )
  }

  LaunchedEffect(value) {
    val padded = value.toString().padStart(2, '0')
    if (padded != text.text) {
      text = TextFieldValue(text = padded, selection = TextRange(padded.length))
    }
  }

  BasicTextField(
    value = text,
    onValueChange = { newValue ->
      val currentDigits = text.text
      val typedDigits = newValue.text.filter { it.isDigit() }

      if (typedDigits.length > currentDigits.length) {
        // A digit was appended. Shift in the new one, dropping the oldest.
        val candidate = typedDigits.takeLast(2)
        val parsed = candidate.toIntOrNull()

        // Reject the keystroke entirely if it would make an out-of-range
        // value — the field just bounces back instead of showing "53".
        if (candidate.length < 2 || (parsed != null && parsed in range)) {
          text = TextFieldValue(candidate, TextRange(candidate.length))
          parsed?.let { if (candidate.length == 2) onValueChange(it) }
        }
        // else: do nothing, `text` stays as it was — invalid digit ignored.
      } else {
        // Deletion is always allowed, even down to an empty field.
        text = TextFieldValue(typedDigits, TextRange(typedDigits.length))
        typedDigits.toIntOrNull()?.let { if (it in range) onValueChange(it) }
      }
    },
    modifier = Modifier
      .width(40.dp)
      .border(
        1.dp,
        MaterialTheme.colorScheme.outline,
        RoundedCornerShape(4.dp)
      ),
    textStyle = LocalTextStyle.current.copy(
      color = Color.White,
      textAlign = TextAlign.Center
    ),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    singleLine = true
  )
}
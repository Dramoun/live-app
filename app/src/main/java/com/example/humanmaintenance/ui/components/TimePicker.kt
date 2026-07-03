package com.example.humanmaintenance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
  OutlinedTextField(
    value = value.toString().padStart(2, '0'),
    onValueChange = { text ->
      text.toIntOrNull()?.let {
        if (it in range) {
          onValueChange(it)
        }
      }
    },
    modifier = Modifier.width(56.dp),
    textStyle = LocalTextStyle.current.copy(
      fontSize = MaterialTheme.typography.bodyMedium.fontSize
    ),
    singleLine = true,
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Number
    )
  )
}
package com.example.humanmaintenance.ui.overlays

import android.R
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.theme.AppColors.Expense
import com.example.humanmaintenance.ui.theme.AppColors.Income
import com.example.humanmaintenance.ui.theme.AppColors.Surface
import com.example.humanmaintenance.ui.theme.AppColors.SurfaceVariant


//TODO: saving items need to have some checks for basic data filled out
@Composable
fun <T>OverLayFooter(
  itemData: T,
  itemExists: Boolean,
  onDismiss: () -> Unit,
  onDelete: (T) -> Unit,
  onAdd: (T) -> Unit
){
  Row(
  modifier = Modifier.fillMaxWidth().padding(16.dp),
  horizontalArrangement = Arrangement.SpaceAround
  ) {
    if (itemExists) {
      Button(
        onClick = {
          onDelete(itemData)
        },
        colors = ButtonDefaults.buttonColors(
          contentColor = Expense,
          containerColor = Surface
        )
      ) {
        Text("Delete")
      }
    }
    TextButton(onClick = onDismiss) {
      Text("Cancel")
    }

    Button(
      onClick = {
        onAdd(itemData)
      },
      colors = ButtonDefaults.buttonColors(
        contentColor = Income,
        containerColor = SurfaceVariant
      )
    ) {
      Text("Save")
    }
  }
}
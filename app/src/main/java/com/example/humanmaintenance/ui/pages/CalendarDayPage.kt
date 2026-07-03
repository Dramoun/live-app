package com.example.humanmaintenance.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.humanmaintenance.ui.components.DateTitleLarge


@Composable
fun CalendarDayPage(
  modifier: Modifier = Modifier
) {
  val hourHeight = 80

  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    DateTitleLarge(modifier = Modifier.align(Alignment.CenterHorizontally))

    Box(
      modifier = Modifier
        .fillMaxSize()
    ) {
      val scrollState = rememberScrollState()

      Box(
        modifier = Modifier
          .verticalScroll(scrollState)
          .height((24 * hourHeight).dp)
          .fillMaxSize()
      ) {
        TimeBackground(hourHeight.dp)
        //EventLayer(events)
      }
    }
  }
}

@Composable
fun TimeBackground(hourHeight: Dp) {
  Column {

    repeat(24) { hour ->

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(hourHeight)
      ) {

        HorizontalDivider()

        Text(
          text = "%02d:00".format(hour),
          modifier = Modifier.padding(8.dp)
        )
      }
    }
  }
}

@Composable
fun EventLayer(hourHeight: Dp) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(hourHeight)
  ) {

  }
}
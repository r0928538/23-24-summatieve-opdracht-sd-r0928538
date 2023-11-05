package be.svenlysiak.coolevents.ui.theme

import android.icu.util.Calendar
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.utils.DetailText


@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Button(
                onClick = {

                }, modifier = Modifier
                    .weight(1f)
            ) {
                Text("<")
            }

            //datum van vandaag in nederlands
            val cal: Calendar = Calendar.getInstance()
            var today = "";
            today += cal.get(Calendar.DAY_OF_MONTH);
            today += " ";
            when (cal.get(Calendar.MONTH) + 1) {
                1 -> today += (stringResource(R.string.januari))
                2 -> today += (stringResource(R.string.februari))
                3 -> today += (stringResource(R.string.maart))
                4 -> today += (stringResource(R.string.april))
                5 -> today += (stringResource(R.string.mei))
                6 -> today += (stringResource(R.string.juni))
                7 -> today += (stringResource(R.string.juli))
                8 -> today += (stringResource(R.string.augustus))
                9 -> today += (stringResource(R.string.september))
                10 -> today += (stringResource(R.string.oktober))
                11 -> today += (stringResource(R.string.november))
                12 -> today += (stringResource(R.string.december))
            }
            today += " ";
            today += cal.get(Calendar.YEAR);
            DetailText(text = today)
            Button(
                onClick = {

                }, modifier = Modifier
                    .weight(1f)
            ) {
                Text(">")
            }
        }
        CalendarView()

        Button(
            onClick = {

            }, modifier = Modifier
                .weight(1f)
        ) {
            Text(stringResource(R.string.toevoegen))
        }
    }
}

fun CalendarView() {
    //hier lijst/kalender
}

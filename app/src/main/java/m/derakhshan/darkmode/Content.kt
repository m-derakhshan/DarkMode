package m.derakhshan.darkmode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Content(isDarkMode: Boolean, onChangeDarkMode: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onBackground,
            text = "\nMohammad Derakhshan\n\nLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomAppBar(actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "account box"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "account box"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "account box"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "account box"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "account box"
                )
            }
        },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onChangeDarkMode,
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(
                        painterResource(id = if (isDarkMode) R.mipmap.sun else R.mipmap.moon),
                        "change dark mode",
                        modifier = Modifier.size(28.dp)
                    )
                }

            })
    }
}
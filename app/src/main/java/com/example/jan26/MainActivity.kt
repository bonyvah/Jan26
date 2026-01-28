package com.example.jan26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jan26.ui.theme.Jan26Theme

private val ColorSurface = Color(0xFFBFC6C4)
private val ColorPrimary = Color(0xFF6F8F72)
private val ColorAccent = Color(0xFFF2A65A)

class MainActivity : ComponentActivity() {
    private val logs = mutableStateListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logs.add("onCreate")
        setContent {
            Jan26Theme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorSurface),
                    contentAlignment = Alignment.Center
                ) {
                    ProfileCard(
                        modifier = Modifier.padding(16.dp),
                        logs = logs
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logs.add("onStart")
    }

    override fun onResume() {
        super.onResume()
        logs.add("onResume")
    }

    override fun onPause() {
        super.onPause()
        logs.add("onPause")
    }

    override fun onStop() {
        super.onStop()
        logs.add("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logs.add("onDestroy")
    }
}

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    logs: List<String> = listOf()
) {
    Column(
        modifier = modifier
            .background(ColorSurface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.photo_2026_01_26_11_56_09),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Text(
                text = stringResource(R.string.full_name),
                fontSize = 24.sp,
                color = ColorPrimary
            )

            Text(
                text = stringResource(R.string.title),
                fontSize = 16.sp,
                color = ColorAccent
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ LazyColumn for logs
        LazyColumn(
            modifier = Modifier
                .weight(1f, fill = true)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(logs) { log ->
                Text(text = log, color = ColorPrimary)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        ContactRow(
            icon = Icons.Default.Phone,
            text = stringResource(R.string.phone)
        )

        ContactRow(
            icon = Icons.Default.Share,
            text = stringResource(R.string.social)
        )

        ContactRow(
            icon = Icons.Default.Email,
            text = stringResource(R.string.email)
        )
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp),
            tint = ColorAccent
        )
        Text(text = text, color = ColorPrimary)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    Jan26Theme {
        ProfileCard(
            modifier = Modifier.padding(16.dp),
            logs = listOf("onCreate", "onStart", "onResume")
        )
    }
}

package com.example.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen() {
    var time by remember { mutableStateOf(0f) } // 현재 시간 (초)
    var running by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // 시간 표시
        Text(
            text = "Time: ${"%.2f".format(time)}",
            fontSize = 40.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 버튼
        Row {
            Button(onClick = {
                running = true
                time = 0f
                message = ""
            }) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {
                running = false
                message = if (time in 0.99..1.01) "🎉 축하드립니다!" else "❌ 다시 시도하세요!"
            }) {
                Text("Stop")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 메시지 표시
        Text(
            text = message,
            fontSize = 30.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
            color = if (message.contains("승리!")) Color.Green else Color.Red
        )
    }

    // 타이머 업데이트
    LaunchedEffect(running) {
        while (running) {
            delay(10) // 0.01초 단위
            time += 0.01f
        }
    }
}

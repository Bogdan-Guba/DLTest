package com.example.testappdl.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.testappdl.model.User.User
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sign
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.input.pointer.PointerInputChange


@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit,
    onDelete: (User) -> Unit
) {
    var targetOffsetX by remember { mutableFloatStateOf(0f) }

    val animatedOffsetX by animateFloatAsState(
        targetValue = targetOffsetX,
        animationSpec = tween(durationMillis = 300),
    )

    var itemWidthPx by remember { mutableFloatStateOf(0f) }

    val swipeThresholdPx = itemWidthPx * 0.25f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .onGloballyPositioned { coordinates ->
                itemWidthPx = coordinates.size.width.toFloat()
            }
            .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
            .pointerInput(user) {
                var currentDragOffset by mutableFloatStateOf(0f)

                detectHorizontalDragGestures(
                    onDragStart = {
                        currentDragOffset = 0f
                    },
                    onDragEnd = {
                        if (abs(currentDragOffset) > swipeThresholdPx) {
                            onDelete(user)
                            targetOffsetX = itemWidthPx * 2f * currentDragOffset.sign
                        } else {
                            targetOffsetX = 0f
                        }
                        currentDragOffset = 0f
                    },
                    onDragCancel = {
                        targetOffsetX = 0f
                        currentDragOffset = 0f
                    },
                    onHorizontalDrag = { change: PointerInputChange, dragAmount: Float ->
                    currentDragOffset += dragAmount
                    targetOffsetX = currentDragOffset.coerceIn(-itemWidthPx, itemWidthPx)
                    }
                )
            }
            .clickable(
                onClick = onClick,
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Name: ${user.name}")
            Text(text = "Surname: ${user.surname}")
            Text(text = "Age: ${user.age} years old")
            Text(text = "Data destination: ${user.dataDestination.mark}")
        }
    }
}
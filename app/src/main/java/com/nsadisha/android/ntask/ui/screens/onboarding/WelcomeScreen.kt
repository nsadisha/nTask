package com.nsadisha.android.ntask.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.navigation.AppRoutes
import com.nsadisha.android.ntask.ui.components.FilledButton

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_img),
                contentDescription = "welcome image",
                modifier = Modifier
                    .width(230.dp)
                    .height(262.70212.dp)
            )
        }
        Box(modifier = Modifier
            .height(380.dp)
            .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (0).dp, y = 290.dp)
                    .requiredSize(size = 1000.dp)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 84.dp,
                        bottom = 32.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    text = stringResource(id = R.string.welcome_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
                FilledButton(
                    label = stringResource(id = R.string.welcome_btn_text),
                    onclick = {navController.navigate(AppRoutes.SETUP_SCREEN.name)},
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WelcomeScreen_preview() {
    WelcomeScreen(navController = rememberNavController())
}
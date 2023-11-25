package com.nsadisha.android.ntask.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.navigation.AppRoutes
import com.nsadisha.android.ntask.ui.components.FilledButton
import com.nsadisha.android.ntask.vm.OnboardingViewModel

@Composable
fun SetupScreen(
    navController: NavHostController,
    vm: OnboardingViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    fun handleFinish() {
        if(name != ""){
            isError = false;
            vm.writeName(name)
            navController.navigate(AppRoutes.HOME_SCREEN_HOST.name)
        }else{
            isError = true;
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5 / 4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.setup_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(id = R.string.setup_txt_lbl)) },
                placeholder = { Text(stringResource(id = R.string.setup_txt_hint)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                isError = isError && name == "",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){}

        Box(modifier = Modifier
            .height(245.dp)
//            .weight(1f)
            .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .absoluteOffset(x = (0).dp, y = 380.dp)
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
                    text = stringResource(id = R.string.setup_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
                FilledButton(
                    label = stringResource(id = R.string.welcome_btn_text),
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onclick = { handleFinish() },
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SetupScreen_preview() {
    SetupScreen(navController = rememberNavController())
}
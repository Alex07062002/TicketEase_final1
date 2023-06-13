package com.example.ticketease.Screens.Organizer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ticketease.MVVM.Event.SelectPlace.SelectPlace
import com.example.ticketease.MVVM.Event.SelectPlace.SelectPlaceViewModel
import com.example.ticketease.MVVM.Event.getEvents.ViewModelRepositoryGetEvents
import com.example.ticketease.MVVM.HelloPages.HelloPage5ViewModel
import com.example.ticketease.R

@Composable
fun PlaceSelector(navController: NavHostController, viewModel: ViewModelRepositoryGetEvents = hiltViewModel()) {
    val list = viewModel.listPlaces.observeAsState(initial = listOf())
    Box(
        modifier = Modifier
            .background(color = colorResource(R.color.white))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .size(300.dp, 900.dp)
                .padding(top = 50.dp)
        ) {
            Text(
                "Площадка",
                fontSize = 25.sp,
                modifier = Modifier.offset(x = 85.dp),
                color = Color.Black
            )
            for (l in list.value) {
                ListItemPlace(
                    name = l.name,
                    capacity = l.capacity!!,
                    navController
                )
            }
        }
    }
}


@Composable
fun ListItemPlace(name: String, capacity: Long, navController: NavHostController, viewModel: SelectPlaceViewModel = hiltViewModel()) {

    val isButtonPressed = remember { mutableStateOf(false) }
    Box(contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                isButtonPressed.value = !isButtonPressed.value
                viewModel.placeState.capacity = capacity
                viewModel.place(SelectPlace.selectPlace)
                navController.navigate("TimeSelector")
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(120.dp)
                .width(300.dp)
                .offset(y = 0.dp, x = 0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isButtonPressed.value) colorResource(R.color.backgroud) else colorResource(
                   R.color.find
                )
            ),

            ) {

            Column {
                Text(name, fontSize = 18.sp, color = Color.White)

                Text("Количество" + capacity.toString(), fontSize = 18.sp, color = Color.White)

            }
        }
    }
}




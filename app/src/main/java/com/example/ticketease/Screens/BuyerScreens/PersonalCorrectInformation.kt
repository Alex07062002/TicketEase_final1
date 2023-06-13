package com.example.ticketease.Screens.EnterAppByer

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerResult
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerStateTextFields
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.ViewModelUpdateBuyer

import com.example.ticketease.R

@Composable
fun PersonalCorrectInformation(navController: NavHostController, viewModel: ViewModelUpdateBuyer = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.Results.collect{
                res ->
            when (res){
                is UpdateBuyerResult.Correct -> {
                    navController.navigate("Personal")
                }
                is UpdateBuyerResult.Uncorrect -> {
                    Toast.makeText(
                        context,
                        "error",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }
    Column() {
        Box(

            modifier = Modifier
                .background(color = colorResource(R.color.backgroud))
                .height(150.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.padding(50.dp)) {
                Text("Личный кабинет", fontSize = 35.sp, color = Color.White)

            }
        }
        Box(
            modifier = Modifier
                .background(color = colorResource(R.color.white))
                .fillMaxSize()
        ) {
            Column() {


                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .height(100.dp)
                        .width(450.dp),
                    border = BorderStroke(3.dp, colorResource(R.color.find)),
                    shape = RoundedCornerShape(40),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.find)),

                    )
                {
                    Row() {

                        Column {
                            Row() {
                                Text(state.name + " ", fontSize = 25.sp, color = Color.Black)
                                Text(state.surname, fontSize = 25.sp, color = Color.Black)

                            }
                            Text(state.mobile.toString(), fontSize = 25.sp, color = Color.Black)


                        }
                    }
                }
                Column(modifier = Modifier.size(700.dp, 500.dp),horizontalAlignment = Alignment.CenterHorizontally)
                {
                    TextField(
                        value = state.name,
                        onValueChange = { viewModel.update(UpdateBuyerStateTextFields.Name(it)) },
                        placeholder = { Text(text = "Имя") },
                        modifier = Modifier
                            .padding(5.dp)

                    )
                    TextField(
                        value = state.surname,
                        onValueChange = { viewModel.update(UpdateBuyerStateTextFields.Surname(it)) },
                        placeholder = { Text(text = "Фамилия") },
                        modifier = Modifier
                            .padding(5.dp)
                        ,
                    )

                    TextField(
                        value = state.email,
                        onValueChange = {viewModel.update(UpdateBuyerStateTextFields.Email(it))},
                        placeholder = { Text(text = "email") },
                        modifier = Modifier
                            .padding(5.dp)
                        ,
                    )

                    TextField(
                        value = state.mobile.toString(),
                        onValueChange = { viewModel.update(UpdateBuyerStateTextFields.Mobile(it))},
                        placeholder = { Text(text = "Номер телефона") },
                        modifier = Modifier
                            .padding(5.dp),
                    )

                    Button(
                        onClick = {
                            if (!viewModel.flag) {
                                viewModel.update(UpdateBuyerStateTextFields.UpdateBuyer)
                                navController.navigate("Personal")
                                viewModel.flag = false
                            }
                        },
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .height(50.dp)
                            .width(300.dp),
                        border = BorderStroke(3.dp, colorResource(R.color.backgroud)),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.backgroud)),

                        )
                    {

                        Column {
                            Text("Применить изменения", fontSize = 25.sp, color = Color.White)

                        }
                    }
                }
            }
        }
    }
}
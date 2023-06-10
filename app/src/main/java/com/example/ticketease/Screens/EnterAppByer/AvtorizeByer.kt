package com.example.ticketease.Screens.EnterAppByer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtResult
import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtStateTextFields
import com.example.ticketease.MVVM.Person.Buyer.Avtorize.ViewModelAvtBuyer
import com.example.ticketease.R



@Composable
fun AvtorizeByer(navController: NavHostController, viewModel: ViewModelAvtBuyer = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.avtorizeResults.collect{
                res ->
            when (res){
                is AvtResult.Avtorized -> {
                    navController.navigate("Catalog")
                }
                is AvtResult.IncorrectPassword -> {
                    navController.navigate("UncorrectTextBuyer")

                }
                is AvtResult.UnknownLogin -> {
                    navController.navigate("CannotFindUserBuyer")
                }
            }
        }
    }
    Box(modifier = Modifier
        .background(color = colorResource(R.color.white))
        .fillMaxSize()
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.backgroud))
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(  modifier = Modifier.padding(50.dp)) {
                    Text("Авторизация", fontSize = 35.sp, color = Color.White)

                }
            }
            Column(modifier = Modifier.size(500.dp, 1200.dp),horizontalAlignment = Alignment.CenterHorizontally)
            {



                Column(
                    modifier = Modifier.size(500.dp, 1200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    TextField(
                        value = state.login,
                        onValueChange = {viewModel.avtorize(AvtStateTextFields.Login(it))},
                        placeholder = { Text(text = "Логин") },
                        modifier = Modifier
                            .padding(5.dp)
                        ,

                        )
                    TextField(
                        value = state.password,
                        onValueChange = { viewModel.avtorize(AvtStateTextFields.Password(it))},
                        placeholder = { Text(text = "Пароль") },
                        modifier = Modifier
                            .padding(5.dp)
                        ,
                    )


                    Box(modifier = Modifier.padding(top = 200.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Button(
                                onClick = {
                                    if(state.login!="" && state.password!="") {
                                        if (!viewModel.flag) {
                                                viewModel.avtorize(AvtStateTextFields.AvtBuyer)
                                                navController.navigate("Catalog")
                                            viewModel.flag = false
                                        } else {
                                            navController.navigate("ErrorNoLogin")
                                        }
                                    }else{
                                        navController.navigate("ErrorNoLogin")
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 150.dp)
                                    .height(50.dp)
                                    .width(300.dp),
                                border = BorderStroke(3.dp, colorResource(R.color.backgroud)),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        R.color.backgroud
                                    )
                                ),

                                )
                            {

                                Column {
                                    Text("Войти", fontSize = 15.sp, color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


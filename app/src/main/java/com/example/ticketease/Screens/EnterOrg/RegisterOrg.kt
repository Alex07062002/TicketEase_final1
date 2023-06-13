package com.example.ticketease.Screens.EnterOrg

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.ticketease.MVVM.Person.Buyer.Register.RegistResult
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterStateTextFields
import com.example.ticketease.MVVM.Person.Buyer.Register.ViewModelRegistBuyer
import com.example.ticketease.MVVM.Person.Organizer.Register.RegistResultOrg
import com.example.ticketease.MVVM.Person.Organizer.Register.RegisterStateTextFieldsOrg
import com.example.ticketease.MVVM.Person.Organizer.Register.ViewModelRegistOrg
import com.example.ticketease.R
import com.example.ticketease.Screens.EnterAppByer.checkSymbols
import kotlinx.coroutines.flow.collect
import java.util.*

@Composable
fun RegisterOrg(navController: NavHostController, viewModel: ViewModelRegistOrg = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.registerResults.collect(){
                res ->
            when (res){
                is RegistResultOrg.Registered -> {
                    navController.navigate("CreateEvent")
                }
                is RegistResultOrg.Unregistered -> {
                   navController.navigate("UncorrectTextOrg")
                }
                is RegistResultOrg.UnknownError -> {
                    navController.navigate("UncorrectTextOrg")

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
                    Text("Регистрация", fontSize = 35.sp, color = Color.White)

                }
            }
            Column(modifier = Modifier.size(500.dp, 600.dp),horizontalAlignment = Alignment.CenterHorizontally)
            {
                TextField(
                    value = state.name,
                    onValueChange = { viewModel.register(RegisterStateTextFieldsOrg.Name(it)) },
                    placeholder = { Text(text = "Имя") },
                    modifier = Modifier
                        .padding(5.dp).clickable(){
                            val locale = Locale("ru")
                            Locale.setDefault(locale)
                            val resources = context.resources
                            val configuration = resources.configuration
                            configuration.locale = locale
                            resources.updateConfiguration(configuration, resources.displayMetrics)
                        }

                )
                TextField(
                    value = state.surname,
                    onValueChange = { viewModel.register(RegisterStateTextFieldsOrg.Surname(it)) },
                    placeholder = { Text(text = "Фамилия") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )
                TextField(
                    value = state.login,
                    onValueChange = {viewModel.register(RegisterStateTextFieldsOrg.Login(it))},
                    placeholder = { Text(text = "Логин") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,

                    )
                TextField(
                    value = state.email,
                    onValueChange = {viewModel.register(RegisterStateTextFieldsOrg.Email(it))},
                    placeholder = { Text(text = "email") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                TextField(
                    value = state.mobile,
                    onValueChange = { viewModel.register(RegisterStateTextFieldsOrg.Mobile(it))},
                    placeholder = { Text(text = "Номер телефона") },
                    modifier = Modifier
                        .padding(5.dp),
                )

                TextField(
                    value = state.password,
                    onValueChange = { viewModel.register(RegisterStateTextFieldsOrg.Password(it))},
                    placeholder = { Text(text = "Пароль") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                TextField(
                    value = viewModel.repeatPassword.value,
                    onValueChange = { viewModel.register(RegisterStateTextFieldsOrg.RepeatPassword(it))},
                    placeholder = { Text(text = "Подтверждение пароля") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                Button(
                    onClick = {
                        if (viewModel.repeatPassword.value == state.password) {
                            if (checkSymbols(state.name) and checkSymbols(state.surname) and android.util.Patterns.PHONE.matcher(
                                    state.mobile
                                ).matches()
                            ) {
                                if (!viewModel.flag) {
                                    viewModel.register(RegisterStateTextFieldsOrg.RegisterOrganizer)
                                    navController.navigate("CreateEvent")
                                    viewModel.flag = false
                                } else {
                                    navController.navigate("UncorrectTextOrg")
                                    Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                                }
                            }else{
                                navController.navigate("UncorrectTextOrg")
                                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()

                            }
                        }
                        else {
                            navController.navigate("UncorrectTextOrg")
                            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                        }
                              },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(50.dp)
                        .width(300.dp),
                    border = BorderStroke(3.dp, colorResource(R.color.backgroud)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.backgroud)),

                    )
                {

                    Column {
                        Text("Зарегистрироваться", fontSize = 25.sp, color = Color.White)

                    }
                }
            }

        }
    }
}
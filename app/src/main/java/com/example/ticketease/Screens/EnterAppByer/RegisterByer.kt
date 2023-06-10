package com.example.ticketease.Screens.EnterAppByer

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
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
import com.example.ticketease.R
import java.util.*


@Composable
fun RegisterBuyer(navController: NavHostController, viewModel: ViewModelRegistBuyer = hiltViewModel()) {
   val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.registerResults.collect{
            res ->
            when (res){
                is RegistResult.Registered -> {
                    navController.navigate("Catalog")
                }
                is RegistResult.Unregistered -> {
                    navController.navigate("AvtorizeByer")

                }
                is RegistResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "UncorrectTextBuyer",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    Box(modifier = Modifier
        .background(color = colorResource(R.color.white))
        .fillMaxSize()
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(
            rememberScrollState())
        ) {
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
                    onValueChange = { viewModel.register(RegisterStateTextFields.Name(it)) },
                    placeholder = { Text(text = "Имя") },
                    modifier = Modifier
                        .padding(5.dp)

                )
                TextField(
                    value = state.surname,
                    onValueChange = { viewModel.register(RegisterStateTextFields.Surname(it)) },
                    placeholder = { Text(text = "Фамилия") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )
                TextField(
                    value = state.login,
                    onValueChange = {viewModel.register(RegisterStateTextFields.Login(it))},
                    placeholder = { Text(text = "Логин") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,

                    )
                TextField(
                    value = state.email,
                    onValueChange = {viewModel.register(RegisterStateTextFields.Email(it))},
                    placeholder = { Text(text = "email") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                    TextField(
                        value = state.mobile,
                        onValueChange = { viewModel.register(RegisterStateTextFields.Mobile(it))},
                        placeholder = { Text(text = "Номер телефона") },
                        modifier = Modifier
                            .padding(5.dp),
                        )

                TextField(
                    value = state.password,
                    onValueChange = { viewModel.register(RegisterStateTextFields.Password(it))},
                    placeholder = { Text(text = "Пароль") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                TextField(
                    value = viewModel.repeatPassword.value,
                    onValueChange = { viewModel.register(RegisterStateTextFields.RepeatPassword(it))},
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
                                    viewModel.register(RegisterStateTextFields.RegisterBuyer)
                                    navController.navigate("Catalog")
                                    viewModel.flag = false
                                } else {
                                    navController.navigate("UncorrectTextBuyer")
                                    Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()

                                }
                            }else{
                                navController.navigate("UncorrectTextBuyer")
                                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()

                            }
                        }
                        else {
                            navController.navigate("UncorrectTextBuyer")

                            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(50.dp)
                        .width(300.dp),
                    border = BorderStroke(3.dp, colorResource(R.color.backgroud)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.backgroud)),

                    ) {
                    Text(text = "Регистрация", modifier = Modifier.padding(8.dp))
                }

            }
        }
    }
}
fun checkSymbols(name: String):Boolean{
    if(name.contains("[a-z]".toRegex()) or  name.contains("[A-Z]".toRegex()) or name.contains("[а-я]".toRegex()) or  name.contains("[А-Я]".toRegex())){
        return true
    }
    return  false
}

fun checkNum(number: String):Boolean{
    if(number.contains("[0-9]".toRegex()) ){
        return true
    }
    return  false
}


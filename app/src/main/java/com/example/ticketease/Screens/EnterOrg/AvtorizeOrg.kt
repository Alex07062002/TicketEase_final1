package com.example.ticketease.Screens.EnterOrg
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
import com.example.ticketease.MVVM.Person.Organizer.Avtorize.AvtResultOrg
import com.example.ticketease.MVVM.Person.Organizer.Avtorize.AvtStateTextFieldsOrg
import com.example.ticketease.MVVM.Person.Organizer.Avtorize.ViewModelAvtOrg
import com.example.ticketease.R


@Composable
fun AvtorizeOrg(navController: NavHostController, viewModel: ViewModelAvtOrg = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.avtorizeResults.collect{
                res ->
            when (res){
                is AvtResultOrg.Avtorized -> {
                    navController.navigate("CreateEvent")
                }
                is AvtResultOrg.IncorrectPassword -> {
                    navController.navigate("UncorrectTextOrg")

                }
                is AvtResultOrg.UnknownLogin -> {
                    navController.navigate("CannotFindUserOrg")
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
            Column(modifier = Modifier.size(500.dp, 600.dp),horizontalAlignment = Alignment.CenterHorizontally)
            {

                TextField(
                    value = state.login,
                    onValueChange = {viewModel.avtorize(AvtStateTextFieldsOrg.Login(it))},
                    placeholder = { Text(text = "Логин") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,

                    )
                TextField(
                    value = state.password,
                    onValueChange = { viewModel.avtorize(AvtStateTextFieldsOrg.Password(it))},
                    placeholder = { Text(text = "Пароль") },
                    modifier = Modifier
                        .padding(5.dp)
                    ,
                )

                Button(
                    onClick = {
                        if(state.login!= "" && state.password!= "") {
                            if (!viewModel.flag) {
                                viewModel.avtorize(AvtStateTextFieldsOrg.AvtOrg)
                                viewModel.flag = false
                                navController.navigate("CreateEvent")
                            }
                        }
                              },
                    modifier = Modifier
                        .padding(top = 300.dp)
                        .height(50.dp)
                        .width(300.dp),
                    border = BorderStroke(3.dp, colorResource(R.color.backgroud)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.backgroud)),

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
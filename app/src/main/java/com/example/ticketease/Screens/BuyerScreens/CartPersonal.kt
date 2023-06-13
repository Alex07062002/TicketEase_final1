package com.example.testcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ticketease.MVVM.Person.Buyer.CartPersonal.ViewModelCartPersonal
import com.example.ticketease.R
import java.time.Instant


@Composable
fun CartPersonal(navController: NavHostController, viewModel : ViewModelCartPersonal = hiltViewModel()) {
    val events = viewModel.cart.observeAsState(initial = listOf())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(730.dp)
            .background(Color.White)
    ) {
        Column{
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .height(50.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text("Корзина", fontSize = 25.sp, color = Color.Black)

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(730.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
            ) {
                var totalPrice = 0.0 // Переменная для хранения суммы цен всех билетов
                for (ticket in events.value){
                    ListItemCart(cost = ticket.price,
                        location = ticket.location,
                        date = Instant.ofEpochMilli(ticket.date),
                        name = ticket.name,
                        ID =  R.drawable.vkz)
                    totalPrice += ticket.price
                }
                Text(
                    text = "Итого: " + totalPrice + " р",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.Black
                )
                Text(
                    text = "Способ оплаты: СБП",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .offset(10.dp, 0.dp),
                    color = Color.Black
                )
                    Button(
                        onClick = {
                            viewModel.updateTicket()
                            navController.navigate("SuccessfulOrder")
                                  },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(60.dp)
                            .width(250.dp)
                            .offset(80.dp, 0.dp),
                       shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(
                                R.color.backgroud
                            )
                        ),

                        )
                    {
                        Column {
                            Text("Оформить заказ", fontSize = 25.sp, color = Color.White)

                        }
                    }
                }
            panel(navController = navController)
                }
            }
        }

@Composable
fun panel(navController: NavHostController){

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(70.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Row{
            Column{
                Image(
                    painterResource(id = R.drawable.barbl),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(29.dp, 29.dp)
                        .offset(25.dp, (-5).dp)
                        .clickable{
                            navController.navigate("Catalog")
                        },
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Каталог ",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(23.dp, 5.dp)
                )
            }
            Box(modifier = Modifier.size(30.dp, 30.dp)) {

            }
            Column() {
                Image(
                    painterResource(id = R.drawable.dscds),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(35.dp, 35.dp)
                        .offset(0.dp, (-7).dp)
                        .clickable() {
                            navController.navigate("Preference")
                        },
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Предпочтения ",
                    fontSize = 10.sp,
                    modifier = Modifier.offset(-20.dp, 0.dp)
                )
            }
            Box(modifier = Modifier.size(30.dp, 30.dp)) {

            }
            Column {
                Image(
                    painterResource(id = R.drawable.vkz),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(30.dp, 30.dp)
                        .offset(-5.dp, -5.dp)
                        .clickable {
                        },
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Корзина ",
                    fontSize = 10.sp,
                    modifier = Modifier.offset(-8.dp, 5.dp)
                )
            }
            Box(modifier = Modifier.size(30.dp, 30.dp)) {

            }
            Column() {
                Image(
                    painterResource(id = R.drawable.avatar),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(31.dp, 31.dp)
                        .offset(15.dp, (-5).dp)
                        .clickable() {
                            navController.navigate("Personal")
                        },
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Личный кабинет ",
                    fontSize = 10.sp,
                    modifier = Modifier.offset(-10.dp, 5.dp)
                )
            }
        }
    }
}

@Composable
fun ListItemCart(cost:Double, location:String, date: Instant, name:String, ID : Int){

    Card(modifier = Modifier
        .width(400.dp)
        .height(380.dp)
        .padding(10.dp)
    ) {
        Box(modifier = Modifier.background(color = colorResource(R.color.vgrey))) {
            Column() {

                Row() {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter) {
                        Text(
                            text = name, fontSize = 25.sp
                        )
                    }
                }
                Row() {
                    Image(
                        painterResource(id = ID),
                        contentDescription = "image",
                        modifier = Modifier
                            .size(250.dp, 230.dp)
                            .offset(65.dp, 10.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Column() {
                        Box(modifier = Modifier.height(120.dp)) {}
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .height(40.dp)
                                .offset(15.dp, 140.dp)
                                .width(120.dp),

                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.find))
                        )
                        {
                            Text("Купить", fontSize = 15.sp, color = Color.White)

                        }

                    }
                }
                Row() {
                    Image(
                        painterResource(id = R.drawable.free_icon_ruble_1868089),
                        contentDescription = "image",
                        modifier = Modifier
                            .size(17.dp, 17.dp)
                            .offset(15.dp, 22.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = cost.toString(),
                        modifier = Modifier.offset(20.dp, 20.dp),
                        fontSize = 15.sp
                    )
                    Box(modifier = Modifier.width(20.dp)) {

                    }
                }
                Row {
                    Image(
                        painterResource(id = R.drawable.free_icon_place_711170),
                        contentDescription = "image",
                        modifier = Modifier
                            .size(17.dp, 17.dp)
                            .offset(15.dp, 22.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = location,
                        modifier = Modifier.offset(20.dp, 20.dp),
                        fontSize = 15.sp
                    )
                }
                Row{
                    Image(
                        painterResource(id = R.drawable.free_icon_dates_4253987),
                        contentDescription = "image",
                        modifier = Modifier
                            .size(17.dp, 17.dp)
                            .offset(15.dp, 22.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = date.toString(),
                        modifier = Modifier.offset(20.dp, 20.dp),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}


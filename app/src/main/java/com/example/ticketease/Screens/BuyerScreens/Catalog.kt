package com.example.ticketease.Screens.EnterAppByer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.ticketease.MVVM.Event.Catalog.ViewModelCatalog
import com.example.ticketease.R
import java.time.Instant


@Composable
 fun Catalog(navController: NavHostController, viewModel: ViewModelCatalog = hiltViewModel()) {
    val events = viewModel.catalog.observeAsState(initial = listOf())

    Column{
        Box(
            modifier = Modifier
                .background(color = colorResource(R.color.backgroud))
                .height(80.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center) {

            Column() {Spacer(modifier = Modifier.height(5.dp))


            }
        }
        Column{
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.white))
                ,
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.white))
                        .wrapContentSize(Alignment.Center)
                        .verticalScroll(rememberScrollState())
                ) {
                    for(event in events.value){
                        ListItem(
                            name = event.name,
                            location = event.location,
                            date =  Instant.ofEpochMilli(event.date),
                            cost = event.price,
                            ID = R.drawable.vkz
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .background(color = colorResource(R.color.white))
                        .fillMaxWidth()
                        .height(70.dp), contentAlignment = Alignment.BottomCenter
                ) {
                    Row() {
                        Column() {
                            Image(
                                painterResource(id = R.drawable.bar1),
                                contentDescription = "image",
                                modifier = Modifier
                                    .size(29.dp, 29.dp)
                                    .offset(25.dp, (-5).dp)
                                    .clickable {
                                        navController.navigate("Catalog")
                                    }
                                ,
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "Каталог", fontSize = 10.sp, modifier = Modifier.padding(23.dp, 5.dp))
                        }
                        Box(modifier = Modifier.size(30.dp, 30.dp)){

                        }
                        Box(modifier = Modifier.size(30.dp, 30.dp)){

                        }
                        Column() {
                            Image(
                                painterResource(id = R.drawable.dscds),
                                contentDescription = "image",
                                modifier = Modifier
                                    .size(35.dp, 35.dp)
                                    .offset((-7).dp, (-7).dp)
                                    .clickable() {
                                        navController.navigate("Preference")
                                    }
                                ,
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "Предпочтения", fontSize = 10.sp, modifier = Modifier.offset((-25).dp, 0.dp))
                        }
                        Box(modifier = Modifier.size(30.dp, 30.dp)){

                        }
                        Column() {
                            Image(
                                painterResource(id = R.drawable.shopcart),
                                contentDescription = "image",
                                modifier = Modifier
                                    .size(30.dp, 30.dp)
                                    .offset((-25).dp, (-5).dp)
                                    .clickable() {
                                        // TODO load ticket
                                        // navController.navigate(NavigationItem.Cart.route) // TODO change this
                                    }
                                ,
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "Корзина", fontSize = 10.sp, modifier = Modifier.offset(-28.dp, 5.dp))
                        }
                        Box(modifier = Modifier.size(30.dp, 30.dp)){

                        }
                        Column() {
                            Image(
                                painterResource(id = R.drawable.avatar),
                                contentDescription = "image",
                                modifier = Modifier
                                    .size(31.dp, 31.dp)
                                    .offset(-15.dp, -5.dp)
                                    .clickable {
                                        navController.navigate("Personal")
                                    }
                                ,
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "Личный кабинет", fontSize = 10.sp, modifier = Modifier.offset(-20.dp, -3.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(cost:Double, location:String, date:Instant, name:String,  ID: Int){
    var isLiked by remember { mutableStateOf(false) }

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
                        Image(
                            painterResource(
                                id = if (isLiked) R.drawable.likecarrot else R.drawable.like
                            ),
                            contentDescription = "image2",
                            modifier = Modifier
                                .size(30.dp, 30.dp)
                                .offset(98.dp, -32.dp)
                                .clickable {
                                    isLiked = !isLiked
                                },
                            contentScale = ContentScale.Crop
                        )
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
                Row() {
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
                Row() {
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
package com.example.ticketease.Screens.Organizer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ticketease.MVVM.Person.Organizer.Personal.ViewModelPersonalOrg
import com.example.ticketease.R


@Composable
fun PersonalOrg(navController: NavHostController, viewModel: ViewModelPersonalOrg = hiltViewModel()) {
    val city = viewModel.city
    val organizer = viewModel.personal.value!!
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column() {
            Box(

                modifier = Modifier
                    .background(color = colorResource(R.color.white))
                    .height(50.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text("Личный кабинет", fontSize = 25.sp, color = Color.Black)

                }
            }
            Column() {
                Box(
                    modifier = Modifier
                        .background(color = colorResource(R.color.white)),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Column() {


                        Button(
                            onClick = {
                                navController.navigate("PersonalCorrectInformationOrg")
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .height(95.dp)
                                .width(450.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.find)),

                            )
                        {
                            Row() {

                                Column {
                                    Image(
                                        painterResource(id = R.drawable.arrow),
                                        contentDescription = "image",
                                        modifier = Modifier
                                            .size(29.dp, 29.dp)
                                            .offset(205.dp, 25.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Row(
                                        modifier = Modifier
                                            .offset(-80.dp, -20.dp)
                                    ) {
                                        Text(
                                            organizer.name + " ",
                                            fontSize = 20.sp,
                                            color = Color.Black
                                        )
                                        Text(
                                            organizer.surname,
                                            fontSize = 20.sp,
                                            color = Color.Black
                                        )


                                    }
                                    Text(
                                        organizer.mobile.toString(),
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .offset(-80.dp, -20.dp),
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                        Box(modifier = Modifier.padding(20.dp)) {
                            Spacer(modifier = Modifier.height(60.dp))
                            Column() {
                                Text(
                                    "Ваш город",
                                    fontSize = 25.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Row() {
                                    Text(
                                        city,
                                        fontSize = 25.sp,
                                        color = Color.Black
                                    )
                                    Box(modifier = Modifier.width(25.dp)) {

                                    }
                                    Image(
                                        painterResource(id = R.drawable.oeemhqaf_transformed),
                                        contentDescription = "image",
                                        modifier = Modifier
                                            .size(25.dp, 25.dp)
                                            .clickable() {
                                                navController.navigate("CitySelectorOrg")

                                            },
                                        contentScale = ContentScale.Crop,

                                        )

                                }


                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(50.dp)
                                ) {

                                    Button(
                                        onClick = {
                                            navController.navigate("HelloPage5")
                                        },
                                        modifier = Modifier
                                            .height(50.dp)
                                            .offset(0.dp, 45.dp)
                                            .width(350.dp),
                                        shape = RoundedCornerShape(50),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(
                                                R.color.backgroud
                                            )
                                        ),

                                        )
                                    {

                                        Column {
                                            Text("Выйти", fontSize = 25.sp, color = Color.White)

                                        }
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .padding(top = 15.dp)
                                        .height(305.dp)
                                        .width(450.dp)
                                ) {

                                }
                                Box(
                                    modifier = Modifier
                                        .background(color = colorResource(R.color.white))
                                        .fillMaxWidth()
                                        .offset(0.dp, 50.dp)
                                        .height(70.dp), contentAlignment = Alignment.BottomCenter
                                ) {
                                    Row() {
                                        Column() {
                                            Image(
                                                painterResource(id = R.drawable.eventwhite),
                                                contentDescription = "image",
                                                modifier = Modifier
                                                    .size(40.dp, 40.dp)
                                                    .offset(105.dp, -5.dp)
                                                    .clickable() {
                                                        navController.navigate("CreateEvent")
                                                    },
                                                contentScale = ContentScale.Crop
                                            )
                                            Text(
                                                text = "Создать мероприятие ",
                                                fontSize = 10.sp,
                                                modifier = Modifier.padding(85.dp, 5.dp)
                                            )
                                        }
                                        Box(modifier = Modifier.size(15.dp, 30.dp)) {

                                        }
                                        Column() {
                                            Image(
                                                painterResource(id = R.drawable.avatarbl),
                                                contentDescription = "image",
                                                modifier = Modifier
                                                    .size(35.dp, 35.dp)
                                                    .offset(-20.dp, -4.dp)
                                                    .clickable() {
                                                        navController.navigate("PersonalOrg")
                                                    },
                                                contentScale = ContentScale.Crop
                                            )
                                            Text(
                                                text = "Личный кабинет",
                                                fontSize = 10.sp,
                                                modifier = Modifier.offset(-40.dp, 10.dp)
                                            )
                                        }
                                        Box(modifier = Modifier.size(30.dp, 30.dp)) {

                                        }
                                        Box(modifier = Modifier.size(30.dp, 30.dp)) {

                                        }

                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
    }
}


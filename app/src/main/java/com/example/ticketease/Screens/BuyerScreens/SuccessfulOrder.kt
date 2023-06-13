package com.example.testcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticketease.R


@Composable
fun SuccessfulOrder(navController: NavHostController) {
    Box(

        modifier = Modifier
            .background(color = colorResource(R.color.backgroud))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier= Modifier.size(300.dp, 900.dp))
        {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.size(300.dp, 300.dp).offset(y = 295.dp, x = 10.dp)) {
                    Text(
                        text = "Отлично!",
                        fontSize = 32.sp,
                        lineHeight = 50.sp,
                        color = Color.White
                    )

                    Text(
                        text = "Покупка совершена",
                        fontSize = 25.sp,
                        lineHeight = 40.sp,
                        color = Color.White
                    )
                }
            }
            Box(contentAlignment = Alignment.Center) {

                Button(

                    onClick = {navController.navigate("Personal")},
                    modifier = Modifier
                        .height(50.dp)
                        .offset(y = 352.dp, x = 28.dp)
                        .width(250.dp),
                    border = BorderStroke(3.dp, Color.White),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.backgroud)),

                    )
                {

                    Column {
                        Text("Продолжить", fontSize = 25.sp, color = Color.White)

                    }
                }
            }

        }
    }
}
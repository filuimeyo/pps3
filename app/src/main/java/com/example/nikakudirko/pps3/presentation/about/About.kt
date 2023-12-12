package com.example.nikakudirko.pps3.presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikakudirko.pps3.R
import com.example.nikakudirko.pps3.common.ScreenViewState
import com.example.nikakudirko.pps3.presentation.bookmark.BookMarkState
import com.example.nikakudirko.pps3.presentation.bookmark.BookmarkScreen
import com.example.nikakudirko.pps3.presentation.home.articles

@Composable
fun AboutScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp)
                .width(360.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.n),
                contentDescription = null,

                )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.about_screen_string),
                color = Color.Black,
                fontSize = 45.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 6.sp,

                )
            Text(
                stringResource(id = R.string.about_screen_little_string),
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(170.dp)

            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    ),


                ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ){
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp),
                        )
                    Column {
                        Text(
                            stringResource(R.string.mail_link_name),
                            color =  Color.White,
                            fontSize = 20 .sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                        Text(
                            stringResource(R.string.mail_link),
                            color = Color.White,
                            fontSize = 17 .sp
                        )
                    }
                }
            }
        }
        ShowInfo()

    }
}

@Composable
fun ShowInfo(modifier: Modifier = Modifier){

    val aboutMeStrings = listOf(
        R.string.fact1,
        R.string.fact2,
        R.string.fact3

    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        aboutMeStrings.forEach{
            Row {
                Text(
                    text = stringResource(id = it),
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 20 .sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevAbout(){
   AboutScreen()
}
package com.halawystory.askandanswer.mvvm.view.room_game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halawystory.askandanswer.ui.theme.backgroundExitRoom
import com.halawystory.askandanswer.ui.theme.green
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.red
import com.halawystory.askandanswer.ui.theme.textDialogExitRoom

@Composable
fun DialogExitRoomGame(
    onExitClicked: () -> Unit, // Callback when "نعم" is clicked
    onStayClicked: () -> Unit  // Callback when "لا" is clicked
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
        ){

        Card(modifier =
        Modifier.padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundExitRoom
        )

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp , vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(text = "هل تريد الخروج؟", style = TextStyle(
                    fontFamily = ibmBold,
                    color = textDialogExitRoom,

                    fontSize = 25.sp
                ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ){

                    //Not Exit Room Game
                    Button(
                        modifier = Modifier.weight(0.5f),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = green
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = onStayClicked
                    ) {
                        Text(text = "لا", style = TextStyle(
                            fontFamily = ibmBold,
                            color = Color.White,
                            fontSize = 25.sp                        ),
                            modifier = Modifier.fillMaxWidth(1f),
                            textAlign = TextAlign.Center
                        )
                    }//end Button


                    Spacer(modifier = Modifier.width(15.dp))



                    //Exit Room Game
                    Button(
                        modifier = Modifier.weight(0.5f),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = red
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = onExitClicked // Call onExitClicked when "نعم" is clicked

                    ) {
                        Text(text = "نعم", style = TextStyle(
                            fontFamily = ibmBold,
                            color = Color.White,
                            fontSize = 25.sp
                        ),
                            modifier = Modifier.fillMaxWidth(1f),
                            textAlign = TextAlign.Center
                        )
                    }//end Button


                }
            }
        }

    }
}
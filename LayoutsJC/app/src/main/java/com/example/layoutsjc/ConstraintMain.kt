package com.example.layoutsjc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.layoutsjc.ui.theme.LayoutsJCTheme
//import ConstraintLayoutScope.ConstrainedLayout

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScrollLinearPreview(){
    LayoutsJCTheme {
        ConstraintView(Modifier.padding(top = 24.dp))
    }
}

@Composable
fun ConstraintView(modifier: Modifier){
    ConstraintLayout(
        modifier.fillMaxSize()
            .background(Color.Green)
    ) {
        val (tvCenter, imgPhoto, tvTitle, vSpace, btnOne, btnTwo, btnThree,
            imgMegadeth, ibPlay, ibPlayTwo, cGuides) = createRefs()
        Text(
            "Constraint",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(tvCenter){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
                .width(200.dp)
                .background(Color.Blue),
            textAlign = TextAlign.Center
        )
        Image(painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.constrainAs(imgPhoto){
                start.linkTo(parent.start, margin = 16.dp)
                top.linkTo(parent.top, margin = 16.dp)
            }
                .background(Color.DarkGray)
            )
        Text("margin margin margin margin margin maregin",
                Modifier.constrainAs(tvTitle){
                    start.linkTo(imgPhoto.end, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(imgPhoto.top)
                    width = Dimension.fillToConstraints
                }
                    .background(Color.Red),
            
                maxLines = 1
            )
        Box(
            Modifier.constrainAs(vSpace){
                start.linkTo(tvTitle.start)
                end.linkTo(tvTitle.end)
                top.linkTo(tvTitle.bottom)
                bottom.linkTo(imgPhoto.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
                .padding(top = 16.dp)
                .background(Color.Cyan)

        ) {  }

        Button(onClick = {},
            Modifier.constrainAs(btnOne){
                start.linkTo(vSpace.start)
                end.linkTo(vSpace.end)
                top.linkTo(imgPhoto.bottom, margin = 16.dp)
            }
            ) {
            Text("Buton1")
        }
        Button(onClick = {},
            Modifier.constrainAs(btnTwo){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(btnOne.bottom, margin = 16.dp)
            }
        ) {
            Text("Buton2")
        }
        Button(onClick = {},
            Modifier.constrainAs(btnThree){
                start.linkTo(btnOne.start)
                end.linkTo(btnOne.end)
                top.linkTo(btnTwo.bottom, margin = 16.dp)
            }
        ) {
            Text("Buton3")
        }

        Image(painter = painterResource(R.drawable.megadeth),
            contentDescription = null,
            Modifier.constrainAs(imgMegadeth){
                top.linkTo(tvCenter.bottom, margin = 16.dp)
                start.linkTo(parent.start,  margin = 16.dp)
                end.linkTo(parent.end,  margin = 16.dp)
                width = Dimension.fillToConstraints
            }
                //.fillMaxWidth()
                .height(180.dp)
                .background(Color.Magenta)
            )
        Image(imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                Modifier.constrainAs(ibPlay){
                    end.linkTo(imgMegadeth.end)
                    bottom.linkTo(imgMegadeth.bottom)
                }
                    .clickable{}
                    .background(Color.Gray)
            )
        Image(imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            Modifier.constrainAs(ibPlayTwo){
                end.linkTo(ibPlay.end, margin = 16.dp)
                bottom.linkTo(ibPlay.bottom)
            }
                .clickable{}
                .background(Color.Gray)
        )

        ConstraintLayout(
            Modifier.constrainAs(cGuides){
                top.linkTo(imgMegadeth.bottom)
            }
                .fillMaxWidth()
                .background(Color.White)
        ) {
            val startGuideLine = createGuidelineFromStart(0.25f)
            val endGuideLine = createGuidelineFromEnd(.5f)
            val topGuideLine = createGuidelineFromTop(16.dp)
            val bottomGuideLine = createGuidelineFromBottom(32.dp)

            val (txtStart, textEnd) = createRefs()

            Text("start = 25%, top 16dp",
                Modifier
                    .constrainAs(txtStart){
                        start.linkTo(startGuideLine)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        top.linkTo(topGuideLine)
                    }
                    .background(Color.LightGray)

                )
            Text("start = 50%, top 32dp",
                Modifier
                    .constrainAs(textEnd){
                        start.linkTo(parent.start)
                        end.linkTo(endGuideLine)
                        bottom.linkTo(bottomGuideLine, margin = 8.dp)
                        width = Dimension.fillToConstraints
                    }
                    .background(Color.Black)
                ,
                color = Color.White
                )
        }
    }
}
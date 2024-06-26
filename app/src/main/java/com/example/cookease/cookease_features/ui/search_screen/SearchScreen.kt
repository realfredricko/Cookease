package com.example.cookease.cookease_features.ui.search_screen

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.cookease.cookease_features.data.model.SearchResponse

@Composable
fun SearchScreen(
    searchResponse: SearchResponse,
    onRecipeClick:()->Unit
){
        Surface(
            color = MaterialTheme.colorScheme.background
        ){
            Scaffold(
                topBar = {
                    SearchTopBar(
                        query = it, onSearchClicked = {

                        },
                        onQueryChange = {

                        },
                        onClosedClicked = {

                        })
                },
                content = {
                    LazyColumn {

                    }
                }

            )
        }

    }
    @Composable
    fun SearchTopBar(
        query:String,
        onSearchClicked:()->Unit,
        modifier: Modifier = Modifier,
        onQueryChange:() -> Unit,
        onClosedClicked:() -> Unit
    ){
        TextField(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background, shape = RectangleShape)
            .semantics {
                contentDescription = "TextField"
            },
            value = query,
            onValueChange ={
                onQueryChange(it)},
            placeholder = {
                Text(text = "Search...",
                    modifier = Modifier
                        .alpha(alpha = 0.5f),
                    color = Color.White
                )
            },
            textStyle = MaterialTheme.typography.headlineSmall,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription ="Search Icon" ,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (query.isNotBlank()){
                        onQueryChange("")
                    }
                    else{
                        onClosedClicked()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        )
    }
}

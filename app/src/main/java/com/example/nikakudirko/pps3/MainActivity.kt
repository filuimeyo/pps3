package com.example.nikakudirko.pps3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Accessible
import androidx.compose.material.icons.outlined.Blender
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.nikakudirko.pps3.presentation.bookmark.BookMarkViewModel
import com.example.nikakudirko.pps3.presentation.detail.DetailAssistedFactory
import com.example.nikakudirko.pps3.presentation.home.HomeViewModel
import com.example.nikakudirko.pps3.presentation.navigation.ArticleNavigation
import com.example.nikakudirko.pps3.presentation.navigation.Screens
import com.example.nikakudirko.pps3.presentation.navigation.navigateToSingleTop
import com.example.nikakudirko.pps3.presentation.network.NetworkViewModel
import com.example.nikakudirko.pps3.ui.theme.Pps3Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var assistedFactory: DetailAssistedFactory

    private val networkViewModel: NetworkViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pps3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {

                    ArticleApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ArticleApp() {
        val homeViewModel: HomeViewModel = viewModel()
        val bookMarkViewModel: BookMarkViewModel = viewModel()
        val navController = rememberNavController()

        var currentTab by remember {
            mutableStateOf(TabScreen.Home)
        }


        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            InputChip(
                                selected = currentTab == TabScreen.Home,
                                onClick = {
                                    currentTab = TabScreen.Home
                                    navController.navigateToSingleTop(
                                        route = Screens.Home.name
                                    )
                                },
                                label = {
                                    Text("Home")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                }
                            )

                            Spacer(modifier = Modifier.Companion.size(12.dp))

                            InputChip(
                                selected = currentTab == TabScreen.BookMark,
                                onClick = {
                                    currentTab = TabScreen.BookMark
                                    navController.navigateToSingleTop(
                                        route = Screens.Bookmark.name
                                    )
                                },
                                label = {
                                    Text("Bookmark")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Default.Bookmark, contentDescription = null)
                                }
                            )

                            Spacer(modifier = Modifier.Companion.size(12.dp))

                            InputChip(
                                selected = currentTab == TabScreen.About,
                                onClick = {
                                    currentTab = TabScreen.About
                                    navController.navigateToSingleTop(
                                        route = Screens.About.name
                                    )
                                },
                                label = {
                                    Text("")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Blender, contentDescription = null)
                                }
                            )

                            Spacer(modifier = Modifier.Companion.size(2.dp))

                            InputChip(
                                selected = currentTab == TabScreen.Network,
                                onClick = {
                                    currentTab = TabScreen.Network
                                    navController.navigateToSingleTop(
                                        route = Screens.Network.name
                                    )
                                },
                                label = {
                                    Text("")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Accessible, contentDescription = null)
                                }
                            )

                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigateToSingleTop(
                                    route = Screens.Detail.name
                                )
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                    
                )
            }
        ) {
            ArticleNavigation(
                modifier = Modifier.padding(it),
                navHostController = navController,
                homeViewModel = homeViewModel,
                bookmarkViewModel = bookMarkViewModel,
                assistedFactory = assistedFactory,
                networkViewModel = networkViewModel

            )
        }
    }
}


enum class TabScreen {
    Home, BookMark, About, Network
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pps3Theme {
        Greeting("Android")
    }
}


/*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import com.example.nikakudirko.pps3.network.Resourse
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.nikakudirko.pps3.presentation.detail.DetailAssistedFactory
import com.example.nikakudirko.pps3.presentation.network.NetworkArticlesList
import com.example.nikakudirko.pps3.presentation.network.NetworkViewModel
import com.example.nikakudirko.pps3.ui.theme.Pps3Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<NetworkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val movies = viewModel.networkArticles.collectAsState()

            Pps3Theme {
                movies.value?.let {
                    when (it) {
                        is Resourse.Failure -> {
                            Toast.makeText(context, it.exception.message!!, Toast.LENGTH_SHORT)
                        }
                        Resourse.Loading -> {
                            Text("loading")
                        }
                        is Resourse.Success -> {
                            NetworkArticlesList(it.result)
                        }
                    }
                }
            }
        }
    }
}*/

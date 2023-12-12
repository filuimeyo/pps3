package com.example.nikakudirko.pps3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nikakudirko.pps3.presentation.about.AboutScreen
import com.example.nikakudirko.pps3.presentation.bookmark.BookMarkViewModel
import com.example.nikakudirko.pps3.presentation.bookmark.BookmarkScreen
import com.example.nikakudirko.pps3.presentation.detail.DetailAssistedFactory
import com.example.nikakudirko.pps3.presentation.detail.DetailScreen
import com.example.nikakudirko.pps3.presentation.home.HomeScreen
import com.example.nikakudirko.pps3.presentation.home.HomeViewModel
import com.example.nikakudirko.pps3.presentation.network.NetworkArticlesList
import com.example.nikakudirko.pps3.presentation.network.NetworkScreen
import com.example.nikakudirko.pps3.presentation.network.NetworkViewModel

enum class Screens{
    Home, Detail, Bookmark, About, Network
}


@Composable
fun ArticleNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    bookmarkViewModel: BookMarkViewModel,
    assistedFactory: DetailAssistedFactory,
    networkViewModel: NetworkViewModel
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.name
    ){
        composable(route = Screens.Home.name){
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onBookMarkChange = homeViewModel::onBookMarkChange,
                onDeleteArticle = homeViewModel::deleteArticle,
                onArticleClicked = {
                    navHostController.navigateToSingleTop(
                        route = "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }

        composable(route = Screens.Bookmark.name){
           val state by bookmarkViewModel.state.collectAsState()
            BookmarkScreen(
                state = state,
                modifier = modifier,
                onBookmarkChange = bookmarkViewModel::onBookMarkedChange,
                onDelete = bookmarkViewModel::deleteArticle ,
                onArticleClicked ={
                    navHostController.navigateToSingleTop(
                        route = "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }
        composable(
            route = "${Screens.Detail.name}?id={id}",

            arguments = listOf(
                navArgument("id"){
                    NavType.LongType
                    defaultValue = -1L
                }
            )
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1L
            DetailScreen(
                articleId = id,
                assistedFactory = assistedFactory,
                navigateUp = {
                    navHostController.navigateUp() // просто вернуться на главную
                }
            )

        }

        composable(route = Screens.About.name){
            AboutScreen()
        }

        composable(route = Screens.Network.name){
            NetworkScreen(networkViewModel)
        }
    }

}

fun NavHostController.navigateToSingleTop(route: String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){saveState = true}
        launchSingleTop = true
        restoreState = true
    }
}


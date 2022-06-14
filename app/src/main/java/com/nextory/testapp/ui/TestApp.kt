package com.nextory.testapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nextory.testapp.ui.bookdetails.BookDetails
import com.nextory.testapp.ui.booklist.BookList
import com.nextory.testapp.ui.theme.TestAppTheme

sealed class Screen(val route: String) {
    object BookList : Screen("book_list")
    object BookDetails : Screen("book_details")
}

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun TestApp() {
    TestAppTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)
        AnimatedNavHost(navController, startDestination = Screen.BookList.route) {
            composable(route = Screen.BookList.route) {
                BookList(
                    navController = navController
                )
            }

            composable(
                route = Screen.BookDetails.route+ "?bookId={bookId}",
                arguments = listOf(
                    navArgument(
                        name = "bookId"
                    ) {
                        type = NavType.LongType
                        defaultValue = -1L
                    },
                )
            ) {
                BookDetails(navController = navController,)
            }
        }
    }
}
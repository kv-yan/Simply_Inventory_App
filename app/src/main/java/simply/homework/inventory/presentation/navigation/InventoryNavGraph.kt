package simply.homework.inventory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import   simply.homework.inventory.presentation.home.HomeDestination
import   simply.homework.inventory.presentation.home.HomeScreen
import   simply.homework.inventory.presentation.item.ItemDetailsDestination
import   simply.homework.inventory.presentation.item.ItemDetailsScreen
import   simply.homework.inventory.presentation.item.ItemEditDestination
import   simply.homework.inventory.presentation.item.ItemEditScreen
import   simply.homework.inventory.presentation.item.ItemEntryDestination
import   simply.homework.inventory.presentation.item.ItemEntryScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {

                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                })
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}

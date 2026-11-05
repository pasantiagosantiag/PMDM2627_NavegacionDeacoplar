package ies.sequeros.pmdm.navegacion.principal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ies.sequeros.pmdm.Principal
import ies.sequeros.pmdm.Profile
import ies.sequeros.pmdm.Settings

@Composable
fun Principal(
    modifier: Modifier = Modifier,
    onExist: (PrincipalRoutes) -> Unit,
    viewModel: PrincipalViewModel
) {
    val navController = rememberNavController()
    //navcontroller posee un estado de la cima de la pila
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    //para saber que ruta es la que se encuentra en la cima de la pila
    val currentDestination = navBackStackEntry?.destination

    Row(modifier = Modifier.fillMaxSize()) {
        // Rail izquierdo
        NavigationRail {

            NavigationRailItem(
                //si el destino actual es el de home se marca como seleccinado
                selected = currentDestination?.hierarchy?.any { it.hasRoute(PrincipalRoutes.Home::class) } == true,
                onClick = {
                    navController.navigate(PrincipalRoutes.Home as PrincipalRoutes){
                        //  Limpia la pila hacia atrás hasta el inicio, guardando el estado de lo que quitas
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive=true
                        }
                        // Evita duplicados si la pantalla ya está arriba
                        launchSingleTop = true
                        //  Recupera el estado previo si ya habías visitado esta pantalla antes
                        restoreState = true
                    }
                },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            NavigationRailItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(PrincipalRoutes.Profile::class) } == true,

                onClick = {
                    navController.navigate(PrincipalRoutes.Profile(userId =" id", userRole =" role")){
                        //  Limpia la pila hacia atrás hasta el inicio, guardando el estado de lo que quitas
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive=true
                        }
                        // Evita duplicados si la pantalla ya está arriba
                        launchSingleTop = true
                        //  Recupera el estado previo si ya habías visitado esta pantalla antes
                        restoreState = true
                    }
                },
                icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                label = { Text("Perfil") }
            )
            NavigationRailItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(PrincipalRoutes.Settings::class) } == true,
                onClick = {
                    navController.navigate(PrincipalRoutes.Settings){

                        //  Limpia la pila hacia atrás hasta el inicio, guardando el estado de lo que quitas
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive=true
                        }
                        // Evita duplicados si la pantalla ya está arriba
                        launchSingleTop = true
                        //  Recupera el estado previo si ya habías visitado esta pantalla antes
                        restoreState = true
                    }
                },
                icon = { Icon(Icons.Default.Settings, contentDescription = "Ajustes") },
                label = { Text("Ajustes") }
            )
            NavigationRailItem(
                selected = false,
                onClick = {
                    onExist(PrincipalRoutes.Home)

                },
                icon = { Icon(Icons.Default.DoorBack, contentDescription = "Ajustes") },
                label = { Text("Salir") }
            )

        }
        Box(modifier = Modifier.weight(1f).fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = PrincipalRoutes.Home,
                modifier = Modifier.fillMaxSize()
            ) {


            composable< PrincipalRoutes.Home>{

                    Principal(


                    )
                }
                composable< PrincipalRoutes.Profile>{
                    //se pasa la cima de la pila, para poder consultar
                        backStackEntry ->
                    //se obtiene de la cima la ruta (deserializar
                    val profileArgs: PrincipalRoutes.Profile = backStackEntry.toRoute()
                    val id =profileArgs.userId
                    Profile (id=id)
                }
                composable< PrincipalRoutes.Settings>{
                        backStackEntry ->
                    Settings ()
                }

            }
        }

    }
}
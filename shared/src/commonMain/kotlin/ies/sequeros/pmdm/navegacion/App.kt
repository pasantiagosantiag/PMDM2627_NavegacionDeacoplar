package ies.sequeros.pmdm.navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.pmdm.navegacion.login.LoginScreen
import ies.sequeros.pmdm.navegacion.login.LoginViewModel
import ies.sequeros.pmdm.navegacion.principal.Principal
import ies.sequeros.pmdm.navegacion.principal.PrincipalViewModel


@Composable
@Preview
fun App() {
    //navcontroller, se recuerda, para en caso de recomposición no se pierda
    val navController = rememberNavController()
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = MainRoutes.Login,
                modifier = Modifier.fillMaxSize()
            ) {


                composable<MainRoutes.Home> {
                    val viewModel = viewModel { PrincipalViewModel() }
                    Principal(
                        viewModel = viewModel,
                        onExist = {
                            navController.navigate(MainRoutes.Login) {
                                popUpTo(0) { inclusive = true }
                                // popUpTo("principal") { inclusive = true }

                                //evita múltiples instancias si se presiona el botón rápido
                                launchSingleTop = true
                            }
                        }

                    )
                }
                composable<MainRoutes.Login> {
                    //se pasa la cima de la pila, para poder consultar
                        backStackEntry ->
                    //se obtiene de la cima la ruta (deserializar
                    val viewModel = viewModel { LoginViewModel() }
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LoginScreen(viewModel, {

                            navController.navigate(it) {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                        })
                    }

                }
            }
        }
    }
}
package ies.sequeros.pmdm.navegacion

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainRoutes  {
    @Serializable
    data object Home :  MainRoutes

    @Serializable
    data object Login :  MainRoutes

    @Serializable
    data object Register: MainRoutes


}
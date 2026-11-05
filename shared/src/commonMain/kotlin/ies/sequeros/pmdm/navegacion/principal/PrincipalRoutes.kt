package ies.sequeros.pmdm.navegacion.principal

import kotlinx.serialization.Serializable

@Serializable
sealed interface PrincipalRoutes {
    @Serializable
    data object Home : PrincipalRoutes

    @Serializable
    data class Profile(
        val userId: String,
        val userRole: String = "guest" // Parámetro opcional con valor por defecto
    ) : PrincipalRoutes

    @Serializable
    data object Settings : PrincipalRoutes

    @Serializable
    data object Register: PrincipalRoutes


}
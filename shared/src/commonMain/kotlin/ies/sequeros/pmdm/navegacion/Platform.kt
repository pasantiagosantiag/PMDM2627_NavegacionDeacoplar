package ies.sequeros.pmdm.navegacion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
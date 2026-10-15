
// ---------- Factores de conversión fijos (val) ----------
val dolar = 1.08
val libra = 0.86
val yen = 163.0
val francoSuizo = 0.95

val metrosPorKm = 1000.0
val millasPorKm = 0.621371
val kmPorMilla = 1.609344
val cmPorMetro = 100.0

val kelvinOffset = 273.15

// ---------- Utilidades de entrada ----------

/** Lee un número decimal. Devuelve null si la entrada no es válida. */
fun leerNumero(mensaje: String): Double? {
    print(mensaje)
    val entrada = readLine()?.trim()?.replace(',', '.')
    val numero = entrada?.toDoubleOrNull()
    if (numero == null || numero.isNaN() || numero.isInfinite()) {
        println("Error: entrada no válida, debes introducir un número.")
        return null
    }
    return numero
}

/** Lee una opción entera de menú. Devuelve null si no es un entero. */
fun leerOpcion(): Int? {
    print("Seleccione una opción: ")
    return readLine()?.trim()?.toIntOrNull()
}

fun fmt(valor: Double): String = "%.2f".format(valor)

// ---------- Fase 3: divisas ----------

fun menuDivisas() {
    println()
    println("--- CONVERSOR DE DIVISAS (desde euros) ---")
    println("1. Euros a dólares")
    println("2. Euros a libras esterlinas")
    println("3. Euros a yenes")
    println("4. Euros a francos suizos")
    println("0. Volver")

    val opcion = leerOpcion()
    if (opcion == 0) return
    if (opcion == null || opcion !in 1..4) {
        println("Error: opción no válida.")
        return
    }

    val euros = leerNumero("Cantidad en euros: ") ?: return
    if (euros < 0) {
        println("Error: la cantidad no puede ser negativa.")
        return
    }

    when (opcion) {
        1 -> println("${fmt(euros)} euros equivalen a ${fmt(euros * dolar)} dólares.")
        2 -> println("${fmt(euros)} euros equivalen a ${fmt(euros * libra)} libras esterlinas.")
        3 -> println("${fmt(euros)} euros equivalen a ${fmt(euros * yen)} yenes.")
        4 -> println("${fmt(euros)} euros equivalen a ${fmt(euros * francoSuizo)} francos suizos.")
    }
}

// ---------- Fase 4: distancias ----------

fun menuDistancias() {
    println()
    println("--- CONVERSOR DE DISTANCIAS ---")
    println("1. Kilómetros a metros")
    println("2. Metros a kilómetros")
    println("3. Kilómetros a millas")
    println("4. Millas a kilómetros")
    println("5. Centímetros a metros")
    println("0. Volver")

    val opcion = leerOpcion()
    if (opcion == 0) return
    if (opcion == null || opcion !in 1..5) {
        println("Error: opción no válida.")
        return
    }

    val cantidad = leerNumero("Introduce la distancia: ") ?: return
    if (cantidad < 0) {
        println("Error: la cantidad no puede ser negativa.")
        return
    }

    when (opcion) {
        1 -> println("${fmt(cantidad)} kilómetros equivalen a ${fmt(cantidad * metrosPorKm)} metros.")
        2 -> println("${fmt(cantidad)} metros equivalen a ${fmt(cantidad / metrosPorKm)} kilómetros.")
        3 -> println("${fmt(cantidad)} kilómetros equivalen a ${fmt(cantidad * millasPorKm)} millas.")
        4 -> println("${fmt(cantidad)} millas equivalen a ${fmt(cantidad * kmPorMilla)} kilómetros.")
        5 -> println("${fmt(cantidad)} centímetros equivalen a ${fmt(cantidad / cmPorMetro)} metros.")
    }
}

// ---------- Fase 5: temperaturas ----------

fun menuTemperaturas() {
    println()
    println("--- CONVERSOR DE TEMPERATURAS ---")
    println("1. Celsius a Fahrenheit")
    println("2. Fahrenheit a Celsius")
    println("3. Celsius a Kelvin")
    println("4. Kelvin a Celsius")
    println("0. Volver")

    val opcion = leerOpcion()
    if (opcion == 0) return
    if (opcion == null || opcion !in 1..4) {
        println("Error: opción no válida.")
        return
    }

    val temp = leerNumero("Introduce la temperatura: ") ?: return

    // Validaciones físicas (cero absoluto)
    if (opcion == 4 && temp < 0) {
        println("Error: la temperatura en Kelvin no puede ser inferior a 0.")
        return
    }
    if ((opcion == 1 || opcion == 3) && temp < -kelvinOffset) {
        println("Error: la temperatura en Celsius no puede ser inferior a -273.15.")
        return
    }
    if (opcion == 2 && temp < -459.67) {
        println("Error: la temperatura en Fahrenheit no puede ser inferior a -459.67.")
        return
    }

    when (opcion) {
        1 -> println("${fmt(temp)} °C equivalen a ${fmt(temp * 9 / 5 + 32)} °F.")
        2 -> println("${fmt(temp)} °F equivalen a ${fmt((temp - 32) * 5 / 9)} °C.")
        3 -> println("${fmt(temp)} °C equivalen a ${fmt(temp + kelvinOffset)} K.")
        4 -> println("${fmt(temp)} K equivalen a ${fmt(temp - kelvinOffset)} °C.")
    }
}

// ---------- Fase 6: menú principal con bucle ----------

fun main() {
    var opcion: Int?

    do {
        println()
        println("===== CONVERSOR DE UNIDADES =====")
        println("1. Conversor de divisas")
        println("2. Conversor de distancias")
        println("3. Conversor de temperaturas")
        println("4. Salir")

        opcion = leerOpcion()

        when (opcion) {
            1 -> menuDivisas()
            2 -> menuDistancias()
            3 -> menuTemperaturas()
            4 -> println("Adiós...")
            else -> println("Error: opción no válida.")
        }
    } while (opcion != 4)
}
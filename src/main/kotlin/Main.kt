@file:Suppress("SpellCheckingInspection")

import java.util.*

/**
 * Función principal que gestiona la inserción de empresas, empleados y nóminas en la base de datos.
 */
fun main() {
    // Creación de los objetos necesarios para la gestión de la base de datos y la inserción de datos
    val conexionBD = ConexionBD()
    val gestorTablas = GestorTablas(conexionBD)
    val insertarObjetos = InsertarObjetos()
    val generadorXML = GeneradorXML()

    // Me conecto a la Base de Datos SQL
    conexionBD.conectar()

    // Creo las tablas en la Base de Datos SQL
    gestorTablas.crearTablas()

    // Bucle para la inserción de empresas en la BD SQL
    while (true) {
        println("¿Quieres insertar una empresa? (s/n)")
        val respuesta = readln()
        if (respuesta.lowercase(Locale.getDefault()) != "s") break

        val empresa = insertarObjetos.insertarEmpresa()
        gestorTablas.insertarEmpresa(empresa)
    }

    // Bucle para la inserción de empleados en la BD SQL>
    while (true) {
        println("¿Quieres insertar un empleado? (s/n)")
        val respuesta = readln()
        if (respuesta.lowercase(Locale.getDefault()) != "s") break

        val empleado = insertarObjetos.insertarEmpleado()
        gestorTablas.insertarEmpleado(empleado)
    }

    // Bucle para la inserción de nóminas en la BD SQL>
    while (true) {
        println("¿Quieres insertar una nómina? (s/n)")
        val respuesta = readln()
        if (respuesta.lowercase(Locale.getDefault()) != "s") break

        val nomina = insertarObjetos.insertarNomina()
        gestorTablas.insertarNomina(nomina)
    }

    // Genero BD en XML con los datos de las empresas, empleados y nóminas
    generadorXML.generarXML(insertarObjetos.empresas, insertarObjetos.empleados,insertarObjetos.nominas)

    // Desconexión de la base de datos
    conexionBD.desconectar()
}
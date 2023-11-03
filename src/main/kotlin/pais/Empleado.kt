package pais

import java.sql.Date

@Suppress("SpellCheckingInspection")
class Empleado(val dni: String, val nombre: String, val apellidos: String, val fechaNacimiento: Date,
               val puesto: String, val cif: String)
@file:Suppress("SpellCheckingInspection")

package pais

import java.math.BigDecimal

// creo la clase Nomina con sus parametros, me permite crear objetos de la clase Nómina.
class Nomina(val dni: String, val numeroSeguridadSocial: String, val nombre: String,
             val apellidos: String, val irpf: BigDecimal, val salarioBase: BigDecimal, val trienios: Int, val cif: String)
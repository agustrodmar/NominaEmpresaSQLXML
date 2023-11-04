@Suppress("SpellCheckingInspection")

/**
 * Clase de datos que representa una nómina de un empleado.
 *
 * @property numeroSeguridadSocial El número de la seguridad social del empleado.
 * @property nombre El nombre del empleado.
 * @property apellidos Los apellidos del empleado.
 * @property irpf El porcentaje de IRPF que se aplica al sueldo base del empleado.
 * @property sueldoBase El sueldo base del empleado antes de impuestos.
 * @property trienios El número de trienios que el empleado ha completado en la empresa.
 * @property dniEmpleado El DNI del empleado.
 */
data class Nomina(
    val numeroSeguridadSocial: String,
    val nombre: String,
    val apellidos: String,
    val irpf: Double,
    val sueldoBase: Double,
    val trienios: Int,
    val dniEmpleado: String
)

/**
 * Clase de datos para representar un empleado.
 *
 * @property dni El DNI del empleado.
 * @property nombre El nombre del empleado.
 * @property apellidos Los apellidos del empleado.
 * @property fechaNacimiento La fecha de nacimiento del empleado.
 * @property puestoTrabajo El puesto de trabajo del empleado.
 * @property empresaCif El CIF de la empresa a la que pertenece el empleado.
 */
@Suppress("SpellCheckingInspection")
data class Empleado(
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val puestoTrabajo: String,
    val empresaCif: String
)

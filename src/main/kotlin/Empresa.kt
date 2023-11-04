/**
 * Clase de datos para representar una empresa.
 *
 * @property cif El CIF de la empresa.
 * @property nombreEmpresa El nombre de la empresa.
 * @property direccion La dirección de la empresa.
 * @property fechaCreacion La fecha de creación de la empresa.
 */
@Suppress("SpellCheckingInspection")
data class Empresa(
    val cif: String,
    val nombreEmpresa: String,
    val direccion: String,
    val fechaCreacion: String
)
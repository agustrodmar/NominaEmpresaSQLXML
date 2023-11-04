import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Clase para gestionar la conexión a la base de datos.
 */
@Suppress("SpellCheckingInspection")
class ConexionBD {
    private val jdbcUrl = "jdbc:mysql://localhost:3306/"
    private val usuario = ""
    private val contrasena = ""
    var conexion: Connection? = null

    /**
     * Establece la conexión con la base de datos.
     */
    fun conectar() {
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver")

            // Establecer la conexión
            conexion = DriverManager.getConnection(jdbcUrl, usuario, contrasena)
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos: ${e.message}")
        } catch (e: Exception) {
            println("Error desconocido: ${e.message}")
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    fun desconectar() {
        try {
            conexion?.close()
        } catch (e: SQLException) {
            println("Error al desconectar de la base de datos: ${e.message}")
        }
    }
}
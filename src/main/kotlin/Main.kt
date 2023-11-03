@file:Suppress("SpellCheckingInspection")

package pais
import pais.Pais.TablaEmpleado
import pais.Pais.TablaEmpresa
import pais.Pais.TablaNomina
import java.math.BigDecimal
import java.sql.*

fun main() {

    // Datos de conexión
    val jdbcUrl = "jdbc:mysql://localhost:3306/curso_sql"
    val usuario = "root"
    val contrasena = "1234"
    var conexion: Connection? = null

    // Listas para guardar los objetos creados
    val empresas = mutableListOf<Empresa>()
    val empleados = mutableListOf<Empleado>()
    val nominas = mutableListOf<Nomina>()

    try {
        // Carga el controlador JDBC
        Class.forName("com.mysql.cj.jdbc.Driver")

        // Establece la conexión
        conexion = DriverManager.getConnection(jdbcUrl, usuario, contrasena)

        // Crea las tablas
        TablaEmpresa.crear(conexion)
        TablaEmpleado.crear(conexion)
        TablaNomina.crear(conexion)

        println("Las tablas 'Empresa', 'Empleado' y 'Nomina' se han creado con éxito.")

        // Crea un objeto Empleado y Nomina
        val empleado1 = Empleado("12345678Z", "Juan", "Pérez", Date.valueOf("1980-01-01"), "Gerente", "A12345678")
        val empleado2 = Empleado("23454632f", "Antonio", "Sánchez", Date.valueOf("1999-01-01"), "Presidente", "A12345678")
        val nomina1 = Nomina("12345678Z", "123456789012", "Juan", "Pérez", BigDecimal("15.00"), BigDecimal("20000.00"), 3, "A12345678")
        val nomina2 = Nomina("23454632f", "210987654321", "Antonio", "Sánchez", BigDecimal("25.00"), BigDecimal("35000.00"), 6, "A12345678")
        val laEmpresa = Empresa("324587", "Copinsa.SA", "Desengaño 21", Date.valueOf("2001-01-01"))

        // Añade los objetos a las listas correspondientes
        empleados.add(empleado1)
        empleados.add(empleado2)
        nominas.add(nomina1)
        nominas.add(nomina2)
        empresas.add(laEmpresa)

        // Inserta los objetos Empleado y Nomina en las tablas correspondientes
        TablaEmpleado.insertar(conexion, empleado1)
        TablaEmpleado.insertar(conexion, empleado2)
        TablaNomina.insertar(conexion, nomina1)
        TablaNomina.insertar(conexion, nomina2)
        TablaEmpresa.insertar(conexion, laEmpresa)

    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        conexion?.close()
    }

    // Llamo a mi clase para pasar los objetos que he insertado en SQL a XML
    convertirObjetosAXml(empresas, empleados, nominas)
}
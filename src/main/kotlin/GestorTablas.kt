@file:Suppress("SpellCheckingInspection")
import java.sql.SQLException
import java.sql.Statement

/**
 * Clase para gestionar las tablas de la base de datos.
 *
 * @property conexionBD La conexión a la base de datos.
 */
class GestorTablas(private val conexionBD: ConexionBD) {

    /**
     * Crea las tablas en la base de datos si no existen.
     */
    fun crearTablas() {
        var stmt: Statement? = null
        try {
            stmt = conexionBD.conexion!!.createStatement()

            val sqlEmpleado = "CREATE TABLE IF NOT EXISTS Empleado " +
                    "(dni VARCHAR(255) not NULL, " +
                    " nombre VARCHAR(255), " +
                    " apellidos VARCHAR(255), " +
                    " fechaNacimiento VARCHAR(255), " +
                    " puestoTrabajo VARCHAR(255), " +
                    " empresaCif VARCHAR(255), " +
                    " PRIMARY KEY ( dni ))"

            stmt.executeUpdate(sqlEmpleado)

            val sqlEmpresa = "CREATE TABLE IF NOT EXISTS Empresa " +
                    "(cif VARCHAR(255) not NULL, " +
                    " nombreEmpresa VARCHAR(255), " +
                    " direccion VARCHAR(255), " +
                    " fechaCreacion VARCHAR(255), " +
                    " PRIMARY KEY ( cif ))"

            stmt.executeUpdate(sqlEmpresa)

            val sqlNomina = "CREATE TABLE IF NOT EXISTS Nomina " +
                    "(numeroSeguridadSocial VARCHAR(255) not NULL, " +
                    " nombre VARCHAR(255), " +
                    " apellidos VARCHAR(255), " +
                    " irpf DOUBLE, " +
                    " sueldoBase DOUBLE, " +
                    " trienios INT, " +
                    " dni VARCHAR(255), " +
                    " PRIMARY KEY ( numeroSeguridadSocial ))"

            stmt.executeUpdate(sqlNomina)
        } catch (e: SQLException) {
            println("Error al crear las tablas: ${e.message}")
        } finally {
            try {
                stmt?.close()
            } catch (e: SQLException) {
                println("Error al cerrar el Statement: ${e.message}")
            }
        }
    }

    /**
     * Inserta un empleado en la tabla Empleado.
     *
     * @param empleado El empleado a insertar.
     */
    fun insertarEmpleado(empleado: Empleado) {
        val sql = "INSERT INTO Empleado (dni, nombre, apellidos, fechaNacimiento, puestoTrabajo, empresaCif) VALUES (?, ?, ?, ?, ?, ?)"
        val pstmt = conexionBD.conexion!!.prepareStatement(sql)
        try {
            pstmt.setString(1, empleado.dni)
            pstmt.setString(2, empleado.nombre)
            pstmt.setString(3, empleado.apellidos)
            pstmt.setString(4, empleado.fechaNacimiento)
            pstmt.setString(5, empleado.puestoTrabajo)
            pstmt.setString(6, empleado.empresaCif)

            pstmt.executeUpdate()
        } catch (e: SQLException) {
            println("Error al insertar el empleado: ${e.message}")
        } finally {
            try {
                pstmt?.close()
            } catch (e: SQLException) {
                println("Error al cerrar el PreparedStatement: ${e.message}")
            }
        }
    }

    /**
     * Inserta una nómina en la tabla Nomina.
     *
     * @param empresa La nómina a insertar.
     */
    fun insertarEmpresa(empresa: Empresa) {
        val sql = "INSERT INTO Empresa (cif, nombreEmpresa, direccion, fechaCreacion) VALUES (?, ?, ?, ?)"
        val pstmt = conexionBD.conexion!!.prepareStatement(sql)
        try {
            pstmt.setString(1, empresa.cif)
            pstmt.setString(2, empresa.nombreEmpresa)
            pstmt.setString(3, empresa.direccion)
            pstmt.setString(4, empresa.fechaCreacion)

            pstmt.executeUpdate()
        } catch (e: SQLException) {
            println("Error al insertar la empresa: ${e.message}")
        } finally {
            try {
                pstmt?.close()
            } catch (e: SQLException) {
                println("Error al cerrar el PreparedStatement: ${e.message}")
            }
        }
    }

    /**
     * Inserta una nómina en la tabla Nomina.
     *
     * @param nomina La nómina a insertar.
     */
    fun insertarNomina(nomina: Nomina) {
        val sql = "INSERT INTO Nomina (numeroSeguridadSocial, nombre, apellidos, irpf, sueldoBase, trienios, dni) VALUES (?, ?, ?, ?, ?, ?, ?)"
        val pstmt = conexionBD.conexion!!.prepareStatement(sql)
        try {
            pstmt.setString(1, nomina.numeroSeguridadSocial)
            pstmt.setString(2, nomina.nombre)
            pstmt.setString(3, nomina.apellidos)
            pstmt.setDouble(4, nomina.irpf)
            pstmt.setDouble(5, nomina.sueldoBase)
            pstmt.setInt(6, nomina.trienios)
            pstmt.setString(7, nomina.dniEmpleado)

            pstmt.executeUpdate()
        } catch (e: SQLException) {
            println("Error al insertar la nómina: ${e.message}")
        } finally {
            try {
                pstmt?.close()
            } catch (e: SQLException) {
                println("Error al cerrar el PreparedStatement: ${e.message}")
            }
        }
    }
}


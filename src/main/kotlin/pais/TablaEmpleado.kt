@file:Suppress("SpellCheckingInspection")

package pais.Pais

import pais.Empleado
import java.sql.Connection

object TablaEmpleado {
    // Creo la tabla empleado
    fun crear(conexion: Connection?) {
        val sql = """
            CREATE TABLE IF NOT EXISTS Empleado (
                DNI VARCHAR(9) PRIMARY KEY,
                Nombre VARCHAR(100),
                Apellidos VARCHAR(100),
                FechaNacimiento DATE,
                Puesto VARCHAR(100),
                CIF VARCHAR(9),
                FOREIGN KEY (CIF) REFERENCES Empresa(CIF)
            );
        """
        conexion?.createStatement()?.execute(sql)
    }
    // Inserto en la tabla empleado
    fun insertar(conexion: Connection?, empleado: Empleado) {
        val sql = """
            INSERT INTO Empleado (DNI, Nombre, Apellidos, FechaNacimiento, Puesto, CIF)
            VALUES (?, ?, ?, ?, ?, ?)
        """

        val preparedStatement = conexion?.prepareStatement(sql)
        preparedStatement?.setString(1, empleado.dni)
        preparedStatement?.setString(2, empleado.nombre)
        preparedStatement?.setString(3, empleado.apellidos)
        preparedStatement?.setDate(4, empleado.fechaNacimiento)
        preparedStatement?.setString(5, empleado.puesto)
        preparedStatement?.setString(6, empleado.cif)

        preparedStatement?.executeUpdate()
    }
}

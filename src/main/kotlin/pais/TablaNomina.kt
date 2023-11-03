package pais.Pais

import pais.Nomina
import java.sql.Connection

@Suppress("SpellCheckingInspection")
object TablaNomina {
    fun crear(conexion: Connection?) {
        val sql = """
            CREATE TABLE IF NOT EXISTS Nomina (
                NumeroSeguridadSocial VARCHAR(12) PRIMARY KEY,
                Nombre VARCHAR(100),
                Apellidos VARCHAR(100),
                IRPF DECIMAL(5, 2),
                SalarioBase DECIMAL(10, 2),
                Trienios INT,
                CIF VARCHAR(9),
                DNI VARCHAR(9),
                FOREIGN KEY (DNI) REFERENCES Empleado(DNI)
            );
        """
        conexion?.createStatement()?.execute(sql)
    }

    fun insertar(conexion: Connection?, nomina: Nomina) {
        val sql = """
            INSERT INTO Nomina (NumeroSeguridadSocial, Nombre, Apellidos, IRPF, SalarioBase, Trienios, CIF, DNI)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """

        val preparedStatement = conexion?.prepareStatement(sql)
        preparedStatement?.setString(1, nomina.numeroSeguridadSocial)
        preparedStatement?.setString(2, nomina.nombre)
        preparedStatement?.setString(3, nomina.apellidos)
        preparedStatement?.setBigDecimal(4, nomina.irpf)
        preparedStatement?.setBigDecimal(5, nomina.salarioBase)
        preparedStatement?.setInt(6, nomina.trienios)
        preparedStatement?.setString(7, nomina.cif)
        preparedStatement?.setString(8, nomina.dni)

        preparedStatement?.executeUpdate()
    }
}
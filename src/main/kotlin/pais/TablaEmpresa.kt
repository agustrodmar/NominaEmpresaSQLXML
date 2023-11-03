@file:Suppress("SpellCheckingInspection")

package pais.Pais

import pais.Empresa
import java.sql.Connection

// Uso un singleton para crear la tabla ya que no voy a crear nuevas instancias de esta clase.
object TablaEmpresa {
    // Creo la tabla Empresa
    fun crear(conexion: Connection?) {
        val sql = """
            CREATE TABLE IF NOT EXISTS Empresa (
                CIF VARCHAR(9) PRIMARY KEY,
                Nombre VARCHAR(100),
                Direccion VARCHAR(200),
                FechaFundacion DATE
            );
        """
        conexion?.createStatement()?.execute(sql)
    }
    // Inserto en la tabla Empresa
    fun insertar(conexion: Connection?, empresa: Empresa) {
        val sql = """
            INSERT INTO Empresa (CIF, Nombre, Direccion, FechaFundacion)
            VALUES (?, ?, ?, ?)
       """

        val preparedStatement = conexion?.prepareStatement(sql)
        preparedStatement?.setString(1, empresa.cif)
        preparedStatement?.setString(2, empresa.nombre)
        preparedStatement?.setString(3, empresa.direccion)
        preparedStatement?.setDate(4, empresa.FechaFundacion)
    }
}
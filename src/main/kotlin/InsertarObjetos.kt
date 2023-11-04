
/**
 * Clase que permite la inserción de objetos de tipo Empresa, Empleado y Nomina.
 *
 * @property empresas Lista mutable de objetos Empresa.
 * @property empleados Lista mutable de objetos Empleado.
 * @property nominas Lista mutable de objetos Nomina.
 */
@Suppress("SpellCheckingInspection")
class InsertarObjetos {
    val empresas = mutableListOf<Empresa>()
    val empleados = mutableListOf<Empleado>()
    val nominas = mutableListOf<Nomina>()

    /**
     * Función para insertar una nueva Empresa.
     *
     * @return La Empresa insertada.
     */
    fun insertarEmpresa(): Empresa {
        // Solicita al gestor los datos de la empresa
        println("Introduce el CIF de la empresa:")
        val cif = readln()
        println("Introduce el nombre de la empresa:")
        val nombreEmpresa = readln()
        println("Introduce la dirección de la empresa:")
        val direccion = readln()
        println("Introduce la fecha de creación de la empresa (AAAA-MM-DD):")
        val fechaCreacion = readln()

        // Crea el objeto Empresa y lo añade a la lista de empresas
        val empresa = Empresa(cif, nombreEmpresa, direccion, fechaCreacion)
        empresas.add(empresa)
        return empresa
    }

    /**
     * Función para insertar un nuevo Empleado.
     *
     * @return El Empleado insertado.
     */
    fun insertarEmpleado(): Empleado {
        // Solicita al gestor los datos del empleado
        println("Introduce el DNI del empleado:")
        val dni = readln()
        println("Introduce el nombre del empleado:")
        val nombre = readln()
        println("Introduce los apellidos del empleado:")
        val apellidos = readln()
        println("Introduce la fecha de nacimiento del empleado (AAAA-MM-DD):")
        val fechaNacimiento = readln()
        println("Introduce el puesto de trabajo del empleado:")
        val puestoTrabajo = readln()
        println("Introduce el CIF de la empresa del empleado:")
        val empresaCif = readln()

        // Crea el objeto Empleado y lo añade a la lista de empleados
        val empleado = Empleado(dni, nombre, apellidos, fechaNacimiento, puestoTrabajo, empresaCif)
        empleados.add(empleado)
        return empleado
    }

    /**
     * Función para insertar una nueva Nomina.
     *
     * @return La Nomina insertada.
     */

    fun insertarNomina(): Nomina {
        // Solicita al gestor los datos de la nómina
        println("Introduce el Número de la Seguridad Social del Empleado")
        val numeroSeguridadSocial = readln()
        println("Introduce el nombre al que figura la nómina: ")
        val nombre = readln()
        println("Introduce los apellidos que figuran en la nómina: ")
        val apellidos = readln()
        println("Introduce el porcentaje de IRPF: ")
        val irpf = readln().toDouble()
        println("Introduzca el sueldo base que figura en la nómina: ")
        val sueldoBase = readln().toDouble()
        println("Inserte el número de trienios que figuran en la nómina: ")
        val trienios = readln().toInt()
        println("Introduzca el dni del empleado al que corresponde la nómina: ")
        val dniEmpleado = readln()

        // Crea el objeto Nomina y lo añade a la lista de nóminas
        val nomina = Nomina(numeroSeguridadSocial, nombre, apellidos, irpf, sueldoBase, trienios, dniEmpleado)
        nominas.add(nomina)
        return nomina
    }
}

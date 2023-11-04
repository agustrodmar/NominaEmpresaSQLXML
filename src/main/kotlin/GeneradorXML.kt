@file:Suppress("SpellCheckingInspection")

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Element

/**
 * Clase para generar un archivo XML a partir de listas de empresas, empleados y nóminas.
 */
class GeneradorXML {

    /**
     * Genera un archivo XML a partir de listas de empresas, empleados y nóminas.
     *
     * @param empresas La lista de empresas.
     * @param empleados La lista de empleados.
     * @param nominas La lista de nóminas.
     */
    fun generarXML(empresas: List<Empresa>, empleados: List<Empleado>, nominas: List<Nomina>) {
        val docBuilderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = docBuilderFactory.newDocumentBuilder()
        val doc: Document = docBuilder.newDocument()

        // Crear el elemento raíz "empresas"
        val rootElement: Element = doc.createElement("empresas")
        doc.appendChild(rootElement)

        // Iterar sobre cada empresa
        for (empresa in empresas) {
            // Crear un elemento "empresa" y añadir sus atributos
            val empresaElement: Element = doc.createElement("empresa")
            empresaElement.setAttribute("cif", empresa.cif)
            empresaElement.setAttribute("nombreEmpresa", empresa.nombreEmpresa)
            empresaElement.setAttribute("direccion", empresa.direccion)
            empresaElement.setAttribute("fechaCreacion", empresa.fechaCreacion)

            // Filtrar los empleados que pertenecen a esta empresa
            val empleadosEmpresa = empleados.filter { it.empresaCif == empresa.cif }

            // Iterar sobre cada empleado de la empresa
            for (empleado in empleadosEmpresa) {
                // Crear un elemento "empleado" y añadir sus atributos
                val empleadoElement: Element = doc.createElement("empleado")
                empleadoElement.setAttribute("dni", empleado.dni)
                empleadoElement.setAttribute("nombre", empleado.nombre)
                empleadoElement.setAttribute("apellidos", empleado.apellidos)
                empleadoElement.setAttribute("fechaNacimiento", empleado.fechaNacimiento)
                empleadoElement.setAttribute("puestoTrabajo", empleado.puestoTrabajo)
                empleadoElement.setAttribute("empresaCif", empleado.empresaCif)

                // Filtrar las nóminas que pertenecen a este empleado
                val nominasEmpleado = nominas.filter { it.dniEmpleado == empleado.dni }

                // Iterar sobre cada nómina del empleado
                for (nomina in nominasEmpleado) {
                    // Crear un elemento "nomina" y añadir sus atributos
                    val nominaElement: Element = doc.createElement("nomina")
                    nominaElement.setAttribute("numeroSeguridadSocial", nomina.numeroSeguridadSocial)
                    nominaElement.setAttribute("nombre", nomina.nombre)
                    nominaElement.setAttribute("apellidos", nomina.apellidos)
                    nominaElement.setAttribute("irpf", nomina.irpf.toString())
                    nominaElement.setAttribute("sueldoBase", nomina.sueldoBase.toString())
                    nominaElement.setAttribute("trienios", nomina.trienios.toString())
                    nominaElement.setAttribute("dniEmpleado", nomina.dniEmpleado)

                    // Añadir el elemento "nomina" al elemento "empleado"
                    empleadoElement.appendChild(nominaElement)
                }

                // Añadir el elemento "empleado" al elemento "empresa"
                empresaElement.appendChild(empleadoElement)
            }

            // Añadir el elemento "empresa" al elemento raíz "empresas"
            rootElement.appendChild(empresaElement)
        }

        // Configurar y ejecutar la transformación de Document a XML
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
        val source = DOMSource(doc)
        val result = StreamResult("output.xml")
        transformer.transform(source, result)
    }
}

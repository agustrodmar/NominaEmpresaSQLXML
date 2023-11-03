@file:Suppress("SpellCheckingInspection")

package pais

import java.io.File
import java.io.FileOutputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


fun convertirObjetosAXml(empresas: List<Empresa>, empleados: List<Empleado>, nominas: List<Nomina>) {
    val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val doc = docBuilder.newDocument()

    // Crear el elemento raíz
    val rootElement = doc.createElement("Datos")
    doc.appendChild(rootElement)

    // Crear el elemento Empresas
    val empresasElement = doc.createElement("Empresas")
    rootElement.appendChild(empresasElement)

    // Añadir empresas al documento XML
    for (empresa in empresas) {
        val empresaElement = doc.createElement("Empresa")
        empresasElement.appendChild(empresaElement)

        // Añadir los datos de la empresa
        empresaElement.setAttribute("CIF", empresa.cif)
        empresaElement.setAttribute("Nombre", empresa.nombre)
        empresaElement.setAttribute("Direccion", empresa.direccion)
        empresaElement.setAttribute("FechaFundacion", empresa.FechaFundacion.toString())

        // Crear los elementos Empleados y Nominas
        val empleadosElement = doc.createElement("Empleados")
        val nominasElement = doc.createElement("Nominas")
        empresaElement.appendChild(empleadosElement)
        empresaElement.appendChild(nominasElement)

        // Añadir empleados a la empresa correspondiente
        for (empleado in empleados.filter { it.cif == empresa.cif }) {
            val empleadoElement = doc.createElement("Empleado")
            empleadosElement.appendChild(empleadoElement)

            // Añadir los datos del empleado como elementos hijos
            empleadoElement.appendChild(doc.createElement("DNI").apply { textContent = empleado.dni })
            empleadoElement.appendChild(doc.createElement("Nombre").apply { textContent = empleado.nombre })
            empleadoElement.appendChild(doc.createElement("Apellidos").apply { textContent = empleado.apellidos })
            empleadoElement.appendChild(doc.createElement("FechaNacimiento").apply { textContent = empleado.fechaNacimiento.toString() })
            empleadoElement.appendChild(doc.createElement("Puesto").apply { textContent = empleado.puesto })
            empleadoElement.appendChild(doc.createElement("CIF").apply { textContent = empleado.cif })
        }

        // Añadir nominas a la empresa correspondiente
        for (nomina in nominas.filter { it.cif == empresa.cif }) {
            val nominaElement = doc.createElement("Nomina")
            nominasElement.appendChild(nominaElement)

            // Añadir los datos de la nomina como elementos hijos
            nominaElement.appendChild(doc.createElement("DNI").apply { textContent = nomina.dni })
            nominaElement.appendChild(doc.createElement("NumeroSeguridadSocial").apply { textContent = nomina.numeroSeguridadSocial })
            nominaElement.appendChild(doc.createElement("Nombre").apply { textContent = nomina.nombre })
            nominaElement.appendChild(doc.createElement("Apellidos").apply { textContent = nomina.apellidos })
            nominaElement.appendChild(doc.createElement("IRPF").apply { textContent = nomina.irpf.toString() })
            nominaElement.appendChild(doc.createElement("SalarioBase").apply { textContent = nomina.salarioBase.toString() })
            nominaElement.appendChild(doc.createElement("Trienios").apply { textContent = nomina.trienios.toString() })
            nominaElement.appendChild(doc.createElement("CIF").apply { textContent = nomina.cif })
        }
    }

    // Guardo el documento XML en un archivo
    val transformerFactory = TransformerFactory.newInstance()
    val transformer = transformerFactory.newTransformer()
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2") // 2 espacios.
    val source = DOMSource(doc)
    val result = StreamResult(FileOutputStream(File("datos.xml")))
    transformer.transform(source, result)
}

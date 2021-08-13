package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado

interface IEmpleadoBusiness {
    fun getEmpleado():List<Empleado>
    fun getEmpleadoById(idempleado:Long): Empleado
    fun saveEmpleado(empleado: Empleado): Empleado
    fun removeEmpleado(idempleado:Long)
    fun getEmpleadoByNombre(nombreempleado: String): Empleado
    fun updateEmpleado(empleado: Empleado): Empleado
}
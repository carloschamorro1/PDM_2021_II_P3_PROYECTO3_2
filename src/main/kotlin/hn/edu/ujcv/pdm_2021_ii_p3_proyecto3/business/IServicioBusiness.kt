package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Servicio

interface IServicioBusiness {
    fun getServicio():List<Servicio>
    fun getServicioById(id:Long): Servicio
    fun saveServicio(servicio: Servicio): Servicio
    fun removeServicio(idservicio:Long)
    fun getServicioByNombre(nombreservicio: String): Servicio
    fun updateSercicio(servicio: Servicio): Servicio
}
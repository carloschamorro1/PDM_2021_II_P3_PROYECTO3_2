package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado


interface ICasoEmpleadoBusiness {
    fun getCasoEmpleado():List<CasoEmpleado>
    fun getCasoEmpleadoById(idcasoempleado:Long): CasoEmpleado
    fun saveCasoEmpleado(casoempleado: CasoEmpleado): CasoEmpleado
    fun removeCasoEmpleado(idcasoempleado:Long)
    fun updateCasoEmpleado(casoempleado: CasoEmpleado):CasoEmpleado
}//
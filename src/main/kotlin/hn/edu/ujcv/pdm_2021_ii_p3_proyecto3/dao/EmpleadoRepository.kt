package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmpleadoRepository:JpaRepository<Empleado,Long> {
    fun findBynombreempleado(nombreempleado:String): Optional<Empleado>
}
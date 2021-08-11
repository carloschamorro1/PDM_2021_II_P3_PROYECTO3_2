package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FacturaDetalleRepository:JpaRepository<FacturaDetalle,Long> {
    fun findByidfactura(idfactura:Long): Optional<FacturaDetalle>

}
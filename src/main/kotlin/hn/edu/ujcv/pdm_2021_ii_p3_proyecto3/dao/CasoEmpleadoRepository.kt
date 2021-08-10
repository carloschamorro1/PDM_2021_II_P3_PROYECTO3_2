package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado
import org.springframework.data.jpa.repository.JpaRepository

interface CasoEmpleadoRepository: JpaRepository<CasoEmpleado, Long> {
}
//
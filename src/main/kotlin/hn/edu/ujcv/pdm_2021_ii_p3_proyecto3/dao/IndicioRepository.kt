package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IndicioRepository:JpaRepository<Indicio,Long> {
    fun findByidcaso(idcaso:Long): Optional<Indicio>
}
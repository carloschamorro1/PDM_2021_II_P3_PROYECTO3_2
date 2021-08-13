package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PrecioHistoricoRepository:JpaRepository<PrecioHistorico,Long> {
    fun findByidpreciohistorico(idpreciohistorico:Long): Optional<PrecioHistorico>
}
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CAI
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CAIRepository : JpaRepository<CAI, Long> {
    fun findByidcai(idcai:Long): Optional<CAI>
}
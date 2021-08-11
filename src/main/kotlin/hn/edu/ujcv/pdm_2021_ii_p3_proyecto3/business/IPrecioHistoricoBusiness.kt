package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico

interface IPrecioHistoricoBusiness {
    fun getPrecioHistorico():List<PrecioHistorico>
    fun getPrecioHistoricoById(idpreciohistorico:Long): PrecioHistorico
    fun savePrecioHistorico(preciohistorico: PrecioHistorico): PrecioHistorico
    fun removePrecioHistorico(idpreciohistorico:Long)
    fun updatePrecioHistorico(preciohistorico: PrecioHistorico): PrecioHistorico
}
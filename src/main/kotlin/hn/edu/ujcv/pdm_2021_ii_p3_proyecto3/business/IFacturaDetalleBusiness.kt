package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle

interface IFacturaDetalleBusiness {
    fun getFacturaDetalle():List<FacturaDetalle>
    fun getFacturaDetalleById(iddetalle:Long): FacturaDetalle
    fun saveFacturaDetalle(facturadetalle: FacturaDetalle): FacturaDetalle
    fun removeFacturaDetalle(iddetalle:Long)
    fun updateFacturaDetalle(facturadetalle: FacturaDetalle): FacturaDetalle
}
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaEncabezado

interface IFacturaEncabezadoBusiness {
    fun getFacturaEncabezado():List<FacturaEncabezado>
    fun getFacturaEncabezadoById(idfactura:Long): FacturaEncabezado
    fun saveFacturaEncabezado(facturaencabezado: FacturaEncabezado): FacturaEncabezado
    fun removeFacturaEncabezado(idfactura:Long)
    fun updateFacturaEncabezado(facturaencabezado: FacturaEncabezado): FacturaEncabezado
}
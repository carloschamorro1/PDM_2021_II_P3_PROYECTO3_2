package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business


import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal

interface ISucursalBusiness {
    fun getSucursal():List<Sucursal>
    fun getSucursalById(idsucursal:Long): Sucursal
    fun saveSucursal(sucursal: Sucursal): Sucursal
    fun removeSucursal(idsucursal:Long)
    fun getSucursalByNombre(nombresucursal: String): Sucursal
    fun updateSucursal(sucursal: Sucursal): Sucursal
}

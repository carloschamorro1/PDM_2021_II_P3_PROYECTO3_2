package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.FacturaDetalleRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FacturaDetalleBusiness:IFacturaDetalleBusiness {

    @Autowired
    val facturaDetalleRepository: FacturaDetalleRepository?= null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaDetalle(): List<FacturaDetalle> {
        try{
            return facturaDetalleRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaDetalleById(iddetalle: Long): FacturaDetalle {
        val opt:Optional<FacturaDetalle>
        try{
            opt = facturaDetalleRepository!!.findById(iddetalle)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura $iddetalle")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveFacturaDetalle(facturadetalle: FacturaDetalle): FacturaDetalle {
        try{
        validarEspacios(facturadetalle)
        validarLongitudMaxima(facturadetalle)
        return facturaDetalleRepository!!.save(facturadetalle)
             }catch (e:Exception){
        throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFacturaDetalle(idfactura: Long) {
        val opt:Optional<List<FacturaDetalle>>
        try{
            opt = facturaDetalleRepository!!.findByidfactura(idfactura)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura $idfactura")
        }
        else{
            try{
                facturaDetalleRepository!!.deleteByidfactura(idfactura)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFacturaDetalle(facturadetalle: FacturaDetalle): FacturaDetalle {
        val opt:Optional<FacturaDetalle>
        try{
            opt = facturaDetalleRepository!!.findById(facturadetalle.iddetalle)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura ${facturadetalle.iddetalle}")
        }
        else{
            try{
                validarLongitudMaxima(facturadetalle)
                return facturaDetalleRepository!!.save(facturadetalle)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    fun validarEspacios(facturaDetalle: FacturaDetalle){
        if(facturaDetalle.iddetalle.toString().isEmpty()){
            throw BusinessException("El id del detalle de la factura no debe estar vacío")
        }
        if(facturaDetalle.idfactura.toString().isEmpty()){
            throw BusinessException("El id de la factura no debe estar vacío")
        }
        if(facturaDetalle.idservicio.toString().isEmpty()){
            throw BusinessException("El id del servicio no debe estar vacío")
        }

        if(facturaDetalle.cantidadfactura.isEmpty()){
            throw BusinessException("La cantidad no puede estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(facturaDetalle: FacturaDetalle){
        if(facturaDetalle.iddetalle.toString().length > 10){
            throw BusinessException("El id del detalle de la factura no puede ser mayor a 10 dígitos")
        }
        if(facturaDetalle.idfactura.toString().length > 10){
            throw BusinessException("El id de la factura no puede ser mayor a 10 dígitos")
        }
        if(facturaDetalle.idservicio.toString().length > 10){
            throw BusinessException("El id del servicio no puede ser mayor a 10 dígitos")
        }

        if(facturaDetalle.cantidadfactura.length > 2){
            throw BusinessException("La cantidad no puede ser mayor a 2")
        }
    }
}
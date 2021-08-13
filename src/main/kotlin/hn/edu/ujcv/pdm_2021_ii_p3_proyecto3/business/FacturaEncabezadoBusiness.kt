package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.FacturaEncabezadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaEncabezado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class FacturaEncabezadoBusiness:IFacturaEncabezadoBusiness {

    @Autowired
    val facturaEncabezadoRepository: FacturaEncabezadoRepository?=null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaEncabezado(): List<FacturaEncabezado> {
        try{
            return facturaEncabezadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaEncabezadoById(idfactura: Long): FacturaEncabezado {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(idfactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el encabezado de la factura $idfactura")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveFacturaEncabezado(facturaencabezado: FacturaEncabezado): FacturaEncabezado {
        try{
            validarEspacios(facturaencabezado)
            validarLongitud(facturaencabezado)
            validarLongitudMaxima(facturaencabezado)
            validarFecha(facturaencabezado.fechaemisionfactura)
            return facturaEncabezadoRepository!!.save(facturaencabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFacturaEncabezado(idfactura: Long) {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(idfactura)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el encabezado de la factura $idfactura")
        }
        else{
            try{
                facturaEncabezadoRepository!!.deleteById(idfactura)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFacturaEncabezado(facturaencabezado: FacturaEncabezado): FacturaEncabezado {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(facturaencabezado.idfactura)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura ${facturaencabezado.idfactura}")
        }
        else{
            try{
                validarEspacios(facturaencabezado)
                validarLongitud(facturaencabezado)
                validarLongitudMaxima(facturaencabezado)
                validarFecha(facturaencabezado.fechaemisionfactura)
                return facturaEncabezadoRepository!!.save(facturaencabezado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(facturaencabezado: FacturaEncabezado){
        if(facturaencabezado.fechaemisionfactura.isEmpty()){
            throw BusinessException("La fecha de emisión no debe estar vacía")
        }
        if(facturaencabezado.idcai.toString().isEmpty()){
            throw BusinessException("El ID del CAI no debe estar vacío")
        }
        if(facturaencabezado.idsucursal.toString().isEmpty()){
            throw BusinessException("El ID de la sucursal no debe estar vacío")
        }
        if(facturaencabezado.totalfactura.toString().isEmpty()){
            throw BusinessException("El total de la factura no debe estar vacío")
        }
        if(facturaencabezado.idempleado.toString().isEmpty()){
            throw BusinessException("El ID del empleado no debe estar vacío")
        }
        if(facturaencabezado.idcaso.toString().isEmpty()){
            throw BusinessException("La direccion del cliente no debe estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(facturaencabezado: FacturaEncabezado){
        if(facturaencabezado.fechaemisionfactura.length != 10){
            throw BusinessException("La fecha no puede ser distinta de 10 caracteres")
        }
        if(facturaencabezado.totalfactura.toString().length < 3){
            throw BusinessException("El total de la factura no puede ser menor a 3 dígitos")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(facturaencabezado: FacturaEncabezado){
        if(facturaencabezado.idcai.toString().length > 10){
            throw BusinessException("El ID del CAI no puede ser mayor a 10 caracteres")
        }
        if(facturaencabezado.idsucursal.toString().length > 10){
            throw BusinessException("El dni no puede ser mayor a 10 caracteress")
        }
        if(facturaencabezado.totalfactura.toString().length > 18){
            throw BusinessException("El total de la factura no puede ser distinto a 18 dígitos")
        }
        if(facturaencabezado.idempleado.toString().length > 10){
            throw BusinessException("El ID del empleado no puede ser mayor a 10 caracteres")
        }
        if(facturaencabezado.idcaso.toString().length > 10){
            throw BusinessException("La ID del caso no puede ser mayor a 10 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarFecha(fecha:String) {
        val format = SimpleDateFormat("yyyy-mm-dd")
        if (fecha.length == 10) {
            try{
                format.parse(fecha)
            }catch(e: Exception){
                throw BusinessException("No ha ingresado una fecha valida, solo se pueden valores de fechas entre 1900 - 2099")
            }
            val regex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])"
            val pattern: Pattern = Pattern.compile(regex)
            val matcher: Matcher = pattern.matcher(fecha)
            if (matcher.matches()) {
                return
            } else {
                throw BusinessException("El formato de fecha es incorrecto, debe ingresarlo (dd/mm/yyyy)")

            }
        }
    }
}
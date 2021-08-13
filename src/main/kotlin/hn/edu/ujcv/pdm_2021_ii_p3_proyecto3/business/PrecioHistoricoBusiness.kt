package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.PrecioHistoricoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class PrecioHistoricoBusiness:IPrecioHistoricoBusiness {

    @Autowired
    val precioHistoricoRepository: PrecioHistoricoRepository?= null

    @Throws(BusinessException::class)
    override fun getPrecioHistorico(): List<PrecioHistorico> {
        try{
           return precioHistoricoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getPrecioHistoricoById(idpreciohistorico: Long): PrecioHistorico {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(idpreciohistorico)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del precio historico: $idpreciohistorico")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun savePrecioHistorico(preciohistorico: PrecioHistorico): PrecioHistorico {
        try{
            validarEspacios(preciohistorico)
            validarLongitud(preciohistorico)
            validarLongitudMaxima(preciohistorico)
            validarFecha(preciohistorico.fechainicialpreciohistorico)
            validarFecha(preciohistorico.fechafinalpreciohistorico)
            return precioHistoricoRepository!!.save(preciohistorico)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removePrecioHistorico(idpreciohistorico: Long) {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(idpreciohistorico)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el id del precio historico $idpreciohistorico")
        }
        else{
            try{
                precioHistoricoRepository!!.deleteById(idpreciohistorico)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updatePrecioHistorico(preciohistorico: PrecioHistorico): PrecioHistorico {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(preciohistorico.idpreciohistorico)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el id del precio historico ${preciohistorico.idpreciohistorico}")
        }
        else{
            try{
                validarEspacios(preciohistorico)
                validarLongitud(preciohistorico)
                validarLongitudMaxima(preciohistorico)
                validarFecha(preciohistorico.fechainicialpreciohistorico)
                validarFecha(preciohistorico.fechafinalpreciohistorico)
                return precioHistoricoRepository!!.save(preciohistorico)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(preciohistorico: PrecioHistorico){
        if(preciohistorico.fechainicialpreciohistorico.isEmpty()){
            throw BusinessException("La fecha inicial no debe estar vacía")
        }
        if(preciohistorico.fechafinalpreciohistorico.isEmpty()){
            throw BusinessException("La fecha final no debe estar vacía")
        }
        if(preciohistorico.idservicio.toString().isEmpty()){
            throw BusinessException("El ID del servicio no debe estar vacío")
        }
        if(preciohistorico.precio.toString().isEmpty()){
            throw BusinessException("El precio no debe estar vacío")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(preciohistorico: PrecioHistorico){
        if(preciohistorico.fechainicialpreciohistorico.length != 10){
            throw BusinessException("La fecha inicial no puede ser distinta a 8 dígitos")
        }
        if(preciohistorico.fechafinalpreciohistorico.length != 10){
            throw BusinessException("La fecha final no puede ser distinta a 8 dígitos")
        }
        if(preciohistorico.precio.toString().length < 3){
            throw BusinessException("El precio no puede ser menor a 3 dígitos")
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

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(precioHistorico: PrecioHistorico){
        if(precioHistorico.precio.toString().length > 12){
            throw BusinessException("El precio no puede ser mayor a 12 dígitos")
        }
    }
}
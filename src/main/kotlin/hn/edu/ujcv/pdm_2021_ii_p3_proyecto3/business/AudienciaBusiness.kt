package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.AudienciaRepository

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class AudienciaBusiness: IAudienciaBusiness {
    @Autowired
    val audienciaRepository: AudienciaRepository?=null
    @Throws(BusinessException::class)
    override fun getAudiencia(): List<Audiencia> {
        try{
            return audienciaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getAudienciaById(idcaso: Long): Audiencia{
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findById(idcaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia $idcaso")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveAudiencia(audiencia: Audiencia): Audiencia {
        try{
            validarEspacios(audiencia)
            validarLongitud(audiencia)
            validarLongitudMaxima(audiencia)
            validarFecha(audiencia.fechaaudiencia.toString())
            return audienciaRepository!!.save(audiencia)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeAudiencia(idcaso: Long) {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findById(idcaso)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la audiencia $idcaso")
        }
        else{
            try{
                audienciaRepository!!.deleteById(idcaso)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getAudienciaByidCaso(idcaso: Long): Audiencia {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findByidcaso(idcaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia$idcaso")
        }
        return opt.get()
    }

    override fun updateAudiencia(audiencia: Audiencia): Audiencia {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findByidcaso(audiencia.idfechaaudiencia)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia ${audiencia.idfechaaudiencia}")
        }
        else{
            try{
                validarEspacios(audiencia)
                validarLongitud(audiencia)
                validarLongitudMaxima(audiencia)
                validarFecha(audiencia.fechaaudiencia.toString())
                return audienciaRepository!!.save(audiencia)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
     //VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(audiencia: Audiencia){
        if(audiencia.idfechaaudiencia.toString().isEmpty()){
            throw BusinessException("El id de la fecha no debe estar vacío")
        }
        if(audiencia.idcaso.toString().isEmpty()){
            throw BusinessException("El id del caso no debe estar vacío")
        }
        if(audiencia.fechaaudiencia.toString().isEmpty()){
            throw BusinessException("La fecha no debe estar vacía")
        }
        if(audiencia.idjuzgado.toString().isEmpty()){
            throw BusinessException("El id del juzgado no debe estar vacío")
        }
        if(audiencia.descripcionaudiencia.isEmpty()){
            throw BusinessException("La descripcion no debe estar vacía")
        }

    }
    @Throws(BusinessException::class)
    fun validarLongitud(audiencia: Audiencia){
        if(audiencia.fechaaudiencia.toString().length!=10){
            throw BusinessException("La fecha  no puede ser distinto a 10 caracteres ")
        }
        if(audiencia.descripcionaudiencia.length < 10){
            throw BusinessException("La descripcion no puede ser menor a 10 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(audiencia: Audiencia){
        if(audiencia.idcaso.toString().length >10){
            throw BusinessException("El ID del caso no puede ser mayor a 10 digitos")

        }
        if(audiencia.idjuzgado.toString().length >10){
            throw BusinessException("El ID del juzgado no puede ser mayor a 10 digitos")

        }
        if(audiencia.fechaaudiencia.toString().length!=10){
            throw BusinessException("La fecha  no puede ser distinto a 10 caracteres ")
        }
        if(audiencia.descripcionaudiencia.length > 100){
            throw BusinessException("La descripcion no puede ser mayor a 100 caracteres")
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
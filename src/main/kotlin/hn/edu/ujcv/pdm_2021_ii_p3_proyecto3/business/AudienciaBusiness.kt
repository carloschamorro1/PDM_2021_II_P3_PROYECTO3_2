package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.AudienciaRepository

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

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
        if(audiencia.idfechaaudiencia.toString().length<8){
            throw BusinessException("La fecha no puede ser menor a 8 caracteres")
        }
        if(audiencia.idcaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 caracteres")
        }
        if(audiencia.fechaaudiencia.toString().length<3){
            throw BusinessException("La fecha  no puede ser menor a 3 caracteres ")
        }
        if(audiencia.idjuzgado.toString().length<8){
            throw BusinessException("El id del juzgado no puede ser menor a 8 caracteres")
        }
        if(audiencia.descripcionaudiencia.length<3){
            throw BusinessException("La descripcion no puede ser menor a 3 caracteres")
        }

    }
}
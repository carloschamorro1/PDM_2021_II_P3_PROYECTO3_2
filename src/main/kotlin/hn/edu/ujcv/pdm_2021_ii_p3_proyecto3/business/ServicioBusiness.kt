package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.ServicioRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Servicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServicioBusiness: IServicioBusiness {
    @Autowired
    val servicioRepository: ServicioRepository? = null

    @Throws(BusinessException::class)
    override fun getServicio(): List<Servicio> {
        try {
            return servicioRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getServicioById(idservicio: Long): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(idservicio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Servicio $idservicio")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveServicio(servicio: Servicio): Servicio {
        try{
            validarEspacios(servicio)
            validarLongitud(servicio)
            validarLongitudMaxima(servicio)
            return servicioRepository!!.save(servicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeServicio(idservicio: Long) {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(idservicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Servicio : $idservicio")
        }
        else{
            try{
                servicioRepository!!.deleteById(idservicio)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getServicioByNombre(nombreservicio: String): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findBynombreservicio(nombreservicio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Servicio $nombreservicio")
        }
        return opt.get()
    }

    override fun updateSercicio(servicio: Servicio): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(servicio.idservicio)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Servicio: ${servicio.idservicio}")
        }
        else{
            try{
                validarEspacios(servicio)
                validarLongitud(servicio)
                validarLongitudMaxima(servicio)
                return servicioRepository!!.save(servicio)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }


    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(servicio: Servicio){
        if(servicio.nombreservicio.isEmpty()){
            throw BusinessException("El nombre del servicio no debe estar vacío")
        }
        if(servicio.descripcionservicio.isEmpty()){
            throw BusinessException("La descripcion no debe estar vacío")
        }

    }

    @Throws(BusinessException::class)
    fun validarLongitud(servicio: Servicio) {
        if (servicio.nombreservicio.length < 4) {
            throw BusinessException("El nombre no puede ser menor a 4 caracteres")
        }
        if (servicio.descripcionservicio.length < 10) {
            throw BusinessException("La descripcion no puede ser menor a 10 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(servicio: Servicio) {
        if (servicio.nombreservicio.length > 30) {
            throw BusinessException("El nombre no puede ser mayor a 30 caracteres")
        }
        if (servicio.descripcionservicio.length > 50) {
            throw BusinessException("La descripcion no puede ser mayor a 50 caracteres")
        }
    }

}
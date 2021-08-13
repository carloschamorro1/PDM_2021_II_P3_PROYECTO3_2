package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.SucursalRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SucursalBusiness: ISucursalBusiness {

    @Autowired
    val sucursalRepository: SucursalRepository?=null

    @Throws(BusinessException::class)
    override fun getSucursal(): List<Sucursal> {
        try{
            return sucursalRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSucursalById(idsucursal: Long): Sucursal {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(idsucursal)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal $idsucursal")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveSucursal(sucursal: Sucursal): Sucursal {
        try{
            validarEspacios(sucursal)
            validarLongitud(sucursal)
            validarLongitudMaxima(sucursal)
            return sucursalRepository!!.save(sucursal)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeSucursal(idsucursal: Long) {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(idsucursal)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la sucursal $idsucursal")
        }
        else{
            try{
                sucursalRepository!!.deleteById(idsucursal)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getSucursalByNombre(nombresucursal: String): Sucursal {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findBynombresucursal(nombresucursal)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal $nombresucursal")
        }
        return opt.get()
    }

    override fun updateSucursal(sucursal: Sucursal): Sucursal{
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(sucursal.idsucursal)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal ${sucursal.idsucursal}")
        }
        else{
            try{
                validarEspacios(sucursal)
                validarLongitud(sucursal)
                validarLongitudMaxima(sucursal)
                return sucursalRepository!!.save(sucursal)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

//VALIDACIONES


    @Throws(BusinessException::class)
fun validarEspacios(sucursal: Sucursal){
    if(sucursal.idsucursal.toString().isEmpty()){
        throw BusinessException("El id de la sucursal no debe estar vacío")
    }
    if(sucursal.nombresucursal.isEmpty()){
        throw BusinessException("El nombre no debe estar vacío")
    }
    if(sucursal.direccionsucursal.isEmpty()){
        throw BusinessException("La direccion no debe estar vacío")
    }
    if(sucursal.telefonosucursal.toString().isEmpty()){
        throw BusinessException("El telefono no debe estar vacío")
    }
    if(sucursal.emailsucursal.isEmpty()){
        throw BusinessException("El email no debe estar vacío")
    }
}
    @Throws(BusinessException::class)
    fun validarLongitud(sucursal: Sucursal){

        if(sucursal.nombresucursal.length< 5){
            throw BusinessException("El nombre no puede ser menor a 5 caracteres")
        }
        if(sucursal.direccionsucursal.length< 10){
            throw BusinessException("La direccion no puede ser menor a 10 caracteres")
        }
        if(sucursal.emailsucursal.length< 8){
            throw BusinessException("El email no puede ser menor a 8 caracteres")
        }
    }


    fun validarLongitudMaxima(sucursal: Sucursal){
        if(sucursal.idsucursal.toString().length> 10 ){
            throw BusinessException("El id no puede ser menor a 10 digitos")
        }
        if(sucursal.nombresucursal.length>30){
            throw BusinessException("El nombre no puede ser mayor a 30 caracteres")
        }
        if(sucursal.direccionsucursal.length>60){
            throw BusinessException("La direccion no puede ser mayor a 60 caracteres")
        }
        if(sucursal.telefonosucursal.toString().length!=8){
            throw BusinessException("El telefono no puede ser diferente a 8 dígitos")
        }
        if(sucursal.emailsucursal.length>45){
            throw BusinessException("El email no puede ser mayor a 45 caracteres")
        }
    }


}
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CAIRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CAI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CAIBusiness:ICAIBusiness
{
    @Autowired
    val caiRepository: CAIRepository?=null

    @Throws(BusinessException::class)
    override fun getCAI(): List<CAI> {
        try{
            return caiRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCAIById(idCAI: Long): CAI {
        val opt: Optional<CAI>
        try{
            opt = caiRepository!!.findById(idCAI)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el CAI $idCAI")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveCAI(cai: CAI): CAI {
        try{
            validarEspacios(cai)
            validarLongitud(cai)

            return caiRepository!!.save(cai)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCAI(idCAI: Long) {
        val opt: Optional<CAI>
        try{
            opt = caiRepository!!.findById(idCAI)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el CAI $idCAI")
        }
        else{
            try{
                caiRepository!!.deleteById(idCAI)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun updateCAI(cai: CAI): CAI{
        val opt: Optional<CAI>
        try{
            opt = caiRepository!!.findById(cai.idcai)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el CAI ${cai.idcai}")
        }
        else{
            try{
                return caiRepository!!.save(cai)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    //VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(cai: CAI){
        if(cai.idcai.toString().isEmpty()){
            throw BusinessException("El Id no debe estar vacío")
        }
        if(cai.cai.toString().isEmpty()){
            throw BusinessException("El CAI no debe estar vacío")
        }
        if(cai.rangoinicial.toString().isEmpty()){
            throw BusinessException("El rango inicial no debe estar vacío")
        }
        if(cai.rangofinal.toString().isEmpty()){
            throw BusinessException("El rango final no debe estar vacío")
        }
        if(cai.fechalimite.toString().isEmpty()){
            throw BusinessException("La fecha limite no debe estar vacío")
        }
    }
    @Throws(BusinessException::class)
    fun validarLongitud(cai: CAI){
        if(cai.idcai.toString().length< 3){
            throw BusinessException("El id no puede ser menor a 3 caracteres")
        }
        if(cai.cai.toString().length< 8){
            throw BusinessException("El CAI no puede ser menor a 8 caracteres")
        }
        if(cai.rangoinicial.toString().length<8){
            throw BusinessException("El rango inicial no puede ser menor a 8 digitos")
        }
        if(cai.rangofinal.toString().length<8){
            throw BusinessException("El rango final no puede ser menor a 8 dígitos")
        }
        if(cai.fechalimite.toString().length<4){
            throw BusinessException("La fecha limite no puede ser menor a 4 dígitos")
        }

    }


}
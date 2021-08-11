package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CasoEmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class CasoEmpleadoBusiness:ICasoEmpleadoBusiness
{

    @Autowired
    val casoEmpleadoRepository: CasoEmpleadoRepository?=null

    @Throws(BusinessException::class)
    override fun getCasoEmpleado(): List<CasoEmpleado> {
        try{
            return casoEmpleadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCasoEmpleadoById(idcasoempleado: Long): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idcasoempleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado $idcasoempleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCasoEmpleado(casoempleado: CasoEmpleado): CasoEmpleado {
        try{
            validarEspacios(casoempleado)
            validarLongitud(casoempleado)

            return casoEmpleadoRepository!!.save(casoempleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCasoEmpleado(idcasoempleado: Long) {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idcasoempleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el caso empleado $idcasoempleado")
        }
        else{
            try{
                casoEmpleadoRepository!!.deleteById(idcasoempleado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun updateCasoEmpleado(casoempleado: CasoEmpleado): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(casoempleado.idcasoempleado)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado ${casoempleado.idcasoempleado}")
        }
        else{
            try{
                validarEspacios(casoempleado)
                validarLongitud(casoempleado)
                return casoEmpleadoRepository!!.save(casoempleado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
//VALIDACIONES
@Throws(BusinessException::class)
fun validarEspacios(casoempleado: CasoEmpleado){
    if(casoempleado.idcasoempleado.toString().isEmpty()){
        throw BusinessException("El id del caso empleado no debe estar vacío")
    }
    if(casoempleado.idempleado.toString().isEmpty()){
        throw BusinessException("El id del empleado no debe estar vacío")
    }
    if(casoempleado.idcaso.toString().isEmpty()){
        throw BusinessException("El id del caso no debe estar vacío")
    }
    if(casoempleado.fechainiciotrabajoencaso.toString().isEmpty()){
        throw BusinessException("La fecha de inicio no debe estar vacía")
    }
    if(casoempleado.fechafinaltrabajoencaso.toString().isEmpty()){
        throw BusinessException("La fecha final no debe estar vacío")
    }
    if(casoempleado.descripcioncasoempleado.isEmpty()){
        throw BusinessException("La descripcion no debe estar vacío")
    }

}
    @Throws(BusinessException::class)
    fun validarLongitud(casoempleado: CasoEmpleado){
        if(casoempleado.idcasoempleado.toString().length<8 ){
            throw BusinessException("El id del caso empleado no puede ser menor a 8 digitos")
        }
        if(casoempleado.idempleado.toString().length<8){
            throw BusinessException("El id del empleado no puede ser menor a 8 digitos")
        }
        if(casoempleado.idcaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 dígitos")
        }
        if(casoempleado.fechainiciotrabajoencaso.toString().length<6){
            throw BusinessException("La fecha de inicio no puede ser menor a 6 dígitos")
        }
        if(casoempleado.fechafinaltrabajoencaso.toString().length<6){
            throw BusinessException("La fecha final no puede ser menor a 6 dígitos")
        }
        if(casoempleado.descripcioncasoempleado.length>100){
            throw BusinessException("La descripcion no puede ser mayor a 100 caracteres")
        }
    }
}
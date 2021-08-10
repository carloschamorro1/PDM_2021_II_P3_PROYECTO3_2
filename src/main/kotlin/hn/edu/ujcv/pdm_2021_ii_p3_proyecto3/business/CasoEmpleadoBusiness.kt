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
    override fun getCasoEmpleadoById(idCasoEmpleado: Long): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idCasoEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado $idCasoEmpleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCasoEmpleado(casoEmpleado: CasoEmpleado): CasoEmpleado {
        try{
            validarEspacios(casoEmpleado)
            validarLongitud(casoEmpleado)

            return casoEmpleadoRepository!!.save(casoEmpleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCasoEmpleado(idCasoEmpleado: Long) {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idCasoEmpleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el caso empleado $idCasoEmpleado")
        }
        else{
            try{
                casoEmpleadoRepository!!.deleteById(idCasoEmpleado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun updateCasoEmpleado(casoEmpleado: CasoEmpleado): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(casoEmpleado.idcasoempleado)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado ${casoEmpleado.idcasoempleado}")
        }
        else{
            try{
                validarEspacios(casoEmpleado)
                validarLongitud(casoEmpleado)
                return casoEmpleadoRepository!!.save(casoEmpleado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
//VALIDACIONES
@Throws(BusinessException::class)
fun validarEspacios(casoEmpleado: CasoEmpleado){
    if(casoEmpleado.idcasoempleado.toString().isEmpty()){
        throw BusinessException("El id del caso empleado no debe estar vacío")
    }
    if(casoEmpleado.idempleado.toString().isEmpty()){
        throw BusinessException("El id del empleado no debe estar vacío")
    }
    if(casoEmpleado.idcaso.toString().isEmpty()){
        throw BusinessException("El id del caso no debe estar vacío")
    }
    if(casoEmpleado.fechainiciotrabajoencaso.toString().isEmpty()){
        throw BusinessException("La fecha de inicio no debe estar vacía")
    }
    if(casoEmpleado.fechafinaltrabajoencaso.toString().isEmpty()){
        throw BusinessException("La fecha final no debe estar vacío")
    }
    if(casoEmpleado.descripcioncasoempleado.isEmpty()){
        throw BusinessException("La descripcion no debe estar vacío")
    }

}

    /*
[idCasoEmpleado] [numeric](18, 0) NOT NULL,
[idEmpleado] [numeric](18, 0) NOT NULL,
[idCaso] [numeric](18, 0) NOT NULL,
[fechaInicioTrabajoEnCaso] [date] NOT NULL,
[fechaFinalTrabajoEnCaso] [date] NOT NULL,
[descripcionCasoEmpleado] [varchar](80) NOT NUL
*/
    @Throws(BusinessException::class)
    fun validarLongitud(casoEmpleado: CasoEmpleado){
        if(casoEmpleado.idcasoempleado.toString().length<8 ){
            throw BusinessException("El id del caso empleado no puede ser menor a 8 digitos")
        }
        if(casoEmpleado.idempleado.toString().length<8){
            throw BusinessException("El id del empleado no puede ser menor a 8 digitos")
        }
        if(casoEmpleado.idcaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 dígitos")
        }
        if(casoEmpleado.fechainiciotrabajoencaso.toString().length<6){
            throw BusinessException("La fecha de inicio no puede ser menor a 6 dígitos")
        }
        if(casoEmpleado.fechafinaltrabajoencaso.toString().length<6){
            throw BusinessException("La fecha final no puede ser menor a 6 dígitos")
        }
        if(casoEmpleado.descripcioncasoempleado.length>100){
            throw BusinessException("La descripcion no puede ser mayor a 100 caracteres")
        }


    }
}
//
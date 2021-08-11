package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CobroRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cobro
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobroBusiness: ICobroBusiness{
    @Autowired
    val cobroRepository: CobroRepository?=null

    @Throws(BusinessException::class)
    override fun getCobro(): List<Cobro> {
        try{
            return cobroRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCobroById(idfactura: Long): Cobro {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findByIdFactura(idfactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura $idfactura")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCobro(cobro: Cobro):Cobro {
        try{
            validarEspacios(cobro)
            validarLongitud(cobro)

            return cobroRepository!!.save(cobro)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCobro(idfactura: Long) {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findById(idfactura)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la factura $idfactura")
        }
        else{
            try{
                cobroRepository!!.deleteById(idfactura)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    override fun updateCobro(cobro: Cobro): Cobro {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findById(cobro.idfactura)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura ${cobro.idfactura}")
        }
        else{
            try{
                validarEspacios(cobro)
                validarLongitud(cobro)

                return cobroRepository!!.save(cobro)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    //VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(cobro: Cobro){
        if(cobro.fechaemisionfactura.toString().isEmpty()){
            throw BusinessException("La fecha no debe estar vacío")
        }
        if(cobro.idcai.toString().isEmpty()){
            throw BusinessException("El id CAI no debe estar vacío")
        }
        if(cobro.idsucursal.toString().isEmpty()){
            throw BusinessException("El id Sucursal no debe estar vacío")
        }
        if(cobro.totalfactura.toString().isEmpty()){
            throw BusinessException("El total no debe estar vacío")
        }
        if(cobro.idempleado.toString().isEmpty()){
            throw BusinessException("El id del empleado no debe estar vacío")
        }
        if(cobro.idcaso.toString().isEmpty()){
            throw BusinessException("El id del caso no debe estar vacío")
        }
        if(cobro.iddetalle.toString().isEmpty()){
            throw BusinessException("El id detalle no debe estar vacío")
        }
        if(cobro.idfactura.toString().isEmpty()){
            throw BusinessException("El id de la factura no debe estar vacía")
        }
        if(cobro.cantidadfactura.toString().isEmpty()){
            throw BusinessException("La cantidad de la factura no debe estar vacía")
        }
    }
    @Throws(BusinessException::class)
    fun validarLongitud(cobro: Cobro){
        if(cobro.fechaemisionfactura.toString().length <4 ){
            throw BusinessException("La fecha no puede ser menor a 4 caracteres")
        }
        if(cobro.idcai.toString().length<8){
            throw BusinessException("El id CAI no puede ser menor a 8 digitos")
        }
        if(cobro.idsucursal.toString().length<8){
            throw BusinessException("El id de la sucursal no puede ser menor a 8 dígitos")
        }
        if(cobro.idempleado.toString().length<8){
            throw BusinessException("El id del empleado no puede ser menor a 8 dígitos")
        }
        if(cobro.idcaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 dígitos")
        }
        if(cobro.iddetalle.toString().length<8){
            throw BusinessException("El id del detalle no puede ser menor a 8 dígitos")
        }

        if(cobro.idfactura.toString().length<8){
            throw BusinessException("El id de la factura no puede ser menor a 8 dígitos")
        }


//Marce
    }
}
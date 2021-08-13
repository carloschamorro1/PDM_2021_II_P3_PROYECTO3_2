package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.ClienteRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class ClienteBusiness:IClienteBusiness {

    @Autowired
    val clienteRepository: ClienteRepository?=null

    @Throws(BusinessException::class)
    override fun getCliente(): List<Cliente> {
        try{
            return clienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteById(idcliente: Long): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(idcliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $idcliente")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCliente(cliente: Cliente): Cliente {
        try{
            validarEspacios(cliente)
            validarLongitud(cliente)
            validarTelefono(cliente.telefonocliente)
            validarIdentidad(cliente.dnicliente)
            validarLongitudMaxima(cliente)
            return clienteRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCliente(idcliente: Long) {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(idcliente)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el cliente $idcliente")
        }
        else{
            try{
                clienteRepository!!.deleteById(idcliente)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteByNombre(nombrecliente: String): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findBynombrecliente(nombrecliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $nombrecliente")
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCliente(cliente: Cliente): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(cliente.idcliente)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente ${cliente.idcliente}")
        }
        else{
            try{
                validarEspacios(cliente)
                validarLongitud(cliente)
                validarTelefono(cliente.telefonocliente)
                validarIdentidad(cliente.dnicliente)
                validarLongitudMaxima(cliente)
                return clienteRepository!!.save(cliente)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()

    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(cliente: Cliente){
        if(cliente.nombrecliente.isEmpty()){
            throw BusinessException("El nombre no debe estar vacío")
        }
        if(cliente.apellidocliente.isEmpty()){
            throw BusinessException("El apellido no debe estar vacío")
        }
        if(cliente.dnicliente.isEmpty()){
            throw BusinessException("El dni no debe estar vacío")
        }
        if(cliente.telefonocliente.isEmpty()){
            throw BusinessException("El telefono no debe estar vacío")
        }
        if(cliente.rtncliente.isEmpty()){
            throw BusinessException("El rtn no debe estar vacío")
        }
        if(cliente.direccioncliente.isEmpty()){
            throw BusinessException("La direccion del cliente no debe estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarIdentidad(identidad:String){
        val id = identidad.substring(0,1)
        if(identidad.length == 15){
            if("0".equals(id)){
                return
            }
            else if("1".equals(id)){
                return
            }
            else{
                throw BusinessException("El numero de identidad solo puede comenzar con 1 y 0")
            }
        }
    }

    @Throws(BusinessException::class)
    fun validarTelefono(telefono:String) {
        if (telefono.length == 8) {
            val pattern: Pattern = Pattern.compile("[23789]")
            val matcher: Matcher = pattern.matcher(telefono.substring(0, 1))
            if (matcher.matches()) {
                return
            } else {
                throw BusinessException("El número de teléfono debe comenzar con: 2,3,7,8 o 9")
            }
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(cliente: Cliente){
        if(cliente.nombrecliente.length < 3){
            throw BusinessException("El nombre no puede ser menor a 3 caracteres")
        }
        if(cliente.apellidocliente.length < 3){
            throw BusinessException("El apellido no puede ser menor a 3 caracteres")
        }
        if(cliente.dnicliente.length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 dígitos")
        }
        if(cliente.telefonocliente.length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 dígitos")
        }
        if(cliente.rtncliente.length != 14){
            throw BusinessException("El rtn no puede ser distinto a 14 dígitos")
        }

        if(cliente.direccioncliente.length < 8){
            throw BusinessException("La direccion no puede ser menor a 8 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(cliente: Cliente){
        if(cliente.nombrecliente.length > 40){
            throw BusinessException("El nombre no puede ser mayor a 40 caracteres")
        }
        if(cliente.apellidocliente.length > 40){
            throw BusinessException("El apellido no puede ser mayor a 40 caracteres")
        }
        if(cliente.dnicliente.length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 dígitos")
        }
        if(cliente.telefonocliente.length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 dígitos")
        }
        if(cliente.rtncliente.length != 14){
            throw BusinessException("El rtn no puede ser distinto a 14 dígitos")
        }

        if(cliente.direccioncliente.length > 30){
            throw BusinessException("La direccion no puede ser mayor a 30 caracteres")
        }
    }

}
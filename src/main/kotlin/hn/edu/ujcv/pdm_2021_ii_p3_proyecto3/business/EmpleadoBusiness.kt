package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.EmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class EmpleadoBusiness:IEmpleadoBusiness {

    @Autowired
    val empleadoRepository: EmpleadoRepository?=null

    @Throws(BusinessException::class)
    override fun getEmpleado(): List<Empleado> {
        try{
            return empleadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEmpleadoById(idempleado: Long): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(idempleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $idempleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveEmpleado(empleado: Empleado): Empleado {
        try{
            validarEspacios(empleado)
            validarLongitud(empleado)
            validarSalario(empleado.salarioempleado)
            validarTelefono(empleado.telefonoempleado.toString())
            validarIdentidad(empleado.dniempleado.toString())
            validarContraseñas(empleado.claveusuario)
            validarLongitudMaxima(empleado)
            return empleadoRepository!!.save(empleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeEmpleado(idempleado: Long) {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(idempleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el empleado $idempleado")
        }
        else{
            try{
                empleadoRepository!!.deleteById(idempleado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getEmpleadoByNombre(nombreempleado: String): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findBynombreempleado(nombreempleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $nombreempleado")
        }
        return opt.get()
    }

    override fun updateEmpleado(empleado: Empleado): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(empleado.idempleado)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el empleado ${empleado.idempleado}")
        }
        else{
            try{
                validarEspacios(empleado)
                validarLongitud(empleado)
                validarSalario(empleado.salarioempleado)
                validarTelefono(empleado.telefonoempleado.toString())
                validarIdentidad(empleado.dniempleado.toString())
                validarContraseñas(empleado.claveusuario)
                validarLongitudMaxima(empleado)
                return empleadoRepository!!.save(empleado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(empleado: Empleado){
        if(empleado.nombreempleado.isEmpty()){
            throw BusinessException("El nombre no debe estar vacío")
        }
        if(empleado.apellidoempleado.isEmpty()){
            throw BusinessException("El apellido no debe estar vacío")
        }
        if(empleado.dniempleado.toString().isEmpty()){
            throw BusinessException("El dni no debe estar vacío")
        }
        if(empleado.telefonoempleado.toString().isEmpty()){
            throw BusinessException("El telefono no debe estar vacío")
        }
        if(empleado.salarioempleado.toString().isEmpty()){
            throw BusinessException("El salario no debe estar vacío")
        }
        if(empleado.tipoempleado.isEmpty()){
            throw BusinessException("El tipo del empleado no debe estar vacío")
        }
        if(empleado.nombreusuario.isEmpty()){
            throw BusinessException("El nombre de usuario no debe estar vacío")
        }
        if(empleado.claveusuario.isEmpty()){
            throw BusinessException("La contraseña no debe estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(empleado: Empleado){
        if(empleado.nombreempleado.length > 40 ){
            throw BusinessException("El nombre no puede ser mayor a 40 caracteres")
        }
        if(empleado.apellidoempleado.length > 40){
            throw BusinessException("El apellido no puede ser mayor a 40 caracteres")
        }
        if(empleado.dniempleado.toString().length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 dígitos")
        }
        if(empleado.direccionempleado.length > 30){
            throw BusinessException("La direccion no puede ser mayor a 30 caracteres")
        }
        if(empleado.telefonoempleado.toString().length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 dígitos")
        }
        if(empleado.salarioempleado.toString().length > 10){
            throw BusinessException("El salario no puede ser mayor a 4 dígitos")
        }
        if(empleado.tipoempleado.length > 2){
            throw BusinessException("El tipo del empleado no puede ser mayor a 2 caracteres")
        }

        if(empleado.nombreusuario.length > 25){
            throw BusinessException("El nombre de usuario no puede ser mayor a 25 dígitos")
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

    fun validarSalario(salario:Double){
        if(salario <= 0){
            throw BusinessException("El campo salario no puede comenzar con 0")
        }
    }

    fun validarContraseñas(contraseña: String){
        if (contraseña.length > 7) {
            if (!politicasContraseña(contraseña)) {
                throw BusinessException("\"La contraseña no cumple las siguientes directrices: \\n 1. Debe contener al menos una letra minúscula (a-z)\"\n" +
                        "                 + \"\\n 2. Debe contener al menos una letra mayúscula (A-Z) \\n 3. Debe contener al menos un número (0-9)\"")
            }
        }
    }

    fun politicasContraseña(contraseña: String): Boolean {
        var tieneNumero = false
        var tieneMayusculas = false
        var tieneMinusculas = false
        var c: Char
        for (i in 0 until contraseña.length) {
            c = contraseña[i]
            if (Character.isDigit(c)) {
                tieneNumero = true
            } else if (Character.isUpperCase(c)) {
                tieneMayusculas = true
            } else if (Character.isLowerCase(c)) {
                tieneMinusculas = true
            }
            if (tieneNumero && tieneMayusculas && tieneMinusculas) {
                return true
            }
        }
        return false
    }

    @Throws(BusinessException::class)
    fun validarLongitud(empleado: Empleado){
        if(empleado.nombreempleado.length < 3){
            throw BusinessException("El nombre no puede ser menor a 3 caracteres")
        }
        if(empleado.apellidoempleado.length < 3){
            throw BusinessException("El apellido no puede ser menor a 3 caracteres")
        }
        if(empleado.dniempleado.toString().length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 dígitos")
        }
        if(empleado.telefonoempleado.toString().length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 dígitos")
        }
        if(empleado.salarioempleado.toString().length < 4){
            throw BusinessException("El salario no puede ser menor a 4 dígitos")
        }

        if(empleado.nombreusuario.length < 8){
            throw BusinessException("El nombre de usuario no puede ser menor a 8 dígitos")
        }

        if(empleado.claveusuario.length < 8){
            throw BusinessException("La contraseña no puede ser menor a 8 dígitos")
        }
    }

}
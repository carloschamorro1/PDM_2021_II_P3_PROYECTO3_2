package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.IndicioRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class IndicioBusiness :IIndicioBusiness {
    @Autowired
    val indicioRepository: IndicioRepository?=null

    @Throws(BusinessException::class)
    override fun getIndicio(): List<Indicio> {
        try{
            return indicioRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getIndicioById(idindicio: Long): Indicio {
        val opt: Optional<Indicio>
        try{
            opt = indicioRepository!!.findById(idindicio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el indicio: $idindicio")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveIndicio(indicio: Indicio): Indicio {
        try{
            validarEspacios(indicio)
            validarLongitud(indicio)

            return indicioRepository!!.save(indicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun removeIndicio(idindicio: Long) {
        val opt: Optional<Indicio>
        try{
            opt = indicioRepository!!.findById(idindicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontr?? el Ind??cio $idindicio")
        }
        else{
            try{
                indicioRepository!!.deleteById(idindicio)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getIndicioByIdCaso(idcaso: Long): Indicio {
        val opt: Optional<Indicio>
        try{
            opt = indicioRepository!!.findByidcaso(idcaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el IdCASO $idcaso")
        }
        return opt.get()
    }

    override fun updateIndicio(indicio: Indicio): Indicio {
        val opt: Optional<Indicio>
        try{
            opt = indicioRepository!!.findById(indicio.idindicio)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Indicio ${indicio.idindicio}")
        }
        else{
            try{
                return indicioRepository!!.save(indicio)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    fun validarEspacios(indicio: Indicio){
        if(indicio.idcaso.toString().isEmpty()){
            throw BusinessException("El idCaso no puede ir vacio")
        }

    }


    @Throws(BusinessException::class)
    fun validarLongitud(indicio: Indicio){
        if(indicio.idcaso.toString().length < 1){
            throw BusinessException("El idCaso no puede ser menor a 1 caracteres")
        }
        if(indicio.descripcionindicio.length < 6){
            throw BusinessException("la descripcion no puede ser menor a 6 caracteres")
        }

    }

    @Throws(BusinessException::class)
    fun validarIdentidad(identidad:String){
        val id = identidad.substring(0,1)
        if(identidad.length == 15){
            if("0".equals(identidad)){
                return
            }
            else if("1".equals(identidad)){
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
                throw BusinessException("El n??mero de tel??fono debe comenzar con: 2,3,7,8 o 9")
            }
        }
    }

    fun validarSalario(salario:Double){
        if(salario <= 0){
            throw BusinessException("El campo salario no puede comenzar con 0")
        }
    }

    fun validarContrase??as(contrase??a: String){
        if (contrase??a.length > 7) {
            if (!politicasContrase??a(contrase??a)) {
                throw BusinessException("\"La contrase??a no cumple las siguientes directrices: \\n 1. Debe contener al menos una letra min??scula (a-z)\"\n" +
                        "                 + \"\\n 2. Debe contener al menos una letra may??scula (A-Z) \\n 3. Debe contener al menos un n??mero (0-9)\"")
            }
        }
    }

    fun politicasContrase??a(contrase??a: String): Boolean {
        var tieneNumero = false
        var tieneMayusculas = false
        var tieneMinusculas = false
        var c: Char
        for (i in 0 until contrase??a.length) {
            c = contrase??a[i]
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
}
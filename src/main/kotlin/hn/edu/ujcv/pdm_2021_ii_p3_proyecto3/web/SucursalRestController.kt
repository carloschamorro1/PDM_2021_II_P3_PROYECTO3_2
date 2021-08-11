package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ISucursalBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SUCURSAL)
class SucursalRestController {
    @Autowired
    val sucursalBusiness: ISucursalBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Sucursal>> {
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursal(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idsucursal:Long):ResponseEntity<Sucursal>{
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursalById(idsucursal),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombresucursal:String):ResponseEntity<Sucursal>{
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursalByNombre(nombresucursal),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addsucursal")
    fun insert(@RequestBody sucursal: Sucursal):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.saveSucursal(sucursal)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_SUCURSAL+"/"+sucursal.idsucursal)
            ResponseEntity(sucursal,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody sucursal: Sucursal):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.updateSucursal(sucursal)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
//
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idsucursal:Long):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.removeSucursal(idsucursal)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
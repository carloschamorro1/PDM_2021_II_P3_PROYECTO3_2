package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IFacturaEncabezadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaEncabezado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FACTURAENCABEZADO)
class FacturaEncabezadoRestController {
    @Autowired
    val facturaEncabezadoBusiness: IFacturaEncabezadoBusiness ?= null

    @GetMapping("")
    fun list(): ResponseEntity<List<FacturaEncabezado>> {
        return try{
            ResponseEntity(facturaEncabezadoBusiness!!.getFacturaEncabezado(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idfactura:Long):ResponseEntity<FacturaEncabezado>{
        return try{
            ResponseEntity(facturaEncabezadoBusiness!!.getFacturaEncabezadoById(idfactura),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addfacturaencabezado")
    fun insert(@RequestBody facturaencabezado: FacturaEncabezado):ResponseEntity<Any>{
        return try{
            facturaEncabezadoBusiness!!.saveFacturaEncabezado(facturaencabezado)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_FACTURAENCABEZADO+"/"+facturaencabezado.idfactura)
            ResponseEntity(facturaencabezado,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody facturaencabezado: FacturaEncabezado):ResponseEntity<Any>{
        return try{
            facturaEncabezadoBusiness!!.updateFacturaEncabezado(facturaencabezado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idfactura:Long):ResponseEntity<Any>{
        return try{
            facturaEncabezadoBusiness!!.removeFacturaEncabezado(idfactura)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
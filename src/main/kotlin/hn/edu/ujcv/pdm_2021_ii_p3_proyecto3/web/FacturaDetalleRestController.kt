package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IFacturaDetalleBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FACTURADETALLE)
class FacturaDetalleRestController {
    @Autowired
    val facturaDetalleBusiness: IFacturaDetalleBusiness?= null

    @GetMapping("")
    fun list(): ResponseEntity<List<FacturaDetalle>> {
        return try{
            ResponseEntity(facturaDetalleBusiness!!.getFacturaDetalle(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") iddetalle:Long):ResponseEntity<FacturaDetalle>{
        return try{
            ResponseEntity(facturaDetalleBusiness!!.getFacturaDetalleById(iddetalle),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addfacturadetalle")
    fun insert(@RequestBody facturadetalle: FacturaDetalle):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.saveFacturaDetalle(facturadetalle)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_FACTURADETALLE+"/"+facturadetalle.iddetalle)
            ResponseEntity(facturadetalle,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody facturadetalle: FacturaDetalle):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.updateFacturaDetalle(facturadetalle)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")iddetalle:Long):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.removeFacturaDetalle(iddetalle)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
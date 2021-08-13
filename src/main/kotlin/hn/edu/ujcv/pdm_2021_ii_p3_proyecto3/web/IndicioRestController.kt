package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web


import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IIndicioBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping(Constants.URL_BASE_INDICIO)
class IndicioRestController {

    @Autowired
    val indicioBusiness: IIndicioBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Indicio>> {
        return try{
            ResponseEntity(indicioBusiness!!.getIndicio(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idindicio:Long): ResponseEntity<Indicio> {
        return try{
            ResponseEntity(indicioBusiness!!.getIndicioById(idindicio), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") idcaso:Long): ResponseEntity<Indicio> {
        return try{
            ResponseEntity(indicioBusiness!!.getIndicioByIdCaso(idcaso), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addindicio")
    fun insert(@RequestBody indicio: Indicio): ResponseEntity<Any> {
        return try{
            indicioBusiness!!.saveIndicio(indicio)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_INDICIO+"/"+indicio.idindicio)
            ResponseEntity(indicio,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody indicio: Indicio): ResponseEntity<Any> {
        return try{
            indicioBusiness!!.updateIndicio(indicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idindicio: Long): ResponseEntity<Any> {
        return try{
            indicioBusiness!!.removeIndicio(idindicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
//,
import javax.persistence.*

@Entity
@Table(name = "sucursal")
data class Sucursal( val nombresucursal:String ="", val direccionsucursal:String="", val telefonosucursal:Long=0,
val emailsucursal:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idsucursal:Long=0

}

package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name="audienciadetalle")
data class Audiencia(val idcaso:Long= 0, val fechaaudiencia:String ="",
                     val idjuzgado:Long = 0, val descripcionaudiencia:String= ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idfechaaudiencia:Long=0
}


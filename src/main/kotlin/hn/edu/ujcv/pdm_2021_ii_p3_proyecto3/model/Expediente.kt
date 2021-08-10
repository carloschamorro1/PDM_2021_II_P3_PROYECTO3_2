package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "expediente")
data class Expediente(val entidad:String="", val numexpediente:String="", val idcaso:Long) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idexpediente:Long=0
}
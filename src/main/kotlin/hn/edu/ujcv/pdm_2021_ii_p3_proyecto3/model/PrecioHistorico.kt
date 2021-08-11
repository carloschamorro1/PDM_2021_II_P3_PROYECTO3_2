package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="preciohistorico")
data class PrecioHistorico(val fechainicialpreciohistorico:String="", val fechafinalpreciohistorico:String="",
                           val idservicio:Long = 0, val precio:Double=0.0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idpreciohistorico:Long = 0
}
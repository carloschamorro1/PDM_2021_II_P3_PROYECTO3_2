package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="servicio")
data class Servicio(val nombreservicio:String="", val descripcionservicio:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idservicio: Long = 0
}
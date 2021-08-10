package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Servicio")
data class Servicio(val nombreServicio:String="", val descripcionServicio:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idServicio: Long = 0
}
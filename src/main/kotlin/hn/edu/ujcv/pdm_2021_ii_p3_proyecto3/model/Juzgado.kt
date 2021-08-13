package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="juzgado")
data class Juzgado(val nombrejuzgado:String="", val direccionjuzgado:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idjuzgado:Long=0
}
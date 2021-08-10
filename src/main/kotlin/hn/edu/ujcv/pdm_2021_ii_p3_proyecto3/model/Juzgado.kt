package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Indicio")
data class Juzgado(val nombre:String="", val dirreccion:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}
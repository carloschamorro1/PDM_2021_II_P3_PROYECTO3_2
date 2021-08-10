package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Indicio")
data class Indicio(val idCaso:Long=0, val descripcionIndicio:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idIndicio:Long=0
}
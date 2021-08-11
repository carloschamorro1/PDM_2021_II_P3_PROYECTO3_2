package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="indicio")
data class Indicio(val idcaso:Long=0, val descripcionindicio:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idindicio:Long=0
}
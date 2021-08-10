package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="caso")
class Caso(val tipocaso:String ="", val sentenciacaso:String="", val idcliente:Long = 0,
           val idservicio:Long = 0, val estadocaso: String = "") {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idcaso:Long=0
}
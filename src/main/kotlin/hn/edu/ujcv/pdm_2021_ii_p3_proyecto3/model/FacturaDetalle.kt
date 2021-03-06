package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="facturadetalle")
data class FacturaDetalle(val idfactura:Long = 0, val idservicio:Long = 0, val cantidadfactura:String="") {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var iddetalle:Long = 0
}
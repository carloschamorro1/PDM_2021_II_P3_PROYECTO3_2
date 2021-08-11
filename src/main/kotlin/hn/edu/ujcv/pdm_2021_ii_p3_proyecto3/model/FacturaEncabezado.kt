package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "facturaencabezado")
data class FacturaEncabezado(val fechaemisionfactura: String, val idcai:Long, val idsucursal:Long,
                              val totalfactura:Double, val idempleado:Long, val idcaso:Long) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idfactura:Long=0
}
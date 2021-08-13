package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="empleado")//, catalog = "dbo")
data class Empleado(val nombreempleado:String ="", val apellidoempleado:String="", val dniempleado:String="", val telefonoempleado:Long = 0,
                    val direccionempleado:String = "", val salarioempleado:Double = 0.0, val tipoempleado:String="", val nombreusuario:String="", val claveusuario:String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idempleado: Long = 0
}
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "Cliente")//, catalog = "dbo")
data class Cliente(val nombrecliente:String ="", val apellidocliente:String="", val dnicliente:String = "",
                   val rtncliente:String="", val telefonocliente:String = "",
                   val direccioncliente:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idcliente:Long=0
}
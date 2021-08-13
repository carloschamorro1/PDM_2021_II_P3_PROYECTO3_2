package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="casoempleado")
class CasoEmpleado (val idempleado:Long=0, val idcaso:Long=0, val fechainiciotrabajoencaso:String ="", val fechafinaltrabajoencaso:String = "",
                    val descripcioncasoempleado:String=""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idcasoempleado:Long=0
}//
/*
[idCasoEmpleado] [numeric](18, 0) NOT NULL,
	[idEmpleado] [numeric](18, 0) NOT NULL,
	[idCaso] [numeric](18, 0) NOT NULL,
	[fechaInicioTrabajoEnCaso] [date] NOT NULL,
	[fechaFinalTrabajoEnCaso] [date] NOT NULL,
	[descripcionCasoEmpleado] [varchar](80) NOT NUL
 */
package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity//m
@Table(name="Cobro")
class Cobro (val fechaemisionfactura: Date, val idcai:Long=0, val idsucursal:Long=0, val totalfactura:Float= 0F,
             val idempleado:Long=0, val idcaso:Long=0, val iddetalle:Long=0, val idservicio:Long=0, val cantidadfactura:Long=0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idfactura:Long=0
}
/*
factura encabezado
[fechaEmisionFactura] [date] NOT NULL,
	[idCAI] [numeric](18, 0) NOT NULL,
	[idSucursal] [numeric](18, 0) NOT NULL,
	[totalFactura] [money] NOT NULL,
	[idEmpleado] [numeric](18, 0) NOT NULL,
	[idCaso] [numeric](18, 0) NOT NULL,

factura detalle
[idDetalle] [numeric](18, 0) NOT NULL,
	[idFactura] [numeric](18, 0) NOT NULL,
	[idServicio] [numeric](18, 0) NOT NULL,
	[cantidadFactura] [char](2) NOT NULL,
 */
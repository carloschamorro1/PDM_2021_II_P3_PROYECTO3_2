package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name="cai")
class CAI (val cai:String = "", val rangoinicial:String = "", val rangofinal:String = "", val fechalimite: Date)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idcai:Long=0
}
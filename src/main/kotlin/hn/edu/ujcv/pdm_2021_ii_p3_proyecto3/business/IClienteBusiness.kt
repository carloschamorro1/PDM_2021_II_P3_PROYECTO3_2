package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente

interface IClienteBusiness {
    fun getCliente():List<Cliente>
    fun getClienteById(idcliente:Long): Cliente
    fun saveCliente(cliente: Cliente): Cliente
    fun removeCliente(idcliente:Long)
    fun getClienteByNombre(nombrecliente: String): Cliente
    fun updateCliente(cliente: Cliente): Cliente
}
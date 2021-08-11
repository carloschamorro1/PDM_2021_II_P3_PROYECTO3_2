package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio

interface IIndicioBusiness {
    fun getIndicio():List<Indicio>
    fun getIndicioById(idindicio:Long): Indicio
    fun saveIndicio(indicio: Indicio): Indicio
    fun removeIndicio(idindicio:Long)
    fun getIndicioByIdCaso(idcaso: Long): Indicio
    fun updateIndicio(indicio: Indicio): Indicio
}
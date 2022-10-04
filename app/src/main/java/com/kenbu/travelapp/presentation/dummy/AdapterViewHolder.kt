package com.kenbu.travelapp.presentation.dummy

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.HomeScreenListItemBinding
import com.kenbu.travelapp.domain.model.TravelDataModel

class AdapterViewHolder(private val estateDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(estateDataBinding.root) {
    /**
     * View Holder that Change View Instances
     *
     * @param estateModel
     */
    fun onBind(estateModel: TravelDataModel) {
        val binding = estateDataBinding as HomeScreenListItemBinding

        binding.apply {

            //Setting Layout Binding Adapter
            setVariable(BR.estateList,estateModel)
            //Navigation From Catalog to Clicked Image View


        }
    }
}

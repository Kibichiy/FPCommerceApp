package com.example.fpcommerceapp.view.homecategories.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fpcommerceapp.databinding.ItemSubcategoryLayoutBinding
import com.example.fpcommerceapp.model.Constants
import com.example.fpcommerceapp.view.homecategories.dataclass.Subcategory
import com.example.fpcommerceapp.view.homecategories.ui.DetailsActivity
import com.example.fpcommerceapp.view.homecategories.ui.SubcategoryActivity
import com.google.android.material.R
import com.squareup.picasso.Picasso
import java.io.Serializable

class SubcategoriesAdapter(private val context: Context, val subcategories: List<Subcategory>) :
    RecyclerView.Adapter<SubcategoriesAdapter.SubViewHolder>() {
    private lateinit var binding: ItemSubcategoryLayoutBinding

    override fun getItemCount() = subcategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemSubcategoryLayoutBinding.inflate(layoutInflater, parent, false)
        return SubViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        holder.apply {
            subcategoryTitle.text = subcategories[position].subcategory_name

            var url = "${Constants.IMAGE_URL}${subcategories[position].subcategory_image_url}"
            Picasso.get().load(url).placeholder(R.drawable.mtrl_popupmenu_background)
                .error(R.drawable.mtrl_ic_error).into(binding.txtItemPic)

            itemView.setOnClickListener {
                val intent =  Intent(context, DetailsActivity::class.java)
                intent.putExtra(DETAILS_DATA, subcategories[position].subcategory_id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    inner class SubViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val subcategoryTitle: TextView = binding.txtTitle
    }

    companion object {
        const val DETAILS_DATA = "Details data"
    }
}
package com.example.fpcommerceapp.view.homecategories.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.HomeGridLayoutBinding
import com.example.fpcommerceapp.model.Constants.IMAGE_URL
import com.example.fpcommerceapp.view.homecategories.dataclass.Categories
import com.example.fpcommerceapp.view.homecategories.ui.SubcategoryActivity
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val context: Context, val categories: List<Categories>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private lateinit var binding: HomeGridLayoutBinding

    override fun getItemCount() = categories.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = HomeGridLayoutBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.apply {
            categoryName.text = categories[position].category_name

           bind(categories[position])

            itemView.setOnClickListener {
                val intent = Intent(context, SubcategoryActivity::class.java)
                intent.putExtra(SUB_DATA, categories[position].category_id.toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    inner class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = binding.txtCategory



        fun bind(categories: Categories){
            var url = "${IMAGE_URL}${categories.category_image_url}"

            Log.i("tag","${url.replace("https","http")}")
            Glide.with(context).load( url.replace("https","http"))
                .into(binding.image)
        }
    }

    companion object {
        const val SUB_DATA = "subcategory data"
    }
}
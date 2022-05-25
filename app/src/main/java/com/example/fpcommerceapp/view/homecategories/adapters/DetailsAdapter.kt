package com.example.fpcommerceapp.view.homecategories.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.fpcommerceapp.databinding.ItemDetailsLayoutBinding
import com.example.fpcommerceapp.model.Constants
import com.example.fpcommerceapp.view.cart.database.AppDatabase
import com.example.fpcommerceapp.view.cart.database.CartEntity
import com.example.fpcommerceapp.view.homecategories.dataclass.Product
import com.example.fpcommerceapp.view.homecategories.ui.SubcategoryActivity
import com.google.android.material.R
import com.squareup.picasso.Picasso
import java.io.Serializable

class DetailsAdapter(private val context: Context, val details: List<Product>) :
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private lateinit var binding: ItemDetailsLayoutBinding
    private lateinit var database: AppDatabase

    override fun getItemCount() = details.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemDetailsLayoutBinding.inflate(layoutInflater, parent, false)
        return DetailsViewHolder(binding.root)


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        database = AppDatabase.getInstance(context)

        holder.apply {
            detailsTitle.text = details[position].product_name
            detailsDesc.text = details[position].description
            detailsPrice.text = details[position].price

            var url = "${Constants.IMAGE_URL}${details[position].product_image_url}"
            Picasso.get().load(url).placeholder(R.drawable.mtrl_popupmenu_background)
                .error(R.drawable.mtrl_ic_error).into(binding.txtItemPic)

            binding.txtAddCart.setOnClickListener {
                val cartDao = database.cartDao()
                val item = CartEntity(
                    details[position].product_name,
                    details[position].description,
                    details[position].price,
                    details[position].product_image_url
                )
                cartDao.addItem(item)
            }

           /* itemView.setOnClickListener {
                val intent = Intent(context, SubcategoryActivity::class.java)
                intent.putExtra(DetailsAdapter.SUB_DATA, details[position] as Serializable)
                context.startActivity(intent)
            }*/
        }
    }

    inner class DetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val detailsTitle: TextView = binding.txtTitle
        val detailsDesc: TextView = binding.txtDescription
        val detailsPrice: TextView = binding.txtPrice

    }
}
package com.rudione.tranquilhaven.ui.store.trolley.billing.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.data.model.CartProduct
import com.rudione.tranquilhaven.databinding.BillingProductsRvItemBinding
import com.rudione.tranquilhaven.helper.getProductPrice

class BillingProductsAdapter: RecyclerView.Adapter<BillingProductsAdapter.BillingProductsViewHolder>() {

    inner class BillingProductsViewHolder(
        val binding: BillingProductsRvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(billingProduct: CartProduct) {
            binding.apply {
                Glide.with(itemView).load(billingProduct.product.images[0]).into(imageCartProduct)
                tvProductCartName.text = billingProduct.quantity.toString() + "x " + billingProduct.product.name
                tvBillingProductQuantity.text = billingProduct.quantity.toString()

                val priceAfterPercentage = billingProduct.product.offerPercentage.getProductPrice(billingProduct.product.price)
                tvProductCartPrice.text = "UAH ${String.format("%.2f", priceAfterPercentage)}"
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.product == newItem.product
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BillingProductsAdapter.BillingProductsViewHolder {
        return BillingProductsViewHolder(
            BillingProductsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BillingProductsAdapter.BillingProductsViewHolder, position: Int) {
        val billingProduct = differ.currentList[position]
        return holder.bind(billingProduct)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((CartProduct) -> Unit)? = null

}
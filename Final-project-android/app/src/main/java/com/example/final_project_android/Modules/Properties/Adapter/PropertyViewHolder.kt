package com.example.final_project_android.Modules.Properties.Adapter

import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.LoginActivity
import com.example.final_project_android.Modules.Properties.PropertyRecyclerViewActivity
import com.example.final_project_android.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso

class PropertyViewHolder(
    val itemView: View,
    val listener: PropertyRecyclerViewActivity.OnItemClickListener?,
    var properties: List<Property>?
) :
    RecyclerView.ViewHolder(itemView) {
    var imgImageView: ImageView? = null
    var titleTextView: TextView? = null
    var cityTextView: TextView? = null
    var countryTextView: TextView? = null
    var priceTextView: TextView? = null
    var areaTextView: TextView? = null
    var idTextView: TextView? = null
    var editPropertyButton: Button? = null
    var deletePropertyButton: Button? = null
    var property: Property? = null

    private var currentUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userID: String

    init {
        imgImageView = itemView.findViewById(R.id.ivPropertiesListRowAvatar)
        titleTextView = itemView.findViewById(R.id.tvPropertiesListRowTitle)
        //idTextView = itemView.findViewById(R.id.tvPropertiesListRowId)
        priceTextView = itemView.findViewById(R.id.tvPropertiesListRowPrice)
        areaTextView = itemView.findViewById(R.id.tvPropertiesListRowArea)
        countryTextView = itemView.findViewById(R.id.tvPropertiesListRowCountry)
        cityTextView = itemView.findViewById(R.id.tvPropertiesListRowCity)


        itemView.setOnClickListener {
            Log.i("TAG", "$adapterPosition")
            listener?.onItemClick(adapterPosition)
            listener?.onPropertyClicked(property)
        }

        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth.currentUser
        currentUser?.let {
            userID = currentUser!!.uid
        }
    }

    fun bind(property: Property?) {
        this.property = property
        if (property?.imgUrl != "" && property?.imgUrl != null) {
            Picasso.get()
                .load(property?.imgUrl)
                .into(imgImageView);
        }

        titleTextView?.text = "Title: ${property?.title}"
        countryTextView?.text = "Country: ${property?.country}"
        cityTextView?.text = "City: ${property?.city}"
        priceTextView?.text = "Price: ${property?.price}$"
        areaTextView?.text = "Area: ${property?.area} m^2"

//        propertyCheckbox?.apply {
//            isChecked = property?.isChecked ?: false
//        }
    }
}
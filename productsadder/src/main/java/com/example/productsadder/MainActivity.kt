package com.example.productsadder

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.productsadder.databinding.ActivityMainBinding
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val selectedColors = mutableListOf<Int>()
    private var selectedImages = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonColorPicker.setOnClickListener {
            colorPicker()
        }

        binding.buttonImagesPicker.setOnClickListener {
            imagePicker()
        }
    }

    private val selectImagesResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        }

    private fun imagePicker() {
        val intent = Intent(ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.type = "image/*"
        selectImagesResult.launch(intent)
    }

    private fun colorPicker() {
        ColorPickerDialog.Builder(this)
            .setTitle("Product Color")
            .setPositiveButton("Select", object : ColorEnvelopeListener {
                override fun onColorSelected(envelope: ColorEnvelope?, fromUser: Boolean) {
                    envelope?.let {
                        selectedColors.add(it.color)
                        updateColors()
                    }
                }
            })
            .setNegativeButton("Cancel") { colorPicker, _ ->
                colorPicker.dismiss()
            }.show()
    }

    private fun updateColors() {
        var colors = ""
        selectedColors.forEach {
            colors = "$colors ${Integer.toHexString(it)}"
        }
        binding.tvSelectedColors.text = colors
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.saveProduct) {
            val productValidation = validateInformation()
            if(!productValidation) {
                Toast.makeText(this, "Check your inputs", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateInformation(): Boolean {
        if(binding.edPrice.text.toString().trim().isEmpty())
            return false

        if(binding.edName.text.toString().trim().isEmpty())
            return false

        if(binding.edCategory.text.toString().trim().isEmpty())
            return false

        if(selectedImages.isEmpty())
            return false

        return true
    }

    private fun saveProduct() {
        val name = binding.edCategory.text.toString().trim()
        val category = binding.edCategory.text.toString().trim()
        val price = binding.edCategory.text.toString().trim()
        val offerPercentage = binding.edCategory.text.toString().trim()
        val productType = binding.edCategory.text.toString().trim()
        val description = binding.edCategory.text.toString().trim()
    }
}
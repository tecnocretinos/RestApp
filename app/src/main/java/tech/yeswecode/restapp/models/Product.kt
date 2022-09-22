package tech.yeswecode.restapp.models

data class Product(
    val title: String,
    val price: Double,
    val description: String,
    val categoryId: Int,
    val images: ArrayList<String>
)
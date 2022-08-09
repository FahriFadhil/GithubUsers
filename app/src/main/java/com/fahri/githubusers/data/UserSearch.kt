package com.fahri.githubusers.data

import com.google.gson.annotations.SerializedName

data class UserSearch(

	@field:SerializedName("items")
	val items: List<User?>? = null

)
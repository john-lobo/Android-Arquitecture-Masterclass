package com.techyourchance.mvc.networking

import com.google.gson.annotations.SerializedName

class UserSchema(
    @field:SerializedName("display_name") val userDisplayName: String, @field:SerializedName(
        "profile_image"
    ) val userAvatarUrl: String
)
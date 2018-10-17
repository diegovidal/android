package com.dvidal.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author diegovidal on 01/10/2018.
 */
class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String)
package com.dvidal.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author diegovidal on 01/10/2018.
 */
class ProjectModel(val id: String, val name: String,
                   @SerializedName("full_name") val fullName: String,
                   @SerializedName("stargazers_count") val starCount: Int,
                   @SerializedName("created_at") val dateCreated: String,
                   val owner: OwnerModel)
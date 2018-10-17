package com.dvidal.data.model

/**
 * @author diegovidal on 30/09/2018.
 */
data class ProjectEntity(val id: String, val name: String, val fullName: String,
                    val starCount: String, val dateCreated: String,
                    val ownerName: String, val ownerAvatar: String,
                    val isBookmarked: Boolean)
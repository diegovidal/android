package com.dvidal.domain.model

/**
 * @author diegovidal on 30/09/2018.
 */

class Project(val id: String, val name: String, val fullName: String,
              val starCount: String, val dateCreated: String,
              val ownerName: String, val ownerAvatar: String,
              val isBookmarked: Boolean)
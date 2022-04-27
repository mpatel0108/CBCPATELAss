package com.patel.manpatelcbc.model

import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsModelItems(
    val id: Int? = null,
    val active: Boolean?,
    val description: String?,
    val embedTypes: String?,
    val images: Images?,
    val language: String?,
    val publishedAt: Long?,
    val readablePublishedAt: String?,
    val readableUpdatedAt: String?,
    val source: String?,
    val sourceId: String?,
    val title: String?,
    val type: String?,
    val typeAttributes: TypeAttributes?,
    val updatedAt: Long?,
    val version: String?
)

data class Images(
    val imageLarge: String?
)

data class TypeAttributes(
    val author: Authors?,
    val body: Bodys?,
    val categories: List<Categorys>?,
    val commentsSectionId: String?,
    val contextualHeadlines: List<Any>?,
    val deck: String?,
    val displayComments: Boolean?,
    val flag: String?,
    val flags: Flagss?,
    val headline: Headlines?,
    val imageAspects: String?,
    val imageLarge: String?,
    val imageSmall: String?,
    val media: Any?,
    val mediaCaptionUrl: Any?,
    val mediaDuration: Any?,
    val mediaId: Any?,
    val mediaStreamType: Any?,
    val sectionLabels: List<String>?,
    val sectionList: List<String>?,
    val show: String?,
    val showSlug: String?,
    val trending: Trendings?,
    val url: String?,
    val urlSlug: String?
)

data class Authors(
    val display: String?,
    val image: String?,
    val name: String?
)

data class Bodys(
    val containsAudio: Boolean?,
    val containsPhotogallery: Boolean?,
    val containsVideo: Boolean?,
    val formatVersion: Int?
)

data class Categorys(
    val bannerImage: String?,
    val id: Int?,
    val image: String?,
    val metadata: Metadatas?,
    val name: String?,
    val path: String?,
    val priority: Int?,
    val priorityWhenInlined: Int?,
    val slug: String?,
    val type: String?
)

data class Flagss(
    val label: String?,
    val status: String?
)

data class Headlines(
    val mediaId: String?,
    val type: String?
)

data class Trendings(
    val numViewers: Int?,
    val numViewersSRS: Int?
)

data class Metadatas(
    val adHierarchy: String?,
    val attribution: Attributions?,
    val mpxCategoryName: String?,
    val orderLineupId: String?,
    val orderLineupSlug: String?,
    val ottTitle: Any?,
    val pageDescription: String?,
    val pageTitle: String?,
    val polopolyDeptName: String?,
    val polopolyExternalId: String?,
    val tracking: Trackings?
)

data class Attributions(
    val level1: String?,
    val level2: String?,
    val level3: String?
)

data class Trackings(
    val contentarea: String?,
    val contenttype: String?,
    val subsection1: String?,
    val subsection2: String?,
    val subsection3: String?,
    val subsection4: String?
)
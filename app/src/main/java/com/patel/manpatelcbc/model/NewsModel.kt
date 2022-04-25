package com.patel.manpatelcbc.model



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    val newsModel: MutableList<NewsModelItem>,
    val status: String,
    val totalResults: Int
)

@Entity(
    tableName = "news"
)
data class NewsModelItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val active: Boolean?,
    val description: String?,
    val embedTypes: String?,
    val images: String?,
    val language: String?,
    val publishedAt: Long?,
    val readablePublishedAt: String?,
    val readableUpdatedAt: String?,
    val source: String?,
    val sourceId: String?,
    val title: String?,
    val type: String?,
    val typeAttributes: String?,
    val updatedAt: Long?,
    val version: String?
) : Serializable

data class Image(
    val square_140: String?
) : Serializable

data class TypeAttribute(
    val author: Author?,
    val body: Body?,
    val categories: List<Category>?,
    val commentsSectionId: String?,
    val contextualHeadlines: List<Any>?,
    val deck: String?,
    val displayComments: Boolean?,
    val flag: String?,
    val flags: Flags?,
    val headline: Headline?,
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
    val trending: Trending?,
    val url: String?,
    val urlSlug: String?
) : Serializable

data class Author(
    val display: String?,
    val image: String?,
    val name: String?
) : Serializable

data class Body(
    val containsAudio: Boolean?,
    val containsPhotogallery: Boolean?,
    val containsVideo: Boolean?,
    val formatVersion: Int?
) : Serializable

data class Category(
    val bannerImage: String?,
    val id: Int?,
    val image: String?,
    val metadata: Metadata?,
    val name: String?,
    val path: String?,
    val priority: Int?,
    val priorityWhenInlined: Int?,
    val slug: String?,
    val type: String?
) : Serializable

data class Flags(
    val label: String?,
    val status: String?
) : Serializable

data class Headline(
    val mediaId: String?,
    val type: String?
) : Serializable

data class Trending(
    val numViewers: Int?,
    val numViewersSRS: Int?
) : Serializable

data class Metadata(
    val adHierarchy: String?,
    val attribution: Attribution?,
    val mpxCategoryName: String?,
    val orderLineupId: String?,
    val orderLineupSlug: String?,
    val ottTitle: Any?,
    val pageDescription: String?,
    val pageTitle: String?,
    val polopolyDeptName: String?,
    val polopolyExternalId: String?,
    val tracking: Tracking?
) : Serializable

data class Attribution(
    val level1: String?,
    val level2: String?,
    val level3: String?
) : Serializable

data class Tracking(
    val contentarea: String?,
    val contenttype: String?,
    val subsection1: String?,
    val subsection2: String?,
    val subsection3: String?,
    val subsection4: String?
) : Serializable
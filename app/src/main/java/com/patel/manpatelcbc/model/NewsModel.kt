package com.patel.manpatelcbc.model

import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    val newsModel: MutableList<NewsModelItem>
)


data class NewsModelItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("embedTypes")
    val embedTypes: String?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("publishedAt")
    val publishedAt: Long?,
    @SerializedName("readablePublishedAt")
    val readablePublishedAt: String?,
    @SerializedName("readableUpdatedAt")
    val readableUpdatedAt: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("sourceId")
    val sourceId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("typeAttributes")
    val typeAttributes: TypeAttributes?,
    @SerializedName("updatedAt")
    val updatedAt: Long?,
    @SerializedName("version")
    val version: String?
) : Serializable

data class Images(
    @SerializedName("square_140")
    val square_140: String?
)

data class TypeAttributes(
    @SerializedName("author")
    val author: Author?,
    @SerializedName("body")
    val body: Body?,
    @SerializedName("categories")
    val categories: List<Category>?,
    @SerializedName("commentsSectionId")
    val commentsSectionId: String?,
    @SerializedName("contextualHeadlines")
    val contextualHeadlines: List<Any>?,
    @SerializedName("deck")
    val deck: String?,
    @SerializedName("displayComments")
    val displayComments: Boolean?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("flags")
    val flags: Flags?,
    @SerializedName("headline")
    val headline: Headline?,
    @SerializedName("imageAspects")
    val imageAspects: String?,
    @SerializedName("imageLarge")
    val imageLarge: String?,
    @SerializedName("imageSmall")
    val imageSmall: String?,
    @SerializedName("media")
    val media: Any?,
    @SerializedName("mediaCaptionUrl")
    val mediaCaptionUrl: Any?,
    @SerializedName("mediaDuration")
    val mediaDuration: Any?,
    @SerializedName("mediaId")
    val mediaId: Any?,
    @SerializedName("mediaStreamType")
    val mediaStreamType: Any?,
    @SerializedName("sectionLabels")
    val sectionLabels: List<String>?,
    @SerializedName("vsectionList")
    val sectionList: List<String>?,
    @SerializedName("show")
    val show: String?,
    @SerializedName("showSlug")
    val showSlug: String?,
    @SerializedName("trending")
    val trending: Trending?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlSlug")
    val urlSlug: String?
)

data class Author(
    @SerializedName("display")
    val display: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?
)

data class Body(
    @SerializedName("containsAudio")
    val containsAudio: Boolean?,
    @SerializedName("containsPhotogallery")
    val containsPhotogallery: Boolean?,
    @SerializedName("containsVideo")
    val containsVideo: Boolean?,
    @SerializedName("formatVersion")
    val formatVersion: Int?
)

data class Category(
    @SerializedName("bannerImage")
    val bannerImage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("priority")
    val priority: Int?,
    @SerializedName("priorityWhenInlined")
    val priorityWhenInlined: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("type")
    val type: String?
)

data class Flags(
    @SerializedName("label")
    val label: String?,
    @SerializedName("status")
    val status: String?
)

data class Headline(
    @SerializedName("mediaId")
    val mediaId: String?,
    @SerializedName("type")
    val type: String?
)

data class Trending(
    @SerializedName("numViewers")
    val numViewers: Int?,
    @SerializedName("numViewersSRS")
    val numViewersSRS: Int?
)

data class Metadata(
    @SerializedName("adHierarchy")
    val adHierarchy: String?,
    @SerializedName("attribution")
    val attribution: Attribution?,
    @SerializedName("mpxCategoryName")
    val mpxCategoryName: String?,
    @SerializedName("orderLineupId")
    val orderLineupId: String?,
    @SerializedName("orderLineupSlug")
    val orderLineupSlug: String?,
    @SerializedName("ottTitle")
    val ottTitle: Any?,
    @SerializedName("pageDescription")
    val pageDescription: String?,
    @SerializedName("pageTitle")
    val pageTitle: String?,
    @SerializedName("polopolyDeptName")
    val polopolyDeptName: String?,
    @SerializedName("polopolyExternalId")
    val polopolyExternalId: String?,
    @SerializedName("tracking")
    val tracking: Tracking?
)

data class Attribution(
    @SerializedName("level1")
    val level1: String?,
    @SerializedName("level2")
    val level2: String?,
    @SerializedName("level3")
    val level3: String?
)

data class Tracking(
    @SerializedName("contentarea")
    val contentarea: String?,
    @SerializedName("contenttype")
    val contenttype: String?,
    @SerializedName("subsection1")
    val subsection1: String?,
    @SerializedName("subsection2")
    val subsection2: String?,
    @SerializedName("subsection3")
    val subsection3: String?,
    @SerializedName("subsection4")
    val subsection4: String?
)
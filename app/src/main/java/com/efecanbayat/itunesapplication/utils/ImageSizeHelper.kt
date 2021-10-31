package com.efecanbayat.itunesapplication.utils

class ImageSizeHelper {

    companion object {
        fun imageSizeChanger200(imageUrl: String): String {
            return try {
                val resizedImage = imageUrl.replace("100x100bb.jpg", "200x200bb.jpg")
                resizedImage
            } catch (e: Exception) {
                imageUrl
            }
        }

        fun imageSizeChanger300(imageUrl: String): String {
            return try {
                val resizedImage = imageUrl.replace("100x100bb.jpg", "300x300bb.jpg")
                resizedImage
            } catch (e: Exception) {
                imageUrl
            }
        }

        fun imageSizeChanger400(imageUrl: String): String {
            return try {
                val resizedImage = imageUrl.replace("100x100bb.jpg", "400x400bb.jpg")
                resizedImage
            } catch (e: Exception) {
                imageUrl
            }
        }
    }
}
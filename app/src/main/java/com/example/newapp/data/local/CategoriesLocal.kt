package com.example.newapp.data.local


val categories by lazy {
    ArrayList<Category>()
        .apply {
            add(
                Category(
                    code = "business",
                    arabicName = "أعمال",
                    englishName = "Business",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "entertainment",
                    arabicName = "ترفيه",
                    englishName = "Entertainment",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "general",
                    arabicName = "عام",
                    englishName = "General",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "health",
                    arabicName = "صحه",
                    englishName = "Health",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "science",
                    arabicName = "علوم",
                    englishName = "Science",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "sports",
                    arabicName = "رياضه",
                    englishName = "Sports",
                    isFollowed = false
                )
            )
            add(
                Category(
                    code = "technology",
                    arabicName = "تكنولوجيا",
                    englishName = "Technology",
                    isFollowed = false
                )
            )
        }
}
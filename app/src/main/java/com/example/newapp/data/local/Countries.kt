package com.example.newapp.data.local

import com.example.newapp.common.localValue

data class Country(
    val code: String,
    val arabicName: String,
    val englishName: String
) {
    val countryName: String
        get() = localValue(arabicName, englishName)
}

val countries by lazy {
    listOf(
        Country("AE", "الإمارات العربية المتحدة", "United Arab Emirates"),
        Country("AR", "الأرجنتين", "Argentina"),
        Country("AT", "النمسا", "Austria"),
        Country("AU", "أستراليا", "Australia"),
        Country("BE", "بلجيكا", "Belgium"),
        Country("BG", "بلغاريا", "Bulgaria"),
        Country("BR", "البرازيل", "Brazil"),
        Country("CA", "كندا", "Canada"),
        Country("CH", "سويسرا", "Switzerland"),
        Country("CN", "الصين", "China"),
        Country("CO", "كولومبيا", "Colombia"),
        Country("CU", "كوبا", "Cuba"),
        Country("CZ", "جمهورية التشيك", "Czech Republic"),
        Country("DE", "ألمانيا", "Germany"),
        Country("EG", "مصر", "Egypt"),
        Country("FR", "فرنسا", "France"),
        Country("GB", "المملكة المتحدة", "United Kingdom"),
        Country("GR", "اليونان", "Greece"),
        Country("HK", "هونغ كونغ", "Hong Kong"),
        Country("HU", "المجر", "Hungary"),
        Country("ID", "إندونيسيا", "Indonesia"),
        Country("IE", "أيرلندا", "Ireland"),
        Country("IL", "إسرائيل", "Israel"),
        Country("IN", "الهند", "India"),
        Country("IT", "إيطاليا", "Italy"),
        Country("JP", "اليابان", "Japan"),
        Country("KR", "كوريا الجنوبية", "South Korea"),
        Country("LT", "ليتوانيا", "Lithuania"),
        Country("LV", "لاتفيا", "Latvia"),
        Country("MA", "المغرب", "Morocco"),
        Country("MX", "المكسيك", "Mexico"),
        Country("MY", "ماليزيا", "Malaysia"),
        Country("NG", "نيجيريا", "Nigeria"),
        Country("NL", "هولندا", "Netherlands"),
        Country("NO", "النرويج", "Norway"),
        Country("NZ", "نيوزيلندا", "New Zealand"),
        Country("PH", "الفلبين", "Philippines"),
        Country("PL", "بولندا", "Poland"),
        Country("PT", "البرتغال", "Portugal"),
        Country("RO", "رومانيا", "Romania"),
        Country("RS", "صربيا", "Serbia"),
        Country("RU", "روسيا", "Russia"),
        Country("SA", "السعودية", "Saudi Arabia"),
        Country("SE", "السويد", "Sweden"),
        Country("SG", "سنغافورة", "Singapore"),
        Country("SI", "سلوفينيا", "Slovenia"),
        Country("SK", "سلوفاكيا", "Slovakia"),
        Country("TH", "تايلاند", "Thailand"),
        Country("TR", "تركيا", "Turkey"),
        Country("TW", "تايوان", "Taiwan"),
        Country("UA", "أوكرانيا", "Ukraine"),
        Country("US", "الولايات المتحدة", "United States"),
        Country("VE", "فنزويلا", "Venezuela"),
        Country("ZA", "جنوب أفريقيا", "South Africa")
    )
}



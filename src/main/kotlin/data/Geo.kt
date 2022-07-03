package data

/**
 * [type] — Тип места;
 *
 * [coordinates] — Координаты места;
 *
 * [place] — Описание места (если оно добавлено). Объект места.
 */

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Unit? = null
)

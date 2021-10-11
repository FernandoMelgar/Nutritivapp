package mx.nutritivalabs.nutritivapp.patient

data class AnthropometricData(
    val height: Length = Length(177f, LengthType.CM),
    val weight: Weight
)


data class Weight(
    val value: Float,
    val type: WeightType
)

data class Length(
    val value: Float,
    val type: LengthType
)

enum class LengthType {
    CM, METERS
}

enum class WeightType {
    GRAMS, KG
}
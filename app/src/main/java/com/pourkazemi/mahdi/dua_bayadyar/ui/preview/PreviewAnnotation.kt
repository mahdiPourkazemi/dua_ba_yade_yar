package com.pourkazemi.mahdi.dua_bayadyar.ui.preview

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Preview(
    name = "Phone",
    group = "Devices",
    device = "spec:width=360dp,height=640dp,dpi=480"
)
@Preview(
    name = "Tablet",
    group = "Devices",
    device = "spec:width=800dp,height=1280dp,dpi=480"
)
@Preview(
    name = "Foldable",
    group = "Devices",
    device = "spec:width=673dp,height=841dp,dpi=480"
)
annotation class DevicePreview

@Preview(
    name = "Light Theme",
    group = "Themes",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Preview(
    name = "Dark Theme",
    group = "Themes",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF000000
)
annotation class ThemePreview

@Preview(name = "Small font", group = "Font scales", fontScale = 0.5f)
@Preview(name = "Normal font", group = "Font scales", fontScale = 1f)
@Preview(name = "Large font", group = "Font scales", fontScale = 1.5f)
@Preview(name = "Huge font", group = "Font scales", fontScale = 2f)
annotation class FontScalePreview

@Preview(name = "RTL", group = "Localization", locale = "fa")
@Preview(name = "LTR", group = "Localization", locale = "en")
annotation class LocalePreview

@Preview(name = "Loading State", group = "States")
@Preview(name = "Error State", group = "States")
@Preview( name = "Success State", group = "States" )
@Preview( name = "Empty State", group = "States" )
annotation class StatePreview

class TextPreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "لا إِلهَ إِلّا أَنْتَ مُقَلِّبَ القُلُوبِ وَ الأَبْصارِ، لا إِلهَ إِلّا أَنْتَ خالِقَ الخَلْقِ بِلا حاجَةٍ فِيکَ إِلَيْهِمْ، لا إِلهَ إِلّا أَنْتَ مُبْدِئَ الخَلْقِ لا يَنْقُصُ مِنْ مُلْکِکَ شَيْ\u200Fءٌ. لا إِلهَ إِلّا أَنْتَ باعِثَ مَنْ فِي القُبُورِ، لا إِلهَ إِلّا أَنْتَ مُدَبِّرَ الأُمُورِ، لا إِلهَ إِلّا أَنْتَ دَيّانَ الدِّينِ وَ جَبّارَ الجَبابِرَةِ، لا إِلهَ إِلّا أَنْتَ مُجْرِيَ المآءِ فِي الصَّخْرَةِ الصَّمّآءِ، لا إِلهَ إلّا أَنْتَ مُجْرِيَ المآءِ فِي النَّباتِ، لا إِلهَ إِلّا أَنْتَ مُکَوِّنَ طَعْمِ الِّثمارِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ القَطَرِ وَ ما تَحْمِلُهُ السَّحابُ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ ما تَجْرِي بِهِ الرِّياحُ فِي الهَوآءِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما فِي البِحارِ مِنْ رَطْبٍ وَ يابِسٍ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما يَدِبُّ فِي ظُلُماتِ البِحارِ وَ فِي أَطْباقِ الثَّري، أَسْأَلُکَ بِاسْمِکَ الَّذِي سَمَّيْتَ بِهِ نَفْسَکَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الغَيْبِ عِنْدَکَ وَ أَسْأَلُکَ بِکُلِّ اسْمٍ سَمَّاکَ بِهِ أَحَدٌ مِنْ خَلْقِکَ مِنْ نَبِيٍّ أَوْ صِدِّیقٍ أَوْ شَهِیدٍ أوْ أحَدٍ مِنْ مَلَائِکَتِکَ وَ أسْأَلُکَ بِاسْمِکَ الَّذِي إِذا دُعِيتَ بِهِ أَجَبْتَ وَ إِذَا سُئِلْتَ بِهِ أَعْطَيْتَ، وَ أَسْأَلُکَ بِحَقَّکَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ صَلَواتُکَ عَلَيْهِمْ وَ بَرَکاتُکَ وَ بِحَقِّهِمُ الَّذِي أَوْجَبْتَهُ عَلي نَفْسِکَ وَ أَنَلْتَهُمْ بِهِ فَضْلَکَ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ عَبْدِکَ وَ رَسُولِکَ الدّاعِي إِلَيْکَ بِإِذْنِکَ وَ سِراجِکَ السّاطِعِ بَيْنَ عِبادِکَ فِي أَرْضِکَ وَ سَمآئِکَ وَ جَعَلْتَهُ رَحْمَةً لِلْعالَمِينَ وَ نُوراً اِسْتَضآءَ بِهِ المُؤْمِنُونَ فَبَشَّرَنا بِجَزِيلِ ثَوابِکَ وَ أَنْذَرَنا الأَلِيمَ مِنْ عَقَابِکَ، أَشْهَدُ أَنَّهُ قَدْ جآءَ بِالحَقِّ وَ صَدَّقَ المُرْسَلِينَ، وَ أَشْهَدُ أَنَّ الَّذِينَ کَذَّبُوهُ ذائِقُوا العَذابِ الأَلِيمِ، أَسْأَلُکَ يا اَللَّهُ يا اَللَّهُ يا اَللَّهُ، يا رَبّاهُ يا رَبّاهُ يا رَبّاهُ، يا سَيِّدِي يا سَيِّدِي يا سَيِّدِي، يا مَوْلايَ يا مَوْلايَ يا مَوْلايَ، أَسْأَلُکَ فِي هذِهِ الغَداةِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ آلِ مُحَمَّدٍ وَ أَنْ تَجْعَلَنِي مِنْ أَوْفَرِ عِبادِکَ وَ سآئِلِيکَ نَصِيباً وَ أَنْ تَمُنَّ عَلَيَّ بِفَکاکِ رَقَبَتِي مِنَ النّارِ، يا أَرْحَمَ الرّاحِمِينَ، وَ أَسْأَلُکَ بِجَمِيعِ ما سَأَلْتُکَ وَ ما لَمْ أَسْأَلْکَ مِنْ عَظِيمِ جَلالِکَ ما لَوْ عَلِمْتُهُ لَسَأَلْتُکَ بِهِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ وَ أَنْ تَأْذَنَ لِفَرَجِ مَنْ بِفَرَجِهِ فَرَجُ أَوْلِيآئِکَ وَ أَصْفِيآئِکَ مِنْ خَلْقِکَ وَ بِهِ تُبِيدُ الظّالِمِينَ وَ تُهْلِکُهُمْ، عَجِّلْ ذلِکَ يا رَبِّ العالَمِينَ وَ اعْطِنِي سُؤْلِي يا ذَاالجَلالِ وَالإِکْرامِ فِي جَمِيعِ ما سَأَلْتُکَ لِعاجِلِ الدُّنْيا وَ آجِلِ الآخِرَةِ، يا مَنْ هُوَ أَقْرَبُ إِلَيَّ مِنْ حَبْلِ الوَرِيدِ أَقِلْنِي عَثْرَتِي وَ أَقْلِبْنِي بِقَضآءِ حَوآئِجِي يا خالِقِي وَ يا رازِقِي وَ يا باعِثِي وَ يا مُحْيِي عِظامِي وَ هِيَ رَمِيمٌ، صَلِّ عَلي مُحَمَّدٍ وَآلِ مُحَمَّدٍ وَ اسْتَجِبْ لِي دُعآئِي يا أَرْحَمَ الرّاحِمِينَ ."
    )
}


// Combined preview for all common configurations
@DevicePreview
@FontScalePreview
//@LocalePreview
//@StatePreview
annotation class FullPreview

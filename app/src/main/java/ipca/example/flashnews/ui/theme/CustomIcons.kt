package ipca.example.flashnews.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

// Crie um "object" para agrupar os seus ícones personalizados
object CustomIcons {
    val SportsBasketball: ImageVector
        get() {
            if (_sportsBasketball != null) return _sportsBasketball!!

            _sportsBasketball = ImageVector.Builder(
                name = "Sports_basketball",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(162f, 440f)
                    horizontalLineToRelative(114f)
                    quadToRelative(-6f, -38f, -23f, -71f)
                    reflectiveQuadToRelative(-43f, -59f)
                    quadToRelative(-18f, 29f, -30.5f, 61.5f)
                    reflectiveQuadTo(162f, 440f)
                    moveToRelative(522f, 0f)
                    horizontalLineToRelative(114f)
                    quadToRelative(-5f, -36f, -17.5f, -68.5f)
                    reflectiveQuadTo(750f, 310f)
                    quadToRelative(-26f, 26f, -43f, 59f)
                    reflectiveQuadToRelative(-23f, 71f)
                    moveTo(210f, 650f)
                    quadToRelative(26f, -26f, 43f, -59f)
                    reflectiveQuadToRelative(23f, -71f)
                    horizontalLineTo(162f)
                    quadToRelative(5f, 36f, 17.5f, 68.5f)
                    reflectiveQuadTo(210f, 650f)
                    moveToRelative(540f, 0f)
                    quadToRelative(18f, -29f, 30.5f, -61.5f)
                    reflectiveQuadTo(798f, 520f)
                    horizontalLineTo(684f)
                    quadToRelative(6f, 38f, 23f, 71f)
                    reflectiveQuadToRelative(43f, 59f)
                    moveTo(358f, 440f)
                    horizontalLineToRelative(82f)
                    verticalLineToRelative(-278f)
                    quadToRelative(-53f, 8f, -98.5f, 29.5f)
                    reflectiveQuadTo(260f, 248f)
                    quadToRelative(39f, 38f, 64.5f, 86.5f)
                    reflectiveQuadTo(358f, 440f)
                    moveToRelative(162f, 0f)
                    horizontalLineToRelative(82f)
                    quadToRelative(8f, -57f, 33.5f, -105.5f)
                    reflectiveQuadTo(700f, 248f)
                    quadToRelative(-36f, -35f, -81.5f, -56.5f)
                    reflectiveQuadTo(520f, 162f)
                    close()
                    moveToRelative(-80f, 358f)
                    verticalLineToRelative(-278f)
                    horizontalLineToRelative(-82f)
                    quadToRelative(-8f, 57f, -33.5f, 105.5f)
                    reflectiveQuadTo(260f, 712f)
                    quadToRelative(36f, 35f, 81.5f, 56.5f)
                    reflectiveQuadTo(440f, 798f)
                    moveToRelative(80f, 0f)
                    quadToRelative(53f, -8f, 98.5f, -29.5f)
                    reflectiveQuadTo(700f, 712f)
                    quadToRelative(-39f, -38f, -64.5f, -86.5f)
                    reflectiveQuadTo(602f, 520f)
                    horizontalLineToRelative(-82f)
                    close()
                    moveToRelative(-40f, 82f)
                    quadToRelative(-83f, 0f, -156f, -31.5f)
                    reflectiveQuadTo(197f, 763f)
                    reflectiveQuadToRelative(-85.5f, -127f)
                    reflectiveQuadTo(80f, 480f)
                    reflectiveQuadToRelative(31.5f, -156f)
                    reflectiveQuadTo(197f, 197f)
                    reflectiveQuadToRelative(127f, -85.5f)
                    reflectiveQuadTo(480f, 80f)
                    reflectiveQuadToRelative(156f, 31.5f)
                    reflectiveQuadTo(763f, 197f)
                    reflectiveQuadToRelative(85.5f, 127f)
                    reflectiveQuadTo(880f, 480f)
                    reflectiveQuadToRelative(-31.5f, 156f)
                    reflectiveQuadTo(763f, 763f)
                    reflectiveQuadToRelative(-127f, 85.5f)
                    reflectiveQuadTo(480f, 880f)
                }
            }.build()

            return _sportsBasketball!!
        }

    val Sports_football: ImageVector
        get() {
            if (_Sports_football != null) return _Sports_football!!

            _Sports_football = ImageVector.Builder(
                name = "Sports_football",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(480f, 880f)
                    quadToRelative(-83f, 0f, -156f, -31.5f)
                    reflectiveQuadTo(197f, 763f)
                    reflectiveQuadToRelative(-85.5f, -127f)
                    reflectiveQuadTo(80f, 480f)
                    reflectiveQuadToRelative(31.5f, -156f)
                    reflectiveQuadTo(197f, 197f)
                    reflectiveQuadToRelative(127f, -85.5f)
                    reflectiveQuadTo(480f, 80f)
                    reflectiveQuadToRelative(156f, 31.5f)
                    reflectiveQuadTo(763f, 197f)
                    reflectiveQuadToRelative(85.5f, 127f)
                    reflectiveQuadTo(880f, 480f)
                    reflectiveQuadToRelative(-31.5f, 156f)
                    reflectiveQuadTo(763f, 763f)
                    reflectiveQuadToRelative(-127f, 85.5f)
                    reflectiveQuadTo(480f, 880f)
                    moveToRelative(200f, -500f)
                    lineToRelative(54f, -18f)
                    lineToRelative(16f, -54f)
                    quadToRelative(-32f, -48f, -77f, -82.5f)
                    reflectiveQuadTo(574f, 174f)
                    lineToRelative(-54f, 38f)
                    verticalLineToRelative(56f)
                    close()
                    moveToRelative(-400f, 0f)
                    lineToRelative(160f, -112f)
                    verticalLineToRelative(-56f)
                    lineToRelative(-54f, -38f)
                    quadToRelative(-54f, 17f, -99f, 51.5f)
                    reflectiveQuadTo(210f, 308f)
                    lineToRelative(16f, 54f)
                    close()
                    moveToRelative(-42f, 308f)
                    lineToRelative(46f, -4f)
                    lineToRelative(30f, -54f)
                    lineToRelative(-58f, -174f)
                    lineToRelative(-56f, -20f)
                    lineToRelative(-40f, 30f)
                    quadToRelative(0f, 65f, 18f, 118.5f)
                    reflectiveQuadTo(238f, 688f)
                    moveToRelative(242f, 112f)
                    quadToRelative(26f, 0f, 51f, -4f)
                    reflectiveQuadToRelative(49f, -12f)
                    lineToRelative(28f, -60f)
                    lineToRelative(-26f, -44f)
                    horizontalLineTo(378f)
                    lineToRelative(-26f, 44f)
                    lineToRelative(28f, 60f)
                    quadToRelative(24f, 8f, 49f, 12f)
                    reflectiveQuadToRelative(51f, 4f)
                    moveToRelative(-90f, -200f)
                    horizontalLineToRelative(180f)
                    lineToRelative(56f, -160f)
                    lineToRelative(-146f, -102f)
                    lineToRelative(-144f, 102f)
                    close()
                    moveToRelative(332f, 88f)
                    quadToRelative(42f, -50f, 60f, -103.5f)
                    reflectiveQuadTo(800f, 466f)
                    lineToRelative(-40f, -28f)
                    lineToRelative(-56f, 18f)
                    lineToRelative(-58f, 174f)
                    lineToRelative(30f, 54f)
                    close()
                }
            }.build()

            return _Sports_football!!
        }

    private var _Sports_football: ImageVector? = null
    private var _sportsBasketball: ImageVector? = null
}

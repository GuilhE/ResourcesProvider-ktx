package com.github.guilhe.resourcesprovider

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import androidx.test.core.app.ApplicationProvider
import com.github.guilhe.resourcesprovider.test.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class ResourcesProviderTest {

    private lateinit var appContext: Context
    private lateinit var resourcesProvider: ResourcesProvider
    private val ok = "OK"
    private val stringArray = arrayOf("Home", "Work", "Other", "Custom")
    private val wifiPlural = "Wi-Fi networks available"

    @Before
    fun init() {
        appContext = ApplicationProvider.getApplicationContext<Context>()
        resourcesProvider = ResourcesProvider(appContext)
    }

    @Test
    fun context() {
        assertEquals(resourcesProvider.ctx, appContext)
    }

    @Test
    fun text() {
        assertEquals(resourcesProvider.text(R.string.ok), ok)
    }

    @Test
    fun textArray() {
        assertArrayEquals(resourcesProvider.textArray(R.array.emailAddressTypes), stringArray)
    }

    @Test
    fun textQuantity() {
        assertEquals(resourcesProvider.quantityText(R.plurals.wifi_available, 2), wifiPlural)
    }

    @Test
    fun string() {
        assertEquals(resourcesProvider.string(R.string.ok), ok)
    }

    @Test
    fun stringArgs() {
        assertEquals(resourcesProvider.string(R.string.ok, Locale.getDefault()), ok)
    }

    @Test
    fun stringArray() {
        assertArrayEquals(resourcesProvider.stringArray(R.array.emailAddressTypes), stringArray)
    }

    @Test
    fun stringQuantity() {
        assertEquals(resourcesProvider.quantityString(R.plurals.wifi_available, 2), wifiPlural)
    }

    @Test
    fun stringQuantityArgs() {
        assertEquals(resourcesProvider.quantityString(R.plurals.wifi_available, 2, Locale.getDefault()), wifiPlural)
    }

    @Test
    fun int() {
        assertEquals(resourcesProvider.integer(R.integer.status_bar_notification_info_maxnum), 999)
    }

    @Test
    fun intArray() {
        assertArrayEquals(resourcesProvider.intArray(R.array.intArray), intArrayOf(2, 8, 10, 16))
    }

    @Test
    fun bool() {
        assertEquals(resourcesProvider.boolean(R.bool.isTesting), true)
    }

    @Test
    fun dimension() {
        val dp = 10 * Resources.getSystem().displayMetrics.density
        assertEquals(resourcesProvider.dimension(R.dimen.dimen_1), dp)
    }

    @Test
    fun dimensionPixelOffset() {
        val dp = (10 * Resources.getSystem().displayMetrics.density).toInt()
        assertEquals(resourcesProvider.dimensionPixelOffset(R.dimen.dimen_1), dp)
    }

    @Test
    fun dimensionPixelSize() {
        val dp = (10 * Resources.getSystem().displayMetrics.density).toInt()
        assertEquals(resourcesProvider.dimensionPixelSize(R.dimen.dimen_1), dp)
    }

    @Test
    fun drawable() {
        assertNotNull(resourcesProvider.drawable(R.drawable.ic_android_black_24dp))
    }

    @Test
    fun color() {
        assertEquals(resourcesProvider.color(R.color.black), Color.BLACK)
        assertEquals(resourcesProvider.color(android.R.attr.textColor, R.style.AppTheme_TextView), Color.BLACK)
    }

    @Test
    fun colorStateList() {
        val a = resourcesProvider.colorStateList(R.drawable.color_selector)!!
        val b = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
            intArrayOf(Color.BLACK, Color.WHITE)
        )
        assertEquals(a.changingConfigurations, b.changingConfigurations)
    }

    @Test
    fun font() {
        assertNotNull(resourcesProvider.font(R.font.roboto_bold))
    }

    @Test
    fun loadAnimation() {
        assertNotNull(resourcesProvider.loadAnimation(R.anim.stay))
    }

    @Test
    fun resolveAttribute() {
        resourcesProvider = ResourcesProvider(appContext, R.style.AppTheme_TextView)
        val value = TypedValue()

        assertEquals(resourcesProvider.resolveAttribute(android.R.attr.actionBarSize, value, false), false)

        assertEquals(resourcesProvider.resolveAttribute(android.R.attr.fontFamily, value, false), true)
        assertEquals(value.type, TypedValue.TYPE_REFERENCE)

        resourcesProvider.resolveAttribute(android.R.attr.textColor, value, true)
        assertEquals(resourcesProvider.color(value.resourceId), resourcesProvider.color(R.color.black))

        val valueStyled = TypedValue()
        resourcesProvider.resolveAttribute(android.R.attr.textColor, R.style.AppTheme_TextView, valueStyled, true)
        assertEquals(valueStyled.resourceId, value.resourceId)
        assertEquals(resourcesProvider.color(valueStyled.resourceId), resourcesProvider.color(value.resourceId))
    }

    @Test
    fun value() {
        assertEquals(resourcesProvider.value(R.bool.isTesting, true).type, TypedValue.TYPE_INT_BOOLEAN)
        assertNotEquals(resourcesProvider.value(R.bool.isTesting, true).type, TypedValue.TYPE_FLOAT)
    }

    @Test
    fun identifier() {
        assertEquals(resourcesProvider.identifier("ic_android_black_24dp", "drawable", appContext.getPackageName()), R.drawable.ic_android_black_24dp)
        assertNotEquals(resourcesProvider.identifier("icon", "drawable", appContext.getPackageName()), R.drawable.ic_android_black_24dp)
    }
}
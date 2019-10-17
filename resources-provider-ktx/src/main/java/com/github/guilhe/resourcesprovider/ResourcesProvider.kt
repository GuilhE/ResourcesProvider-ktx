package com.github.guilhe.resourcesprovider

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

@Suppress("unused", "NOTHING_TO_INLINE")
class ResourcesProvider
constructor(val ctx: Context) {

    inline fun text(@StringRes resId: Int): CharSequence = ctx.getText(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun textArray(@ArrayRes resId: Int): Array<CharSequence> = ctx.resources.getTextArray(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun quantityText(@PluralsRes resId: Int, quantity: Int): CharSequence = ctx.resources.getQuantityText(resId, quantity)

    inline fun string(@StringRes resId: Int): String = ctx.getString(resId)

    inline fun string(@StringRes resId: Int, vararg formatArgs: Any): String = ctx.getString(resId, *formatArgs)

    @Throws(Resources.NotFoundException::class)
    inline fun stringArray(@ArrayRes resId: Int): Array<String> = ctx.resources.getStringArray(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun quantityString(@PluralsRes resId: Int, quantity: Int): String = ctx.resources.getQuantityString(resId, quantity)

    @Throws(Resources.NotFoundException::class)
    inline fun quantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any): String =
        ctx.resources.getQuantityString(resId, quantity, *formatArgs)

    @Throws(Resources.NotFoundException::class)
    inline fun integer(@IntegerRes resId: Int): Int = ctx.resources.getInteger(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun intArray(@ArrayRes resId: Int): IntArray = ctx.resources.getIntArray(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun boolean(@BoolRes resId: Int): Boolean = ctx.resources.getBoolean(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun dimension(@DimenRes resId: Int): Float = ctx.resources.getDimension(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun dimensionPixelSize(@DimenRes resId: Int): Int = ctx.resources.getDimensionPixelSize(resId)

    @Throws(Resources.NotFoundException::class)
    inline fun dimensionPixelOffset(@DimenRes resId: Int): Int = ctx.resources.getDimensionPixelOffset(resId)

    inline fun drawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(ctx, resId)

    @ColorInt
    inline fun color(@ColorRes resId: Int): Int = ContextCompat.getColor(ctx, resId)

    @Throws(Resources.NotFoundException::class)
    inline fun colorStateList(@ColorRes resId: Int): ColorStateList? = ContextCompat.getColorStateList(ctx, resId)

    @Throws(Resources.NotFoundException::class)
    inline fun font(@FontRes id: Int): Typeface? = ResourcesCompat.getFont(ctx, id)

    @Throws(Resources.NotFoundException::class)
    inline fun loadAnimation(@AnimRes id: Int): Animation = AnimationUtils.loadAnimation(ctx, id)

    inline fun resolveAttribute(@AttrRes id: Int, outValue: TypedValue, resolveRefs: Boolean): Boolean {
        return ctx.theme.resolveAttribute(id, outValue, resolveRefs)
    }

    @Throws(Resources.NotFoundException::class)
    inline fun value(@DimenRes id: Int, resolveRefs: Boolean): TypedValue = TypedValue().apply {
        ctx.resources.getValue(id, this, resolveRefs)
    }

    inline fun identifier(name: String, defType: String, defPackage: String): Int = ctx.resources.getIdentifier(name, defType, defPackage)
}

//Helper extensions
@Suppress("unused")
fun View.resourcesProvider(): ResourcesProvider {
    return ResourcesProvider(context)
}

@Suppress("unused")
fun Activity.resourcesProvider(): ResourcesProvider {
    return ResourcesProvider(this)
}

@Suppress("unused")
@Throws(IllegalStateException::class)
fun Fragment.resourcesProvider(): ResourcesProvider {
    return ResourcesProvider(requireContext())
}
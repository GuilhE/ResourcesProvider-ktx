# ResourcesProvider-ktx
[![Build Status](https://travis-ci.org/GuilhE/ResourcesProvider-ktx.svg?branch=master)](https://travis-ci.org/GuilhE/ResourcesProvider-ktx) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ResourcesProvider--ktx-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7921)

Need a resource but don't remember where to get it from: ContextCompat, ResourcesCompat, AnimationUtils, Context.resources?  
Fear no more, this lib puts them all in one place! 🤩🥳

## Getting started

The first step is to include ResourcesProvider-ktx into your project, for example, as a Gradle compile dependency:

```groovy
implementation 'com.github.guilhe:resources-provider-ktx:${LATEST_VERSION}'
```
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.guilhe/resources-provider-ktx/badge.svg)](https://search.maven.org/search?q=g:com.github.guilhe%20AND%20resources-provider-ktx) [![Download](https://api.bintray.com/packages/gdelgado/android/ResourcesProvider-ktx/images/download.svg)](https://bintray.com/gdelgado/android/ResourcesProvider-ktx/_latestVersion)

## Resources types

```java
- text(@StringRes resId: Int): CharSequence
- textArray(@ArrayRes resId: Int): Array<CharSequence>
- quantityText(@PluralsRes resId: Int, quantity: Int): CharSequence
- string(@StringRes resId: Int): String
- string(@StringRes resId: Int, vararg formatArgs: Any): String
- stringArray(@ArrayRes resId: Int): Array<String>
- quantityString(@PluralsRes resId: Int, quantity: Int): String
- quantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any): String
- integer(@IntegerRes resId: Int): Int
- intArray(@ArrayRes resId: Int): IntArray
- boolean(@BoolRes resId: Int): Boolean
- dimension(@DimenRes resId: Int): Float
- dimensionPixelSize(@DimenRes resId: Int): Int
- dimensionPixelOffset(@DimenRes resId: Int): Int
- drawable(@DrawableRes resId: Int): Drawable?
- drawable(@DrawableRes id: Int, @StyleRes themeResId: Int): Drawable?
- drawableForDensity(@DrawableRes id: Int, @StyleRes themeResId: Int, density: Int): Drawable?
- color(@ColorRes resId: Int): Int
- color(@AttrRes attrResId: Int, @StyleRes themeResId: Int): Int
- colorStateList(@ColorRes resId: Int): ColorStateList?
- colorStateList(@ColorRes id: Int, @StyleRes themeResId: Int): ColorStateList?
- font(@FontRes id: Int): Typeface?
- loadAnimation(@AnimRes id: Int): Animation
- resolveAttribute(@AttrRes id: Int, outValue: TypedValue, resolveRefs: Boolean): Boolean
- resolveAttribute(@AttrRes id: Int, @StyleRes themeResId: Int, outValue: TypedValue, resolveRefs: Boolean): Boolean
- value(@DimenRes id: Int, resolveRefs: Boolean): TypedValue
- identifier(name: String, defType: String, defPackage: String): Int
```
## Themes

You can also easily change theme attributes as follow:

by `@ColorRes`:  
```java
.setBackgroundColor( 
    TypedValue().also {
        resourcesProvider.resolveAttribute(R.attr.colorPrimary, R.style.App_Style_A, it, true)
    }.resourceId
)
```

by `@ColorInt`:  
```java
.backgroundTintList = ColorStateList.valueOf(resourcesProvider.colorStateList(R.attr.colorPrimary, R.style.App_Style_B))
.setColor(resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_C))
```

## Sample usage

By Kotlin Extensions (View, Activity, Fragment):
```java
class Activity : AppCompatActivity(){
    fun work() {
        resourcesProvider().string(R.string.app_name_label)
    }
}
```
By `@Inject`:
```java
class MyClass
@Inject constructor(resourcesProvider: ResourcesProvider)

fun work(){
    resourcesProvider.color(R.color.color_1)
}
``` 
To add to your dependencies graph using Dagger:
```java
    @JvmStatic
    @Provides
    @Singleton
    fun provideResourcesProvider(context: YourApp): ResourcesProvider{
        return ResourcesProvider(context)
    }
```
Done! 😎
    
## Dependencies
```groovy
'androidx.core:core-ktx'
'androidx.fragment:fragment-ktx'
```

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/GuilhE/ResourcesProvider-ktx/issues).

## Binaries
Additional binaries and dependency information for can be found [here](https://search.maven.org/artifact/com.github.guilhe/resources-provider-ktx).  
<a href='https://bintray.com/gdelgado/android/ResourcesProvider-ktx?source=watch' alt='Get automatic notifications about new "ResourcesProvider-ktx" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_bw.png'></a>

## LICENSE

Copyright (c) 2019-present GuilhE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

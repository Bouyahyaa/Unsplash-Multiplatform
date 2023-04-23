package com.bouyahya.unsplash_multiplatform.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.bouyahya.unsplash_multiplatform.ui.home.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class UnsplashRootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    val stack: Value<ChildStack<*, Child>> = _stack

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.Home -> Child.Home(home(componentContext))
        }
    }

    private fun home(componentContext: ComponentContext) =
        HomeViewModel(
            componentContext,
            getPicturesUseCase = get(),
            deletePicturesUseCase = get(),
            likePicturesUseCase = get()
        )


    sealed class Child {
        class Home(val component: HomeViewModel) : Child()
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object Home : Config()
    }
}
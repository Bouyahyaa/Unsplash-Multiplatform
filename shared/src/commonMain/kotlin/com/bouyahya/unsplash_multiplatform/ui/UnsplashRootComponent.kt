package com.bouyahya.unsplash_multiplatform.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.bouyahya.unsplash_multiplatform.domain.model.Picture
import com.bouyahya.unsplash_multiplatform.ui.details.PictureDetailsViewModel
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
            is Config.Details -> Child.Details(pictureDetails(componentContext, config))
        }
    }

    private fun home(componentContext: ComponentContext) =
        HomeViewModel(
            componentContext,
            getPicturesUseCase = get(),
            deletePicturesUseCase = get(),
            likePicturesUseCase = get()
        ) {
            navigation.push(Config.Details(it))
        }

    private fun pictureDetails(componentContext: ComponentContext, config: Config.Details) =
        PictureDetailsViewModel(componentContext, picture = config.picture) {
            navigation.pop()
        }


    sealed class Child {
        class Home(val component: HomeViewModel) : Child()
        class Details(val component: PictureDetailsViewModel) : Child()
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object Home : Config()

        @Parcelize
        data class Details(val picture: Picture) : Config()
    }
}
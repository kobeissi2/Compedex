package com.kobeissidev.jetpackcomposepokedex.ui.screen.detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kobeissidev.jetpackcomposepokedex.data.model.pokemon.Pokemon
import com.kobeissidev.jetpackcomposepokedex.data.model.supplementary.Palette
import com.kobeissidev.jetpackcomposepokedex.ui.composable.ErrorLayout
import com.kobeissidev.jetpackcomposepokedex.ui.composable.LoadingLayout
import com.kobeissidev.jetpackcomposepokedex.ui.screen.MainViewModel
import com.kobeissidev.jetpackcomposepokedex.ui.screen.detail.section.ArtworkSection
import com.kobeissidev.jetpackcomposepokedex.ui.screen.detail.section.DetailHeaderSection
import com.kobeissidev.jetpackcomposepokedex.ui.screen.detail.section.InfoCard
import com.kobeissidev.jetpackcomposepokedex.util.onStateChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

/**
 * Specific Pokemon Detail screen.
 */
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun DetailScreen(viewModel: MainViewModel = hiltViewModel()) {
    val pokemonState by viewModel.fetchingStatusFlow.collectAsState()
    val speciesState by viewModel.speciesFlow.collectAsState()
    val orientation by viewModel.orientationFlow.collectAsState(initial = viewModel.currentOrientation)

    pokemonState.onStateChanged(
        onLoading = { LoadingLayout(isShowImage = true) },
        onFailure = { ErrorLayout(onRefresh = { viewModel.refetchPokemon() }) }
    ) { pokemon ->
        val palette by remember { mutableStateOf(pokemon.shuffledPalette) }

        speciesState.onStateChanged(
            onLoading = { LoadingLayout(isShowImage = true) },
            onFailure = { Timber.e(it) }
        ) {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> DetailLandscapeSection(
                    pokemon = pokemon,
                    palette = palette
                )
                else -> DetailPortraitSection(
                    pokemon = pokemon,
                    palette = palette
                )
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
private fun DetailPortraitSection(
    pokemon: Pokemon,
    palette: Palette
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = pokemon.primaryPalette.backgroundColor.asComposeColor)
    ) {
        DetailHeaderSection(pokemon, palette)
        ArtworkSection(pokemon = pokemon)
        InfoCard(
            pokemon = pokemon,
            palette = palette
        )
    }
}

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
private fun DetailLandscapeSection(
    pokemon: Pokemon,
    palette: Palette
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = pokemon.primaryPalette.backgroundColor.asComposeColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(all = 16.dp)
        ) {
            DetailHeaderSection(pokemon, palette)
            ArtworkSection(pokemon = pokemon)
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            InfoCard(
                pokemon = pokemon,
                palette = palette
            )
        }
    }
}
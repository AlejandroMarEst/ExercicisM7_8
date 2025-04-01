package cat.itb.m78.exercices.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.API.EmbassyInfoAPIViewModel
import kotlinx.datetime.format.Padding

@Composable
fun PokedexViewmodelView(ToPokeFavs : () -> Unit, ToPokeInfo : () -> Unit){
    val viewModel = viewModel{ PokedexViewmodel() }
    PokedexView(viewModel.pokeFavs.value, ToPokeFavs, ToPokeInfo)
}

@Composable
fun PokedexView(pokemons : List<PokemonWithFavs>?, ToPokeFavs : () -> Unit, ToPokeInfo : () -> Unit) {
    Column(Modifier.fillMaxSize().background(color = Color.hsv(349.95f, 0.9f, 0.8f)), Arrangement.Center, Alignment.CenterHorizontally) {
        Card(colors = CardDefaults.cardColors(
            containerColor = Color.hsv(193.92f, 0.5f, 0.8f)),
            border = BorderStroke(10.dp, Color.Black),
            modifier = Modifier.size(width = 600.dp, height = 700.dp)
        ) {
        if (pokemons != null) {
            LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(pokemons) { pokemonIndex ->
                    Row(Modifier.fillMaxSize(), Arrangement.SpaceBetween){
                        TextButton(onClick = { ToPokeInfo() }) {
                            Text((pokemons.indexOf(pokemonIndex) + 1).toString(), textAlign = TextAlign.Center, fontSize = 0.9.em, color = Color.White)
                            Text(pokemonIndex.pokemon.name.replaceFirstChar { it.titlecase() }, textAlign = TextAlign.Center, fontSize = 0.9.em, color = Color.White)
                        }
                        Icon(
                            Icons.Outlined.Star,
                            contentDescription = null
                        )
                    }
                }
            }
        }else{
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }}
    }
}
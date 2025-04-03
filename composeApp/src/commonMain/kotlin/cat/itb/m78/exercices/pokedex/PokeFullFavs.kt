package cat.itb.m78.exercices.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.API.EmbassyInfoAPIViewModel
import kotlinx.datetime.format.Padding

@Composable
fun PokefavsViewmodelView(ToPokeFavs : () -> Unit, ToPokeInfo : () -> Unit){
    val viewModel = viewModel{ PokefavsViewmodel() }
    PokefavsView(viewModel.pokeFavs.value, ToPokeFavs, ToPokeInfo, viewModel::toggleFav)
}

@Composable
fun PokefavsView(pokemons : List<PokemonWithFavs>?, ToPokeFavs : () -> Unit, ToPokeInfo : () -> Unit, ToggleFav : (PokemonWithFavs) -> Unit) {
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
                            TextButton(onClick = { ToPokeInfo() }, Modifier.width(550.dp)) {
                                Row(Modifier.fillMaxSize()){
                                    Text(
                                        (pokemons.indexOf(pokemonIndex) + 1).toString(),
                                        textAlign = TextAlign.Center,
                                        fontSize = 0.9.em,
                                        color = Color.White,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        pokemonIndex.pokemon.name.replaceFirstChar { it.titlecase() },
                                        textAlign = TextAlign.Center,
                                        fontSize = 0.9.em,
                                        color = Color.White,
                                        modifier = Modifier.weight(3f).padding(5.dp)
                                    )
                                }
                            }
                            TextButton(onClick = {ToggleFav(pokemonIndex)}, Modifier.fillMaxSize()) {
                                if (pokemonIndex.fav){
                                    Icon(
                                        Icons.Outlined.Favorite,
                                        contentDescription = null,
                                        Modifier.fillMaxSize()
                                    )
                                } else {
                                    Icon(
                                        Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        Modifier.fillMaxSize()
                                    )
                                }
                            }
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
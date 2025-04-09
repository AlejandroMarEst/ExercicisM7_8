package cat.itb.m78.exercices.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun PokeInfoViewmodelView(ToPokedex : () -> Unit, ToPokefavs : () -> Unit, pokemon : Int){
    val viewmodel = viewModel{ PokeFullInfoViewModel(pokemon) }
    PokeInfoView(
        viewmodel.pokemon.value,
        viewmodel.shiny.value,
        viewmodel.male.value,
        viewmodel::toggleShiny,
        viewmodel::toggleMale,
        ToPokedex,
        ToPokefavs
    )
}

@Composable
fun PokeInfoView(pokemon: PokemonInfo?, shiny : Boolean, male : Boolean, toggleShiny : () -> Unit, toggleMale : () -> Unit, ToPokedex: () -> Unit, ToPokefavs: () -> Unit){
    Column(Modifier.fillMaxSize().background(color = Color.hsv(349.95f, 0.9f, 0.8f)), Arrangement.Center, Alignment.CenterHorizontally) {
        Card(colors = CardDefaults.cardColors(
            containerColor = Color.hsv(193.92f, 0.5f, 0.8f)),
            border = BorderStroke(10.dp, Color.Black),
            modifier = Modifier.size(width = 600.dp, height = 700.dp)
        ) {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(actions = {
                            Row(
                                Modifier.fillMaxSize().background(color = Color.hsv(193.92f, 0.5f, 0.8f)),
                                horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                                TextButton(onClick = { ToPokedex() }, Modifier.size(250.dp)) {
                                    Row {
                                        Icon(
                                            Icons.Filled.Menu,
                                            contentDescription = "Menu description",
                                        )
                                        Text("Pokedex",
                                            textAlign = TextAlign.Center,
                                            fontSize = 0.9.em,
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp))
                                    }
                                }
                                TextButton(onClick = { ToPokefavs() }, Modifier.size(250.dp)) {
                                    Row {
                                        Icon(
                                            Icons.Filled.Favorite,
                                            contentDescription = "Favorite description",
                                        )
                                        Text("Fav Pokemon",
                                            textAlign = TextAlign.Center,
                                            fontSize = 0.9.em,
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp))
                                    }
                                }
                            }
                        })
                    }
                ){
                    Column(
                        Modifier.fillMaxSize().background(color = Color.hsv(193.92f, 0.5f, 0.8f)),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        if (pokemon!=null) {
                            displayImg(shiny, male, pokemon.sprites)
                            Row {
                                TextButton(
                                    onClick = { toggleShiny() },
                                    Modifier.size(50.dp)
                                ) {
                                    if (shiny) {
                                        Icon(
                                            Icons.Outlined.Star,
                                            contentDescription = null,
                                            Modifier.fillMaxSize()
                                        )
                                    } else {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null,
                                            Modifier.fillMaxSize()
                                        )
                                    }
                                }
                                TextButton(
                                    onClick = { toggleMale() },
                                    Modifier.size(50.dp)
                                ) {
                                    Row {
                                        if (male) {
                                            Icon(
                                                Icons.Filled.Person,
                                                contentDescription = null,
                                                Modifier.fillMaxSize()
                                            )
                                            Text("Male")
                                        } else {
                                            Icon(
                                                Icons.Filled.Person,
                                                contentDescription = null,
                                                Modifier.fillMaxSize()
                                            )
                                            Text("Female")
                                        }
                                    }
                                }
                            }
                            Row {
                                Text("#" + pokemon.numPokedex.toString(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 0.9.em,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp))
                                Text(pokemon.name.replaceFirstChar { it.titlecase() },
                                    textAlign = TextAlign.Center,
                                    fontSize = 0.9.em,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp))
                            }
                            Row {
                                Text(pokemon.primaryType!!.type.name.replaceFirstChar { it.titlecase() },
                                    textAlign = TextAlign.Center,
                                    fontSize = 0.9.em,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp))
                                if (pokemon.secondaryType!=null) {
                                    Text(pokemon.secondaryType!!.type.name.replaceFirstChar { it.titlecase() },
                                        textAlign = TextAlign.Center,
                                        fontSize = 0.9.em,
                                        color = Color.White,
                                        modifier = Modifier.padding(5.dp))
                                }
                            }
                            Row {
                                Text("Height: " + pokemon.height,
                                    textAlign = TextAlign.Center,
                                    fontSize = 0.9.em,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp))
                                Text("Weight: " + pokemon.weight,
                                    textAlign = TextAlign.Center,
                                    fontSize = 0.9.em,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp))
                            }
                        }
                    }
                }
        }
    }
}

@Composable
fun displayImg(shiny: Boolean, male: Boolean, sprites: Sprites){
    Row (Modifier.fillMaxWidth().height(320.dp), Arrangement.Center) {
        if (!shiny && male || !shiny && sprites.frontDefaultFem == null) {
            AsyncImage(
                model = sprites.frontDefaultMasc,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
            if (sprites.backDefaultMasc!=null) {
                AsyncImage(
                    model = sprites.backDefaultMasc,
                    contentDescription = "Translated description of what the image contains",
                    Modifier.size(300.dp)
                )
            }
        } else if (shiny && male || shiny && sprites.frontShinyFem == null) {
            AsyncImage(
                model = sprites.frontShinyMasc,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
            if (sprites.backShinyMasc!=null) {
                AsyncImage(
                    model = sprites.backShinyMasc,
                    contentDescription = "Translated description of what the image contains",
                    Modifier.size(300.dp)
                )
            }
        } else if (!shiny && !male) {
            AsyncImage(
                model = sprites.frontDefaultFem,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
            AsyncImage(
                model = sprites.backDefaultFem,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
        } else {
            AsyncImage(
                model = sprites.frontShinyFem,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
            AsyncImage(
                model = sprites.backShinyFem,
                contentDescription = "Translated description of what the image contains",
                Modifier.size(300.dp)
            )
        }
    }
}
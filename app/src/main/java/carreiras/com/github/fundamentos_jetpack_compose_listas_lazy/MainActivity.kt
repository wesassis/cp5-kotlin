package carreiras.com.github.fundamentos_jetpack_compose_listas_lazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.components.GameCard
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.components.StudioCard
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.model.Game
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.repository.getAllGames
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.repository.getGamesByStudio
import carreiras.com.github.fundamentos_jetpack_compose_listas_lazy.ui.theme.FundamentosjetpackcomposelistaslazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FundamentosjetpackcomposelistaslazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    var searchTextState by remember { mutableStateOf("") }
    var filteredGames by remember { mutableStateOf(getAllGames()) }
    val allGames = remember { getAllGames() }
    var isFiltered by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Meus jogos favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchTextState,
            onValueChange = {
                searchTextState = it
                if (it.isBlank()) {
                    filteredGames = allGames
                    isFiltered = false
                } else {
                    filteredGames = getGamesByStudio(it)
                    isFiltered = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nome do estúdio") },
            trailingIcon = {
                IconButton(onClick = {
                    filteredGames = getGamesByStudio(searchTextState)
                    isFiltered = searchTextState.isNotBlank()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            }
        )
        // Botão de limpar filtro
        if (isFiltered) {
            Text(
                text = "Limpar filtro",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        searchTextState = ""
                        filteredGames = allGames
                        isFiltered = false
                    },
                fontWeight = FontWeight.SemiBold,
                color = androidx.compose.ui.graphics.Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow() {
            items(allGames.distinctBy { it.studio }) { game ->
                StudioCard(game = game, onClick = {
                    searchTextState = game.studio
                    filteredGames = getGamesByStudio(game.studio)
                    isFiltered = true
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn() {
            items(filteredGames) {
                GameCard(game = it)
            }
        }
    }
}

@Preview(showBackground = true, name = "Games Screen Preview")
@Composable
fun PreviewGamesScreen() {
    FundamentosjetpackcomposelistaslazyTheme {
        GamesScreen()
    }
}

@Preview(showBackground = true, name = "Studio Card Preview")
@Composable
fun PreviewStudioCard() {
    FundamentosjetpackcomposelistaslazyTheme {
        StudioCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}

@Preview(showBackground = true, name = "Game Card Preview")
@Composable
fun PreviewGameCard() {
    FundamentosjetpackcomposelistaslazyTheme {
        GameCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}

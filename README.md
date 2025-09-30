Fundamentos Jetpack Compose - Listas Lazy

Este projeto é um aplicativo Android desenvolvido em Kotlin, utilizando Jetpack Compose para a construção de interfaces modernas e reativas. O objetivo principal é demonstrar o uso de listas Lazy (LazyColumn e LazyRow) para exibir e filtrar jogos favoritos por estúdio.

Integrantes da Equipe

•
Wesley Assis - RM 552516

•
Guilherme Silva - RM 98928

Funcionalidades Implementadas

1.
Filtragem por Caixa de Texto: A aplicação permite filtrar a lista de jogos pelo nome do estúdio através de uma caixa de texto. O botão "Limpar filtro" é exibido quando um filtro está ativo.

2.
Botão "Limpar Filtro": Um botão "Limpar filtro" é exibido quando um filtro está ativo, permitindo ao usuário remover o filtro e visualizar todos os jogos novamente.

3.
Filtragem por Clique no Estúdio (LazyRow): Ao clicar em um estúdio na LazyRow superior, a lista de jogos é filtrada para mostrar apenas os jogos daquele estúdio. O botão "Limpar filtro" também é exibido nesta situação.

Como Funciona e Por Que Funciona

As funcionalidades de filtro e o botão "Limpar Filtro" foram implementadas e aprimoradas na função @Composable GamesScreen dentro do arquivo MainActivity.kt. A lógica central reside no gerenciamento de estado reativo do Jetpack Compose.

•
Gerenciamento de Estado: Utilizamos mutableStateOf para criar variáveis de estado observáveis:

•
searchTextState: Armazena o texto digitado na caixa de busca.

•
filteredGames: Contém a lista de jogos atualmente exibida, que pode ser a lista completa ou uma lista filtrada.

•
allGames: Uma cópia imutável da lista original de todos os jogos, usada como referência para resetar os filtros.



•
Filtragem por Caixa de Texto:

•
Quando o usuário digita na OutlinedTextField, o onValueChange é acionado. Ele atualiza searchTextState e, imediatamente, recalcula filteredGames chamando getGamesByStudio(it). Se a caixa de texto estiver vazia (it.isBlank()), filteredGames é resetado para allGames.

•
Por que funciona: O Jetpack Compose observa as mudanças em searchTextState e filteredGames. Quando essas variáveis de estado são atualizadas, a UI é automaticamente recomposta (redesenhada) para refletir os novos valores, exibindo a lista de jogos filtrada em tempo real.



•
Botão "Limpar Filtro":

•
O botão "Limpar filtro" é um Text clicável que é exibido condicionalmente. Sua visibilidade é controlada pela expressão if (searchTextState.isNotEmpty() || filteredGames != allGames). Isso significa que ele aparece se houver texto na caixa de busca OU se a lista de jogos exibida (filteredGames) for diferente da lista original (allGames), indicando que um filtro está ativo.

•
Ao ser clicado, o onClick do botão redefine searchTextState para uma string vazia e filteredGames para allGames.

•
Por que funciona: Ao resetar searchTextState e filteredGames para seus estados iniciais, o Jetpack Compose detecta essas mudanças de estado e recompõe a UI, removendo o texto do filtro e exibindo novamente todos os jogos. A condição de visibilidade do botão também é reavaliada, fazendo-o desaparecer se nenhum filtro estiver mais ativo.



•
Filtragem por Clique no Estúdio (LazyRow):

•
A LazyRow exibe StudioCards para cada estúdio único. O items(allGames.distinctBy { it.studio }) garante que cada estúdio seja listado apenas uma vez.

•
Quando um StudioCard é clicado, seu onClick é acionado. Ele atualiza searchTextState com o nome do estúdio clicado e, em seguida, atualiza filteredGames chamando getGamesByStudio(game.studio).

•
Por que funciona: Similar à filtragem por caixa de texto, a atualização de searchTextState e filteredGames dispara uma recomposição da UI. A LazyColumn de jogos é redesenhada para mostrar apenas os jogos do estúdio selecionado. O searchTextState também é atualizado para que a caixa de texto reflita o estúdio selecionado, e o botão "Limpar filtro" aparece, oferecendo a opção de desfazer a seleção.



Estrutura do Projeto

Plain Text


app/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   │   └── carreiras/com/github/fundamentos_jetpack_compose_listas_lazy/
 │   │   │        ├── MainActivity.kt         # Tela principal e lógica de UI
 │   │   │        ├── components/
 │   │   │        │    ├── GameCard.kt       # Componente visual para jogos
 │   │   │        │    └── StudioCard.kt     # Componente visual para estúdios
 │   │   │        ├── model/
 │   │   │        │    └── Game.kt           # Modelo de dados para jogos
 │   │   │        ├── repository/
 │   │   │        │    ├── GameRepository.kt # Funções de acesso e filtro de dados
 │   │   │        │    └── ...
 │   │   │        └── ui/theme/              # Temas e estilos
 │   │   └── res/                            # Recursos (layouts, strings, etc)
 │   └── ...
 └── ...


Como rodar o projeto

1.
Clone este repositório:

2.
Abra o projeto no Android Studio.

3.
Sincronize o projeto com os arquivos Gradle.

4.
Execute em um emulador ou dispositivo físico Android.

Tecnologias utilizadas

•
Kotlin

•
Jetpack Compose

•
Material 3

•
Gradle Kotlin DSL



# Fundamentos Jetpack Compose - Listas Lazy

Este projeto é um aplicativo Android desenvolvido em **Kotlin**, utilizando **Jetpack Compose** para a construção de interfaces modernas e reativas.  
O objetivo principal é demonstrar o uso de listas **Lazy** (`LazyColumn` e `LazyRow`) para exibir e filtrar jogos favoritos por estúdio.

---

## Integrantes da Equipe
- Wesley Assis — RM 552516  
- Guilherme Silva — RM 98928
- Pablo Aguayo - RM 551548

---

## Funcionalidades Implementadas

1. **Filtragem por Caixa de Texto**  
   - Permite filtrar a lista de jogos pelo nome do estúdio.  
   - O botão **"Limpar filtro"** é exibido quando um filtro está ativo.

2. **Botão "Limpar Filtro"**  
   - Remove o filtro e exibe todos os jogos novamente.  
   - Só aparece quando há filtro ativo.

3. **Filtragem por Clique no Estúdio (LazyRow)**  
   - Ao clicar em um estúdio, a lista mostra apenas os jogos daquele estúdio.  
   - O botão **"Limpar filtro"** também aparece nesta situação.

---

## Como Funciona

As funcionalidades foram implementadas na função `@Composable GamesScreen`, localizada no arquivo `MainActivity.kt`.  
A lógica central utiliza o **gerenciamento de estado reativo** do Jetpack Compose.

### Gerenciamento de Estado
- `searchTextState`: Texto digitado na caixa de busca.  
- `filteredGames`: Lista exibida (completa ou filtrada).  
- `allGames`: Lista imutável de referência com todos os jogos.  

### Filtragem por Caixa de Texto
- Ao digitar, `onValueChange` atualiza `searchTextState` e `filteredGames`.  
- Se o campo está vazio, a lista volta para `allGames`.  
- Funciona porque o Compose recompõe a UI ao detectar mudanças de estado.

### Botão "Limpar Filtro"
- Visível apenas se houver texto na busca ou lista filtrada.  
- Ao clicar:
  - `searchTextState = ""`  
  - `filteredGames = allGames`  
- Funciona porque o Compose recompõe a UI e reavalia a condição de visibilidade.

### Filtragem por Clique no Estúdio
- A **LazyRow** exibe estúdios únicos (`distinctBy { it.studio }`).  
- Ao clicar:
  - `searchTextState` recebe o estúdio.  
  - `filteredGames` mostra apenas os jogos correspondentes.  
- Funciona porque o Compose recompõe a UI e sincroniza filtro e campo de busca.

---
<img width="1365" height="719" alt="Print Nome Limpo" src="https://github.com/user-attachments/assets/c82dc02e-7e61-43bc-b18c-d45765204005" />
<img width="1364" height="721" alt="Print Nome Wesley" src="https://github.com/user-attachments/assets/da7c420e-8e81-444f-96ea-ffefa089dcdf" />
<img width="1363" height="720" alt="image" src="https://github.com/user-attachments/assets/769eda53-f633-4f88-8254-48f2739a203d" />



## Estrutura do Projeto

```plaintext
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
 │   │   │        │    ├── GameRepository.kt # Acesso e filtro de dados
 │   │   │        └── ui/theme/              # Temas e estilos
 │   │   └── res/                            # Recursos (layouts, strings, etc.)
 │   └── ...
 └── ...


---

## Como Rodar o Projeto

1. Clone este repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Abra o projeto no Android Studio.  
3. Sincronize o projeto com os arquivos Gradle.  
4. Execute em um emulador ou dispositivo físico Android.  

---

## Tecnologias Utilizadas
- Kotlin  
- Jetpack Compose  
- Material 3  
- Gradle Kotlin DSL  
```


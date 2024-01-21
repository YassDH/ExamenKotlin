
# Top 100 Movies App

L'application mobile Top 100 Movies récupère la liste des 100 meilleurs films IMDb et offre des fonctionnalités telles que la visualisation détaillée des films, l'ajout aux favoris et la consultation de la liste des favoris.

## Fonctionnalités

1. **Affichage des Top 100 Films**
- L'application affiche une liste des 100 meilleurs films IMDb à partir d'une API.

2. **Détails du Film**
- En cliquant sur un film de la liste, l'utilisateur est redirigé vers une nouvelle page affichant des détails supplémentaires sur le film, y compris son image, titre, année, etc.

3. **Ajout aux Favoris**
- Sur la page de détails du film, l'utilisateur peut appuyer sur un bouton pour ajouter le film à sa liste de favoris.

4. **Affichage des Favoris**
- L'application dispose d'une page distincte permettant à l'utilisateur de consulter la liste de ses films favoris.

5. **Recherche de Films**
- Une option de recherche est disponible, accessible en cliquant sur le bas de l'écran, permettant à l'utilisateur de rechercher des films spécifiques.

## Architecture

L'application suit l'architecture MVVM (Modèle-Vue-VueModèle) pour une gestion propre des données et une séparation des préoccupations.

## API
Lien de l'API: https://rapidapi.com/rapihub-rapihub-default/api/imdb-top-100-movies/
|        Endpoint       | Method |                                         Description                                        |
|:---------------------|:------:|:------------------------------------------------------------------------------------------|
| /           |  GET  | Reetourne la liste des Top 100 films par IMDB                                              |
| /id        |  GET  | Retourne plus de détails supplémentaires sur le film choisi tels que le titre, l'année, le genre...                                       |

## Screenshots

| | | |
:-------------------------:|:-------------------------:|:-------------------------:
|Home| Search | Favourites |
| ![Home](/Screenshots/HomeScreen.png) | ![Search](/Screenshots/SearchScreen.png) | ![Favourites](/Screenshots/FavouritesScreen.png)
|Movie Details| Network Error | Fetch Error |
| ![Movie Details](/Screenshots/MovieDetailsScreen.png) | ![Errors](/Screenshots/NetworkErrorScreen.png.png) | ![Errors](/Screenshots/FetchErrorScreen.png.png) |


## Technologies Utilisées

- [Kotlin](https://kotlinlang.org/): Langage de programmation principal.
- [Jetpack Compose](https://developer.android.com/jetpack/compose):  Ensemble de bibliothèques Android recommandées pour le développement d'interfaces utilisateur déclaratives.
- [Retrofit](https://square.github.io/retrofit/): Client HTTP pour la récupération des données depuis une API.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Gestion des données et de la logique d'affichage.
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences): Stockage des données locales, notamment la liste des films favoris.


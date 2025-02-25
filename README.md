# CEP Finder App

CEP Finder is an Android application built with Kotlin and Jetpack Compose that allows users to search for addresses based on postal codes (CEP) or find postal codes based on address details.

## Features
- Search for an address using a CEP.
- Search for postal codes by providing state, city, and street name.
- Display search results in a list format.
- Error handling for failed searches.
- Modern UI using Jetpack Compose.

## Technologies Used
- **Kotlin**
- **Jetpack Compose**
- **Retrofit** (for API requests)
- **Material 3** (for UI components)

## Installation
1. Clone this repository:
   ```sh
   git clone https://github.com/humbertovitalino/Fiap-Kotlin.git
   ```
2. Open the project in **Android Studio**.
3. Build and run the app on an emulator or physical device.

## API Integration
This app uses an external API service to fetch address details. Ensure you have internet access for the API calls to function properly.

## Project Structure
```
app/
 ├── src/main/java/br/com/fiap/kotlinpracticefiap/
 │   ├── MainActivity.kt
 │   ├── model/Endereco.kt
 │   ├── screens/CepScreen.kt
 │   ├── service/RetroFitFactory.kt
 │   ├── ui/theme/
 │   │   ├── Color.kt
 │   │   ├── Theme.kt
 │   │   ├── Type.kt
```

## License
This project is open-source. Feel free to modify and distribute it as needed.



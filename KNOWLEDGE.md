# Closet Android — Project Knowledge Base

## Purpose

This is a native Android/Jetpack Compose wardrobe app named **Closet**. It mirrors the working wardrobe experience of the companion Django app while remaining fully offline: a person can add an item from the camera or gallery, label it, browse their wardrobe, delete an item, and filter recommendations by occasion.

## Stack

- Kotlin and Jetpack Compose with Material 3
- Single `ComponentActivity` (`MainActivity`)
- Minimum Android API 26; target/compile SDK 37
- Local persistence only: JPEG files in app-private storage and `SharedPreferences` metadata
- No network, database, dependency injection, navigation library, or backend integration

## Source Map

| Path | Responsibility |
| --- | --- |
| `app/src/main/java/com/hpyk/closet/MainActivity.kt` | Local wardrobe model/storage, camera/gallery intake, dashboard, gallery, recommendations, and UI |
| `app/src/main/java/com/hpyk/closet/ui/theme/` | Compose color, typography, and app theme |
| `app/src/main/AndroidManifest.xml` | Launcher activity and app configuration |
| `app/build.gradle.kts` | Android/Compose build configuration and dependencies |
| `gradle/libs.versions.toml` | Central library and plugin version catalog |

## Current Behaviour

1. **Home** provides Django-inspired dashboard cards for upload and recommendations plus a local item/occasion insight.
2. **Upload** takes a camera thumbnail or chooses an image from the gallery, then records gender, category, color, and occasion.
3. **Wardrobe** displays the locally owned items in a two-column gallery and supports delete.
4. **Recommend** has selectors for occasion, user-selected weather, mood, and an optional exact color filter. The dedicated **Recommend for me** button filters by occasion/color and ranks the matching items by weather-appropriate category and mood-associated color.
5. **Save** finds the first unused number from 1 to 100, writes `wardrobe_<number>.jpg` to `filesDir`, and stores the labels in `SharedPreferences("wardrobe_metadata")`.

## Important Types and State

- `WardrobeItem` contains the local numeric id, bitmap, and the four labels.
- `wardrobe` is a state list owned by `MainActivity`; `pendingPhoto` is the selected item that has not yet been saved.
- `Screen` controls the four bottom-navigation destinations: Home, Upload, Wardrobe, and Recommend.
- The fixed occasion choices match the Django template: Casual, Ethnic, Party, Formal, Smart Casual, Sports, and Travel.
- Weather is intentionally selected in the app (Warm, Hot, Cool, Rainy); it is not live weather data. Mood options are Relaxed, Confident, Happy, Elegant, and Energetic.

## Visual System

The app intentionally uses the Django project’s boutique styling rather than Android dynamic colors: burgundy `#5E0707`, white/ivory surfaces, charcoal text, blush accents, rounded cards, and burgundy pill call-to-action buttons. Theme colors live in `ui/theme/Color.kt` and `ui/theme/Theme.kt`.

## Constraints / Known Gaps

- Camera captures are low-resolution thumbnails and rely on an installed camera activity. A production capture flow should use `ActivityResultContracts.TakePicture` with a `FileProvider` URI.
- Images and metadata are limited to 100 numbered records. The first free number is reused if an image is removed manually.
- There is no account login, server sync, ML auto-labelling, edit-label action, outfit pairing, backup/export, or cloud authentication. Those need a defined backend/API contract.
- Metadata uses `SharedPreferences`, while image files use private storage; they can drift if files are removed independently.
- The app has only scaffolded default unit/instrumented tests; no app behaviour is covered yet.

## How to Build and Verify

From this project root on Windows:

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat test
```

Manual check: install on an API 26+ device/emulator with a camera app, capture an image, tag and save it, then select the same mood and press **RECOMMEND**.

## Suggested Next Steps

1. Replace thumbnail capture with full-resolution URI capture and runtime-safe file handling.
2. Introduce a repository plus Room/DataStore so photo records are transactional and not capped at 100.
3. Add a persistent gallery with delete/edit-mood actions.
4. Separate UI, state holder/ViewModel, and storage code; then add tests for slot selection and recommendation filtering.

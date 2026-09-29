# Closet

AI-powered personal wardrobe and outfit recommendation application.

Closet allows users to photograph their clothing, automatically classify wardrobe items, organize them locally, and eventually generate outfit recommendations based on weather, occasion, calendar events, colors, and personal style.

---

## Current Architecture

```text
Closet
│
├── UI / Presentation
│   ├── Home
│   ├── Upload
│   ├── Wardrobe
│   ├── Recommendation
│   └── Style Analysis
│
├── Auto Detection
│   ├── Color Detection
│   ├── Clothing Type Detection
│   ├── Category Detection
│   ├── Occasion Detection
│   ├── Weather Compatibility
│   └── Mood / Style Detection
│
├── Domain
│   └── WardrobeItem
│
├── Data
│   └── Local wardrobe storage
│
└── Future AI Backend
    └── Python / ML / AI classification service
```

---

# Auto Detection Module

The Auto Detection module is intentionally separated from the UI.

Its responsibility is to analyze an uploaded clothing image and return structured clothing metadata.

```text
Image
  │
  ▼
AutoDetectionManager
  │
  ├── ColorDetector
  ├── ClothingTypeDetector
  ├── CategoryDetector
  ├── OccasionDetector
  ├── WeatherDetector
  └── MoodDetector
  │
  ▼
AutoDetectionResult
```

The Upload screen should not directly communicate with individual detectors.

Instead:

```text
UploadViewModel
       │
       ▼
AutoDetectionManager
       │
       ▼
AutoDetectionResult
```

This makes the detection implementation replaceable.

---

# Detection Fields

The target wardrobe metadata is:

| Field         | Description                                   |
| ------------- | --------------------------------------------- |
| Color         | Dominant clothing color                       |
| Clothing Type | Shirt, T-shirt, Jeans, Hoodie, etc.           |
| Category      | Top, Bottom, Dress, Footwear, etc.            |
| Occasion      | Casual, Formal, Party, Business, etc.         |
| Weather       | Hot, Warm, Cool, Cold, Rain, etc.             |
| Mood          | Classic, Minimal, Sporty, Bold, Relaxed, etc. |

---

# Detection Confidence

Every AI-generated field should eventually have a confidence score.

Example:

```text
Color
  #14213D
  confidence: 0.94

Clothing Type
  Shirt
  confidence: 0.89

Category
  Top
  confidence: 0.97
```

This allows the application to decide when user confirmation is necessary.

Example:

```text
confidence >= 0.90
    ↓
accept automatically

0.70 - 0.89
    ↓
show result but allow correction

< 0.70
    ↓
ask user to select
```

---

# User Override

Automatic detection must never prevent the user from correcting an item.

The intended flow is:

```text
AI Detection
      ↓
Pre-filled fields
      ↓
User reviews
      ↓
User edits if necessary
      ↓
Save
```

Manual changes should override AI-generated values.

---

# Color Detection

The current implementation uses a basic dominant-color calculation.

Current flow:

```text
Image
  ↓
Sample pixels
  ↓
Calculate average RGB
  ↓
Convert to HEX
```

Example:

```text
#14213D
```

This is only a temporary implementation.

The production version should:

1. Detect the clothing region.
2. Remove background pixels.
3. Ignore skin/background objects.
4. Calculate dominant clothing colors.
5. Convert RGB/HSV values into semantic color names.
6. Store both the precise color and semantic color.

Example:

```text
colorHex  = "#14213D"
colorName = "Navy"
```

---

# Future AI Pipeline

The long-term target architecture is:

```text
Android
   │
   │ Image
   ▼
AutoDetectionManager
   │
   ▼
AI Classification Service
   │
   ├── Clothing segmentation
   ├── Clothing type
   ├── Category
   ├── Color
   ├── Occasion
   ├── Weather compatibility
   └── Style / mood
   │
   ▼
Structured JSON
   │
   ▼
AutoDetectionResult
   │
   ▼
UploadViewModel
```

A future Python backend may expose an API such as:

```text
POST /classify
```

Input:

```text
image
```

Output:

```json
{
  "color": "#14213D",
  "colorName": "Navy",
  "clothingType": "Shirt",
  "category": "Top",
  "occasion": "Casual",
  "weatherCompatibility": "Mild",
  "mood": "Classic",
  "confidence": {
    "color": 0.94,
    "clothingType": 0.89,
    "category": 0.97,
    "occasion": 0.81
  }
}
```

The Android UI should not need to change when this backend replaces the initial detectors.

---

# Design Principle

The most important architectural rule is:

> UI should not know how detection works.

Avoid:

```text
UploadScreen
    ↓
ColorDetector
```

Prefer:

```text
UploadScreen
    ↓
UploadViewModel
    ↓
AutoDetectionManager
    ↓
Detectors / AI backend
```

This allows the AI system to evolve independently.

---

# Planned Development

## Phase 1 — UI

* [x] Upload screen
* [x] Photo selection
* [x] Camera/gallery integration
* [x] Manual metadata fields
* [x] Color picker
* [ ] AI analysis indicator
* [ ] Detection confidence indicators

## Phase 2 — Local Detection

* [x] Basic color detection
* [ ] Clothing segmentation
* [ ] Clothing type detection
* [ ] Category detection
* [ ] Basic occasion detection

## Phase 3 — AI Detection

* [ ] Python classifier
* [ ] Image preprocessing
* [ ] Clothing segmentation model
* [ ] Clothing classification model
* [ ] Semantic color extraction
* [ ] Confidence scoring
* [ ] Android ↔ Python API

## Phase 4 — Wardrobe Intelligence

* [ ] Weather compatibility
* [ ] Style/mood classification
* [ ] Outfit compatibility
* [ ] Color matching
* [ ] Occasion matching
* [ ] Personal style learning

## Phase 5 — Recommendations

Combine:

```text
Wardrobe
+
Weather
+
Calendar
+
Occasion
+
Color harmony
+
Personal style
+
Previous choices
```

to generate outfit recommendations.

---

# Important Rules

### 1. AI suggestions are editable

Never lock automatically detected fields.

### 2. Keep detection independent

Detectors should not directly modify UI state.

### 3. Keep AI replaceable

A simple local detector should be replaceable with a trained model without changing the Upload screen.

### 4. Store confidence

Every automatically generated field should eventually have confidence metadata.

### 5. Separate raw and semantic data

Prefer:

```text
colorHex = "#14213D"
colorName = "Navy"
```

rather than storing only:

```text
"Navy"
```

### 6. Do not infer personal attributes unnecessarily

Clothing classification should focus on observable garment properties. Fields such as "gender" should not be automatically inferred unless there is a clear product/design requirement and appropriate model behavior.

---

# Target Production Architecture

```text
                    ┌──────────────────┐
                    │   UploadScreen   │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │ UploadViewModel  │
                    └────────┬─────────┘
                             │
                             ▼
                 ┌────────────────────────┐
                 │ AutoDetectionManager   │
                 └───────────┬────────────┘
                             │
             ┌───────────────┼────────────────┐
             │               │                │
             ▼               ▼                ▼
       Local Models      Python API       Future AI
             │               │                │
             └───────────────┼────────────────┘
                             ▼
                 ┌────────────────────────┐
                 │ AutoDetectionResult    │
                 └───────────┬────────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │ Upload UI Fields │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │  User Correction │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │   Wardrobe DB    │
                    └──────────────────┘
```

---

# Current Status

The application currently has:

* Jetpack Compose UI
* Upload workflow
* Camera/gallery support
* Manual metadata entry
* Custom color picker
* Auto Detection architecture
* Basic local color detection

The next major milestone is replacing the placeholder clothing detectors with an actual image-classification pipeline.

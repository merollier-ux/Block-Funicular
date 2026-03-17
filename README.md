# PyTutor — Python for Audio AI Engineering

An Android app that teaches Python programming with a focus on AI/ML for audio processing and sound design. Powered by the Claude AI API.

## Features

- **📚 Structured Lessons** — 7 modules from Python basics to Audio ML, with exercises and real-time AI feedback
- **⚗️ Code Lab** — Write Python and run it through Claude AI (acts as an intelligent interpreter)
- **🤖 AI Tutor** — Multi-turn chat with Claude, specialized in Python for audio/ML
- **🎯 Progress Tracking** — XP system, lesson completion tracking

## Learning Path

1. 🐍 **Python Fundamentals** — Variables, strings, math, print()
2. 🔀 **Control Flow** — if/else decisions, for loops
3. ⚙️ **Functions** — def, parameters, return values
4. 📋 **Lists & Data** — Collections, data structures
5. 🔢 **NumPy** — Array math for audio signals
6. 🎵 **Librosa** — Load and analyze audio files
7. 🤖 **Audio ML** — Feature extraction, MFCC, classification prep

## Setup

### Requirements
- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 34
- Java 17+
- Claude API key (free at [console.anthropic.com](https://console.anthropic.com))

### Build & Run
1. Clone this repository
2. Open in Android Studio
3. Build and run on device/emulator (Android 8.0+)
4. Tap the key icon in the top-right to add your Claude API key

### API Key
The app uses **Claude API** (claude-opus-4-6) for:
- Simulating Python code execution with detailed output
- Evaluating exercise solutions with feedback
- Powering the AI Tutor chat
- Generating hints and explanations

Your API key is stored securely on-device only.

## Architecture

```
app/src/main/kotlin/com/pythonaudio/pytutor/
├── MainActivity.kt
├── api/ClaudeApiService.kt      # Claude API integration
├── data/LessonsRepository.kt   # All lesson content
├── model/                       # Data models
├── ui/
│   ├── home/                    # Dashboard with progress
│   ├── lessons/                 # Learning path + lesson detail
│   ├── codelab/                 # Python code editor
│   ├── tutor/                   # AI chat
│   └── settings/                # API key management
└── util/PreferencesManager.kt
```

## Tech Stack
- Kotlin + Material Design 3 (dark theme)
- Android Navigation Component
- OkHttp + Gson + Kotlin Coroutines
- Claude API (claude-opus-4-6)
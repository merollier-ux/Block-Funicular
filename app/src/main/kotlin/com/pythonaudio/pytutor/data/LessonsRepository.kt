package com.pythonaudio.pytutor.data

import com.pythonaudio.pytutor.model.Exercise
import com.pythonaudio.pytutor.model.Lesson
import com.pythonaudio.pytutor.model.Module

object LessonsRepository {

    val modules: List<Module> = listOf(

        Module(
            id = 1,
            title = "Python Fundamentals",
            description = "Build your foundation — variables, types, and basic operations",
            icon = "🐍",
            color = "#4CAF50",
            lessons = listOf(
                Lesson(
                    id = "1_1",
                    moduleId = 1,
                    title = "Your First Python Program",
                    subtitle = "print() and running code",
                    icon = "👋",
                    xpReward = 50,
                    estimatedMinutes = 5,
                    theory = """
# Hello, Python!

Python is one of the most popular languages for AI and audio processing. Let's start with the basics.

The `print()` function displays output to the screen. It's how you make Python "speak."

```python
print("Hello, World!")
print("I'm learning Python for audio AI!")
```

Every line in Python is an **instruction**. Python reads them top to bottom, like reading music notation.
                    """.trimIndent(),
                    codeExample = """print("Hello, World!")
print("Python is awesome!")
print("I'll build audio AI with this!")""",
                    exercise = Exercise(
                        prompt = "Print three lines: your name, your passion (audio/sound), and your goal (AI engineering).",
                        starterCode = """# Replace the blanks with your own text!
print("My name is ___")
print("I love ___")
print("My goal is ___")""",
                        solution = """print("My name is Alex")
print("I love sound design")
print("My goal is AI audio engineering")""",
                        hints = listOf(
                            "Replace ___ with text inside the quotes. For example: print(\"My name is Jordan\")",
                            "Make sure each print statement is on its own line.",
                            "The text inside print() must be wrapped in quotes: either 'single' or \"double\" quotes."
                        )
                    ),
                    tags = listOf("basics", "print", "output")
                ),

                Lesson(
                    id = "1_2",
                    moduleId = 1,
                    title = "Variables",
                    subtitle = "Storing values with names",
                    icon = "📦",
                    xpReward = 60,
                    estimatedMinutes = 8,
                    theory = """
# Variables: Named Containers

A variable is like a **labeled channel on a mixing board** — it holds a value you can use later.

```python
volume = 75
song_name = "Deep Bass"
is_playing = True
```

Rules for naming variables:
- Use lowercase with underscores: `sample_rate`, `beat_count`
- No spaces or special characters
- Start with a letter, not a number

You can change a variable's value at any time — just like adjusting a fader.
                    """.trimIndent(),
                    codeExample = """sample_rate = 44100
track_name = "Ambient Waves"
bpm = 120

print("Track:", track_name)
print("BPM:", bpm)
print("Sample Rate:", sample_rate, "Hz")""",
                    exercise = Exercise(
                        prompt = "Create variables for a song: title, artist name, duration in seconds, and BPM. Then print all four.",
                        starterCode = """# Create your variables here
title =
artist =
duration_seconds =
bpm =

# Print them
print("Title:", title)
print("Artist:", artist)
print("Duration:", duration_seconds, "seconds")
print("BPM:", bpm)""",
                        solution = """title = "Electric Dreams"
artist = "Synth Masters"
duration_seconds = 245
bpm = 128

print("Title:", title)
print("Artist:", artist)
print("Duration:", duration_seconds, "seconds")
print("BPM:", bpm)""",
                        hints = listOf(
                            "For text (strings), put quotes around the value: title = \"My Song\"",
                            "For numbers, no quotes needed: bpm = 128",
                            "Variables on the left side of = receive the value on the right."
                        )
                    ),
                    tags = listOf("variables", "data types", "assignment")
                ),

                Lesson(
                    id = "1_3",
                    moduleId = 1,
                    title = "Numbers & Math",
                    subtitle = "Arithmetic and calculations",
                    icon = "🔢",
                    xpReward = 60,
                    estimatedMinutes = 10,
                    theory = """
# Math in Python

Python is a powerful calculator — essential for audio processing math.

**Basic operators:**
| Symbol | Operation | Example |
|--------|-----------|---------|
| + | Addition | 440 + 220 |
| - | Subtraction | 1000 - 250 |
| * | Multiplication | 2 * 3.14 |
| / | Division | 44100 / 2 |
| ** | Power | 2 ** 10 |
| % | Remainder | 10 % 3 |

**Audio math example:**
```python
# Frequency of A4 is 440 Hz
# Each octave doubles the frequency
a4 = 440
a5 = a4 * 2   # 880 Hz
a3 = a4 / 2   # 220 Hz
```
                    """.trimIndent(),
                    codeExample = """# Sample rate math
sample_rate = 44100
bit_depth = 16
channels = 2

# Calculate file size for 1 minute of audio (in bytes)
duration_seconds = 60
file_size = sample_rate * bit_depth / 8 * channels * duration_seconds
print("File size for 1 min of CD audio:", file_size, "bytes")
print("In MB:", round(file_size / 1_000_000, 2))""",
                    exercise = Exercise(
                        prompt = "Calculate: if a sound wave has frequency 440 Hz (A4), what are A5 (one octave up) and A3 (one octave down)? Also calculate the wavelength of A4 in air (speed of sound = 343 m/s, wavelength = speed / frequency).",
                        starterCode = """a4_freq = 440  # Hz

# Calculate octaves
a5_freq =   # one octave up = double the frequency
a3_freq =   # one octave down = half the frequency

# Calculate wavelength (speed of sound / frequency)
speed_of_sound = 343  # meters per second
wavelength_a4 =

print("A3:", a3_freq, "Hz")
print("A4:", a4_freq, "Hz")
print("A5:", a5_freq, "Hz")
print("Wavelength of A4:", round(wavelength_a4, 4), "meters")""",
                        solution = """a4_freq = 440

a5_freq = a4_freq * 2
a3_freq = a4_freq / 2

speed_of_sound = 343
wavelength_a4 = speed_of_sound / a4_freq

print("A3:", a3_freq, "Hz")
print("A4:", a4_freq, "Hz")
print("A5:", a5_freq, "Hz")
print("Wavelength of A4:", round(wavelength_a4, 4), "meters")""",
                        hints = listOf(
                            "To double a value: a5_freq = a4_freq * 2",
                            "To halve a value: a3_freq = a4_freq / 2",
                            "Wavelength = speed / frequency: wavelength_a4 = speed_of_sound / a4_freq"
                        )
                    ),
                    tags = listOf("math", "arithmetic", "numbers")
                ),

                Lesson(
                    id = "1_4",
                    moduleId = 1,
                    title = "Strings",
                    subtitle = "Working with text",
                    icon = "📝",
                    xpReward = 70,
                    estimatedMinutes = 12,
                    theory = """
# Strings: Text in Python

A **string** is any text surrounded by quotes. Think of it as the lyrics of a song.

```python
genre = "Electronic"
artist = 'Daft Punk'
description = "French house duo known for robot personas"
```

**Useful string operations:**

```python
title = "deep house beats"
print(title.upper())       # DEEP HOUSE BEATS
print(title.capitalize())  # Deep house beats
print(len(title))          # 16 (number of characters)
print(title.replace("house", "techno"))

# f-strings: embed variables inside text
bpm = 128
label = f"Track at {bpm} BPM"
print(label)  # Track at 128 BPM
```
                    """.trimIndent(),
                    codeExample = """artist = "Burial"
album = "Untrue"
genre = "UK Garage"
year = 2007

# f-string formatting
description = f"{artist} released '{album}' in {year}. Genre: {genre}."
print(description)
print(f"Artist name length: {len(artist)} characters")
print(f"Uppercase: {artist.upper()}")""",
                    exercise = Exercise(
                        prompt = "Create a track info card. Use variables for: artist, title, genre, bpm. Then use an f-string to print: '[ARTIST] - [TITLE] | [GENRE] | [BPM] BPM'. Also print the title in ALL CAPS.",
                        starterCode = """artist = "Four Tet"
title = "Locked"
genre = "Electronic"
bpm = 124

# Create the info card using an f-string
info_card = f""

print(info_card)
print("Title in caps:", )""",
                        solution = """artist = "Four Tet"
title = "Locked"
genre = "Electronic"
bpm = 124

info_card = f"{artist} - {title} | {genre} | {bpm} BPM"

print(info_card)
print("Title in caps:", title.upper())""",
                        hints = listOf(
                            "An f-string starts with f before the quotes: f\"text {variable} more text\"",
                            "To include a variable in an f-string, wrap it in curly braces: {artist}",
                            "To capitalize: title.upper() converts the whole string to uppercase."
                        )
                    ),
                    tags = listOf("strings", "text", "f-strings")
                )
            )
        ),

        Module(
            id = 2,
            title = "Control Flow",
            description = "Make decisions and repeat actions with if/else and loops",
            icon = "🔀",
            color = "#2196F3",
            lessons = listOf(
                Lesson(
                    id = "2_1",
                    moduleId = 2,
                    title = "If / Else Decisions",
                    subtitle = "Making your code choose",
                    icon = "🤔",
                    xpReward = 70,
                    estimatedMinutes = 12,
                    theory = """
# Making Decisions with if/else

Python can make decisions — like a DJ choosing what to play next based on the crowd.

```python
bpm = 140

if bpm > 128:
    print("This is fast — great for techno!")
elif bpm > 100:
    print("Medium tempo — good for house music")
else:
    print("Slow — perfect for ambient or chill")
```

**Comparison operators:**
| Operator | Meaning |
|----------|---------|
| == | equals |
| != | not equal |
| > | greater than |
| < | less than |
| >= | greater or equal |
| <= | less or equal |

**Important:** Python uses **indentation** (4 spaces) to define code blocks.
                    """.trimIndent(),
                    codeExample = """volume = 85

if volume > 100:
    print("Warning: Volume too high! Risk of clipping.")
elif volume > 70:
    print("Good listening level")
elif volume > 40:
    print("Quiet - good for late night sessions")
else:
    print("Very quiet")

# Check if a frequency is in the audible range
frequency = 440
if 20 <= frequency <= 20000:
    print(f"{frequency} Hz is audible to humans")
else:
    print(f"{frequency} Hz is outside human hearing range")""",
                    exercise = Exercise(
                        prompt = "Write a 'key detector': given a musical note frequency, categorize it as 'bass' (< 250 Hz), 'midrange' (250-4000 Hz), or 'treble' (> 4000 Hz). Test it with frequency = 80.",
                        starterCode = """frequency = 80

# Write your if/elif/else here
if frequency < 250:

elif :

else:
    """,
                        solution = """frequency = 80

if frequency < 250:
    print(f"{frequency} Hz is in the bass range")
elif frequency <= 4000:
    print(f"{frequency} Hz is in the midrange")
else:
    print(f"{frequency} Hz is in the treble range")""",
                        hints = listOf(
                            "Start with: if frequency < 250:",
                            "For the middle range use: elif frequency <= 4000:",
                            "Don't forget to indent (4 spaces) the code inside each if/elif/else block."
                        )
                    ),
                    tags = listOf("if", "else", "conditions", "control flow")
                ),

                Lesson(
                    id = "2_2",
                    moduleId = 2,
                    title = "For Loops",
                    subtitle = "Repeating actions automatically",
                    icon = "🔁",
                    xpReward = 80,
                    estimatedMinutes = 15,
                    theory = """
# For Loops: Automate Repetition

Loops let you repeat code without copy-pasting — imagine batch-processing 1000 audio files.

```python
# Loop through a list
tracks = ["Intro", "Drop", "Breakdown", "Outro"]
for track in tracks:
    print(f"Processing: {track}")

# Loop a fixed number of times with range()
for i in range(5):
    print(f"Beat {i + 1}")

# range(start, stop, step)
for beat in range(0, 16, 4):
    print(f"Bar marker at beat {beat}")
```

**range() explained:**
- `range(5)` → 0, 1, 2, 3, 4
- `range(1, 6)` → 1, 2, 3, 4, 5
- `range(0, 16, 4)` → 0, 4, 8, 12
                    """.trimIndent(),
                    codeExample = """# Generate a musical scale (semitones above A4 = 440 Hz)
base_freq = 440  # A4

print("C Major scale frequencies:")
# C major semitones above C4: 0, 2, 4, 5, 7, 9, 11, 12
semitones = [0, 2, 4, 5, 7, 9, 11, 12]
note_names = ["C", "D", "E", "F", "G", "A", "B", "C"]

for i, semitone in enumerate(semitones):
    frequency = 261.63 * (2 ** (semitone / 12))
    print(f"{note_names[i]}: {round(frequency, 2)} Hz")""",
                    exercise = Exercise(
                        prompt = "Use a for loop to print the first 8 harmonics of a 100 Hz fundamental frequency. Harmonics are: fundamental * 1, * 2, * 3... up to * 8.",
                        starterCode = """fundamental = 100  # Hz

print("Harmonics of 100 Hz:")
for harmonic_number in range(1, 9):
    frequency =
    print(f"Harmonic {harmonic_number}: {frequency} Hz")""",
                        solution = """fundamental = 100

print("Harmonics of 100 Hz:")
for harmonic_number in range(1, 9):
    frequency = fundamental * harmonic_number
    print(f"Harmonic {harmonic_number}: {frequency} Hz")""",
                        hints = listOf(
                            "range(1, 9) gives you numbers 1 through 8.",
                            "Each harmonic frequency = fundamental * harmonic_number",
                            "Inside the loop, frequency = fundamental * harmonic_number"
                        )
                    ),
                    tags = listOf("loops", "for", "range", "iteration")
                )
            )
        ),

        Module(
            id = 3,
            title = "Functions",
            description = "Write reusable code blocks — the building blocks of real programs",
            icon = "⚙️",
            color = "#9C27B0",
            lessons = listOf(
                Lesson(
                    id = "3_1",
                    moduleId = 3,
                    title = "Writing Functions",
                    subtitle = "def, parameters, and return",
                    icon = "🔧",
                    xpReward = 80,
                    estimatedMinutes = 15,
                    theory = """
# Functions: Reusable Code Blocks

A function is like a **preset on a synthesizer** — define it once, use it anywhere.

```python
def function_name(parameter1, parameter2):
    # code goes here
    return result
```

**Example:**
```python
def hz_to_midi(frequency):
    """Convert frequency (Hz) to MIDI note number."""
    import math
    midi = 69 + 12 * math.log2(frequency / 440)
    return round(midi)

note = hz_to_midi(440)   # Returns 69 (A4)
note = hz_to_midi(880)   # Returns 81 (A5)
print(f"A4 = MIDI note {hz_to_midi(440)}")
```

**Key parts:**
- `def` starts a function definition
- Parameters are inputs (in parentheses)
- `return` sends back a value
- The docstring (triple quotes) describes what it does
                    """.trimIndent(),
                    codeExample = """def db_to_linear(db):
    """Convert decibels to linear amplitude (0.0 to 1.0+)."""
    return 10 ** (db / 20)

def linear_to_db(amplitude):
    """Convert linear amplitude to decibels."""
    import math
    return 20 * math.log10(amplitude)

def clamp(value, min_val=0.0, max_val=1.0):
    """Clamp a value between min and max."""
    return max(min_val, min(max_val, value))

# Test our functions
print(f"0 dB = {db_to_linear(0)} amplitude")
print(f"-6 dB = {round(db_to_linear(-6), 4)} amplitude")
print(f"0.5 amplitude = {round(linear_to_db(0.5), 2)} dB")
print(f"Clamped 1.5 = {clamp(1.5)}")""",
                    exercise = Exercise(
                        prompt = "Write a function called `frequency_to_note` that takes a frequency and returns the closest note name (A, A#, B, C, C#, D, D#, E, F, F#, G, G#). Test it with 440 Hz and 523.25 Hz.",
                        starterCode = """def frequency_to_note(frequency):
    """Return the closest musical note name for a frequency."""
    import math
    notes = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]

    # Calculate semitones from A4 (440 Hz)
    semitones_from_a4 = round(12 * math.log2(frequency / 440))

    # A4 is MIDI note 69; C4 is MIDI note 60
    midi_note = 69 + semitones_from_a4

    # Get note name from MIDI number
    note_index =
    return notes[note_index]

# Test it
print(frequency_to_note(440))    # Should print A
print(frequency_to_note(523.25)) # Should print C""",
                        solution = """def frequency_to_note(frequency):
    import math
    notes = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
    semitones_from_a4 = round(12 * math.log2(frequency / 440))
    midi_note = 69 + semitones_from_a4
    note_index = midi_note % 12
    return notes[note_index]

print(frequency_to_note(440))
print(frequency_to_note(523.25))""",
                        hints = listOf(
                            "The note index within an octave = midi_note % 12 (remainder when divided by 12)",
                            "notes[note_index] gives you the note name from your list",
                            "% is the modulo operator — it gives the remainder. 69 % 12 = 9, and notes[9] is 'A' ✓"
                        )
                    ),
                    tags = listOf("functions", "def", "return", "parameters")
                )
            )
        ),

        Module(
            id = 4,
            title = "Lists & Data",
            description = "Store collections of values — playlists, samples, and datasets",
            icon = "📋",
            color = "#FF5722",
            lessons = listOf(
                Lesson(
                    id = "4_1",
                    moduleId = 4,
                    title = "Lists",
                    subtitle = "Ordered collections of values",
                    icon = "📜",
                    xpReward = 70,
                    estimatedMinutes = 12,
                    theory = """
# Lists: Your First Data Structure

A list is an **ordered collection** — like a playlist or a sequence of audio samples.

```python
playlist = ["Track 1", "Track 2", "Track 3"]
frequencies = [440, 493, 523, 587, 659]
```

**Accessing items (index starts at 0):**
```python
print(playlist[0])   # "Track 1"
print(playlist[-1])  # "Track 3" (last item)
```

**Common list operations:**
```python
playlist.append("Track 4")   # Add to end
playlist.insert(1, "New")     # Insert at position
playlist.remove("Track 2")   # Remove by value
print(len(playlist))         # Number of items
sorted_list = sorted(frequencies)
```
                    """.trimIndent(),
                    codeExample = """# Build a simple audio chain (signal processing order)
fx_chain = []

fx_chain.append("EQ")
fx_chain.append("Compressor")
fx_chain.append("Reverb")
fx_chain.append("Limiter")

print("Signal chain:", fx_chain)
print("Number of effects:", len(fx_chain))
print("First effect:", fx_chain[0])
print("Last effect:", fx_chain[-1])

# Process each effect in the chain
print("\nProcessing audio through chain:")
for i, effect in enumerate(fx_chain):
    print(f"  Step {i+1}: Applying {effect}")""",
                    exercise = Exercise(
                        prompt = "Create a list of 5 frequency values (any frequencies you like). Then: print the list, print the highest frequency (using max()), print the lowest (min()), print the average, and add a new frequency to the end.",
                        starterCode = """# Create your frequency list
frequencies = []  # Add 5 frequency values inside

print("Frequencies:", frequencies)
print("Highest:", )
print("Lowest:", )
print("Average:", )

# Add a new frequency
frequencies.append()
print("Updated list:", frequencies)""",
                        solution = """frequencies = [100, 440, 880, 1200, 4000]

print("Frequencies:", frequencies)
print("Highest:", max(frequencies))
print("Lowest:", min(frequencies))
print("Average:", sum(frequencies) / len(frequencies))

frequencies.append(8000)
print("Updated list:", frequencies)""",
                        hints = listOf(
                            "To add values to a list: frequencies = [100, 440, 880, 1200, 4000]",
                            "max(frequencies) gives the highest, min(frequencies) gives the lowest",
                            "Average = sum(frequencies) / len(frequencies)"
                        )
                    ),
                    tags = listOf("lists", "collections", "data structures")
                )
            )
        ),

        Module(
            id = 5,
            title = "NumPy Basics",
            description = "Powerful math arrays — the backbone of audio signal processing",
            icon = "🔢",
            color = "#00BCD4",
            lessons = listOf(
                Lesson(
                    id = "5_1",
                    moduleId = 5,
                    title = "NumPy Arrays",
                    subtitle = "Fast math on large datasets",
                    icon = "📊",
                    xpReward = 90,
                    estimatedMinutes = 15,
                    theory = """
# NumPy: The Foundation of Audio Processing

**NumPy** is the most important library for working with audio data. Audio is just a long array of numbers!

```python
import numpy as np
```

**Creating arrays:**
```python
# A simple audio snippet (sine wave samples)
samples = np.array([0.0, 0.5, 1.0, 0.5, 0.0, -0.5, -1.0, -0.5])

# Create arrays automatically
zeros = np.zeros(1000)        # 1000 silent samples
ones = np.ones(44100)         # 1 second of max amplitude
t = np.linspace(0, 1, 44100)  # Time axis for 1 second
```

**The power of NumPy: no loops needed!**
```python
signal = np.array([0.1, 0.5, 0.8, 0.3])

# Apply gain (multiply entire signal at once)
gain = 2.0
amplified = signal * gain  # Works on ALL elements at once!

# Normalize to -1.0 to 1.0
normalized = signal / np.max(np.abs(signal))
```
                    """.trimIndent(),
                    codeExample = """import numpy as np

# Generate a 440 Hz sine wave (A4 note)
sample_rate = 44100  # Hz
duration = 0.1       # seconds (100ms snippet)
frequency = 440      # Hz

t = np.linspace(0, duration, int(sample_rate * duration))
sine_wave = np.sin(2 * np.pi * frequency * t)

print(f"Generated {len(sine_wave)} samples")
print(f"First 5 samples: {np.round(sine_wave[:5], 4)}")
print(f"Max amplitude: {np.max(sine_wave):.4f}")
print(f"Min amplitude: {np.min(sine_wave):.4f}")
print(f"Mean: {np.mean(sine_wave):.6f}")

# Apply -6dB gain reduction
gain = 10 ** (-6/20)  # -6 dB
quieter_wave = sine_wave * gain
print(f"\nAfter -6dB: Max = {np.max(quieter_wave):.4f}")""",
                    exercise = Exercise(
                        prompt = "Generate a 1-second sine wave at 220 Hz (A3). Calculate and print: total samples, max value, min value, RMS (Root Mean Square = sqrt(mean(samples^2))), and the first 3 sample values.",
                        starterCode = """import numpy as np

sample_rate = 44100
duration = 1.0
frequency = 220

# Generate time axis
t = np.linspace(0, duration, int(sample_rate * duration))

# Generate sine wave
signal =

# Print stats
print("Total samples:", )
print("Max value:", )
print("Min value:", )
print("RMS:", )
print("First 3 samples:", )""",
                        solution = """import numpy as np

sample_rate = 44100
duration = 1.0
frequency = 220

t = np.linspace(0, duration, int(sample_rate * duration))
signal = np.sin(2 * np.pi * frequency * t)

print("Total samples:", len(signal))
print("Max value:", round(np.max(signal), 4))
print("Min value:", round(np.min(signal), 4))
print("RMS:", round(np.sqrt(np.mean(signal**2)), 4))
print("First 3 samples:", np.round(signal[:3], 4))""",
                        hints = listOf(
                            "Sine wave formula: np.sin(2 * np.pi * frequency * t)",
                            "RMS = np.sqrt(np.mean(signal**2)) — power two the signal, take mean, then square root",
                            "First 3 samples: signal[:3] — slice notation gives elements 0, 1, 2"
                        )
                    ),
                    tags = listOf("numpy", "arrays", "signal processing")
                )
            )
        ),

        Module(
            id = 6,
            title = "Audio with Librosa",
            description = "Load, analyze, and visualize audio files — your first real audio AI tools",
            icon = "🎵",
            color = "#E91E63",
            lessons = listOf(
                Lesson(
                    id = "6_1",
                    moduleId = 6,
                    title = "Loading Audio Files",
                    subtitle = "Read audio with librosa.load()",
                    icon = "📂",
                    xpReward = 100,
                    estimatedMinutes = 15,
                    theory = """
# Librosa: Professional Audio Analysis in Python

**Librosa** is the go-to library for audio and music analysis in AI/ML.

```python
import librosa
import numpy as np
```

**Loading audio:**
```python
# Load an audio file
y, sr = librosa.load("song.mp3")
# y = audio samples (NumPy array)
# sr = sample rate (default 22050 Hz)

# Load with original sample rate
y, sr = librosa.load("song.mp3", sr=None)

# Load only first 10 seconds
y, sr = librosa.load("song.mp3", duration=10)
```

**Basic properties:**
```python
duration = librosa.get_duration(y=y, sr=sr)
print(f"Duration: {duration:.2f} seconds")
print(f"Sample rate: {sr} Hz")
print(f"Total samples: {len(y)}")
```
                    """.trimIndent(),
                    codeExample = """import librosa
import numpy as np

# In a real project, you'd load: y, sr = librosa.load("your_file.mp3")
# For this demo, we'll generate a test signal
sr = 22050
duration = 3.0
t = np.linspace(0, duration, int(sr * duration))

# Create a chord: A4 + E5 + A5 (power chord)
y = (np.sin(2 * np.pi * 440 * t) +    # A4
     np.sin(2 * np.pi * 659 * t) +    # E5
     np.sin(2 * np.pi * 880 * t))     # A5

# Normalize
y = y / np.max(np.abs(y))

# Analyze
print(f"Sample rate: {sr} Hz")
print(f"Duration: {len(y) / sr:.2f} seconds")
print(f"Total samples: {len(y)}")
print(f"Max amplitude: {np.max(np.abs(y)):.4f}")

# Compute RMS energy
rms = librosa.feature.rms(y=y)
print(f"Average RMS energy: {np.mean(rms):.4f}")""",
                    exercise = Exercise(
                        prompt = "Simulate loading audio by creating a 2-second signal with two frequencies mixed together (any two frequencies you choose). Calculate and print: duration, sample rate, total samples, peak amplitude, and the Zero Crossing Rate (librosa.feature.zero_crossing_rate).",
                        starterCode = """import librosa
import numpy as np

# Your audio parameters
sr = 22050
duration = 2.0
t = np.linspace(0, duration, int(sr * duration))

# Create a 2-frequency mix (choose your own frequencies!)
freq1 =
freq2 =
y = np.sin(2 * np.pi * freq1 * t) + np.sin(2 * np.pi * freq2 * t)
y = y / np.max(np.abs(y))  # normalize

# Print your analysis
print("Duration:", )
print("Sample rate:", )
print("Total samples:", )
print("Peak amplitude:", )

# Zero crossing rate
zcr = librosa.feature.zero_crossing_rate(y)
print("Avg zero crossing rate:", )""",
                        solution = """import librosa
import numpy as np

sr = 22050
duration = 2.0
t = np.linspace(0, duration, int(sr * duration))

freq1 = 440
freq2 = 880
y = np.sin(2 * np.pi * freq1 * t) + np.sin(2 * np.pi * freq2 * t)
y = y / np.max(np.abs(y))

print("Duration:", len(y) / sr, "seconds")
print("Sample rate:", sr, "Hz")
print("Total samples:", len(y))
print("Peak amplitude:", round(np.max(np.abs(y)), 4))

zcr = librosa.feature.zero_crossing_rate(y)
print("Avg zero crossing rate:", round(float(np.mean(zcr)), 4))""",
                        hints = listOf(
                            "Choose any two frequencies, e.g. freq1 = 440, freq2 = 880",
                            "Duration in seconds = len(y) / sr",
                            "librosa.feature.zero_crossing_rate(y) returns an array; use np.mean() on it"
                        )
                    ),
                    tags = listOf("librosa", "audio", "signal analysis")
                )
            )
        ),

        Module(
            id = 7,
            title = "ML for Audio",
            description = "Use machine learning to classify and analyze sounds",
            icon = "🤖",
            color = "#FF9800",
            lessons = listOf(
                Lesson(
                    id = "7_1",
                    moduleId = 7,
                    title = "Audio Feature Extraction",
                    subtitle = "MFCCs and spectral features",
                    icon = "🎛️",
                    xpReward = 120,
                    estimatedMinutes = 20,
                    theory = """
# Audio Features for Machine Learning

To teach a computer to recognize sounds, we first convert audio into **numerical features** that ML algorithms understand.

**The most important features:**

**MFCCs (Mel-Frequency Cepstral Coefficients):**
The standard feature for speech and music classification.
```python
mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)
# Returns shape (13, time_frames)
mfcc_mean = np.mean(mfccs, axis=1)  # One value per coefficient
```

**Spectral Features:**
```python
# Spectral centroid (brightness)
centroid = librosa.feature.spectral_centroid(y=y, sr=sr)

# Spectral rolloff (energy distribution)
rolloff = librosa.feature.spectral_rolloff(y=y, sr=sr)

# Chroma (musical pitch classes)
chroma = librosa.feature.chroma_stft(y=y, sr=sr)
```

**Why this matters:** In audio ML, you extract these features, then train a classifier to recognize patterns — "this MFCC pattern = kick drum", "this pattern = snare".
                    """.trimIndent(),
                    codeExample = """import librosa
import numpy as np

# Generate a test signal (simulating a musical tone)
sr = 22050
duration = 1.0
t = np.linspace(0, duration, int(sr * duration))
y = np.sin(2 * np.pi * 440 * t) * np.exp(-3 * t)  # Decaying tone

# Extract MFCC features (used in 90% of audio ML tasks)
mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)
mfcc_mean = np.mean(mfccs, axis=1)

print("MFCC shape:", mfccs.shape)
print("MFCC means (feature vector):")
for i, val in enumerate(mfcc_mean):
    print(f"  MFCC {i+1}: {val:.2f}")

# Spectral centroid (perceptual brightness)
centroid = librosa.feature.spectral_centroid(y=y, sr=sr)
print(f"\nAvg Spectral Centroid: {np.mean(centroid):.1f} Hz")
print("(Higher = brighter/more treble)")""",
                    exercise = Exercise(
                        prompt = "Extract a full feature vector from a generated signal: MFCCs (13), spectral centroid, spectral rolloff, and zero crossing rate. Print each feature's mean value. This is exactly what you'd feed into an ML classifier!",
                        starterCode = """import librosa
import numpy as np

# Generate signal
sr = 22050
t = np.linspace(0, 1.0, sr)
y = np.sin(2 * np.pi * 220 * t) + 0.5 * np.sin(2 * np.pi * 440 * t)
y = y / np.max(np.abs(y))

# Extract features
mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)
centroid =
rolloff =
zcr = librosa.feature.zero_crossing_rate(y)

# Build feature vector
feature_vector = np.concatenate([
    np.mean(mfccs, axis=1),           # 13 MFCC means
    [np.mean(centroid)],              # 1 centroid mean
    ,                                  # 1 rolloff mean
    [np.mean(zcr)]                    # 1 ZCR mean
])

print(f"Feature vector length: {len(feature_vector)}")
print(f"MFCCs: {np.round(np.mean(mfccs, axis=1)[:3], 2)}...")
print(f"Spectral centroid: {np.mean(centroid):.2f} Hz")
print(f"Spectral rolloff: {np.mean(rolloff):.2f} Hz")
print(f"Zero crossing rate: {np.mean(zcr):.4f}")""",
                        solution = """import librosa
import numpy as np

sr = 22050
t = np.linspace(0, 1.0, sr)
y = np.sin(2 * np.pi * 220 * t) + 0.5 * np.sin(2 * np.pi * 440 * t)
y = y / np.max(np.abs(y))

mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)
centroid = librosa.feature.spectral_centroid(y=y, sr=sr)
rolloff = librosa.feature.spectral_rolloff(y=y, sr=sr)
zcr = librosa.feature.zero_crossing_rate(y)

feature_vector = np.concatenate([
    np.mean(mfccs, axis=1),
    [np.mean(centroid)],
    [np.mean(rolloff)],
    [np.mean(zcr)]
])

print(f"Feature vector length: {len(feature_vector)}")
print(f"MFCCs: {np.round(np.mean(mfccs, axis=1)[:3], 2)}...")
print(f"Spectral centroid: {np.mean(centroid):.2f} Hz")
print(f"Spectral rolloff: {np.mean(rolloff):.2f} Hz")
print(f"Zero crossing rate: {np.mean(zcr):.4f}")""",
                        hints = listOf(
                            "spectral_centroid: librosa.feature.spectral_centroid(y=y, sr=sr)",
                            "spectral_rolloff: librosa.feature.spectral_rolloff(y=y, sr=sr)",
                            "For rolloff in feature_vector: [np.mean(rolloff)]"
                        )
                    ),
                    tags = listOf("mfcc", "features", "machine learning", "librosa")
                )
            )
        )
    )

    fun getAllLessons(): List<Lesson> = modules.flatMap { it.lessons }

    fun getLessonById(id: String): Lesson? = getAllLessons().find { it.id == id }

    fun getModuleById(id: Int): Module? = modules.find { it.id == id }
}

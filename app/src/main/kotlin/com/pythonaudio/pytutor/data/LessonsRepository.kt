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
    ${"\"\"\""}Convert frequency (Hz) to MIDI note number.${"\"\"\""}

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
    ${"\"\"\""}Convert decibels to linear amplitude (0.0 to 1.0+).${"\"\"\""}
    return 10 ** (db / 20)

def linear_to_db(amplitude):
    ${"\"\"\""}Convert linear amplitude to decibels.${"\"\"\""}
    import math
    return 20 * math.log10(amplitude)

def clamp(value, min_val=0.0, max_val=1.0):
    ${"\"\"\""}Clamp a value between min and max.${"\"\"\""}

    return max(min_val, min(max_val, value))

# Test our functions
print(f"0 dB = {db_to_linear(0)} amplitude")
print(f"-6 dB = {round(db_to_linear(-6), 4)} amplitude")
print(f"0.5 amplitude = {round(linear_to_db(0.5), 2)} dB")
print(f"Clamped 1.5 = {clamp(1.5)}")""",
                    exercise = Exercise(
                        prompt = "Write a function called `frequency_to_note` that takes a frequency and returns the closest note name (A, A#, B, C, C#, D, D#, E, F, F#, G, G#). Test it with 440 Hz and 523.25 Hz.",
                        starterCode = """def frequency_to_note(frequency):
    ${"\"\"\""}Return the closest musical note name for a frequency.${"\"\"\""}
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

        // ── Sound Design & Audio Engineering ────────────────────────────────
        Module(
            id = 8,
            title = "Sound Design Fundamentals",
            description = "Understand the physics and craft of sound — from waveforms to synthesis",
            icon = "🎛️",
            color = "#9C27B0",
            lessons = listOf(
                Lesson(
                    id = "8_1",
                    moduleId = 8,
                    title = "The Physics of Sound",
                    subtitle = "Frequency, amplitude, and waveforms",
                    icon = "〰️",
                    xpReward = 60,
                    estimatedMinutes = 10,
                    theory = """
# Understanding Sound

Sound is vibration — molecules of air moving in waves. Every sound you hear or design starts here.

## Key Properties

**Frequency (Hz)** — How fast the wave cycles per second.
- 20 Hz → 20,000 Hz is the human hearing range
- 440 Hz = A4 (concert pitch)
- Lower frequency = lower pitch

**Amplitude** — The height/strength of the wave.
- Controls loudness
- Measured in decibels (dB) — a logarithmic scale
- 0 dB = threshold of hearing, 120 dB = pain threshold

**Waveform Types:**
| Shape | Sound | Use |
|-------|-------|-----|
| Sine | Pure, smooth | Sub bass, test tones |
| Square | Hollow, buzzy | 8-bit, organ |
| Sawtooth | Bright, harsh | Synth leads, brass |
| Triangle | Warm, mellow | Flute-like |
| Noise | Hiss, texture | Cymbals, wind |

**Period** — Time for one complete cycle = 1 / frequency
At 440 Hz: period = 1/440 = 0.00227 seconds
                    """.trimIndent(),
                    codeExample = """import numpy as np

# Demonstrate the four main waveform types
sr = 44100
duration = 0.01  # 10ms for clear visualization
f = 440  # Hz
t = np.linspace(0, duration, int(sr * duration), endpoint=False)

# Sine wave (pure tone)
sine = np.sin(2 * np.pi * f * t)

# Square wave (harmonics at odd multiples)
square = np.sign(np.sin(2 * np.pi * f * t))

# Sawtooth wave
sawtooth = 2 * (t * f - np.floor(0.5 + t * f))

# Triangle wave
triangle = 2 * np.abs(2 * (t * f - np.floor(t * f + 0.5))) - 1

print(f"Period of {f} Hz: {1/f*1000:.3f} ms")
print(f"Samples per cycle: {sr/f:.1f}")
print(f"Sine range: [{sine.min():.3f}, {sine.max():.3f}]")
print(f"Square range: [{square.min():.1f}, {square.max():.1f}]")
print(f"Sawtooth range: [{sawtooth.min():.3f}, {sawtooth.max():.3f}]")
print(f"Triangle range: [{triangle.min():.3f}, {triangle.max():.3f}]")""",
                    exercise = Exercise(
                        prompt = "Calculate the following for a 220 Hz sine wave: (1) period in milliseconds, (2) how many complete cycles fit in 1 second, (3) wavelength in air (speed of sound = 343 m/s), (4) how many samples represent one cycle at 44100 Hz sample rate.",
                        starterCode = """frequency = 220  # Hz
sample_rate = 44100
speed_of_sound = 343  # m/s

# 1. Period (time for one cycle) in milliseconds
period_ms =

# 2. Cycles per second (this is just the frequency!)
cycles_per_second =

# 3. Wavelength in air
wavelength =

# 4. Samples per cycle
samples_per_cycle =

print(f"Period: {period_ms:.3f} ms")
print(f"Cycles per second: {cycles_per_second}")
print(f"Wavelength: {wavelength:.4f} m")
print(f"Samples per cycle: {samples_per_cycle:.1f}")""",
                        solution = """frequency = 220
sample_rate = 44100
speed_of_sound = 343

period_ms = (1 / frequency) * 1000
cycles_per_second = frequency
wavelength = speed_of_sound / frequency
samples_per_cycle = sample_rate / frequency

print(f"Period: {period_ms:.3f} ms")
print(f"Cycles per second: {cycles_per_second}")
print(f"Wavelength: {wavelength:.4f} m")
print(f"Samples per cycle: {samples_per_cycle:.1f}")""",
                        hints = listOf(
                            "Period in seconds = 1 / frequency. To get ms: multiply by 1000.",
                            "Wavelength = speed_of_sound / frequency",
                            "Samples per cycle = sample_rate / frequency"
                        )
                    ),
                    tags = listOf("sound", "frequency", "amplitude", "waveforms")
                ),

                Lesson(
                    id = "8_2",
                    moduleId = 8,
                    title = "Decibels & Loudness",
                    subtitle = "The logarithmic scale of hearing",
                    icon = "🔊",
                    xpReward = 70,
                    estimatedMinutes = 12,
                    theory = """
# Decibels: The Language of Loudness

Human hearing is **logarithmic** — we perceive loudness on a multiplicative scale, not additive. Decibels (dB) match how we actually hear.

## dB Formulas

**For amplitude (voltage/pressure):**
dB = 20 × log10(amplitude)
amplitude = 10^(dB/20)

**For power (energy):**
dB = 10 × log10(power)

## Key Reference Points
| dB SPL | Sound |
|--------|-------|
| 0 | Threshold of hearing |
| 30 | Quiet library |
| 60 | Normal conversation |
| 85 | Prolonged exposure risk |
| 110 | Rock concert |
| 120 | Pain threshold |

## In Digital Audio (dBFS — Full Scale)
- 0 dBFS = maximum digital level (clipping!)
- -6 dBFS = roughly half amplitude
- -18 dBFS = typical headroom target
- -∞ = complete silence

## Why -6 dB = Half Amplitude
amplitude = 10^(-6/20) = 10^(-0.3) ≈ 0.501
So -6 dB cuts amplitude nearly in half.
                    """.trimIndent(),
                    codeExample = """import math

def db_to_amplitude(db):
    return 10 ** (db / 20)

def amplitude_to_db(amplitude):
    if amplitude <= 0:
        return float('-inf')
    return 20 * math.log10(amplitude)

# Common dB values in audio production
print("=== dBFS Reference Chart ===")
for db in [0, -3, -6, -12, -18, -24, -48, -96]:
    amp = db_to_amplitude(db)
    print(f"{db:4d} dBFS → amplitude: {amp:.6f}")

print("\n=== Amplitude to dBFS ===")
for amp in [1.0, 0.5, 0.25, 0.1, 0.01]:
    db = amplitude_to_db(amp)
    print(f"amplitude {amp:.3f} → {db:.1f} dBFS")

# Practical: -6dB gain reduction
signal_peak = 0.9  # 90% of full scale
gain_db = -6
attenuated = signal_peak * db_to_amplitude(gain_db)
print(f"\nSignal {signal_peak} × -6dB = {attenuated:.4f}")""",
                    exercise = Exercise(
                        prompt = "You're mastering a track. The peak is at -1 dBFS. You need to apply a gain change so the output is -14 dBFS (streaming normalization target). Calculate: (1) the required gain change in dB, (2) the linear multiplier to apply, (3) if the original RMS is -18 dBFS, what will the new RMS be?",
                        starterCode = """import math

def db_to_amplitude(db):
    return 10 ** (db / 20)

def amplitude_to_db(amplitude):
    if amplitude <= 0: return float('-inf')
    return 20 * math.log10(amplitude)

original_peak_db = -1   # dBFS
target_peak_db = -14    # dBFS streaming target
original_rms_db = -18   # dBFS

# 1. Required gain change (in dB)
gain_change_db =

# 2. Linear multiplier
linear_multiplier =

# 3. New RMS level
new_rms_db =

print(f"Gain change: {gain_change_db} dB")
print(f"Linear multiplier: {linear_multiplier:.6f}")
print(f"New RMS: {new_rms_db:.1f} dBFS")""",
                        solution = """import math

def db_to_amplitude(db):
    return 10 ** (db / 20)

def amplitude_to_db(amplitude):
    if amplitude <= 0: return float('-inf')
    return 20 * math.log10(amplitude)

original_peak_db = -1
target_peak_db = -14
original_rms_db = -18

gain_change_db = target_peak_db - original_peak_db
linear_multiplier = db_to_amplitude(gain_change_db)
new_rms_db = original_rms_db + gain_change_db

print(f"Gain change: {gain_change_db} dB")
print(f"Linear multiplier: {linear_multiplier:.6f}")
print(f"New RMS: {new_rms_db:.1f} dBFS")""",
                        hints = listOf(
                            "Gain change in dB = target_peak - original_peak (simple subtraction!)",
                            "Linear multiplier = 10^(gain_change_db / 20) = db_to_amplitude(gain_change_db)",
                            "When you apply the same dB gain to RMS: new_rms = original_rms + gain_change"
                        )
                    ),
                    tags = listOf("decibels", "dBFS", "gain", "loudness")
                ),

                Lesson(
                    id = "8_3",
                    moduleId = 8,
                    title = "Synthesis Basics",
                    subtitle = "Oscillators, envelopes, and filters",
                    icon = "🎹",
                    xpReward = 90,
                    estimatedMinutes = 18,
                    theory = """
# The Three Pillars of Synthesis

Nearly every synthesizer is built on three concepts:

## 1. Oscillator (OSC) — The Sound Source
Generates the raw waveform at a given frequency.
- Sine, square, sawtooth, triangle, noise
- Pitch = frequency of oscillation
- Multiple oscillators = richer sound (detuned for chorus effect)

## 2. Envelope (ADSR) — Shape Over Time
Controls how a sound evolves from key-press to key-release:

**A** — Attack: time to reach full volume (0 = instant, slow = fade in)
**D** — Decay: time to fall to sustain level
**S** — Sustain: level held while key is pressed (0-1)
**R** — Release: fade-out time after key released

```python
# Envelope example (in code)
attack_time = 0.01   # 10ms
decay_time = 0.1     # 100ms
sustain_level = 0.7  # 70% volume
release_time = 0.5   # 500ms
```

## 3. Filter — Shape the Spectrum
Removes or emphasizes frequency ranges:
- **Low-pass filter (LPF)**: cuts highs, lets lows through → warm/dark
- **High-pass filter (HPF)**: cuts lows, lets highs through → thin/bright
- **Cutoff frequency**: the point where filtering begins
- **Resonance (Q)**: boosts frequencies near cutoff → nasal/honky

## ADSR in Python
```python
def apply_adsr(signal, sr, attack, decay, sustain, release):
    n = len(signal)
    a_s = int(attack * sr)
    d_s = int(decay * sr)
    r_s = int(release * sr)
    s_s = n - a_s - d_s - r_s
    envelope = np.concatenate([
        np.linspace(0, 1, a_s),      # attack
        np.linspace(1, sustain, d_s), # decay
        np.full(max(0, s_s), sustain),# sustain
        np.linspace(sustain, 0, r_s)  # release
    ])
    return signal[:len(envelope)] * envelope[:n]
```
                    """.trimIndent(),
                    codeExample = """import numpy as np

def generate_oscillator(frequency, duration, sr=44100, wave_type='sine'):
    t = np.linspace(0, duration, int(sr * duration), endpoint=False)
    if wave_type == 'sine':
        return np.sin(2 * np.pi * frequency * t)
    elif wave_type == 'square':
        return np.sign(np.sin(2 * np.pi * frequency * t))
    elif wave_type == 'sawtooth':
        return 2 * (t * frequency - np.floor(0.5 + t * frequency))
    return t  # fallback

def apply_adsr(signal, sr, attack, decay, sustain, release):
    n = len(signal)
    a_s = int(attack * sr)
    d_s = int(decay * sr)
    r_s = int(release * sr)
    s_s = max(0, n - a_s - d_s - r_s)
    envelope = np.concatenate([
        np.linspace(0, 1, a_s),
        np.linspace(1, sustain, d_s),
        np.full(s_s, sustain),
        np.linspace(sustain, 0, r_s)
    ])[:n]
    return signal[:n] * envelope

# Create a synth "pluck" sound
sr = 44100
signal = generate_oscillator(440, 0.5, sr, 'sawtooth')

# Short attack, medium decay, low sustain, quick release = pluck!
pluck = apply_adsr(signal, sr,
    attack=0.002, decay=0.1, sustain=0.1, release=0.1)

print(f"Generated {len(pluck)} samples ({len(pluck)/sr:.2f} sec)")
print(f"Peak amplitude: {np.max(np.abs(pluck)):.4f}")
print(f"First 100ms max: {np.max(np.abs(pluck[:int(sr*0.1)])):.4f}")
print(f"Last 100ms max: {np.max(np.abs(pluck[-int(sr*0.1):])):.4f}")""",
                    exercise = Exercise(
                        prompt = "Create a 'pad' sound (slow attack, long sustain) and a 'stab' sound (fast attack, fast release) both at 330 Hz. Print the peak amplitude and the max amplitude at 50% through the note to verify the envelope shapes are different.",
                        starterCode = """import numpy as np

def generate_osc(freq, duration, sr=44100):
    t = np.linspace(0, duration, int(sr * duration), endpoint=False)
    return np.sin(2 * np.pi * freq * t)

def apply_adsr(signal, sr, attack, decay, sustain, release):
    n = len(signal)
    a_s = int(attack * sr)
    d_s = int(decay * sr)
    r_s = int(release * sr)
    s_s = max(0, n - a_s - d_s - r_s)
    env = np.concatenate([
        np.linspace(0, 1, a_s),
        np.linspace(1, sustain, d_s),
        np.full(s_s, sustain),
        np.linspace(sustain, 0, r_s)
    ])[:n]
    return signal[:n] * env

sr = 44100
freq = 330

# Pad: slow attack (0.5s), long sustain (0.8), slow release
raw_pad = generate_osc(freq, 2.0, sr)
pad = apply_adsr(raw_pad, sr, attack=0.5, decay=0.1, sustain=0.8, release=0.5)

# Stab: fast attack (0.005s), low sustain, fast release
raw_stab = generate_osc(freq, 0.5, sr)
stab = apply_adsr(raw_stab, sr, attack=0.005, decay=0.05, sustain=0.2, release=0.1)

# Print comparisons
mid_pad = len(pad) // 2
mid_stab = len(stab) // 2
print(f"Pad peak: {np.max(np.abs(pad)):.4f}")
print(f"Pad at 50%: {np.max(np.abs(pad[mid_pad-100:mid_pad+100])):.4f}")
print(f"Stab peak: {np.max(np.abs(stab)):.4f}")
print(f"Stab at 50%: {np.max(np.abs(stab[mid_stab-100:mid_stab+100])):.4f}")""",
                        solution = """import numpy as np

def generate_osc(freq, duration, sr=44100):
    t = np.linspace(0, duration, int(sr * duration), endpoint=False)
    return np.sin(2 * np.pi * freq * t)

def apply_adsr(signal, sr, attack, decay, sustain, release):
    n = len(signal)
    a_s = int(attack * sr)
    d_s = int(decay * sr)
    r_s = int(release * sr)
    s_s = max(0, n - a_s - d_s - r_s)
    env = np.concatenate([
        np.linspace(0, 1, a_s),
        np.linspace(1, sustain, d_s),
        np.full(s_s, sustain),
        np.linspace(sustain, 0, r_s)
    ])[:n]
    return signal[:n] * env

sr = 44100
freq = 330

raw_pad = generate_osc(freq, 2.0, sr)
pad = apply_adsr(raw_pad, sr, attack=0.5, decay=0.1, sustain=0.8, release=0.5)

raw_stab = generate_osc(freq, 0.5, sr)
stab = apply_adsr(raw_stab, sr, attack=0.005, decay=0.05, sustain=0.2, release=0.1)

mid_pad = len(pad) // 2
mid_stab = len(stab) // 2
print(f"Pad peak: {np.max(np.abs(pad)):.4f}")
print(f"Pad at 50%: {np.max(np.abs(pad[mid_pad-100:mid_pad+100])):.4f}")
print(f"Stab peak: {np.max(np.abs(stab)):.4f}")
print(f"Stab at 50%: {np.max(np.abs(stab[mid_stab-100:mid_stab+100])):.4f}")""",
                        hints = listOf(
                            "Pad = slow attack (0.5s), high sustain (0.8). Stab = fast attack (0.005s), low sustain.",
                            "The code is provided — your job is to understand why the 50% values differ for pad vs stab.",
                            "Pad at 50% should be near 0.8 (sustain level). Stab at 50% should be near 0 (already releasing)."
                        )
                    ),
                    tags = listOf("synthesis", "ADSR", "oscillator", "envelope", "filter")
                ),

                Lesson(
                    id = "8_4",
                    moduleId = 8,
                    title = "EQ & Frequency Shaping",
                    subtitle = "Carving space in a mix with Python",
                    icon = "🎚️",
                    xpReward = 100,
                    estimatedMinutes = 20,
                    theory = """
# EQ: Sculpting Frequency Content

Equalization is the art of boosting or cutting specific frequency ranges. In audio ML, spectral shaping is a core skill.

## Frequency Ranges in Music
| Range | Hz | Instruments |
|-------|----|-------------|
| Sub-bass | 20-60 | Kick sub, bass rumble |
| Bass | 60-250 | Bass guitar, kick punch |
| Low-mid | 250-500 | Warmth, muddiness |
| Mid | 500-2k | Vocals, snare body |
| High-mid | 2k-6k | Presence, clarity |
| Highs | 6k-20k | Air, brightness |

## Simple EQ in Python
A basic shelf filter attenuates frequencies above or below a cutoff.

**Low-shelf boost** = makes low frequencies louder
**High-shelf cut** = makes high frequencies quieter
**Peak filter** = boosts/cuts a specific band

## The Mix Bus Goal
Every instrument should occupy its own frequency "lane":
- Kick: sub + click (60 Hz + 5 kHz)
- Snare: body (200 Hz) + snap (8 kHz)
- Bass: fundamental + harmonics
- Vocals: cut competing lows, boost presence (3 kHz)
- Pad: cut lows, cut highs → fit in the middle

## In Python (scipy.signal)
```python
from scipy import signal

# Butterworth low-pass filter
b, a = signal.butter(2, cutoff_hz, btype='low', fs=sample_rate)
filtered = signal.filtfilt(b, a, audio_signal)
```
                    """.trimIndent(),
                    codeExample = """import numpy as np
from scipy import signal as sp_signal

# Create a test signal: mix of bass (100Hz) + mid (1kHz) + high (8kHz)
sr = 44100
duration = 0.05  # 50ms
t = np.linspace(0, duration, int(sr * duration))

bass = np.sin(2 * np.pi * 100 * t)
mid = 0.5 * np.sin(2 * np.pi * 1000 * t)
high = 0.25 * np.sin(2 * np.pi * 8000 * t)
mixed = bass + mid + high

# Low-pass filter: keep only bass (cutoff 300 Hz)
b_lp, a_lp = sp_signal.butter(4, 300, btype='low', fs=sr)
bass_only = sp_signal.filtfilt(b_lp, a_lp, mixed)

# High-pass filter: keep only highs (cutoff 4 kHz)
b_hp, a_hp = sp_signal.butter(4, 4000, btype='high', fs=sr)
highs_only = sp_signal.filtfilt(b_hp, a_hp, mixed)

print(f"Original RMS: {np.sqrt(np.mean(mixed**2)):.4f}")
print(f"After LPF (300Hz) RMS: {np.sqrt(np.mean(bass_only**2)):.4f}")
print(f"After HPF (4kHz) RMS: {np.sqrt(np.mean(highs_only**2)):.4f}")
print("Observation: LPF captures mainly the bass component")""",
                    exercise = Exercise(
                        prompt = "Build a 'vocal treatment' EQ: start with a signal containing 80Hz + 500Hz + 3000Hz + 10000Hz. Apply (1) a high-pass filter at 120 Hz to remove rumble, (2) a low-pass filter at 8000 Hz to reduce harshness. Print the RMS before and after each filter to show the effect.",
                        starterCode = """import numpy as np
from scipy import signal as sp_signal

sr = 44100
t = np.linspace(0, 0.05, int(sr * 0.05))

# Original vocal-like signal
raw = (np.sin(2 * np.pi * 80 * t) +
       np.sin(2 * np.pi * 500 * t) +
       np.sin(2 * np.pi * 3000 * t) +
       np.sin(2 * np.pi * 10000 * t))

print(f"Original RMS: {np.sqrt(np.mean(raw**2)):.4f}")

# Step 1: High-pass at 120 Hz (remove low rumble)
b_hp, a_hp = sp_signal.butter(4, , btype='high', fs=sr)
after_hp =

print(f"After HPF 120Hz RMS: {np.sqrt(np.mean(after_hp**2)):.4f}")

# Step 2: Low-pass at 8000 Hz on the result above
b_lp, a_lp = sp_signal.butter(4, , btype='low', fs=sr)
after_lp =

print(f"After LPF 8kHz RMS: {np.sqrt(np.mean(after_lp**2)):.4f}")
print("Vocal treatment complete!")""",
                        solution = """import numpy as np
from scipy import signal as sp_signal

sr = 44100
t = np.linspace(0, 0.05, int(sr * 0.05))

raw = (np.sin(2 * np.pi * 80 * t) +
       np.sin(2 * np.pi * 500 * t) +
       np.sin(2 * np.pi * 3000 * t) +
       np.sin(2 * np.pi * 10000 * t))

print(f"Original RMS: {np.sqrt(np.mean(raw**2)):.4f}")

b_hp, a_hp = sp_signal.butter(4, 120, btype='high', fs=sr)
after_hp = sp_signal.filtfilt(b_hp, a_hp, raw)
print(f"After HPF 120Hz RMS: {np.sqrt(np.mean(after_hp**2)):.4f}")

b_lp, a_lp = sp_signal.butter(4, 8000, btype='low', fs=sr)
after_lp = sp_signal.filtfilt(b_lp, a_lp, after_hp)
print(f"After LPF 8kHz RMS: {np.sqrt(np.mean(after_lp**2)):.4f}")
print("Vocal treatment complete!")""",
                        hints = listOf(
                            "sp_signal.butter(4, CUTOFF_FREQ, btype='high', fs=sr) for high-pass",
                            "sp_signal.filtfilt(b, a, signal) applies the filter",
                            "For step 2, filter after_hp (not raw) to chain the two filters"
                        )
                    ),
                    tags = listOf("EQ", "filter", "frequency", "mixing", "scipy")
                )
            )
        ),

        // ── Android Development ─────────────────────────────────────────────
        Module(
            id = 9,
            title = "Android Development",
            description = "Build native Android apps with Kotlin — from Hello World to real features",
            icon = "📱",
            color = "#4CAF50",
            lessons = listOf(
                Lesson(
                    id = "9_1",
                    moduleId = 9,
                    title = "Kotlin Basics",
                    subtitle = "Variables, functions, and Android's language",
                    icon = "🔷",
                    xpReward = 70,
                    estimatedMinutes = 15,
                    theory = """
# Kotlin: Android's Modern Language

Kotlin is the official language for Android development. It's concise, safe, and interoperable with Java.

## Variables
```kotlin
// Immutable (val) — like Python's constant
val sampleRate: Int = 44100
val appName: String = "PyAudio Tutor"

// Mutable (var) — can change
var currentBpm: Int = 120
var isPlaying: Boolean = false
```

## Type Inference
Kotlin can infer types (like Python):
```kotlin
val frequency = 440.0  // Kotlin infers Double
var genre = "Electronic"  // Kotlin infers String
```

## Functions
```kotlin
fun greetUser(name: String): String {
    return "Welcome back, ${'$'}name!"
}

// Single-expression (no braces needed):
fun double(x: Int) = x * 2
fun hzToMidi(hz: Double) = (69 + 12 * Math.log(hz / 440.0) / Math.log(2.0)).toInt()
```

## String Templates
Just like Python f-strings:
```kotlin
val bpm = 128
println("Track BPM: ${'$'}bpm")  // Track BPM: 128
println("Next bar at beat ${'$'}{bpm * 4}")
```

## Null Safety
Kotlin prevents null pointer crashes:
```kotlin
var name: String? = null  // ? means nullable
val length = name?.length ?: 0  // Elvis operator: use 0 if null
```
                    """.trimIndent(),
                    codeExample = """// Kotlin basics demo (run in Claude's interpreter as pseudocode)
// Note: real Kotlin runs in Android Studio, not Python

// These are Python equivalents to illustrate concepts:

# val = constant (Kotlin)
sample_rate = 44100  # (Python equivalent)

# var = mutable variable (Kotlin)
current_bpm = 120

# Kotlin function:
# fun describe_track(title: String, bpm: Int): String {
#     return "${'$'}title at ${'$'}bpm BPM"
# }

# Python equivalent:
def describe_track(title, bpm):
    return f"{title} at {bpm} BPM"

result = describe_track("Ambient Drift", 80)
print(result)

# Null safety comparison
genre = None  # Kotlin: var genre: String? = null
length = len(genre) if genre else 0  # Kotlin: genre?.length ?: 0
print(f"Genre length (safe): {length}")""",
                    exercise = Exercise(
                        prompt = "Write Python code that mirrors these Kotlin concepts: (1) Create an immutable 'val' equivalent (a constant) for max_volume = 100, (2) a mutable 'var' equivalent for current_volume = 75, (3) a function that takes a frequency and returns the note name using the formula from Module 3, (4) demonstrate null-safe access using Python's 'or' pattern.",
                        starterCode = """# Kotlin concept: val (immutable)
MAX_VOLUME = 100  # Python convention for constants

# Kotlin concept: var (mutable)
current_volume = 75

# Kotlin function equivalent
def frequency_to_note_name(frequency):
    import math
    notes = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
    # Your code here
    pass

# Kotlin null safety: name?.length ?: 0
def safe_length(text):
    # Return 0 if text is None, otherwise return len(text)
    pass

# Test your functions
print(frequency_to_note_name(440))   # Should print A
print(safe_length("Electronic"))     # Should print 10
print(safe_length(None))             # Should print 0""",
                        solution = """import math

MAX_VOLUME = 100
current_volume = 75

def frequency_to_note_name(frequency):
    notes = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
    semitones = round(12 * math.log2(frequency / 440))
    midi = 69 + semitones
    return notes[midi % 12]

def safe_length(text):
    return len(text) if text else 0

print(frequency_to_note_name(440))
print(safe_length("Electronic"))
print(safe_length(None))""",
                        hints = listOf(
                            "For frequency_to_note_name: use 12 * math.log2(frequency / 440) to get semitones from A4",
                            "MIDI note = 69 + semitones. Note name index = MIDI % 12",
                            "Safe length: return len(text) if text is not None else 0 — or use: return len(text) if text else 0"
                        )
                    ),
                    tags = listOf("kotlin", "android", "variables", "functions")
                ),

                Lesson(
                    id = "9_2",
                    moduleId = 9,
                    title = "Android UI Basics",
                    subtitle = "Views, layouts, and ViewBinding",
                    icon = "🖼️",
                    xpReward = 90,
                    estimatedMinutes = 20,
                    theory = """
# Android UI: Building Screens

Android screens are built with XML layouts + Kotlin code. The two connect through ViewBinding.

## XML Layout Structure
```xml
<!-- activity_main.xml -->
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/tv_title"
        android:text="Hello Audio!"
        android:textSize="24sp" />

    <Button
        android:id="@+id/btn_play"
        android:text="Play" />
</LinearLayout>
```

## Key Layout Types
| Layout | Use |
|--------|-----|
| LinearLayout | Stack views horizontally or vertically |
| ConstraintLayout | Position views relative to each other |
| ScrollView | Scrollable single-child container |
| RecyclerView | Efficient scrolling list |

## ViewBinding (Modern Approach)
```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access views directly — no findViewById!
        binding.tvTitle.text = "PyAudio Tutor"
        binding.btnPlay.setOnClickListener {
            // Handle button click
        }
    }
}
```

## View Dimensions
- `match_parent`: Fill parent's width/height
- `wrap_content`: Just enough for content
- `dp`: Density-independent pixels (scales with screen)
- `sp`: Scale-independent pixels (for text, respects user font size)

## Colors in Android
```xml
android:textColor="@color/white"        <!-- from colors.xml -->
android:background="#FF5722"            <!-- hex directly -->
android:backgroundTint="@color/primary" <!-- tint a drawable -->
```
                    """.trimIndent(),
                    codeExample = """# Android XML + Kotlin — illustrated in Python pseudocode

# What this XML does:
${"\"\"\""}
<!-- activity_player.xml -->
<LinearLayout vertical>
    <TextView id="tv_now_playing" text="Now Playing: -" />
    <SeekBar id="seek_position" />
    <LinearLayout horizontal>
        <Button id="btn_prev" text="⏮" />
        <Button id="btn_play_pause" text="▶" />
        <Button id="btn_next" text="⏭" />
    </LinearLayout>
</LinearLayout>
${"\"\"\""}

# What the Kotlin Activity does:
${"\"\"\""}
class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlayPause.setOnClickListener {
            isPlaying = !isPlaying
            binding.btnPlayPause.text = if (isPlaying) "⏸" else "▶"
            binding.tvNowPlaying.text = if (isPlaying) "Now Playing: Track 1" else "Paused"
        }
    }
}
${"\"\"\""}


# Python simulation of the state machine:
is_playing = False
track = "Ambient Waves"

def toggle_play():
    global is_playing
    is_playing = not is_playing
    btn_text = "⏸" if is_playing else "▶"
    now_playing = f"Now Playing: {track}" if is_playing else "Paused"
    print(f"Button: {btn_text} | Status: {now_playing}")

toggle_play()  # Press play
toggle_play()  # Press pause""",
                    exercise = Exercise(
                        prompt = "Simulate an Android audio player state machine in Python. Create a class AudioPlayerViewModel with: (1) properties: is_playing (bool), current_track (str), volume (int 0-100), (2) methods: play(track_name), pause(), set_volume(level), get_status() that returns a formatted string. Demonstrate using all methods.",
                        starterCode = """class AudioPlayerViewModel:
    def __init__(self):
        self.is_playing = False
        self.current_track = None
        self.volume = 80

    def play(self, track_name):
        self.is_playing = True
        self.current_track = track_name

    def pause(self):
        pass  # Set is_playing to False

    def set_volume(self, level):
        pass  # Clamp volume between 0 and 100

    def get_status(self):
        pass  # Return a formatted status string

# Test your player
player = AudioPlayerViewModel()
player.play("Deep Space Reverb")
print(player.get_status())
player.set_volume(65)
print(player.get_status())
player.pause()
print(player.get_status())""",
                        solution = """class AudioPlayerViewModel:
    def __init__(self):
        self.is_playing = False
        self.current_track = None
        self.volume = 80

    def play(self, track_name):
        self.is_playing = True
        self.current_track = track_name

    def pause(self):
        self.is_playing = False

    def set_volume(self, level):
        self.volume = max(0, min(100, level))

    def get_status(self):
        state = "▶ Playing" if self.is_playing else "⏸ Paused"
        track = self.current_track or "No track"
        return f"{state}: {track} | Volume: {self.volume}%"

player = AudioPlayerViewModel()
player.play("Deep Space Reverb")
print(player.get_status())
player.set_volume(65)
print(player.get_status())
player.pause()
print(player.get_status())""",
                        hints = listOf(
                            "pause() just sets self.is_playing = False",
                            "set_volume: use max(0, min(100, level)) to clamp between 0 and 100",
                            "get_status: use an f-string combining state, track name, and volume"
                        )
                    ),
                    tags = listOf("android", "UI", "views", "layouts", "ViewBinding")
                ),

                Lesson(
                    id = "9_3",
                    moduleId = 9,
                    title = "RecyclerView & Adapters",
                    subtitle = "Displaying scrollable lists efficiently",
                    icon = "📋",
                    xpReward = 100,
                    estimatedMinutes = 22,
                    theory = """
# RecyclerView: Efficient Lists in Android

RecyclerView is Android's powerful list component — it reuses (recycles) views for memory efficiency. Perfect for playlists, lesson lists, and music libraries.

## The Three-Part Pattern

### 1. Data Model
```kotlin
data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val durationMs: Int,
    val bpm: Int
)
```

### 2. Adapter (connects data to views)
```kotlin
class TrackAdapter(
    private val tracks: List<Track>,
    private val onTrackClick: (Track) -> Unit
) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTrackBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrackBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks[position]
        holder.binding.tvTitle.text = track.title
        holder.binding.tvArtist.text = track.artist
        holder.binding.tvBpm.text = "${'$'}{track.bpm} BPM"
        holder.itemView.setOnClickListener { onTrackClick(track) }
    }

    override fun getItemCount() = tracks.size
}
```

### 3. Activity/Fragment setup
```kotlin
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = TrackAdapter(tracks) { track ->
    // Handle track click
    Toast.makeText(this, "Playing: ${'$'}{track.title}", LENGTH_SHORT).show()
}
```

## ViewHolder Pattern
The `ViewHolder` caches references to views so Android doesn't have to search the view tree on every scroll. Critical for smooth performance with 1000+ items.
                    """.trimIndent(),
                    codeExample = """# RecyclerView pattern in Python (demonstrates the same architecture)

class Track:
    def __init__(self, id, title, artist, duration_ms, bpm):
        self.id = id
        self.title = title
        self.artist = artist
        self.duration_ms = duration_ms
        self.bpm = bpm

    def format_duration(self):
        seconds = self.duration_ms // 1000
        return f"{seconds // 60}:{seconds % 60:02d}"

# Sample data (like a music library database)
tracks = [
    Track("1", "Midnight Reverb", "Burial", 287000, 138),
    Track("2", "Acid Rain", "Aphex Twin", 312000, 160),
    Track("3", "Solar Wind", "Boards of Canada", 445000, 96),
    Track("4", "Neon Bass", "Flying Lotus", 198000, 88),
]

# Simulating what RecyclerView's adapter does:
class TrackAdapter:
    def __init__(self, tracks, on_click=None):
        self.tracks = tracks
        self.on_click = on_click

    def get_item_count(self):
        return len(self.tracks)

    def bind_view(self, position):
        track = self.tracks[position]
        # In Android, this would set TextViews; here we return a dict
        return {
            "title": track.title,
            "artist": track.artist,
            "duration": track.format_duration(),
            "bpm": f"{track.bpm} BPM"
        }

    def render_list(self):
        print(f"Showing {self.get_item_count()} tracks:\\n")
        for i in range(self.get_item_count()):
            view = self.bind_view(i)
            print(f"[{i+1}] {view['title']} - {view['artist']}")
            print(f"    {view['duration']} | {view['bpm']}")

adapter = TrackAdapter(tracks)
adapter.render_list()""",
                    exercise = Exercise(
                        prompt = "Extend the Track class with a method `get_display_info()` and build a PlaylistAdapter class that: (1) stores a list of tracks, (2) has a filter_by_bpm(min_bpm, max_bpm) method returning matching tracks, (3) has a sort_by_duration() method, (4) has render() that prints formatted list. Test filtering for 90-150 BPM.",
                        starterCode = """class Track:
    def __init__(self, id, title, artist, duration_ms, bpm):
        self.id = id
        self.title = title
        self.artist = artist
        self.duration_ms = duration_ms
        self.bpm = bpm

    def get_display_info(self):
        mins = self.duration_ms // 60000
        secs = (self.duration_ms % 60000) // 1000
        return f"{self.title} - {self.artist} | {mins}:{secs:02d} | {self.bpm} BPM"

class PlaylistAdapter:
    def __init__(self, tracks):
        self.tracks = tracks

    def filter_by_bpm(self, min_bpm, max_bpm):
        pass  # Return tracks where min_bpm <= bpm <= max_bpm

    def sort_by_duration(self):
        pass  # Sort self.tracks by duration_ms in place

    def render(self, title="Playlist"):
        print(f"=== {title} ({len(self.tracks)} tracks) ===")
        for i, track in enumerate(self.tracks):
            print(f"{i+1}. {track.get_display_info()}")

tracks = [
    Track("1", "Midnight Reverb", "Burial", 287000, 138),
    Track("2", "Acid Rain", "Aphex Twin", 312000, 160),
    Track("3", "Solar Wind", "Boards of Canada", 445000, 96),
    Track("4", "Neon Bass", "Flying Lotus", 198000, 88),
    Track("5", "Sequence 7", "Autechre", 367000, 125),
]

adapter = PlaylistAdapter(tracks)

# Test filter
filtered = adapter.filter_by_bpm(90, 150)
filtered_adapter = PlaylistAdapter(filtered)
filtered_adapter.render("90-150 BPM Tracks")

# Test sort
adapter.sort_by_duration()
adapter.render("Sorted by Duration")""",
                        solution = """class Track:
    def __init__(self, id, title, artist, duration_ms, bpm):
        self.id = id
        self.title = title
        self.artist = artist
        self.duration_ms = duration_ms
        self.bpm = bpm

    def get_display_info(self):
        mins = self.duration_ms // 60000
        secs = (self.duration_ms % 60000) // 1000
        return f"{self.title} - {self.artist} | {mins}:{secs:02d} | {self.bpm} BPM"

class PlaylistAdapter:
    def __init__(self, tracks):
        self.tracks = tracks

    def filter_by_bpm(self, min_bpm, max_bpm):
        return [t for t in self.tracks if min_bpm <= t.bpm <= max_bpm]

    def sort_by_duration(self):
        self.tracks.sort(key=lambda t: t.duration_ms)

    def render(self, title="Playlist"):
        print(f"=== {title} ({len(self.tracks)} tracks) ===")
        for i, track in enumerate(self.tracks):
            print(f"{i+1}. {track.get_display_info()}")

tracks = [
    Track("1", "Midnight Reverb", "Burial", 287000, 138),
    Track("2", "Acid Rain", "Aphex Twin", 312000, 160),
    Track("3", "Solar Wind", "Boards of Canada", 445000, 96),
    Track("4", "Neon Bass", "Flying Lotus", 198000, 88),
    Track("5", "Sequence 7", "Autechre", 367000, 125),
]

adapter = PlaylistAdapter(tracks)

filtered = adapter.filter_by_bpm(90, 150)
filtered_adapter = PlaylistAdapter(filtered)
filtered_adapter.render("90-150 BPM Tracks")

adapter.sort_by_duration()
adapter.render("Sorted by Duration")""",
                        hints = listOf(
                            "filter_by_bpm: use a list comprehension: [t for t in self.tracks if min_bpm <= t.bpm <= max_bpm]",
                            "sort_by_duration: self.tracks.sort(key=lambda t: t.duration_ms)",
                            "For render, enumerate(self.tracks) gives (index, track) pairs"
                        )
                    ),
                    tags = listOf("RecyclerView", "adapter", "lists", "android", "data")
                ),

                Lesson(
                    id = "9_4",
                    moduleId = 9,
                    title = "Coroutines & API Calls",
                    subtitle = "Async networking with Kotlin Coroutines",
                    icon = "🌐",
                    xpReward = 120,
                    estimatedMinutes = 25,
                    theory = """
# Coroutines: Async Without the Pain

Android apps must never block the main thread (UI would freeze). Kotlin Coroutines make async code look synchronous.

## The Problem
```kotlin
// ❌ WRONG — blocks UI thread, crashes on Android!
val result = makeNetworkRequest()  // Takes 2 seconds...
// UI is frozen for 2 seconds!
```

## The Solution: Coroutines
```kotlin
// ✅ CORRECT — runs on background thread
lifecycleScope.launch {
    val result = withContext(Dispatchers.IO) {
        makeNetworkRequest()  // Runs on IO thread
    }
    // Back on main thread — safe to update UI
    binding.tvResult.text = result
}
```

## Dispatchers
| Dispatcher | Use |
|-----------|-----|
| Dispatchers.Main | UI updates (main thread) |
| Dispatchers.IO | Network calls, file I/O |
| Dispatchers.Default | CPU-intensive (audio processing!) |

## Suspend Functions
```kotlin
// suspend = can be paused without blocking thread
suspend fun fetchAudioAnalysis(url: String): AudioData {
    return withContext(Dispatchers.IO) {
        // Network call here
        apiClient.get(url)
    }
}
```

## Result<T> Pattern
Kotlin's safe error handling (similar to Python's try/except):
```kotlin
result.fold(
    onSuccess = { data -> binding.tvData.text = data.toString() },
    onFailure = { error -> binding.tvError.text = error.message }
)
```

## In This App
Every Claude API call uses this pattern:
```kotlin
lifecycleScope.launch {
    val result = claudeService.evaluateExercise(code, prompt, apiKey)
    result.fold(
        onSuccess = { response -> showFeedback(response) },
        onFailure = { error -> showError(error.message) }
    )
}
```
                    """.trimIndent(),
                    codeExample = """# Coroutines illustrated in Python using asyncio

import asyncio
import time

# Simulated async API calls
async def fetch_audio_metadata(track_id):
    ${"\"\"\""}Simulates a network request (runs 'in background')${"\"\"\""}
    await asyncio.sleep(0.1)  # Simulate 100ms network delay
    return {
        "id": track_id,
        "title": f"Track {track_id}",
        "bpm": 120 + track_id * 5,
        "key": ["Am", "C", "Dm", "F"][track_id % 4]
    }

async def fetch_all_tracks(track_ids):
    ${"\"\"\""}Fetch multiple tracks CONCURRENTLY (like Kotlin's async/await)${"\"\"\""}
    # Python equivalent of Kotlin's async { ... } blocks
    tasks = [fetch_audio_metadata(tid) for tid in track_ids]
    results = await asyncio.gather(*tasks)  # Run all at once!
    return results

async def main():
    start = time.time()
    track_ids = [1, 2, 3, 4, 5]

    # Concurrent fetch (all happen at the same time)
    tracks = await fetch_all_tracks(track_ids)

    elapsed = time.time() - start
    print(f"Fetched {len(tracks)} tracks in {elapsed:.2f}s (concurrent!)")
    for track in tracks:
        print(f"  {track['title']}: {track['bpm']} BPM, Key: {track['key']}")

asyncio.run(main())""",
                    exercise = Exercise(
                        prompt = "Simulate the pattern used in this app: write a Python async function `analyze_audio_async(signal_id)` that 'fetches' audio analysis (simulate with sleep + return dict of fake features). Then write `batch_analyze(ids)` that analyzes multiple signals concurrently and returns all results. Compare sequential vs concurrent time.",
                        starterCode = """import asyncio
import time

async def analyze_audio_async(signal_id):
    \"\"\"Simulate fetching audio analysis from an API.\"\"\"
    await asyncio.sleep(0.05)  # 50ms simulated delay
    # Return fake audio features
    return {
        "id": signal_id,
        "rms": round(0.3 + signal_id * 0.05, 3),
        "spectral_centroid": 1000 + signal_id * 200,
        "note": ["C", "D", "E", "F", "G", "A", "B"][signal_id % 7]
    }

async def batch_analyze(signal_ids):
    \"\"\"Analyze all signals concurrently.\"\"\"
    pass  # Use asyncio.gather to run all at once

async def sequential_analyze(signal_ids):
    \"\"\"Analyze signals one by one (slow!).\"\"\"
    results = []
    for sid in signal_ids:
        result = await analyze_audio_async(sid)
        results.append(result)
    return results

async def main():
    ids = list(range(8))

    # Time sequential
    start = time.time()
    seq_results = await sequential_analyze(ids)
    seq_time = time.time() - start

    # Time concurrent
    start = time.time()
    con_results = await batch_analyze(ids)
    con_time = time.time() - start

    print(f"Sequential: {seq_time:.2f}s")
    print(f"Concurrent: {con_time:.2f}s")
    print(f"Speedup: {seq_time/con_time:.1f}x faster!")

asyncio.run(main())""",
                        solution = """import asyncio
import time

async def analyze_audio_async(signal_id):
    await asyncio.sleep(0.05)
    return {
        "id": signal_id,
        "rms": round(0.3 + signal_id * 0.05, 3),
        "spectral_centroid": 1000 + signal_id * 200,
        "note": ["C", "D", "E", "F", "G", "A", "B"][signal_id % 7]
    }

async def batch_analyze(signal_ids):
    tasks = [analyze_audio_async(sid) for sid in signal_ids]
    return await asyncio.gather(*tasks)

async def sequential_analyze(signal_ids):
    results = []
    for sid in signal_ids:
        results.append(await analyze_audio_async(sid))
    return results

async def main():
    ids = list(range(8))

    start = time.time()
    seq_results = await sequential_analyze(ids)
    seq_time = time.time() - start

    start = time.time()
    con_results = await batch_analyze(ids)
    con_time = time.time() - start

    print(f"Sequential: {seq_time:.2f}s")
    print(f"Concurrent: {con_time:.2f}s")
    print(f"Speedup: {seq_time/con_time:.1f}x faster!")

asyncio.run(main())""",
                        hints = listOf(
                            "batch_analyze: create a list of tasks then use await asyncio.gather(*tasks)",
                            "asyncio.gather runs all coroutines concurrently instead of one at a time",
                            "tasks = [analyze_audio_async(sid) for sid in signal_ids] — list comprehension creates all coroutines"
                        )
                    ),
                    tags = listOf("coroutines", "async", "networking", "kotlin", "concurrency")
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

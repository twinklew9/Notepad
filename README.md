# 📝 Notepad Application

A lightweight Java-based desktop text editor with essential text editing, file management, search and replace, and custom encryption/decryption, all through a simple graphical interface.

![Java](https://img.shields.io/badge/Java-JDK-orange?logo=openjdk&logoColor=white)
![GUI](https://img.shields.io/badge/GUI-Java%20AWT-blue)
![Type](https://img.shields.io/badge/Type-Desktop%20App-green)
![License](https://img.shields.io/badge/Purpose-Educational-lightgrey)

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Class Descriptions](#-class-descriptions)
- [Encryption & Decryption Workflow](#-encryption--decryption-workflow)
- [Supported File Types](#-supported-file-types)
- [Keyboard Shortcuts](#-keyboard-shortcuts)
- [Getting Started](#-getting-started)
- [Application Workflow](#-application-workflow)
- [Project Objectives](#-project-objectives)
- [Learning Outcomes](#-learning-outcomes)
- [Feature Status](#-feature-status)
- [Known Limitations](#-known-limitations)
- [Future Enhancements](#-future-enhancements)
- [Security Note](#-security-note)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)

---

## 📌 Overview

The **Notepad Application** is a desktop text editor built with **Java AWT**. It provides a simple, intuitive environment for creating, editing, opening, and saving text-based documents.

Beyond standard editing, it includes **Find**, **Replace**, **Replace All**, and a custom **encryption/decryption** feature. The project is split into multiple Java classes, with separate components for file operations, dialogs, encryption, decryption, and shared constants. Encrypted files are stored in a custom `.mg` format.

The project demonstrates practical use of:

- Object-Oriented Programming
- Java AWT GUI development
- Event-driven programming
- File handling and I/O
- Text processing
- Dialog-based user interaction
- Keyboard event handling
- Custom encryption and decryption logic
- Modular application design

---

## ✨ Features

### 📝 Text Editing
A basic text editor built on Java's `TextArea` component. Users can enter, modify, and manage text directly in the window.

### 📂 File Management
Open and save files using Java's `FileDialog`. See [Supported File Types](#-supported-file-types) for the full list.

### ➕ New Document
Create a new Notepad window with the **New** option.

### 💾 Save File
Save documents with the **Save** option. For new documents, a save dialog appears and `.mg` is used as the default extension.

### ✂️ Cut, Copy and Paste
Basic clipboard operations. The selected text is held temporarily inside the application.

### 🔎 Find
Search for specific text in the document. Matches are highlighted, and the search can continue from the previous position.

### 🔄 Replace
A dedicated Replace dialog with:

| Control | Purpose |
|---------|---------|
| **Find What** | Text to search for |
| **Replace With** | Replacement text |
| **Find Next** | Jump to the next occurrence |
| **Replace** | Replace the current selection |
| **Replace All** | Replace every occurrence |

### 🔐 Encryption
A custom mechanism implemented in the `Encrypt` class. A random key is generated for each encryption, and key information is embedded at the beginning of the text before the content is transformed.

### 🔓 Decryption
The `Decrypt` class reconstructs the key from the encrypted content and recovers the original text. Files in the `.mg` format are automatically decrypted when opened.

### ⚠️ Error Handling
Custom alert dialogs display messages for:

- Empty Find field
- Text not found
- Unsupported file types
- Other user interaction errors

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| **Java** | Core programming language |
| **Java AWT** | Graphical user interface |
| **Java I/O** | File reading and writing |
| **Java Event Handling** | User interaction and application events |
| **Object-Oriented Programming** | Application structure and modular design |

---

## 🏗️ Architecture

Each class is responsible for a specific part of the system.

```text
                    ┌──────────────────────┐
                    │     Notepad.java     │
                    │   Main Application   │
                    └──────────┬───────────┘
                               │
             ┌─────────────────┼─────────────────┐
             │                 │                 │
             ▼                 ▼                 ▼
     ┌───────────────┐  ┌───────────────┐  ┌────────────────┐
     │ FileOperations│  │ FindDialogBox │  │ReplaceDialogBox│
     └───────┬───────┘  └───────────────┘  └────────────────┘
             │
        ┌────┴─────┐
        ▼          ▼
  ┌──────────┐ ┌──────────┐
  │ Encrypt  │ │ Decrypt  │
  └──────────┘ └──────────┘

              ┌──────────────────┐
              │  AlertDialogBox  │
              └──────────────────┘

              ┌──────────────────┐
              │    Constants     │
              └──────────────────┘
```

### Core Modules

| Module | Responsibility |
|--------|----------------|
| `Notepad` | Main application and GUI |
| `FileOperations` | File reading and writing |
| `FindDialogBox` | Text searching |
| `ReplaceDialogBox` | Find and replace |
| `Encrypt` | Text encryption |
| `Decrypt` | Text decryption |
| `AlertDialogBox` | Error and alert messages |
| `Constants` | Shared encryption constants |

---

## 📁 Project Structure

```text
notepad/
│
├── Notepad.java
├── FileOperations.java
├── FindDialogBox.java
├── ReplaceDialogBox.java
├── Encrypt.java
├── Decrypt.java
├── AlertDialogBox.java
├── Constants.java
├── .gitignore
└── README.md
```

---

## 📄 Class Descriptions

### `Notepad.java`
The main class of the application; it extends Java's `Frame` class. It is responsible for:

- Creating the main window, text area, and menu bar
- Handling menu actions
- Opening and saving files
- Creating new Notepad windows
- Cut, copy, and paste operations
- Invoking the Find and Replace dialogs
- Handling keyboard shortcuts
- Managing supported file extensions

The application starts from:

```java
public static void main(String[] args) {
    new Notepad();
}
```

### `FileOperations.java`
Handles file-related operations:

- Opening files and reading their content
- Saving documents
- Processing encrypted `.mg` files
- Calling the encryption and decryption classes

Uses Java I/O classes such as `FileInputStream`, `FileOutputStream`, `BufferedReader`, `DataInputStream`, and `DataOutputStream`.

### `FindDialogBox.java`
Implements the Find dialog. It lets the user:

- Enter search text
- Find the next occurrence
- See an error if the search field is empty
- See an error if the text is not found

The dialog communicates with the main `Notepad` class to perform the search.

### `ReplaceDialogBox.java`
Implements the Replace dialog with three operations:

- **Find Next**: searches for the next occurrence of the specified text
- **Replace**: replaces the currently selected occurrence
- **Replace All**: replaces all matching occurrences in the document

### `Encrypt.java`
Implements the custom encryption mechanism. When an `Encrypt` object is created, it:

1. Generates a random encryption key
2. Converts the key into binary representation
3. Embeds key information into the text
4. Transforms characters using the generated key
5. Produces the encrypted text, which is then stored in the file

### `Decrypt.java`
Reverses the encryption process. It:

1. Extracts the embedded key information
2. Reconstructs the encryption key
3. Removes the embedded metadata
4. Applies the reverse transformation
5. Returns the decrypted text

Primarily used when opening `.mg` files.

### `AlertDialogBox.java`
A reusable dialog for displaying messages and errors, such as empty search input, text not found, and unsupported files.

### `Constants.java`
An interface holding the constants used by encryption and decryption. It defines the special characters used to embed and recover encryption information.

---

## 🔐 Encryption & Decryption Workflow

The application uses a custom character-based encryption mechanism.

### Encryption Flow

```text
User enters text
       ↓
Generate random encryption key
       ↓
Convert key to binary
       ↓
Embed key information into text
       ↓
Transform characters using key
       ↓
Generate encrypted content
       ↓
Save encrypted content
```

### Decryption Flow

```text
Open encrypted .mg file
       ↓
Read encrypted content
       ↓
Extract embedded key information
       ↓
Reconstruct encryption key
       ↓
Reverse character transformation
       ↓
Recover original text
       ↓
Display text in editor
```

> **Note:** The encryption mechanism is a custom educational implementation and is not a replacement for modern, industry-standard cryptographic algorithms.

---

## 📂 Supported File Types

| Extension | Description |
|-----------|-------------|
| `.txt` | Plain text |
| `.mg` | Encrypted file format used by this application |
| `.java` | Java source |
| `.c` | C source |
| `.cpp` | C++ source |
| `.py` | Python source |
| `.php` | PHP source |
| `.js` | JavaScript source |
| `.html` | HTML |
| `.css` | CSS |
| `.xml` | XML |
| `.log` | Log files |

---

## ⌨️ Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `Ctrl + N` | Create a new Notepad window |
| `Ctrl + O` | Open a file |
| `Ctrl + S` | Save the current file |
| `Ctrl + F` | Open the Find dialog |
| `Ctrl + H` | Open the Replace dialog |
| `Ctrl + W` | Exit the application |

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Java compiler (`javac`)
- Java Runtime Environment
- A Java-compatible IDE or terminal

Verify your installation:

```bash
java -version
javac -version
```

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/YOUR-USERNAME/notepad-project.git
```

> Replace `YOUR-USERNAME` with your GitHub username.

**2. Navigate to the project directory**

```bash
cd notepad-project
```

**3. Compile the source files**

```bash
javac *.java
```

**4. Run the application**

```bash
java Notepad
```

The Notepad window should open.

### Running Through an IDE

Open the project directory in any of the following and run `Notepad.java` using its `main()` method:

- IntelliJ IDEA
- Eclipse
- NetBeans
- Visual Studio Code with Java extensions

---

## 🧪 Application Workflow

```text
Launch Application
       ↓
Create / Open Document
       ↓
Write or Edit Content
       ↓
Find / Replace Text (Optional)
       ↓
Save Document
       ↓
Encrypted Content Stored
       ↓
Reopen File
       ↓
Content Decrypted
       ↓
Continue Editing
```

---

## 🎯 Project Objectives

- Develop a functional desktop text editor using Java
- Understand Java AWT-based GUI development
- Implement event-driven programming
- Implement file handling using Java I/O
- Provide text search and replacement functionality
- Implement keyboard shortcuts for common operations
- Understand modular software design
- Implement a custom encryption and decryption mechanism
- Provide a simple and interactive user interface
- Gain practical experience building a complete Java desktop application

---

## 📚 Learning Outcomes

Building this project helps practically reinforce:

- Java classes and objects
- Inheritance
- Interfaces
- Event listeners
- AWT components
- Layout managers
- Dialog boxes
- File streams
- Buffered file reading
- Character manipulation
- String processing
- Keyboard event handling
- Exception handling
- Modular programming
- Basic encryption concepts

---

## 📊 Feature Status

| Feature | Status |
|---------|--------|
| Text Editing | ✅ Implemented |
| New Document | ✅ Implemented |
| Open File | ✅ Implemented |
| Save File | ✅ Implemented |
| Cut / Copy / Paste | ✅ Implemented |
| Find | ✅ Implemented |
| Replace / Replace All | ✅ Implemented |
| Keyboard Shortcuts | ✅ Implemented |
| Custom Encryption | ✅ Implemented |
| Custom Decryption | ✅ Implemented |
| Multiple Tabs | ❌ Not implemented |
| Auto Save | ❌ Not implemented |
| Undo / Redo | ❌ Not implemented |
| Dark Mode | ❌ Not implemented |
| Syntax Highlighting | ❌ Not implemented |

---

## ⚠️ Known Limitations

- The UI is based on Java AWT and has a basic visual design.
- No modern text formatting features.
- Clipboard functionality is implemented internally rather than through the system clipboard.
- The encryption mechanism is custom and intended for educational purposes only.
- A machine-specific command path is used to launch a new window, which may need updating on another system.
- Advanced document management (tabs, auto-save, undo/redo) is not included.

---

## 🔮 Future Enhancements

**User Interface**
- Modern graphical interface, dark mode, and custom themes
- Adjustable font size and font family
- Text formatting options
- Improved responsive layout

**Editing**
- Undo and redo
- Word count and character count
- Line numbering
- Auto-indentation
- Select All
- Improved (system) clipboard support

**File Management**
- Recent files
- Auto-save and file recovery
- Multiple document tabs
- Drag-and-drop file opening
- Better file format detection

**Security**
- Replace the custom scheme with established standards such as AES, RSA, and PBKDF2
- Secure password-based key derivation

**Additional Features**
- Print documents
- Export to PDF
- Syntax highlighting
- Custom keyboard shortcuts
- Preferences/settings panel
- Search history

---

## 🛡️ Security Note

The encryption functionality in this project demonstrates a custom encryption concept **for educational purposes only**. It should **not** be used to protect sensitive or confidential information. For real-world applications, use well-established, thoroughly reviewed cryptographic algorithms and libraries.

---

## 📸 Screenshots

> Add screenshots to a `screenshots/` folder in the repository and reference them below.

### Application Interface

![Notepad Application](screenshots/notepad-home.png)

### Find and Replace

![Find and Replace](screenshots/find-replace.png)

---

## 🤝 Contributing

Contributions and improvements are welcome.

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/new-feature
   ```
3. Make your changes.
4. Commit your changes:
   ```bash
   git commit -m "Add new feature"
   ```
5. Push the branch:
   ```bash
   git push origin feature/new-feature
   ```
6. Open a Pull Request.

---

## 📄 License

This project is intended for educational and learning purposes.

# Notepad Application

A lightweight Java-based desktop text editor that provides essential text editing, file management, search, replace, and custom encryption/decryption functionality through a simple graphical user interface.

---

## 📌 Overview

The **Notepad Application** is a desktop-based text editor developed using **Java AWT**. The application provides a simple and intuitive environment for creating, editing, opening, and saving text-based documents.

In addition to standard text-editing functionality, the application includes dedicated **Find**, **Replace**, **Replace All**, and custom **encryption/decryption** features. The project is structured into multiple Java classes, with separate components responsible for file operations, dialogs, encryption, decryption, and application constants.

The application supports working with several commonly used file extensions and uses a custom `.mg` format for encrypted file storage.

The project demonstrates practical implementation of Java concepts such as:

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

The application provides a basic text editor using Java's `TextArea` component. Users can enter, modify, and manage text directly within the application.

### 📂 File Management

The application supports opening and saving files using Java's `FileDialog`.

Supported file extensions include:

- `.txt`
- `.mg`
- `.java`
- `.c`
- `.cpp`
- `.py`
- `.php`
- `.js`
- `.html`
- `.css`
- `.xml`
- `.log`

### ➕ New Document

Users can create a new Notepad window through the **New** option.

### 💾 Save File

Documents can be saved using the **Save** option.

For new documents, the application provides a save dialog and uses the `.mg` extension as the default file format.

### ✂️ Cut, Copy and Paste

The application implements basic text editing operations:

- Cut
- Copy
- Paste

The selected text is temporarily maintained within the application for these operations.

### 🔎 Find

The **Find** feature allows users to search for specific text within the document.

The application highlights the matching text when it is found and allows users to continue searching from the previous position.

### 🔄 Replace

The application provides a dedicated Replace dialog containing:

- Find What
- Replace With
- Find Next
- Replace
- Replace All

This allows users to efficiently modify repeated text within a document.

### 🔐 Encryption

The application includes a custom encryption mechanism implemented through the `Encrypt` class.

A randomly generated key is used during the encryption process. The encryption logic embeds key information into the beginning of the text before transforming the document content.

### 🔓 Decryption

The `Decrypt` class reconstructs the encryption key from the encrypted content and uses it to recover the original text.

Files saved using the `.mg` format are automatically processed through the decryption mechanism when opened.

### ⚠️ Error Handling

The application uses custom alert dialog boxes to display messages such as:

- Empty Find field
- Text not found
- Unsupported file types
- Other user interaction errors

### ⌨️ Keyboard Shortcuts

The application supports several keyboard shortcuts:

| Shortcut | Function |
|----------|----------|
| `Ctrl + N` | New document |
| `Ctrl + O` | Open file |
| `Ctrl + S` | Save file |
| `Ctrl + F` | Find |
| `Ctrl + H` | Replace |
| `Ctrl + W` | Exit |

---

# 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| **Java** | Core programming language |
| **Java AWT** | Graphical user interface |
| **Java I/O** | File reading and writing |
| **Java Event Handling** | User interaction and application events |
| **Object-Oriented Programming** | Application structure and modular design |

---

# 🏗️ Application Architecture

The application is divided into multiple classes, where each class is responsible for a specific part of the system.

```text
                    ┌──────────────────────┐
                    │      Notepad.java    │
                    │   Main Application   │
                    └──────────┬───────────┘
                               │
             ┌─────────────────┼─────────────────┐
             │                 │                 │
             ▼                 ▼                 ▼
     ┌──────────────┐  ┌──────────────┐  ┌───────────────┐
     │FileOperations│  │ FindDialogBox │  │ReplaceDialogBox│
     └──────┬───────┘  └──────────────┘  └───────────────┘
            │
       ┌────┴─────┐
       ▼          ▼
 ┌──────────┐ ┌──────────┐
 │ Encrypt  │ │ Decrypt  │
 └──────────┘ └──────────┘

              ┌──────────────────┐
              │ AlertDialogBox   │
              └──────────────────┘

              ┌──────────────────┐
              │    Constants     │
              └──────────────────┘

📁 Project Structure
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


📄 Class Description
Notepad.java

This is the main class of the application and extends Java's Frame class.

It is responsible for:

Creating the main Notepad window
Creating the text area
Creating the menu bar
Handling menu actions
Opening files
Saving files
Creating new Notepad windows
Cut, copy and paste operations
Invoking Find and Replace dialogs
Handling keyboard shortcuts
Managing supported file extensions

The application starts from:

public static void main(String[] args) {
    new Notepad();
}
FileOperations.java

This class handles file-related operations.

Its major responsibilities include:

Opening files
Reading file content
Saving documents
Processing encrypted .mg files
Calling the encryption and decryption classes

The class uses Java I/O classes such as:

FileInputStream
FileOutputStream
BufferedReader
DataInputStream
DataOutputStream
FindDialogBox.java

This class implements the Find dialog.

It allows the user to:

Enter search text
Find the next occurrence
Display an error if the search field is empty
Display an error if the requested text is not found

The dialog communicates with the main Notepad class to perform the search operation.

ReplaceDialogBox.java

This class implements the Replace dialog.

It provides three primary operations:

Find Next

Searches for the next occurrence of the specified text.

Replace

Replaces the currently selected occurrence.

Replace All

Replaces all matching occurrences in the document.

Encrypt.java

The Encrypt class implements the application's custom encryption mechanism.

A random encryption key is generated when an Encrypt object is created.

The class:

Generates an encryption key.
Converts the key into binary representation.
Embeds key information into the text.
Performs character transformation using the generated key.
Produces encrypted text.

The encrypted output is subsequently stored in the file.

Decrypt.java

The Decrypt class reverses the encryption process.

It:

Extracts the embedded key information.
Reconstructs the encryption key.
Removes the embedded metadata.
Applies the reverse transformation.
Returns the decrypted text.

This functionality is primarily used when opening .mg files.

AlertDialogBox.java

This class provides a reusable dialog box for displaying application messages and errors.

It is used for situations such as:

Empty search input
Text not found
Unsupported files
Constants.java

This interface contains constants used by the encryption and decryption mechanisms.

It defines special characters used during the embedding and recovery of encryption information.


🔐 Encryption and Decryption Workflow

The application uses a custom character-based encryption mechanism.

Encryption Flow
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
Decryption Flow
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

Note: The encryption mechanism implemented in this project is a custom educational implementation and should not be considered a replacement for modern, industry-standard cryptographic algorithms.

📂 Supported File Types

The application recognizes the following extensions:

.txt
.mg
.java
.c
.cpp
.py
.php
.js
.html
.css
.xml
.log

The .mg format is used by the application for its encrypted file storage mechanism.

⌨️ Keyboard Shortcuts
Keyboard Shortcut	Action
Ctrl + N	Create a new Notepad window
Ctrl + O	Open a file
Ctrl + S	Save the current file
Ctrl + F	Open Find dialog
Ctrl + H	Open Replace dialog
Ctrl + W	Exit the application

🚀 Getting Started
Prerequisites

Before running the project, make sure you have:

Java Development Kit (JDK)
Java compiler (javac)
Java Runtime Environment
A Java-compatible IDE or terminal

Verify your Java installation:

java -version

Verify the Java compiler:

javac -version
📥 Installation
1. Clone the Repository
git clone https://github.com/YOUR-USERNAME/notepad-project.git

Replace YOUR-USERNAME with your GitHub username.

2. Navigate to the Project Directory
cd notepad-project
3. Compile the Source Files
javac *.java
4. Run the Application
java Notepad

The Notepad application window should open.

💻 Running Through an IDE

The project can also be opened using a Java IDE such as:

IntelliJ IDEA
Eclipse
NetBeans
Visual Studio Code with Java extensions

Open the project directory and run:

Notepad.java

using the main() method.

🧪 Application Workflow

A typical user workflow is:

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
🎯 Project Objectives

The main objectives of this project are:

To develop a functional desktop text editor using Java.
To understand Java AWT-based GUI development.
To implement event-driven programming.
To implement file handling using Java I/O.
To provide text search and replacement functionality.
To implement keyboard shortcuts for common operations.
To understand modular software design.
To implement a custom encryption and decryption mechanism.
To provide a simple and interactive user interface.
To gain practical experience in developing a complete Java desktop application.
📚 Learning Outcomes

By developing this project, the following concepts can be practically understood:

Java classes and objects
Inheritance
Interfaces
Event listeners
AWT components
Layout managers
Dialog boxes
File streams
Buffered file reading
Character manipulation
String processing
Keyboard event handling
Exception handling
Modular programming
Basic encryption concepts
🔮 Future Enhancements

The project can be further improved by introducing additional functionality.

User Interface Improvements
Modern graphical interface
Dark mode
Custom themes
Adjustable font size
Font family selection
Text formatting options
Improved responsive layout
Editing Features
Undo and redo
Word count
Character count
Line numbering
Auto-indentation
Select All functionality
Improved clipboard support
File Management
Recent files
Auto-save
File recovery
Multiple document tabs
Drag-and-drop file opening
Better file format detection
Security Improvements

The current encryption mechanism is intended for educational purposes. For production use, it could be replaced with established cryptographic standards such as:

AES
RSA
PBKDF2
Secure password-based key derivation
Additional Features
Print documents
Export to PDF
Syntax highlighting
Custom keyboard shortcuts
Preferences/settings panel
Search history
🛡️ Security Note

The encryption functionality included in this project demonstrates the implementation of a custom encryption concept for educational purposes.

It should not be used to protect sensitive or confidential information in a production environment.

For real-world applications, well-established and thoroughly reviewed cryptographic algorithms and libraries should be used instead of custom encryption implementations.

⚠️ Known Limitations

The current implementation has some limitations:

The user interface is based on Java AWT and has a basic visual design.
The application does not provide modern text formatting features.
Clipboard functionality is implemented internally rather than using the system clipboard.
The encryption mechanism is custom and intended primarily for educational purposes.
The application contains a machine-specific command path for launching a new window, which may need to be updated when running on another system.
Advanced document management features such as tabs, auto-save, and undo/redo are not currently included.
📸 Screenshots

Screenshots can be added here to demonstrate the application's interface and functionality.

Example:

## Application Interface

![Notepad Application](screenshots/notepad-home.png)

## Find and Replace

![Find and Replace](screenshots/find-replace.png)

Create a screenshots folder in the repository and place the corresponding images inside it.

🧩 Core Modules
Module	Responsibility
Notepad	Main application and GUI
FileOperations	File reading and writing
FindDialogBox	Text searching
ReplaceDialogBox	Find and replace
Encrypt	Text encryption
Decrypt	Text decryption
AlertDialogBox	Error and alert messages
Constants	Shared encryption constants
📊 Feature Summary
Feature	Status
Text Editing	✅ Implemented
New Document	✅ Implemented
Open File	✅ Implemented
Save File	✅ Implemented
Cut	✅ Implemented
Copy	✅ Implemented
Paste	✅ Implemented
Find	✅ Implemented
Replace	✅ Implemented
Replace All	✅ Implemented
Keyboard Shortcuts	✅ Implemented
Custom Encryption	✅ Implemented
Custom Decryption	✅ Implemented
Multiple Tabs	❌ Not Implemented
Auto Save	❌ Not Implemented
Undo / Redo	❌ Not Implemented
Dark Mode	❌ Not Implemented
Syntax Highlighting	❌ Not Implemented


🤝 Contributing

Contributions and improvements are welcome.

To contribute:

Fork the repository.
Create a new branch.
git checkout -b feature/new-feature
Make your changes.
Commit your changes.
git commit -m "Add new feature"
Push the branch.
git push origin feature/new-feature
Open a Pull Request.


📄 License

This project is intended for educational and learning purposes.
# Item Management System

## Overview

The Item Management System is a Java-based console application designed to manage a collection of items. It provides functionalities for adding, viewing, updating, deleting, and searching items. The system utilizes various data structures for efficient storage and retrieval, including a Doubly Linked List for sequential access, a Binary Search Tree for fast lookups by ID, a Priority Queue for viewing items by priority, and a Stack for undoing deletions. Item data can be persisted to and loaded from a file.

## Features

The system offers a console-based interactive menu with the following key features:

* **Add Item**: Add a new item with details such as ID, name, description, category, and priority.
* **View Item by ID**: Display details of a specific item by searching for its ID.
* **View All Items**: Display all items, typically sorted by priority.
* **Update Item**: Modify the details (name, description, category, priority) of an existing item.
* **Delete Item**: Remove an item from the system by its ID.
* **Undo Last Deletion**: Restore the most recently deleted item.
* **Search Items**:
    * Search items by name.
    * Search items by category.
* **Save to File**: Persist the current list of items to a file (e.g., CSV format via `ItemDBHandler`).
* **Load from File**: (Implicitly, as `ItemDBHandler` suggests loading/saving capabilities, typically at startup).
* **Interactive Console Menu**: User-friendly console interface with navigation (up/down arrow keys, enter to select) and styled output (colors, box-drawing characters for a more polished look).

## Data Structures Implemented

This project features custom implementations of several fundamental data structures:

* **`DLL<E>` (Doubly Linked List)**: As described in `DLL.html`, used for storing items in a sequential manner, allowing for efficient insertions, deletions, and traversals. It implements `ILinkedList<E>`.
* **`BinarySearchTree<TKey extends Comparable<TKey>, Value>`**: As detailed in `BinarySearchTree.html`, implemented for fast item lookups based on their ID. This BST includes self-balancing capabilities (AVL-like rotations) upon insertion to maintain performance. It implements `IBinarySearchTree<TKey, Value>`.
* **`LinkedPriorityQueue<E>`**: (Inferred from `allclasses-index.html`) A priority queue implemented using a sorted singly linked list, used for viewing items based on their priority. It implements `IPriorityQueue<E>`.
* **`MyStack<E>`**: (Inferred from `allclasses-index.html`) A generic stack implemented using a singly linked list, utilized for the "undo deletion" feature.

## Core Classes and Interfaces

The project is structured around the following core components (all within an unnamed package as per `allpackages-index.html`):

### Interfaces

* **`IBinarySearchTree<TKey, Value>`**: Defines the contract for Binary Search Tree operations like insert, delete, get, contains, height, etc. (see `IBinarySearchTree.html`).
* **`IItemManager`**: Outlines the functionalities for managing the collection of items, including adding, viewing (by ID, all), updating, deleting, undoing deletion, searching (by name, category), and saving to file (see `IItemManager.html`).
* **`ILinkedList<E>`**: Specifies the standard operations for a generic List data structure, such as add, get, set, remove, clear, size, etc. (see `ILinkedList.html`).
* **`IPriorityQueue<E>`**: (Inferred from `allclasses-index.html`) Defines the contract for a Priority Queue data structure (e.g., enqueue, dequeue, peek).

### Classes

* **`Main`**: (Inferred from `allclasses-index.html`) The entry point for the Item Management System application. It likely initializes `ItemManager` and `ConsoleMenu` and starts the main application loop.
* **`ConsoleMenu`**: Manages the console-based user interface. It displays an interactive menu, processes user input for navigation and option selection, and uses ANSI escape codes (defined as constants like `FG_CYAN`, `HORIZONTAL`, etc. in `ConsoleMenu.html` and `constant-values.html`) for styling the output.
* **`ItemManager`**: (Inferred from `allclasses-index.html` and `IItemManager.html`) The core class responsible for managing items. It uses `DLL` for sequential storage, `BinarySearchTree` for ID-based lookups, `LinkedPriorityQueue` for priority-based viewing, and `MyStack` for undoing deletions. It implements `IItemManager`.
* **`ItemDBHandler`**: (Inferred from `allclasses-index.html`) Handles the persistence of item data, likely responsible for saving to and loading from a file (e.g., CSV).
* **`BinarySearchTree<TKey, Value>`**: An implementation of `IBinarySearchTree` providing a self-balancing binary search tree.
* **`DLL<E>`**: An implementation of `ILinkedList` providing a doubly linked list.
* **`LinkedPriorityQueue<E>`**: An implementation of `IPriorityQueue` using a sorted singly linked list.
* **`MyStack<E>`**: A generic stack implementation using a singly linked list.
* **`Item`**: (Implicitly used) A class representing the items being managed. Its attributes would include ID, name, description, category, and priority.

## Getting Started

To run the Item Management System:

1.  **Prerequisites**:
    * Java Development Kit (JDK) version 17 or compatible (as indicated by Javadoc generation timestamp: Mon May 26 17:05:01 EEST 2025).
2.  **Compilation**:
    Compile all `.java` files. Since they are in an unnamed package, you can typically compile them from their directory:
    ```bash
    javac *.java
    ```
3.  **Execution**:
    Run the `Main` class to start the application:
    ```bash
    java Main
    ```
    This will launch the console-based interactive menu.

## Project Structure

All classes and interfaces are currently located in an **unnamed package**. The system is designed with a separation of concerns:

* **`Main`**: Application entry point and orchestrator.
* **`ConsoleMenu`**: Handles user interaction and presentation logic.
* **`ItemManager`**: Encapsulates the business logic for item management and coordinates the use of various data structures.
* **Data Structures (`DLL`, `BinarySearchTree`, `LinkedPriorityQueue`, `MyStack`)**: Provide the underlying mechanisms for storing and organizing data efficiently.
* **`ItemDBHandler`**: Manages data persistence (saving to and loading from files).
* **Interfaces (`IBinarySearchTree`, `IItemManager`, `ILinkedList`, `IPriorityQueue`)**: Define clear contracts for the core components, promoting modularity and allowing for alternative implementations.

The `ConsoleMenu` interacts with the `ItemManager` to perform operations based on user choices. The `ItemManager` then utilizes the appropriate data structures and the `ItemDBHandler` for these operations.

---



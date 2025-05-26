# Item Management System

## 🌟 Overview
This project is a comprehensive **Data Structure application** as an **Item Management System** designed and implemented to organize and manipulate a collection of items, each with attributes like a unique identifier, name, description, category, and priority. The system provides functionalities for adding, viewing, updating, deleting, and searching items.

It leverages several core data structures for efficient data handling:
* A **`MyStack`** is utilized to implement an "undo" feature for item deletions.
* A **`LinkedPriorityQueue`** (a type of queue) manages items based on their priority (e.g., urgent, normal), allowing for prioritized viewing.
* A **`DLL` (Doubly Linked List)** is used to store items, enabling efficient insertion and deletion operations for sequential access.
* A **`BinarySearchTree` (BST)**, featuring AVL-like rotations for self-balancing upon insertion, categorizes items by their unique identifiers, facilitating efficient searching and retrieval.

The system offers a console-based user interface for interaction and includes features like undoing deletions and persisting data through a database and CSV files.

---
## ✨ Features
* **Item Management:**
    * Add new items with ID, name, description, category, and priority.
    * View details of a specific item by its ID.
    * View all items, ordered by priority.
    * Update existing item details.
    * Delete items from the system.
* **Undo Functionality:** Undo the last item deletion using a Stack.
* **Search Capabilities:**
    * Search for items by name.
    * Search for items by category.
    * Ability to save search results to a csv file.
* **Data Persistence:**
    * Load items from a database (`Items.db` via SQLite).
    * Database operations handled by `ItemDBHandler`.
* **Console Interface:** Interactive menu-driven console UI for easy navigation and operation.

---
## 🛠️ Core Data Structures & Components
The system is built upon several key data structures and classes:

* **`Main`**: The main entry point for the application. It initializes the `ItemManager` and `ConsoleMenu`, loads data, and handles the main interaction loop.
* **`ItemManager`**: The central class responsible for managing the collection of items. It uses:
    * A **`DLL` (Doubly Linked List)** for sequential access and iteration of items.
    * A **`BinarySearchTree`** for fast lookups of items by their ID.
    * A **`LinkedPriorityQueue`** for viewing items based on their priority.
    * A **`MyStack`** to manage and undo deletions.
* **`ConsoleMenu`**: Handles the console-based user interface, displaying menus and processing user input with styled output.
* **`ItemDBHandler`**: Manages all database operations (SQLite) for items, including creating tables, inserting, updating, and deleting item records.
* **`Item`**: Represents an item with attributes like ID, name, description, category, and priority.
* **Custom Data Structure Implementations:**
    * **`DLL<E>`**: A generic Doubly Linked List.
    * **`BinarySearchTree<TKey extends Comparable<TKey>, Value>`**: A generic Binary Search Tree, featuring AVL-like rotations for self-balancing upon insertion.
    * **`LinkedPriorityQueue<E>`**: A generic Priority Queue implemented using a sorted singly linked list.
    * **`MyStack<E>`**: A generic Stack implemented using a singly linked list.
* **Interfaces:**
    * `IItemManager`: Defines the contract for item management.
    * `IBinarySearchTree`: Defines the contract for a Binary Search Tree.
    * `ILinkedList`: Defines the contract for a List data structure.
    * `IPriorityQueue`: Defines the contract for a Priority Queue.
    * `IStack`: Defines the contract for a Stack data structure.

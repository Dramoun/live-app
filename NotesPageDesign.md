# Notes Page - Design Specification

## Overview

The Notes page provides a lightweight way to organize short text notes into groups. It follows the same design philosophy as the rest of the application: simple, fast, and consistent.

The feature consists of two levels:

```
Notes
├── Programming
│   ├── Fix SQLite migration
│   ├── RecyclerView animation idea
│   └── Login testing notes
│
├── Shopping
│   ├── HDMI cable
│   ├── Coffee
│   └── Batteries
│
└── Random
    ├── Movie recommendation
    └── Weekend ideas
```

Each group contains a list of simple text notes.

---

# Navigation

The existing sidebar gains a **Notes** entry.

```
📅 Calendar
✅ Todos
📝 Notes
💰 Finance
```

Selecting **Notes** opens the **Groups View**.

Selecting a group opens the **Notes View** for that group.

While inside a group, a floating **Back** button appears in the bottom-left corner.

```
←                    +
```

* **Back** returns to the Groups View.
* **+** creates a new note.

This matches the interaction pattern already established throughout the application.

---

# Data Model

## Group

```
id
title
icon
color
createdAt
updatedAt
notes[]
```

### Visible Properties

* Title
* Icon
* Color

### Hidden Properties

* ID
* Created date
* Last updated date
* Notes collection

The `updatedAt` field is automatically refreshed whenever a note is created, edited, or deleted.

Groups are sorted by **Last Updated**.

---

## Note

```
id
text
createdAt
updatedAt
```

Notes are intentionally minimal.

Each note consists of a single text block with no formatting, attachments, or additional metadata.

---

# Groups View

The Groups View displays all note groups.

Each card contains:

```
📘 Programming

12 notes

Updated
Today
```

Example:

```
🛒 Shopping

5 notes

Updated
Yesterday
```

Displayed information:

* Icon
* Title
* Note count
* Last updated

Hidden information:

* Created date
* Internal IDs

---

# Empty State

When no groups exist:

```
📝

No groups yet.

Press + to create one.
```

The application may optionally rotate between several lighthearted messages to make the interface feel more alive.

Examples:

```
Your brain is suspiciously empty.
```

```
Time to forget fewer things.
```

---

# Create Group

The existing floating action button creates a new group.

Dialog:

```
New Group

Title

Icon

Color

[Cancel]     [Create]
```

The Icon Picker and Color Picker reuse existing application components.

---

# Notes View

Selecting a group opens its notes.

Header:

```
← Programming
```

Below the header is a vertically scrolling list of notes.

Example:

```
────────────────────────

Fix SQLite migration

────────────────────────

Need to test login flow

────────────────────────

RecyclerView animation idea

────────────────────────
```

Each note is displayed as a simple text card.

Selecting a note opens the edit dialog.

---

# Creating a Note

Floating Action Button:

```
+
```

Dialog:

```
New Note

__________________________

[Cancel]     [Save]
```

---

# Editing a Note

Dialog:

```
Edit Note

__________________________

[Delete]

[Cancel]     [Save]
```

Deleting a note also updates the parent group's `updatedAt` timestamp.

---

# Sidebar Shortcuts

To improve navigation, the sidebar may display the three most recently updated groups beneath the Notes entry.

Example:

```
📝 Notes
    Programming
    Shopping
    Random
```

Selecting:

* **Notes** opens the Groups View.
* **Programming** opens that group directly.

This provides quick access to frequently used groups without adding unnecessary complexity.

---

# Component Reuse

The Notes feature intentionally reuses existing application components wherever possible.

Existing reusable components include:

* Floating Action Button
* Icon Picker
* Color Picker
* Dialog layouts
* Card layouts
* Navigation structure
* Sidebar

Implementation should closely follow the existing Calendar and Todo pages to maintain consistency throughout the application.

---

# Design Principles

The Notes page is designed around a few core principles:

* Simple and distraction-free.
* Fast CRUD operations.
* Minimal visual noise.
* Consistent interaction patterns.
* Hidden metadata that improves usability without cluttering the interface.

The goal is to create a lightweight personal notebook rather than a feature-rich note-taking application.

---

# Future Ideas

These ideas are intentionally outside the initial implementation scope but fit naturally into the existing design.

### Todo Integration

Allow a note to create a Todo item.

Example workflow:

```
Programming

"Finish RecyclerView animations"
```

↓

Press:

```
Add to Todo
```

↓

The application opens today's Todo page and creates a new task.

Example title:

```
Finish Rec...
```

The task title is generated from the beginning of the selected note.

This provides a quick way to turn ideas into actionable tasks while keeping Notes and Todos connected.


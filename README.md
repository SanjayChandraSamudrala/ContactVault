---

# 📇 ContactVault

A simple **Java Swing GUI** application to manage and store your contacts locally using a table and file storage. You can **add, edit, delete, search**, and **persist contacts** using a user-friendly interface.

---

## 🛠️ Features

* 📝 Add new contacts with **Name**, **Phone Number**, and **Email**
* ✏️ Edit existing contact details
* ❌ Delete a selected contact
* 🔍 Search for contacts based on name, phone, or email
* 📄 Automatically saves contacts in a local file (`contacts.txt`)
* 🔁 Loads contacts on application startup
* 🧹 Clear input fields and selection with a button click
* 🖱️ Select a contact by clicking on a table row

---

## 🖥️ Technologies Used

* Java 8+
* Swing (Java GUI framework)
* File I/O (for saving/loading contacts)

---

## 📂 Project Structure

```
ContactVault.java      --> Main source code
contacts.txt           --> (Auto-created) Data file storing contacts
README.md              --> Project documentation
```

---

## 🚀 How to Run

### Prerequisites:

* JDK 8 or later
* Java IDE (like IntelliJ, Eclipse) or a terminal

### Steps:

1. **Clone the Repository:**

```bash
git clone https://github.com/your-username/ContactVault.git
cd ContactVault
```

2. **Compile the Program:**

```bash
javac ContactVault.java
```

3. **Run the Application:**

```bash
java ContactVault
```

That's it! The GUI will launch, and you can start adding contacts.

---

## 📸 Screenshots

> *You can insert screenshots here to show the UI*
> Example:
> ![screenshot](path/to/screenshot.png)

---

## 📌 Notes

* The contact data is saved in a file called `contacts.txt` in the root directory.
* Data persists between runs, so your contacts are saved even after you close the application.

---

## 📢 Future Improvements (Optional Ideas)

* Export/Import contacts as CSV
* Phone number/email validation
* Sorting functionality
* Dark mode UI

---

## 🧑‍💻 Author

**Sanjay Chandra**

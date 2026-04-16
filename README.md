# 🎬 Movie Recommendation System  
### *OOAD Mini Project — Java*

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Concepts-blue?style=for-the-badge)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-Strategy-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

---

## 📌 Overview

This project is a **Movie Recommendation System** built using **Java**, applying core **Object-Oriented Analysis and Design (OOAD)** principles.

The system allows users to:
- Browse movies from a dataset  
- Track watched content  
- Add reviews and ratings  
- Get personalized recommendations using multiple strategies  

---

## ✨ Features

- 🎥 Movie catalog from CSV dataset  
- 👤 User-based tracking system  
- ⭐ Review and rating functionality  
- 🤖 Multiple recommendation strategies:
  - Genre-based recommendations  
  - Top-rated movies  
  - Unwatched movies  
  - Watch-history-based suggestions  
- 🔄 Runtime strategy switching  
- 🧩 Modular and scalable architecture  

---

### 🔑 OOP Concepts Used
- Encapsulation  
- Abstraction  
- Inheritance  
- Polymorphism
  
```java
RecommendationStrategy strategy = new GenreBasedRecommendation();
engine.setStrategy(strategy);
engine.recommend(user);
```

✔ Easily extendable  
✔ Clean separation of logic  
✔ No need to modify existing code  

---

## 🏗️ Architecture (MVC)

- **Model** → Movie, User, Review  
- **View** → MovieUI  
- **Controller** → MovieController  

---

## ⚙️ Installation & Setup

### 🛠️ Prerequisites
- Java JDK 8 or above  
- Any IDE (IntelliJ / Eclipse / VS Code)  

### ▶️ Run the Project

```bash
# Clone repository
git clone https://github.com/your-username/your-repo-name.git

# Navigate to project folder
cd your-repo-name

# Compile all files
javac *.java

# Run application
java MovieUI
```

---

## 📊 Dataset

The system uses a CSV file (`movies.csv`) containing:
- Movie titles  
- Genres  
- Ratings  

---

## 🔄 Recommendation Flow

```
User Input → Controller → Recommendation Engine → Strategy → Output
```

Each strategy processes user preferences differently to generate recommendations.

---

## 🧪 Recommendation Strategies

| Strategy | Description |
|--------|------------|
| Genre-Based | Recommends movies based on user’s preferred genres |
| Top-Rated | Suggests highest-rated movies |
| Unwatched | Filters movies not yet seen by the user |
| Watch-Based | Uses user history for suggestions |

---

## 📈 Learning Outcomes

- Practical implementation of **OOAD principles**  
- Hands-on experience with **design patterns**  
- Improved understanding of **modular architecture**  
- Strong foundation in **Java OOP development**  

---

## 👨‍💻 Authors
**Rishab Thotapally**
(PES2UG23CS478)

**Ruhan Kaushik**
(PES2UG23CS499)

**Sachi Anikhindi**
(PES2UG23CS503)

**Rahil Pathan**  (PES2UG23CS930)

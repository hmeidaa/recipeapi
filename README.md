# Recipe Management API

A full-stack application for managing recipes with a simple front-end and robust back-end.

## 👨‍🍳 Author
**Ahmad Kazzaz**  
GitHub: [hmeidaa](https://github.com/hmeidaa)

## 🌐 Live Demo
- **Front-End**: [https://recipe-frontend-z4nc.onrender.com](https://recipe-frontend-z4nc.onrender.com)
- **API Base URL**: [https://recipeapi-qpai.onrender.com/recipes](https://recipeapi-qpai.onrender.com/recipes)
- **Database Explorer (MongoDB Atlas)**: [View on Atlas](https://cloud.mongodb.com/v2/681a320c1b7b486a45c4906c#/metrics/replicaSet/681a326169febe36212f2fab/explorer/recipeapi/recipes/find)

## 🚀 Technologies Used
- Java 21 (Spring Boot)
- MongoDB Atlas (Cloud DB)
- HTML + Bootstrap (Frontend)
- Docker + Render (Deployment)
- Postman / Swagger for API Testing

## 📦 Features

### Public Website (Frontend)
- `GET /recipes`: Display all recipes
- `POST /recipes`: Submit a new recipe

### API Capabilities (via Postman or Swagger)
- `GET /recipes`: Retrieve all recipes
- `GET /recipes/{id}`: Fetch recipe by ID
- `POST /recipes`: Create a new recipe
- `PUT /recipes/{id}`: Update an existing recipe
- `DELETE /recipes/{id}`: Delete a recipe
- `GET /recipes/search?title=...`: Search by title
- `GET /recipes/filter?category=...`: Filter by category
- `GET /recipes/paged`: Pagination support

> Swagger UI and Postman both support full functionality: pagination, filtering, editing, and deletion.

## 🧾 Recipe Format (JSON)
```json
{
  "title": "Shawarma",
  "description": "Delicious Middle Eastern wrap",
  "ingredients": ["Chicken", "Garlic Sauce", "Pita Bread"],
  "instructions": ["Grill the chicken", "Spread sauce on pita", "Wrap it up"],
  "cookingTime": 30,
  "category": "Main"
}
```

## 🛠 How to Run Locally
1. Clone the repo: `git clone https://github.com/hmeidaa/recipeapi.git`
2. Navigate into it: `cd recipeapi`
3. Build the project: `./mvnw clean package`
4. Run the JAR file: `java -jar target/recipeapi-0.0.1-SNAPSHOT.jar`

> MongoDB connection string is hardcoded. Make sure you have access.

## 🌍 Deployment
- Backend and frontend are deployed on Render.
- Dockerfile is used to containerize the backend.
- Frontend is static HTML hosted as a web service.

## 📝 License
This project is not licensed under MIT or any open license.

---
For feedback or suggestions, feel free to visit [my GitHub](https://github.com/hmeidaa).
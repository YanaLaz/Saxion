document.getElementById('add-pizza').addEventListener('click', event =>{
    window.location = '../pages/edit-pizza.html';
})

var images = [],
    index = 0;

const pizzaDisplay = document.getElementById("pizza-content");

async function getPizzas(){
    try {
        const response = await fetch('http://localhost:8080/pizzas', {
            method : "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }});

        const responseJson = await response.json();

        for (const item of responseJson) {
            const div = document.createElement("div");
            div.className = "in-box";
            div.id = item.id

            const img = document.createElement("img");
            const h2Name = document.createElement("h2");
            const divPrice = document.createElement("div");
            divPrice.className = "price";
            const btnEditIngredient = document.createElement("button")
            btnEditIngredient.className ="btn add-ingredient";
            btnEditIngredient.id = "add-ingredient"
            btnEditIngredient.addEventListener('click', function (event){
                window.location = '../pages/ingredients.html?id=' + event.target.parentNode.id;
            });
            const btnEdit = document.createElement("button");
            btnEdit.className = "btn edit-pizza";
            btnEdit.addEventListener('click', function (event){
                window.location = '../pages/edit-pizza.html?id=' + event.target.parentNode.id;
            });
            const btnDelete = document.createElement("button");
            btnDelete.className = "btn delete-pizza";
            btnDelete.addEventListener('click', function (event){
                deletePizza(event.target.parentNode.id);
            });

            img.src = await getImg(item.id);
            h2Name.innerHTML = item.name;
            divPrice.innerHTML = item.price + "€";
            // ingredients.innerHTML = getIngredientsOfPizza(item.id);
            btnEditIngredient.innerHTML = "<i class=\"fa-solid fa-plus\"></i>Edit Ingredients";
            btnEdit.innerHTML = "<i class=\"fa-solid fa-pen-to-square\"></i>Edit pizza";
            btnDelete.innerHTML = "<i class=\"fa-solid fa-trash-can delete-pizza\"></i>Delete pizza";

            div.append(img, h2Name, divPrice, await getIngredientsOfPizza(item.id, item.price), btnEditIngredient, btnEdit, btnDelete);
            pizzaDisplay.appendChild(div);
            // document.getElementById("pizza-content").appendChild(div);
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
}


async function getIngredientsOfPizza(idd, pizzaPrice) {

    const allIngredients = document.createElement("div");
    const ingredientsText = document.createElement("h2");
    ingredientsText.innerHTML = "Ingredients of pizza:";
    allIngredients.append(ingredientsText);
    try {
        const response = await fetch('http://localhost:8080/pizzas/' + idd + "/ingredients", {
            method: "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        const responseJson = await response.json();

        if (responseJson.length!=0){

        const ingredient = document.createElement("div");
        const ulElem = document.createElement("ul");

        // return responseJson;
        for (const item of responseJson) {

            const name = document.createElement("li");

            name.innerHTML = "<i class=\"fa-solid fa-star\"></i>" + item.name + ' +' + item.price + "€";

            ulElem.append(name);
            ingredient.appendChild(ulElem);
            allIngredients.appendChild(ingredient);

            pizzaPrice = pizzaPrice + item.price;
        } } else {
            const empty = document.createElement("h2");
            empty.innerHTML= "Empty list";
            allIngredients.appendChild(empty);
        }
        const totP = document.createElement("h2");
        totP.id = "message";
        totP.innerHTML = "Total price is: " + pizzaPrice + "€";
        allIngredients.appendChild(totP);
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }

    return allIngredients;
}

async function deletePizza(idd) {
    await fetch('http://localhost:8080/pizzas/' + idd, {
        method: 'delete'
    })
    location.reload();
}

getPizzas();

async function getImg(idd){
    try {
        const response = await fetch('http://localhost:8080/pizzas/' + idd + "/ingredients", {
            method: "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        const responseJson = await response.json();

        var ingredientList = [];
        // return responseJson;
        for (const item of responseJson) {
            ingredientList.push(item.name);
        }

        if (ingredientList.length == 0){
            return '../img/pizzas/margarita.png';
        }

        if (ingredientList.includes('pepperoni')){
            if (ingredientList.includes("mushrooms")){
                return "../img/pizzas/mushrooms-pepperoni.png";
            } else if (ingredientList.includes("bacon") || ingredientList.includes("ham")) {
                return "../img/pizzas/pepperoni-bacon.png";
            }
            return "../img/pizzas/pepperoni.png";
        } else if (ingredientList.includes("mushrooms")){
            if (ingredientList.includes("bacon") || ingredientList.includes("ham")){
                return "../img/pizzas/mushrooms-bacon.png";
            } else if (ingredientList.includes("chicken")){
                return "../img/pizzas/mushrooms-chicken.png";
            } else if (ingredientList.includes("vegetables")){
                return "../img/pizzas/mushrooms-vegetables.png";
            }
            return "../img/pizzas/mushrooms.png";
        } else if (ingredientList.includes("vegetables")){
            return "../img/pizzas/vegetables.png";
        } else {
            return '../img/pizzas/piz.png';
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
}


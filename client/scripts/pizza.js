// document.querySelectorAll('.edit-pizza').forEach(
//     editIcon =>{
//     editIcon.addEventListener('click', event => {
//         event.stopPropagation();
//         console.log('EDIT CLICKED', event.target.parentNode.id);
//         // window.location = '../pages/edit-pizza.html?id=' + event.target.parentNode.id;
//         window.location = '../pages/edit-pizza.html';
//     })
// })

// document.getElementById('edit-pizza').forEach(
//     editIcon => {
//         editIcon.addEventListener('click', event => {
//             event.stopPropagation();
//             console.log('EDIT CLICKED', event.target.parentNode.id);
//             window.location = '../pages/edit-pizza.html?id=' + event.target.parentNode.id;
//         })
//     })

document.getElementById('add-pizza').addEventListener('click', event =>{
    window.location = '../pages/edit-pizza.html';
})


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

            img.src = "../img/piz.png";
            h2Name.innerHTML = item.name;
            divPrice.innerHTML = item.price + "€";
            // ingredients.innerHTML = getIngredientsOfPizza(item.id);
            btnEditIngredient.innerHTML = "<i class=\"fa-solid fa-plus\"></i>Edit Ingredient";
            btnEdit.innerHTML = "<i class=\"fa-solid fa-pen-to-square\"></i>Edit pizza";

            div.append(img, h2Name, divPrice, await getIngredientsOfPizza(item.id), btnEditIngredient, btnEdit);
            pizzaDisplay.appendChild(div);
            // document.getElementById("pizza-content").appendChild(div);
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
}

function editPizza() {
    window.location = '../pages/edit-pizza.html';
}

async function getIngredientsOfPizza(idd) {
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

        const ingredient = document.createElement("div");
        const ulElem = document.createElement("ul");

        // return responseJson;
        for (const item of responseJson) {

            const name = document.createElement("li");

            name.innerHTML = "<i class=\"fa-solid fa-star\"></i>" + item.name;

            ulElem.append(name);
            ingredient.appendChild(ulElem);
            allIngredients.appendChild(ingredient);
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
    return allIngredients;
}


getPizzas();




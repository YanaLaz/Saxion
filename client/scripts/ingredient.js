const urlParams = new URLSearchParams(window.location.search);
// console.log('id clicked', urlParams.get('id'));
const idd = urlParams.get('id');

const ingredientsDisplay = document.getElementById("ingredients-content");

document.getElementById('add-ingredient').addEventListener('click', event =>{
    window.location = '../pages/edit-ingredient.html?id=' + idd;
})


async function getIngredients(){
    try {
        const response = await fetch('http://localhost:8080/pizzas/' + idd + '/ingredients', {
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
            const divKcal = document.createElement("div");
            divKcal.className = "kcal";
            const divQuantity = document.createElement("div");
            divQuantity.className = "quantity";
            const btnEdit = document.createElement("button");
            btnEdit.className = "btn edit-ingredient";
            btnEdit.addEventListener('click', function (event){
                window.location = '../pages/edit-ingredient.html?id=' + div.id;
            });

            switch (item.name) {
                case 'pepperoni':
                    img.src = "../img/pepperoni.png";
                    break;
                case 'mushrooms':
                    img.src = "../img/Mushrooms.png";
                    break;
                case 'green pepper':
                    img.src = "../img/greenpepper.png";
                    break;
                case 'ham':
                    img.src = "../img/ham.png";
                    break;
                case 'chicken':
                    img.src = "../img/chicken.png";
                case 'pineapple':
                    img.src = "../img/pineapple.png";
                    break;
                case 'bacon':
                    img.src = "../img/bacon.png";
                    break;
                case 'vegetables':
                    img.src = "../img/vegetables.png";
                    break;
            }

            h2Name.innerHTML = item.name;
            divPrice.innerHTML = item.price + "€";
            divKcal.innerHTML = item.kcal + " kcal";
            divQuantity.innerHTML = item.quantity + " pc";
            btnEdit.innerHTML = "<i class=\"fa-solid fa-pen-to-square\"></i>Edit ingredient";

            div.append(img, h2Name, divPrice, divKcal, divQuantity, btnEdit);

            ingredientsDisplay.appendChild(div);
            console.log(div);
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
}

getIngredients();

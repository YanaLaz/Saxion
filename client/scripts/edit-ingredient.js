const urlParams = new URLSearchParams(window.location.search);
const pizzaId = urlParams.get('id');

// await getIngredient(idd);

document.getElementById("ingredient-form").addEventListener("submit", async function(event) {
    event.preventDefault();
    //Check if valid input
    let ingredient = {};

    for (const elem of document.querySelectorAll("input[type=number], input[type=number], input[type=number]")) {
        ingredient[elem.name] = elem.value;
    }
    var checked = document.querySelectorAll('#tk-name :checked');
    ingredient["name"] = [...checked].map(option => option.value)[0];
    console.log(ingredient["name"]);
    addIngredient(ingredient);

    //Clear form
    document.querySelector("#ingredient-form").reset();
    //Display the message that added successfully
    document.getElementById("message").innerHTML = "The ingredient has been successfully added";
})


async function addIngredient(ingredient){
    try{
        const response = await fetch('http://localhost:8080/pizzas/' + pizzaId +'/ingredients', {
            method: 'POST',
            headers: {
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(ingredient)
        });
        await response.json();
    } catch(e){
        console.error(e);
        alert("Something went wrong!: " +e);
    }
}
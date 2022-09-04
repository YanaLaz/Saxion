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

    if (ingredient["kcal"] == '' || ingredient["price"] == '' || ingredient["quantity"] == ''){
        alert("Attributes cannot be null!");
    } else if (ingredient["kcal"] < 0 || ingredient["kcal"] > 10000 || ingredient["price"] < 0 || ingredient["price"] > 10000 || ingredient["quantity"] < 0 || ingredient["quantity"] > 10000 ){
        alert("Attributes cannot be greater than 10000 or less than 0");
    } else{

    await addIngredient(ingredient);

    //Clear form
    document.querySelector("#ingredient-form").reset();
    //Display the message that added successfully
    document.getElementById("message").innerHTML = "The ingredient has been successfully added!";}
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
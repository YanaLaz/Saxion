const urlParams = new URLSearchParams(window.location.search);
// console.log('id clicked', urlParams.get('id'));
const idd = urlParams.get('id');

if (idd != null) {
    getPizza(idd);

    document.getElementById("pizza-form").addEventListener("submit", async function(event){
        event.preventDefault();
        //Check if valid input
        let pizza = {};
        pizza["id"] = idd;

        const listOfIngredients = await getIngredientsOfPizzaJson(idd);
        if (listOfIngredients == null){
            pizza["listOfIngredients"] = [];
        } else {
            pizza["listOfIngredients"] = listOfIngredients;
        }
        for(const elem of document.querySelectorAll("input[type=text], input[type=number]")){
            pizza[elem.name]=elem.value;
        }
        editPizza(pizza, idd);

        //Clear form
        document.querySelector("#pizza-form").reset();
        //Display the message that added successfully
        document.getElementById("message").innerHTML = "The pizza has been successfully edited";

})
} else {
    document.getElementById("pizza-form").addEventListener("submit", async function(event){
        event.preventDefault();

        //Check if valid input
        let pizza = {};
        for(const elem of document.querySelectorAll("input[type=text], input[type=number]")){
            pizza[elem.name]=elem.value;
        }
        addPizza(pizza);
        //Clear form
        document.querySelector("#pizza-form").reset();
        document.getElementById("message").innerHTML = "Added " + pizza;
    })
}

async function getPizza(idd){
    const response = await fetch('http://localhost:8080/pizzas/' + idd, {
        method : "GET",
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        }});

    const responseJson = await response.json();

    document.querySelector("#tk-name").value = responseJson.name;
    document.querySelector("#tk-price").value = responseJson.price;
}

async function editPizza(pizza, idd){
    try{
        const response = await fetch('http://localhost:8080/pizzas/' + idd, {
            method: 'PUT',
            headers: {
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(pizza)
        });
        await response.json();
    } catch(e){
        console.error(e);
        alert("Something went wrong!: " +e);
    }
}


async function addPizza(pizza){
    try{
        const response = await fetch('http://localhost:8080/pizzas', {
            method: 'POST',
            headers: {
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(pizza)
        });
        await response.json();
    } catch(e){
        console.error(e);
        alert("Something went wrong!: " +e);
    }
}

async function getIngredientsOfPizzaJson(idd){
    const response = await fetch('http://localhost:8080/pizzas/' + idd + "/ingredients", {
        method : "GET",
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        }});

    const responseJson = await response.json();

    if (responseJson.length == 0){
        // console.log("ResponseJson is null!")
        return null;
    } else {
        // console.log("ResponseJson is not null!")
        return responseJson;
    }

}

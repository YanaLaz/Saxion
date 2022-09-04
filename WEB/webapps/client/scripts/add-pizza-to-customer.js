const urlParams = new URLSearchParams(window.location.search);
const customerId = urlParams.get('id');

async function getPizzasId(){
    var select = document.getElementById("tk-pizzaId");
    try {
        const response = await fetch('http://localhost:8080/pizzas', {
            method : "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }});

        const responseJson = await response.json();

        if (responseJson.length == 0){
            return alert("There is no pizzas in a database. Add some pizza at first!")
        } else {
            for (const item of responseJson) {
                const option = document.createElement("option");
                option.value = item.id;
                option.innerHTML = item.name;
                select.appendChild(option);
            }
        }

        } catch(e) {
            console.error(e);
            alert("Something went wrong: " + e);
        }
}

document.getElementById("pizza-form").addEventListener("submit", async function(event) {
    event.preventDefault();
    //Check if valid input

    var pizzaId = document.querySelector('#tk-pizzaId :checked').value;

    try {
        const response = await fetch('http://localhost:8080/customers/' + customerId + '/pizzas/' + pizzaId, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            }
        });
    } catch (e) {
        console.error(e);
        alert("Something went wrong!: " + e);
    }

    document.getElementById("message").innerHTML = "The pizza has been successfully added to customer";
});

getPizzasId();

const urlParams = new URLSearchParams(window.location.search);
// console.log('id clicked', urlParams.get('id'));
const idd = urlParams.get('id');

if (idd != null){
    getCustomer(idd);

    document.getElementById("customer-form").addEventListener("submit", async function(event) {
        event.preventDefault();

        let customer = {};
        customer["id"] = idd;
        for (const elem of document.querySelectorAll("input[type=text], input[type=text], input[type=text]")) {
            customer[elem.name] = elem.value;
        }
        editCustomer(customer, idd);

        document.getElementById("message").innerHTML = "The client has been successfully edited";
    })
} else {
    document.getElementById("customer-form").addEventListener("submit", async function(event){
        event.preventDefault();

        //Check if valid input
        let customer = {};
        for(const elem of document.querySelectorAll("input[type=text], input[type=text], input[type=text]")){
            customer[elem.name]=elem.value;
        }
        addCustomer(customer);
        //Clear form
        document.querySelector("#customer-form").reset();
        document.getElementById("message").innerHTML = "Added " + customer;
    })
}

async function addCustomer(customer){
    try{
        const response = await fetch('http://localhost:8080/customers', {
            method: 'POST',
            headers: {
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(customer)
        });
        await response.json();
    } catch(e){
        console.error(e);
        alert("Something went wrong!: " +e);
    }
}

async function editCustomer(customer, idd){
    try{
        const response = await fetch('http://localhost:8080/customers/' + idd, {
            method: 'PUT',
            headers: {
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(customer)
        });
        await response.json();
    } catch(e){
        console.error(e);
        alert("Something went wrong!: " +e);
    }
}

async function getCustomer(idd){
    const response = await fetch('http://localhost:8080/customers/' + idd, {
        method : "GET",
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        }});

    const responseJson = await response.json();

    document.querySelector("#tk-name").value = responseJson.name;
    document.querySelector("#tk-address").value = responseJson.address;
    document.querySelector("#tk-phoneNumber").value = responseJson.phoneNumber;
}
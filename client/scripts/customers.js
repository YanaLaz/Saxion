// document.querySelectorAll('tbody > tr').forEach(row => {
// // document.querySelectorAll('.projects').forEach(row => {
//     row.addEventListener('click', event => {
//         console.log('ROW CLICKED', event.target.parentNode.id);
//         window.location = '../pages/edit-customer.html?id=' + event.target.parentNode.id;
//     })
// });

// document.getElementById('customers').addEventListener("click", event => {
//     window.location = '../pages/edit-customer.html?id=' + event.target.parentNode.id;
// })


document.querySelectorAll('.edit-customer').forEach(editIcon =>{
    editIcon.addEventListener('click', event => {
        // event.stopPropagation();
        console.log('EDIT CLICKED', event.target.parentNode.parentNode.id);
        window.location = '../pages/edit-customer.html?id=' + event.target.parentNode.parentNode.id;
    })
})

document.querySelectorAll('.delete-customer').forEach(trashIcon =>{
    trashIcon.addEventListener('click', event => {
        // event.stopPropagation();
        console.log('DELETE CLICKED', event.target.parentNode.parentNode.id);
        deleteCustomer(event.target.parentNode.parentNode.id);
    })
})

document.getElementById('add-customer').addEventListener('click', event =>{
    window.location = '../pages/edit-customer.html';
})

const customerDisplay = document.getElementById("customers");

async function editCustomer(idd){
    window.location = '../pages/edit-customer.html?id=' + idd;
}

async function deleteCustomer(idd) {
    await fetch('http://localhost:8080/customers/' + idd, {
        method: 'delete'
    })
    location.reload();
        // .then(response => response.json());
}

async function getCustomers(){
    try {
        const response = await fetch('http://localhost:8080/customers', {
            method : "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }});

        const responseJson = await response.json();

        for (const item of responseJson) {
            const tr = document.createElement("tr");
            tr.id = item.id;

            const tdName = document.createElement("td");
            const tdAddress = document.createElement("td");
            const tdPhoneNumber = document.createElement("td");
            const tdEditTrash = document.createElement("td");

            tdName.innerHTML = item.name;
            tdAddress.innerHTML = item.address;
            tdPhoneNumber.innerHTML = item.phoneNumber;
            tdEditTrash.innerHTML = "<i class=\"fa-solid fa-pen-to-square edit-customer\" onclick='editCustomer(" + tr.id + ")'></i>   <i onclick='deleteCustomer(" + tr.id + ")' class=\"fa-solid fa-trash-can delete-customer\"></i>";

            tr.append(tdName, tdAddress, tdPhoneNumber, tdEditTrash);

            customerDisplay.appendChild(tr);
        }
    } catch(e) {
        console.error(e);
        alert("Something went wrong: " + e);
    }
}

getCustomers();

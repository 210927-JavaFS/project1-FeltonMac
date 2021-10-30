const URL = "http://localhost:8081/";

let buttonrow = document.getElementById("buttonRow");
let UButton = document.createElement('userRetrieveButton');
let RButton = document.createElement('reimbursementRetrieveButton');
let addButton = document.getElementById('addUserButton');
let loginButton = document.getElementById('loginButton');
let userFind = document.
RButton.onclick = getReimbursements
UButton.onclick = getUsers;
addHomeButton.onclick = addHome;
loginButton.onclick = loginToApp; 

avengerButton.innerText = "Avengers Assemble";
homeButton.innerText = "See Homes";

async function loginToApp(){
  let user = {
    username:document.getElementById("username").value,
    password:document.getElementById("password").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" //This will save the cookie when we receive it. 
  });

  if(response.status===200){
    document.getElementsByClassName("formClass")[0].innerHTML = '';
    buttonRow.appendChild(avengerButton);
    buttonRow.appendChild(homeButton);
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function getUsers(){
  let response = await fetch(URL+"users", {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    populateUsersTable(data);
  }else{
    console.log("no users returned.");
  }
}

function populateUsersTable(data){
  let tbody = document.getElementById("userBody");

  tbody.innerHTML="";

  for(let user of data){
    let row = document.createElement("tr");

    for(let cell in user){
      let td = document.createElement("td");
      if(cell!="reimbursements"|| cell!="role"){
        td.innerText=user[cell];
      }else if(cell=="reimbursements"){//${user[cell].firstname}
        td.innerText = `${user[cell].Re_id}`
      }else if(cell=="role"){
        td.innerText = `${user[cell].roleString}`
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

async function getReimbursements(){
  let response = await fetch(URL+"reimbursements", {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbursementTable(data);
  }else{
    console.log("Reimbursements not available");
  }
}

function populateReimbursementTable(data){
  let tbody = document.getElementById("reimBody");

  tbody.innerHTML="";
//var dateString = new Date().toISOString().substring(0,10);
  for(let Reimb of data){
    let row = document.createElement("tr");
    for(let cell in reimb){
      if (cell!="author" || cell != "resolver" || cell !="type" || cell !="status" ){
        if(cell=="submitted" || cell == "resolve"){
           cell = new Date().toISOString().substring(0,10);
        }
      let td = document.createElement("td");
      td.innerText = reimb[cell];
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

function userFromInput(){
  let newName = document.getElementById("homeName").value;
  let newStreetNum = document.getElementById("homeStreetNum").value; 
  let newStreetName = document.getElementById("homeStreetName").value;
  let newCity = document.getElementById("homeCity").value;
  let newRegion = document.getElementById("homeRegion").value;
  let newZip = document.getElementById("homeZip").value;
  let newCounty = document.getElementById("homeCountry").value;

  let home =  {
    name:newName,
    streetNumber:newStreetNum,
    streetName:newStreetName,
    city:newCity,
    region:newRegion,
    zip:newZip,
    country:newCounty
  }

  return home;
}
function reimbursementFromInput(){
  let newAmount = document.getElementById("amountInput").value;
  let newSubmitted = document.getElementById("submittedInput").value; 
  let newresolved = document.getElementById

  let newStreetName = document.getElementById("resolvedInput").value;
  let newCity = document.getElementById("Input").value;
  let newRegion = document.getElementById("Input").value;
  let newZip = document.getElementById("Input").value;
  let newCounty = document.getElementById("Input").value;

  let newReimb =  {
    name:newName,
    streetNumber:newStreetNum,
    streetName:newStreetName,
    city:newCity,
    region:newRegion,
    zip:newZip,
    country:newCounty
  }

  return home;
}

async function addreimbursement(){
  let reimb = getReimbursements();

  let response = await fetch(URL+"reimbursements", {
    method:'POST',
    body:JSON.stringify(reimb),
    credentials:"include"
  });

  if(response.status===201){
    console.log("reimb added sussesfully");
  }else{
    console.log("Something went wrong creating the Reimbursement.")
  }

}
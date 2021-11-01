const URL = "http://localhost:8081/";

let buttonrow = document.getElementById("buttonRow");
let UButton = document.createElement('userRetrieveButton');
let RButton = document.createElement('reimbursementRetrieveButton');
let userAdd = document.getElementById('addUserButton');
let userFind = document.getElementById('findUserButton');
let loginButton = document.getElementById('loginButton');
let reimbursementFind = document.getElementById("findByIDReimbursement");
let reimbursementAdd = document.getElementById("addReimbursement");

let approveButton = document.getElementById("approve");




RButton.onclick = getReimbursementList;
UButton.onclick = getUsersList;
userAdd.onclick = addUser;
userFind.onclick = findUser;
loginButton.onclick = loginToApp; 
reimbursementFind.onclick = findReimbursement;
reimbursementAdd.onclick = addReimbursement;
RButton.innerText = "Reimbursement list";
UButton.innerText = "user list";

async function loginToApp(){
  let user = {
    username:document.getElementById("uname").value,
    password:document.getElementById("pword").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" //This will save the cookie when we receive it. 
  });

  if(response.status===200){
    document.getElementsByClassName("formClass")[0].innerHTML = '';
    buttonRow.appendChild(UButton);
    buttonRow.appendChild(RButton);
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function getUsersList(){
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
        td.innerText = `${user[cell][0].Re_id}`// this may need to getinside a list 
      }else if(cell=="role"){
        td.innerText = `${user[cell].roleString}`
      }
      row.appendChild(td);
      }
    tbody.appendChild(row);
  }
}

async function getReimbursementList(){
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
  for(let reimb of data){
    let row = document.createElement("tr");
    for(let cell in reimb){
      let td = document.createElement("td");
      if (cell!="author" || cell != "resolver"){
        td.innerText = reimb[cell].id;
      }else if(cell=="submitted" || cell == "resolved"){
        td.innerText = new Date(reimb[cell]).toISOString().substring(0,10);// this might be a problem 
      }else if(cell =="type"){
        td.innerText =reimb[cell].typestring;
      }else if(cell =="status"){
        td.innerText =reimb[cell].status;
      }
      td.innerText = reimb[cell];
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

function userFromInput(){
  //let newName = document.getElementById("").value;
  let newUsername = document.getElementById("name").value; 
  let newFirstname = document.getElementById("firstname").value;
  let newLastname = document.getElementById("lastname").value;
  let newEmail = document.getElementById("email").value;
  let newReimbursement  = document.getElementById("reimbursement").value;
  let newPassword=null;
  let newRole=null

  let newUser =  {
    username:newUsername,
    password:newPassword,
    firstname:newFirstname,
    lastname:newLastname,
    email:newEmail,
    reimbursements:[{
        re_id:newReimbursement
      }],
    role:{
      role:newRole
    }
  }

  return newUser;
}
function reimbursementFromInput(){
  let newReimbID = document.getElementById("reimbnumber").value;
  let newAmount = document.getElementById("amountInput").value;
  let newSubmitted = null; 
  let newresolved = null;
  let newAuthor = null;// this should be who ever is signed in 
  let newResolver = null;
  let newDescription = document.getElementById("descriptionInput").value;
  let newType = document.getElementById("typeInput").value;
  let newStatus = null;

  let newReimb = {
    re_id : newReimbID,
    amount:newAmount, 
    submitted:newSubmitted,
    resolved:newresolved,
    description:newDescription,
    author:newAuthor,
    resolver:newResolver,
    status:newStatus,
    type: {
        typestring:newType
    }
     //should I add id?
}

  return newReimb;
}

async function addReimbursement(){
  let reimb = reimbursementFromInput();

  let response = await fetch(URL+"reimbursements/:"+reimb, {
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
async function addUser(){
  let usertoadd = userFromInput();

  let response = await fetch(URL+`users/${usertoadd}`, {
    method:'POST',
    body:JSON.stringify(usertoadd),
    credentials:"include"
  });

  if(response.status===201){
    console.log("reimb added sussesfully");
  }else{
    console.log("Something went wrong creating the Reimbursement.")
  }

}

async function findReimbursement(){
  let reimb = reimbursementFromInput();

  let response = await fetch(URL+"reimbursements/"+reimb.re_id, {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbursementTable(data);
  }else{
    console.log("Reimbursements not available");
  }
}
async function findUser(){
  let reimb = userFromInput();

  let response = await fetch(URL+"reimbursements/"+reimb.re_id, {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbursementTable(data);
  }else{
    console.log("Reimbursements not available");
  }
}
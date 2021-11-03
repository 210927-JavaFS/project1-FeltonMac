const URL = "http://localhost:8081/"
// window.onload = function(){}
     
let buttonrow = document.getElementById("buttonRow");
let UButton = document.getElementById('userRetrieveButton');
let RButton = document.getElementById("reimbursementRetrieveButton");
let userAdd = document.getElementById('addUserButton');
let userFind = document.getElementById('findUserButton');
let loginButton = document.getElementById('loginButton');
let reimbursementFind = document.getElementById("findByIDReimbursement");
let reimbursementAdd = document.getElementById("addReimbursement");

let approveButton = document.getElementById("approve");
let loginuser1;
let loggedinuser;



//  RButton.onclick = getReimbursementList;
//  UButton.onclick = getUsersList;
//  RButton.innerText = "Reimbursement list";
//  UButton.innerText = "user list";

userAdd.onclick = addUser;
userFind.onclick = findUser;
loginButton.onclick = loginToApp; 
reimbursementFind.onclick = findReimbursement;
reimbursementAdd.onclick = addReimbursement;
approveButton.onclick = approveReimbursement;


async function loginToApp(){
  // fetch(request).then((response) => {
  //   console.log(response);
  //   response.json().then((data) => {
  //       console.log(data);
  //   });
// });
  let user = {
    username:document.getElementById("uname").value,
    password:document.getElementById("pword").value
  }
  //sessionStorage.setItem("loginuser",user)//may have to stringify;
  //loginuser1 = sessionStorage.getItem("loginuser");
  //console.log(loginuser1+ " was saved to session");
  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" ,//This will save the cookie when we receive it. 
  });
 
  if(response.status===200){
    document.getElementsByClassName("formClass")[1].innerHTML = '';
    getLoggedinUser(user.username);
    //buttonRow.appendChild(UButton);
    //buttonRow.appendChild(RButton);
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "[LOGIN FAILED]"
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}
async  function getLoggedinUser(username){

 let response = await fetch(URL+ "usersbyname/"+ username ,{credentials:"include"});
 if (response.status === 200){
   let data = await response.json();
  sessionStorage.setItem("loggedinuser",JSON.stringify(data))
  // loggedinuser=session.getItem("loggedinuser");
  // console.log(loggedinuser + " this is the logged in full user ")
   // sessionStorage.setItem("loginuser",JSON.stringify(data));
   // loggedinuser = await JSON.parse(sessionStorage.getitem("loginuser"));
 }else console.log( response);

}

async function getUsersList(){
  let response = await fetch(URL+"users", {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    console.log(data)
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
       if(cell=="reimbursements"){//${user[cell].firstname}
          if(user[cell].length=0){
          console.log(cell + ' :the object content' + user[cell])
          td.innerText = `${user[cell][0].re_id}`}
          else{td.innerText = " no reimbursement assigned "}// this may need to getinside a list 
      }else if(cell=="role"){
        td.innerText = `${user[cell].roleString}`
      }else{
        td.innerText = `${user[cell]}`
      }
      row.appendChild(td);
      }
    tbody.appendChild(row);
  }
}

async function getReimbursementList(){
  let response = await fetch(URL+"reimbursements", {credentials:"include"});
  console.log(response.status);
  if(response.status===200){
    let data = await response.json();
    populateReimbursementTable(data);
  }else{
    console.log("Reimbursements not available");
  }
}

function populateReimbursementTable(data){
  console.log(data);
  let tbody = document.getElementById("reimbBody");
  tbody.innerHTML="";
//var dateString = new Date().toISOString().substring(0,10);
  for(let reimb of data){
    let row = document.createElement("tr");
    for(let cell in reimb){
      let td = document.createElement("td");
      if (cell=="author"){
        if(reimb[cell]){td.innerText = reimb[cell].id;}
        else{td.innerText = null}
      }
      if( cell == "resolver"){
        if(reimb[cell]){td.innerText = reimb[cell].id;}
        else{td.innerText = null}
       
      }else if(cell=="submitted" || cell == "resolved"){
        td.innerText = new Date(reimb[cell]).toISOString().substring(0,10);// this might be a problem }
      }else if(cell =="type"){
        td.innerText = reimb[cell].typestring;
      }else if(cell =="status"){
        td.innerText =reimb[cell].status;
      }else{
      td.innerText = reimb[cell];
      }
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
  let newRole=null;

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

  if(!newAmount){
    console.log("please enter a ID");
  }
  let newSubmitted = null; 
  let newresolved = null;
  let newAuthor = JSON.parse(sessionStorage.getItem("loggedinuser"));// this should be who ever is signed in 
  let newResolver = null;
  let newDescription ;
  if (document.getElementById("descriptionInput").value){
     newDescription = document.getElementById("descriptionInput").value;
  }else{
     newDescription ="no description added"
  }
  let newType = document.getElementById("typeInput").value;
  let newStatus = null;

  let newReimb = {
    re_id : newReimbID,
    amount: newAmount, 
    submitted: newSubmitted,
    resolved: newresolved,
    description: newDescription,
    author: newAuthor,
    resolver: newResolver,
    status: newStatus,
    type: {
        typestring: newType
    }
     //should I add id?
}
console.log(newReimbID)
console.log(newReimb)
  return newReimb;
}

async function addReimbursement(){
  let reimb = reimbursementFromInput();
  //reimb.re_id=0;
  let response = await fetch(URL+"reimbursements/", {
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
    console.log("Something went wrong adding the user.")
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
  let usermade = userFromInput();

  let response = await fetch(URL+"reimbursements/"+usermade.username, {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbursementTable(data);
  }else{
    console.log("user not available check username again");
  }
}
async function approveReimbursement(){
  let reimb = reimbursementFromInput();
  console.log(reimb);
  let response = await fetch(URL+"reimbursementsapprove/"+reimb.re_id, {
    method:'PUT',
    body:JSON.stringify(reimb),
    credentials:"include"
  });

  if(response.status===201){
    console.log("reimb approved sussesfully");
  }else{
    console.log("Something went wrong approving the Reimbursement.")
  }

}
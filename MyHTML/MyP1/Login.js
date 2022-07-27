const url = "http://localhost:8181";
document.getElementById("loginButton").onclick = loginFunction 
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

var myVar;
function myFunction(){
    myVar = setTimeout(showPage, 8181);
}

function showPage(){
document.getElementById("loader").style.display = "none";
document.getElementById("myDiv").style.display = "block";

}


async function loginFunction(){
    //gather user's login input
    let user = document.getElementById("username").value//names in our java objects
    let pass= document.getElementById("password").value
    let firstName= document.getElementById("firstName").value
    let lastName= document.getElementById("lastName").value
    let email= document.getElementById("email").value
     let userCreds = {
        username: user,
        password: pass,
        firstName: firstName,
        lastName: lastName,
        email: email
     }
     console.log(userCreds)
     //fetch request
     let response = await fetch (url + "/login", {
        method: "POST", //sends a post request
        body: JSON.stringify(userCreds),
        credentials: "include"//this line ensure a cookie gets captured so that we can use session
        
     })

     console.log(response.status)

     if(response.status === 202){
        let data = await response.json();//this converts incoming user data into js

        console.log("Welcome " + data.username)

     }else{
        console.log("Login Failed! Please try again!!!").style.color = "red";
       

     }
}
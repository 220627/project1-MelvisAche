const url = "http://localhost:8181";
document.getElementById("employeeButton").onclick = getReimbusrement
document.getElementById("loginButton").onclick = reimbFunction

async function reimbFunction(){
    //gather user's login input
    let amount = document.getElementById("getAmount").value//names in our java objects
    let description= document.getElementById("getDescription").value
    let author= document.getElementById("getAuthor").value
    let resolver= document.getElementById("getResolver").value
    let statusId = document.getElementById("getStatus_id_fk").value
    let typeId= document.getElementById("getType_id_fk").value
    let receipt= document.getElementById("getReceipt").value
    let dateSubmitted= document.getElementById("getSubmitted").value
    let dateResolved= document.getElementById("getResolved").value
   
     let userCreds = {
        getAmount: amount,
        getDescription: description,
        getAuthor: author,
        getResolver: resolver,
        getStatus_id_fk: statusId,
        getType_id_fk: typeId,
        getReceipt: receipt,
        getSubmitted: dateSubmitted,
        getResolved: dateResolved


        
     }
     console.log(userCreds)
     //fetch request
     let response = await fetch (url + "/reimbursement", {
        method: "POST", //sends a post request
        body: JSON.stringify(userCreds),
        credentials: "include"//this line ensure a cookie gets captured so that we can use session
        
     })

     console.log(response.status)

     if(response.status === 202){
        let data = await response.json();//this converts incoming user data into js

        document.getElementById("reimbRow").innerText = "Successfully Inserted " 

     }else{
        document.getElementById("welcomeHead").innerText="Failed to insert new reimbursement Please try again!!!"
        document.getElementById("welcomeHead").style.color = "red";

     }
}

async function getReimbusrement(){ //getEmployees is a sync function which uses fecth request to get employees from our server
    let response = await fetch(url + "/reimbursement")
    console.log(response)
    //control flow on the status code to check if the response was succesful
     if(response.status === 200){
        let data = await response.json();
        console.log()
        for(let reimbursement of data){
            let row = document.createElement("tr")
            let cell = document.createElement("td")
            //fill the call with the appropriate data
            cell.innerHTML = reimbursement.reimb_id
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.amount
            row.appendChild(cell);
            
            cell = document.createElement("td")
            cell.innerHTML = reimbursement.description
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.author
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.resolver
            row.appendChild(cell);

            

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.receipt
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.submitted
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.resolved
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.status.status
            row.appendChild(cell)

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.type.type
            row.appendChild(cell);

            document.getElementById("reimbursementBody").appendChild(row)

        }
     }else{
        alert("Something went wrong make sure your java is running!")
     }//end of get employess

}

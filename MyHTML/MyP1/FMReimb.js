const url = "http://localhost:8181";
document.getElementById("employeeButton").onclick = getReimbursement
document.getElementById("statusButton").onclick = getStatus
document.getElementById("updateButton").onclick = updateStatus

async function getReimbursement(){ //getEmployees is a sync function which uses fecth request to get employees from our server
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
            cell.innerHTML = reimbursement.status.status
            row.appendChild(cell)

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.type.type
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.submitted
            row.appendChild(cell);

            cell = document.createElement("td")
            cell.innerHTML = reimbursement.resolved
            row.appendChild(cell);


            document.getElementById("reimbursementBody").appendChild(row)

        }
     }else{
        alert("Something went wrong make sure your java is running!")
     }//end of get employess

}



async function getStatus(){
    //gather user's login input
    let status = document.getElementById("status").value//names in our java objects
    
   
     let userCreds = {
        status: status
        
     }
     console.log(userCreds)
     //fetch request
     let response = await fetch (url + "/insertStatus", {
        method: "POST", //sends a post request
        body: JSON.stringify(userCreds),
        credentials: "include"//this line ensure a cookie gets captured so that we can use session
        
     })

     console.log(response.status)

     if(response.status === 202){
        let data = await response.json();//this converts incoming user data into js

        document.getElementById("reimbRow").innerText = "Successfully Inserted " 

     }else{
        document.getElementById("welcomeHead").innerText="Failed to insert new Status Please try again!!!"
        document.getElementById("welcomeHead").style.color = "red";

     }
}

async function updateStatus(){
   //fetch request Put
   let id = document.getElementById("statusId").value//values in paranthesis are the IDS of the input elements in HTML
   let status = document.getElementById("status").value
   let response = await fetch(url + "/ers_reimbursement_status/" + id, {
       method: "PUT", //sending a put request
       body:status//no need to turn into JSOn cuz its just one number
   } )

   if (response.status === 202){
       document.getElementById("updateHeader").innerText ="Status for ID " + id + " has been successfully updated to: " + status
   }else{
       document.getElementById("updateHeader").innerText ="Update failed! Please check the Status Id again!!!"
   }

   
}
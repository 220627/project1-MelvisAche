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
    myVar = setTimeout(showPage, 3000);
}

function showPage(){
document.getElementById("loader").style.display = "none";
document.getElementById("myDiv").style.display = "block";

}
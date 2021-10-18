function switchTab(action){
    var tabcontents = document.getElementsByClassName("tabcontent");
    for (let i = 0; i < tabcontents.length; i++) {
        tabcontents[i].style.display = "none";
    }
    document.getElementById(action).style.display = "block";
}

function defaultLoad(){
    document.getElementById("signIn").style.display = "block";
}

window.onload = defaultLoad;
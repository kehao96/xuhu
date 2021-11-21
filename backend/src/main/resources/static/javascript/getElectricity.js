function getJSON(url){

    var xhr = new XMLHttpRequest();
    xhr.open('get',url,true);
    xhr.responseType ='json'
    xhr.send();

    xhr.onload =  function(){
        var status = xhr.status;
        if (status == 200) {
            data = xhr.response
            console.log(data)
        } else {
            console.log("No records found")
        }
    };

};

function startHttpQuery(){
    var cityName = document.getElementById("cityName").value
    var url= "http://localhost:8080/city";
    if(null != cityName){
        url=url + "/" + cityName;
    }
    getJSON(url)
}

function  changeCity(selectObject){
    var cityName = selectObject.value
    document.getElementById("cityName").value = cityName
}

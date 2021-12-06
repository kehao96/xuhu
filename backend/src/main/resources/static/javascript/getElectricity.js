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
            run(data)
        } else {
            console.log("No records found")
        }
    };

};
function getR(url){
    var xhr = new XMLHttpRequest();
    xhr.open('get',url,true);
    xhr.responseType ='json'
    xhr.send();

    xhr.onload =  function(){
        var status = xhr.status;
        if (status == 200) {
            data = xhr.response
            alert("R value equals: "+data)
        } else {
            console.log("No records found")
        }
    };

};

function startHttpQuery(){
    var cityName = document.getElementById("cityName").value
    var url= "http://localhost:8080/predictCity";
    var urlR= "http://localhost:8080/getR"
    if(null != cityName){
        url=url + "/" + cityName;
        urlR = urlR+ "/" + cityName;
    }
    getJSON(url)
    getR(urlR)


}

function  changeCity(selectObject){
    var cityName = selectObject.value
    document.getElementById("cityName").value = cityName
}

function run(data){
    var myChart = echarts.init(document.getElementById('main'));

    realData = Object.values(data[0])
    predictData = Object.values(data[1])
    // Specify the configuration items and data for the chart
    var option = {
        title: {
            text: 'City Electricity Consumption'
        },
        tooltip: {},
        legend: {
            data: ['Real usage', 'Predicted usage']
        },
        xAxis: {
            type: 'category',
            data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            typr: 'value',
        },
        series: [
            {
                name: 'Real usage',
                type: 'line',
                data: realData
            },
            {
                name: 'Predicted usage',
                type: 'line',
                data: predictData
            }
        ]
    };

    // Display the chart using the configuration items and data just specified.
    myChart.setOption(option);
}


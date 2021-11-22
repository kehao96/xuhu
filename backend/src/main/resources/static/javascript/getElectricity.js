function getJSON(url){

    var xhr = new XMLHttpRequest();
    xhr.open('get',url,true);
    xhr.responseType ='json'
    xhr.send();

    xhr.onload =  function(){
        var status = xhr.status;
        if (status == 200) {
            data = xhr.response
            console.log(Object.values(data))
            run(Object.values(data))
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

function run(data){
    var myChart = echarts.init(document.getElementById('main'));

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
                data: data
            },
            {
                name: 'Predicted usage',
                type: 'line',
                data: [6, 30, 41, 13, 7, 16]
            }
        ]
    };

    // Display the chart using the configuration items and data just specified.
    myChart.setOption(option);
}


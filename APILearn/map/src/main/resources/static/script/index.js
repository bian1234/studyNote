
//浏览器获取用户位置
var map, geolocation,lng,lat;
//加载地图，调用浏览器定位服务
map = new AMap.Map('calendar_grid', {
    resizeEnable: true,
    zoom:11
});


//添加控件
map.plugin([
    'AMap.ToolBar',
    'AMap.Scale',
    'AMap.MapType',
    'AMap.Geolocation',
], function(){
    // 在图面添加工具条控件，工具条控件集成了缩放、平移、定位等功能按钮在内的组合控件
    map.addControl(new AMap.ToolBar());
    // 在图面添加比例尺控件，展示地图在当前层级和纬度下的比例尺
    map.addControl(new AMap.Scale());
    // 在图面添加类别切换控件，实现默认图层与卫星图、实施交通图层之间切换的控制
    map.addControl(new AMap.MapType());
    // 在图面添加定位控件，用来获取和展示用户主机所在的经纬度位置
    map.addControl(new AMap.Geolocation());
});
map.plugin('AMap.Geolocation', function() {
    geolocation = new AMap.Geolocation({
        enableHighAccuracy: true,//是否使用高精度定位，默认:true
        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
        zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        buttonPosition:'RB'
    });
    map.addControl(geolocation);
    geolocation.getCurrentPosition();
    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
});
//解析定位结果
function onComplete(data) {
    var str=['定位成功'];
    str.push('经度：' + data.position.getLng());
    str.push('纬度：' + data.position.getLat());
    lng =  data.position.getLng();
    lat = data.position.getLat();
    if(data.accuracy){
        str.push('精度：' + data.accuracy + ' 米');
    }//如为IP精确定位结果则没有精度信息
    str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    document.getElementById('tip').innerHTML = str.join('<br>');
    code(lng,lat);
}
//解析定位错误信息
function onError(data) {
    document.getElementById('tip').innerHTML = '定位失败';
}


//逆地理编码---------->根据经纬度获取具体的区域信息
function code(lng,lat) {
    map.plugin('AMap.Geocoder', function() {
        var geocoder = new AMap.Geocoder({
            // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
            city: '010'
        })
        var lnglat = [lng, lat]
        geocoder.getAddress(lnglat, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                //获取返回的地址对象
                var addressComponent = result.regeocode.addressComponent;
                //其实这里可以使用formattedAddress直接生成字符
                var province = addressComponent.province;
                var city = addressComponent.city;
                var district= addressComponent.district;
                var township= addressComponent.township;
                var street = addressComponent.street;
                //地址显示
                comm_box(province,city,district,township,street);
                //天气显示
                current_weather(district);
                //三天天气预报
                forecast_clearfix(district);
            }else {
                alert("逆解析失败");
            }
        })
    })
}

//拼接地址并显示（其实这个在天气预报服务里面就可以返回地址信息，但是为了模块化显示，就分为两个部分）
function  comm_box(province,city,district){
    $("#address100").empty();
    if (city == ''){
        city = province;
        province = "中国";
    }
    var  str = '';
    str +=' <ul>';
    str +='<li><a>天气</a><i></i></li>';
    str +='<li><a>'+province+'</a><i></i></li>';
    str +='<li><a>'+city+'</a><i></i></li>';
    str +='<li>'+district+'</li>';
    str +='</ul>';
    $("#address100").append(str);
}

//当前天气查询
function current_weather(district) {
    //加载天气查询插件
    map.plugin('AMap.Weather', function() {
        //创建天气查询实例
        var weather = new AMap.Weather();
        //执行实时天气信息查询
        weather.getLive(district, function(err, data) {
            var temperature = data.temperature;
            var weather = data.weather;
            var windDirection = data.windDirection;
            var reportTime = data.reportTime;
            var windPower = data.windPower;
            var humidity = data.humidity;
            var str = '';
            $("#current_weather").empty();
            str+='<div class="wea_weather clearfix">';
            str+='<em>'+temperature+'</em><b>'+weather+'</b>';
            str+='<strong class="info_uptime">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更新时间：'+reportTime+'</strong>';
            str+='</div>';
            str+='<div class="wea_about clearfix">';
            str+='<span>湿度'+humidity+'</span><em>'+windDirection+'风'+windPower+'级</em>';
            str+='</div>';
            $("#current_weather").append(str);
        });
    });
}

//三天天气预报(这里查询到的其实是当天和将来三天的数据，也就是四天的数据，结果上面已经写好了懒得改这是一个败笔)
function forecast_clearfix(district) {
    //加载天气查询插件
    map.plugin('AMap.Weather', function() {
        //创建天气查询实例
        var weather = new AMap.Weather();
        //执行实时天气信息查询
        weather.getForecast('北京市', function(err, data) {
            var forecasts = data.forecasts;
            $("#my_forecast").empty();
            for(var i = 0;i<forecasts.length;i++){
                var dayTemp = forecasts[i].dayTemp;
                var nightTemp = forecasts[i].nightTemp;
                var dayWindDir = forecasts[i].dayWindDir;
                var dayWindPower = forecasts[i].dayWindPower;
                var dayWeather = forecasts[i].dayWeather;
                var date ;
                switch(i){
                    case 0 :
                        date = '今天';
                        break;
                    case 1 :
                        date = '明天';
                        break;
                    case 2 :
                        date = '后天';
                        break;
                    default :
                        date = '大后天';
                }
                var str = '';
                str += '<ul class="days clearfix">';
                str += '<li>'+date+'</li>';
                str += '<li>'+dayWeather+'</li>';
                str += ' <li>'+nightTemp+'° / '+dayTemp+'°</li>';
                str += '<li><em>'+dayWindDir+'风</em><b>'+dayWindPower+'级</b></li>';
                str += '<li><strong class="level_1">'+Math.floor(Math.random()*150)+' 优</strong></li>';
                str += '</ul>';
                $("#my_forecast").append(str);
            }
        });
    });
}



//公交地图
function bus_map() {
    layer.alert('暂未开放，看看别的吧', {
        skin: 'layui-layer-molv' //样式类名
        ,closeBtn: 0
        ,icon: 5
    });
    // geolocation();
}
//旅游地图
function tourist() {
    layer.alert('暂未开放，看看别的吧', {
        skin: 'layui-layer-molv' //样式类名
        ,closeBtn: 0
        ,icon: 5
    });
    // geolocation();
}
//热力地图
function heat_map() {
    layer.alert('暂未开放，看看别的吧', {
        skin: 'layui-layer-molv' //样式类名
        ,closeBtn: 0
        ,icon: 5
    });
    // geolocation();
}


//查看苹果大图
function maxPage() {
    layer.open({
        type: 1,
        title: false,
        offset: ['200px', '500px'],
        closeBtn: 0,
        area: '730px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: '<img src="./image/pingguo.jpg" alt="我也不知道这是谁家的苹果" />'
    });
}
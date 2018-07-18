window.onload=function (){
    welcome();
}
// 欢迎页面
function welcome() {
    var str = '<ol>' +
        '<li>这个系统是基于百度AI开放平台（<a href="https://ai.baidu.com/">https://ai.baidu.com/</a>）开发而成，所以感谢百度的大牛。</li>' +
        '<li>目前比较简单，只支持文字识别，可识别身份证、驾驶证、行驶证、银行卡、车牌、营业执照、表格文字、通用票据以及其他文字信息。后期可能会加入人脸识别和语音识别，只是可能。</li>' +
        '<li>识别信息技术来源于百度AI开放平台，不保证数据百分之百准确，有错误也是百度的错误，不是我的错。</li>' +
        '<li>这个系统可以用来玩，也可以用于减轻工作负担，但是切勿用来违法犯罪，否则谁违法谁承担。</li>' +
        '<li>有建议或需求的可以直接联系我（微信:wwwbyk，加我请备注来意）。</li>' +
        '</ol>';
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['480px', '300px'], //宽高
        content: str
    });
}
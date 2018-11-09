// 기간이 존재하는 쿠키를 생성해줌
function setCookie(cname,cvalue,exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
// 기간이 존재하지 않는 쿠키를 생성해줌
function setInstanceCookie(cname,cvalue) {
    document.cookie = cname + "=" + cvalue + ";path=/";
}
// URI 인코딩 후 쿠키 생성
function setInstanceEncodedCookie(cname, cvalue) {
	cvalue = encodeURIComponent(cvalue);
	document.cookie = cname + "=" + cvalue + ";path=/";
}
// 쿠키의 value값을 가져옴
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
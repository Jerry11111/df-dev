function showtime(){
today=new Date(); 
var year=today.getFullYear();
var month=today.getMonth()+1;
var day=today.getDate();
var week=today.getDay();
var w="";
if(week==0){
	w="日";
}else if(week==1){
	w="一";
}else if(week==2){
	w="二";
}else if(week==3){
	w="三";
}else if(week==4){
	w="四";
}else if(week==5){
	w="五";
}else if(week==6){
	w="六";
}
var hours = today.getHours(); 
var minutes = today.getMinutes(); 
var seconds = today.getSeconds(); 
var timeValue= hours;
timeValue += ((minutes < 10) ? ":0" : ":") + minutes+""; 
timeValue+=((seconds < 10) ? ":0" : ":") + seconds+"";
var timetext=year+"年"+month+"月"+day+"日    "+"星期"+w+" "+timeValue
document.getElementById("liveclock").innerHTML = timetext; //div的html是now这个字符串 
setTimeout("showtime()",1000); //设置过1000毫秒就是1秒，调用showtime方法 
}

window.onload=function(){
	showtime();
}
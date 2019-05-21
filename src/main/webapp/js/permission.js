/**
 * 使用前需引入jquery
 */

$(function(){
	//获得登录用户
	let user;
	$.get("/bookTicket/get/user",function(data){
		user=data;
		if(data==null||data===""){
			location.replace("/bookTicket/login.html");
		}
		//存在用户，则在导航栏上
		let elem=$("<img src='/bookTicket/img/person2.jpg'>");
		elem.css("width","30px");
		elem.css("height","30px");
		elem.css("float","right");
		elem.css("margin-top","15px");
		elem.css("border-radius","50%");
		elem.click(function(){
			layui.use("layer",function(){
				layui.layer.msg("当前用户："+user.name);
			});
		});
		
		$(".layui-nav").first().append(elem);
	});
})
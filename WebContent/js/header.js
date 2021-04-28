 var bar = document.getElementById("bar");
            bar.onclick = function(){
                var sidebar = document.getElementsByClassName("sider")[0];
                if(sidebar.style.display == "none" ){
                    sidebar.style.display = "block";
                }
                else{
                    sidebar.style.display = "none";
                }
            }
            var close = document.getElementById("closeicon");
            close.onclick = function(){
                var sidebar = document.getElementsByClassName("sider")[0];
                sidebar.style.display = "none";
            }
            
            
var suggestion = document.getElementById("inputSearch");


const images = [
    "/shop/FileDisplay?filename=Acer Aspire3.jpg",
    "/shop/FileDisplay?filename=Acer Aspire 5.jpg",
    "/shop/FileDisplay?filename=Xylys-Titan.jpg",
    "/shop/FileDisplay?filename=samsung-43inch-4K.jpg",
    "/shop/FileDisplay?filename=JBL IPX7.jpg",
];
var slideIndex = 0,indicatorIndex=0;
slides();
function slides(){
var image = document.getElementById("img1");
slideIndex++;

if (slideIndex > images.length) {slideIndex = 1}

image.src =images[slideIndex-1];
setTimeout(slides,3000);
}

/*function lookup1(sugg){
	document.getElementById("suggestions").style.display ="block";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
		      document.getElementById("suggestions").innerHTML = this.responseText;
		    }
	};
	xhttp.open("GET", "suggestions.jsp?q="+sugg, true);
	xhttp.send();
	
}*/
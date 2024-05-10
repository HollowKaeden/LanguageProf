"use strict";

const checkbox = document.querySelector(".checkbox");
const main = document.querySelector("main");

function checkbox_toggle(){
    if (checkbox.checked && screen.width < 1000)
    {
        main.style.opacity = "0";
        main.style.visibility = "hidden";
    }
    else
    {
        main.style.opacity = "1";
        main.style.visibility = "visible";
    }
}

checkbox.addEventListener("change", checkbox_toggle);
window.onload = checkbox_toggle;
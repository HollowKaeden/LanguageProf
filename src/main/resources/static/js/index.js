"use strict";

function parallax_effect()
{
    var parallax = document.querySelector('.parallax');
    parallax.style.backgroundPosition = 'center ' + (window.scrollY * 0.1) + 'px';
}

window.onload = parallax_effect;
window.onscroll = parallax_effect;
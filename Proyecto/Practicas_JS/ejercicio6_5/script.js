// Ejercicio 6.5 - Manejo de eventos
// Un evento es algo que pasa en la pagina (un click, escribir, mover el mouse...)
// y addEventListener nos permite ejecutar una funcion cuando ese evento ocurre.

const formulario = document.getElementById("formulario");
const numero = document.getElementById("numero");
const vistaPrevia = document.getElementById("vista-previa");
const resultado = document.getElementById("resultado");
const caja = document.getElementById("caja");

// 1. Evento "submit": se dispara al enviar el formulario
formulario.addEventListener("submit", (evento) => {
    // Evita que la pagina se recargue (comportamiento por defecto del form)
    evento.preventDefault();

    // Leemos el valor DENTRO del evento, para tener lo que el usuario escribio
    const valor = Number(numero.value);
    const total = valor * 35;

    resultado.textContent = "Tu criatura tiene " + total + " puntos de poder!";
});

// 2. Evento "input": se dispara cada vez que cambia el contenido del input
numero.addEventListener("input", () => {
    vistaPrevia.textContent = "Escribiendo: " + numero.value;
});

// 3. Eventos del mouse: "mouseover" (entra) y "mouseout" (sale)
caja.addEventListener("mouseover", () => {
    caja.style.background = "lightgreen";
    caja.textContent = "Hola!";
});

caja.addEventListener("mouseout", () => {
    caja.style.background = "lightgray";
    caja.textContent = "Pasa el mouse por aqui";
});

// 4. Evento "keydown" en todo el documento: detecta la tecla presionada
document.addEventListener("keydown", (evento) => {
    console.log("Presionaste la tecla: " + evento.key);
});

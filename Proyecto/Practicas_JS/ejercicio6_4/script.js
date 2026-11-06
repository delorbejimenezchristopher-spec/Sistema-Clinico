const formulario = document.getElementById("formulario");
const numero = document.getElementById("numero");

formulario.addEventListener("submit", (evento) => {
   // Evita que la pagina se recargue al enviar el formulario
   evento.preventDefault();

   const valor = Number(numero.value);
   const total = valor * 35;

   alert(total);
});

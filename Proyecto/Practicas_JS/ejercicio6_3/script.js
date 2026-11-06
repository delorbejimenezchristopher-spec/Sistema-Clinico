const formulario = document.getElementById("formulario");
const entradaNombre = document.getElementById("nombre");
const entradaRaza = document.getElementById("raza");
const entradaHabilidad = document.getElementById("habilidad");
const entradaSalud = document.getElementById("salud");

// Usamos "submit" en el formulario para que funcionen min, max y required
formulario.addEventListener("submit", (evento) => {
    // Evita que la pagina se recargue
    evento.preventDefault();

    // El objeto se crea DENTRO del evento y guarda los valores (.value),
    // no los elementos del HTML
    const criatura = {
        nombre: entradaNombre.value,
        raza: entradaRaza.value,
        habilidad: entradaHabilidad.value,
        salud: Number(entradaSalud.value)
    };

    alert(`Tu criatura se llama ${criatura.nombre} de la raza ${criatura.raza}. Su habilidad es ${criatura.habilidad} y posee ${criatura.salud} puntos de salud`);

    formulario.reset();
});

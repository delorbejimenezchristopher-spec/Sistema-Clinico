const boton = document.getElementById("btn-init");
const entrada = document.getElementById("primer_numero");
const miVariable = 5;

boton.addEventListener("click", () => {
    // Si el input esta vacio, avisamos y no seguimos
    if (entrada.value === "") {
        alert("Primero escribe un numero");
        return;
    }

    const valorInput = Number(entrada.value);
    const laVariableSumada = miVariable + valorInput;

    if (laVariableSumada < 10) {
        alert("Tu numero no alcanza");
    } else {
        alert("Tu numero es suficiente");
    }

    // Se limpia una sola vez, pase lo que pase
    entrada.value = "";
});

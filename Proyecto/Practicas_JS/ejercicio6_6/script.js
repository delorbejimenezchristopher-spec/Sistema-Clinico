// Ejercicio 6.6 - Depuracion simple
// Depurar = encontrar y corregir errores en el codigo.
// Herramientas basicas: console.log, console.table, debugger y try/catch.

const resultado = document.getElementById("resultado");

// Lista de productos de una compra
const productos = [
    { nombre: "Pan", precio: "50" },     // <- el precio viene como texto
    { nombre: "Leche", precio: 75 },
    { nombre: "Huevos", precio: 120 },
];

// ---------------------------------------------------------
// 1. Funcion CON ERROR
// Se espera 245, pero el resultado sale "05075120". Por que?
// ---------------------------------------------------------
function calcularTotalConError() {
    let total = "0";   // ERROR: total empieza como texto, no como numero

    for (let i = 0; i < productos.length; i++) {
        total = total + productos[i].precio;

        // console.log nos deja ver el valor y el tipo en cada vuelta
        console.log("Vuelta", i, "-> total:", total, "| tipo:", typeof total);
    }

    return total;
}

// ---------------------------------------------------------
// 2. Funcion CORREGIDA
// ---------------------------------------------------------
function calcularTotalCorregido() {
    let total = 0;     // ahora empieza como numero

    for (let i = 0; i < productos.length; i++) {
        // Number() convierte "50" (texto) en 50 (numero)
        total = total + Number(productos[i].precio);

        console.log("Vuelta", i, "-> total:", total, "| tipo:", typeof total);
    }

    return total;
}

document.getElementById("btn-error").addEventListener("click", () => {
    console.clear();
    console.log("=== Total CON error ===");

    // console.table muestra un arreglo como tabla: facil ver que "50" es texto
    console.table(productos);

    // Descomenta la siguiente linea para pausar aqui con la consola abierta
    // y avanzar paso a paso (F10) viendo las variables:
    // debugger;

    const total = calcularTotalConError();
    resultado.textContent = "Total (con error): " + total;
});

document.getElementById("btn-corregido").addEventListener("click", () => {
    console.clear();
    console.log("=== Total CORREGIDO ===");

    const total = calcularTotalCorregido();
    resultado.textContent = "Total (corregido): " + total;
});

// ---------------------------------------------------------
// 3. try / catch: atrapar un error sin que se detenga el programa
// ---------------------------------------------------------
document.getElementById("btn-try").addEventListener("click", () => {
    console.clear();

    try {
        // productos[5] no existe (undefined), asi que .nombre lanza un error
        console.log(productos[5].nombre);
    } catch (error) {
        console.error("Se atrapo un error:", error.message);
        resultado.textContent = "Ocurrio un error: " + error.message;
    }

    console.log("El programa sigue funcionando despues del error :)");
});

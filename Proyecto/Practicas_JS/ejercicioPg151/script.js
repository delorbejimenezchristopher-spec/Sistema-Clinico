const caja1 = document.getElementById("caja-1");
const explicacion = document.getElementById("explicacion");
const botones = document.querySelectorAll("#controles button");

const textos = {
    static: "static: posicion por defecto. La caja sigue el flujo normal y top/left no tienen efecto.",
    relative: "relative: la caja se desplaza 25px desde su posicion original, pero su espacio se conserva.",
    absolute: "absolute: la caja sale del flujo y se ubica a 25px del borde de #cajapadre. Las otras cajas ocupan su lugar."
};

function cambiarPosicion(posicion) {
    caja1.style.position = posicion;
    explicacion.textContent = textos[posicion];

    // Marcamos el boton activo
    botones.forEach((boton) => {
        boton.classList.toggle("activo", boton.dataset.posicion === posicion);
    });
}

botones.forEach((boton) => {
    boton.addEventListener("click", () => cambiarPosicion(boton.dataset.posicion));
});

cambiarPosicion("static");

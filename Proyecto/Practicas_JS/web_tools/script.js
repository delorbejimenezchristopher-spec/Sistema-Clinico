// Elementos de la página
const popup = document.getElementById("popup");
const formulario = document.getElementById("formulario");
const pasos = document.querySelectorAll(".paso");
const circulos = document.querySelectorAll(".circulo");
const contador = document.getElementById("contador");
const mensaje = document.getElementById("mensaje");
const aviso = document.getElementById("aviso");
const botonAnterior = document.getElementById("anterior");
const botonSiguiente = document.getElementById("siguiente");
const botonEnviar = document.getElementById("enviar");

// Número del paso que se está mostrando (empieza en 0)
let pasoActual = 0;

// Muestra solo el paso actual y enciende sus círculos (POR FIN)
function mostrarPaso() {
  for (let i = 0; i < pasos.length; i++) {
    pasos[i].hidden = i !== pasoActual;

    if (i <= pasoActual) {
      circulos[i].classList.add("activo");
    } else {
      circulos[i].classList.remove("activo");
    }
  }

  contador.textContent = "Paso " + (pasoActual + 1) + " de " + pasos.length;

  const esPrimero = pasoActual === 0;
  const esUltimo = pasoActual === pasos.length - 1;

  botonAnterior.hidden = esPrimero;
  botonSiguiente.hidden = esUltimo;
  botonEnviar.hidden = !esUltimo;
}

// Revisa que los campos "required" del paso actual estén llenos
function validarPaso() {
  const campos = pasos[pasoActual].querySelectorAll("[required]");
  let valido = true;

  for (let i = 0; i < campos.length; i++) {
    const campo = campos[i];
    let vacio;

    if (campo.type === "checkbox") {
      vacio = !campo.checked;
    } else {
      vacio = campo.value.trim() === "";
    }

    if (vacio) {
      campo.classList.add("error");
      valido = false;
    } else {
      campo.classList.remove("error");
    }
  }

  aviso.hidden = valido;

  // Pone el cursor en el primer campo con error
  if (!valido) {
    pasos[pasoActual].querySelector(".error").focus();
  }

  return valido;
}

function siguiente() {
  if (!validarPaso()) {
    return;
  }
  pasoActual++;
  mostrarPaso();
}

// Quita el rojo cuando el usuario corrige el campo
function quitarError(evento) {
  evento.target.classList.remove("error");
}

function anterior() {
  aviso.hidden = true;
  pasoActual--;
  mostrarPaso();
}

function abrir() {
  pasoActual = 0;
  aviso.hidden = true;
  mostrarPaso();
  popup.showModal();
}

function cerrar() {
  popup.close();
}

function enviar(evento) {
  evento.preventDefault();

  // Si se presiona Enter antes del último paso, solo avanza
  if (pasoActual < pasos.length - 1) {
    siguiente();
    return;
  }

  if (!validarPaso()) {
    return;
  }

  const nombre = formulario.nombre.value;
  mensaje.textContent = "¡Gracias, " + nombre + "! Tu solicitud fue enviada.";
  mensaje.hidden = false;

  formulario.reset();
  cerrar();
}

document.getElementById("abrir").addEventListener("click", abrir);
document.getElementById("cerrar").addEventListener("click", cerrar);
botonSiguiente.addEventListener("click", siguiente);
botonAnterior.addEventListener("click", anterior);
formulario.addEventListener("submit", enviar);
formulario.addEventListener("input", quitarError);

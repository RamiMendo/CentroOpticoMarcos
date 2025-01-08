// const inputBuscador = document.getElementById('');

// inputBuscador.addEventListener('input', e => {
//     mostrarFichas();
// })

// function mostrarFichas(){
//     const tabla = document.getElementById('');
//     tabla.innerHTML = '';

//     const fg = document.createDocumentFragment();
    
// }


function muestraFiltros(){
    const fg = document.createDocumentFragment();

    const divFiltros = document.createElement('div');
    divFiltros.id = "container-father-filtros";

    const divPago = document.createElement('div');
    divPago.id = "container-pago";
    divPago.className ="container-son-filtro";

    divPago.innerHTML = `
        <input type="checkbox" id="check-saldo">
        <label id="label-saldo">Saldado</label>
        <input type="checkbox" id="check-senia">
        <label id="label-senia">Señado</label>

        <hr class="hr-div">
    `;

    const divTotales = document.createElement('div');
    divTotales.id = "container-totales"
    divTotales.className ="container-son-filtro";

    divTotales.innerHTML = `
        <h4 class="titulo-container">Total</h4>
        <label class="label-desde label-total label-filtro" for="">Desde</label>
        <input class="input-desde input-total input-filtro">
        <hr class="hr-inputs">
        <label class="label-hasta label-total label-filtro">Hasta</label>
        <input class="input-hasta input-total input-filtro">

        <hr class="hr-div">
    `;

    const divFechas = document.createElement('div');
    divFechas.id = "container-fechas";
    divFechas.className = "container-son-filtro";

    divFechas.innerHTML = `
        <h4 class="titulo-container">Fecha De Entrega</h4>
        <label class="label-desde label-fecha label-filtro" for="">Desde</label>
        <input type="date" class="input-desde input-fecha input-filtro">
        <hr class="hr-inputs">
        <label class="label-hasta label-fecha label-filtro" for="">Hasta</label>
        <input type="date" class="input-hasta input-fecha input-filtro">

        <hr class="hr-div">
    `;

    divFiltros.append(divPago, divTotales, divFechas);
    divFiltros.innerHTML += `
        <div id="container-botones">
            <button id="boton-cerrar" class="boton-filtro">CERRAR</button>
            <button id="boton-aplicar" class="boton-filtro">APLICAR</button>
        </div>
    `
    fg.append(divFiltros);

    document.body.appendChild(fg);

}

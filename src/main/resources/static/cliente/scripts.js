const config ={
    modoCreacion: true,
    idModificar: null
};

const cliente =[
    {
        nombre: 'Conor',
        telefono: 11122121,
        direccion: 'Avenida Juan B Justo 4000'
    },
    {
        nombre: 'Pedro',
        telefono: 14234121,
        direccion: 'Avenida De Los Corrales 4000'
    }
]

function pideClientes(){
    request('get', '/clientes', muestraClientes, handleError);
}

function request(metodo, url, handleError, handleOk){
    const baseURL ='http://localhost:8080/';
    const xhr = new XMLHttpRequest();

    xhr.open(metodo, `${baseURL}${url}`);
    
    xhr.responseType ='json';
    xhr.setRequestHeader('content-type', 'application/json');

    xhr.addEventListener('error', handleError);

    xhr.addEventListener('load', e=>{
        if(xhr.status >= 200 && e.target.status <= 299){
            handleOk(xhr.response);
        }else{
            handleError();
        }
    });

    xhr.send();
}

function handleError(){
    alert('error');
}

function modalCliente(){

    const modal = document.createElement('div');
    modal.id = "container-modal";

    modal.innerHTML = `
        <h1 id="titulo-modal">Cliente</h1>
        <form id="form-modal">
            <label class="label-modal" id="label-nombre-modal">Nombre</label>
            <input class="input-modal" id="input-nombre-modal" type="text" placeholder="Conor McGregor">
            <span id="span-nombre" class="error">* El nombre es obligatorio</span>
            <label class="label-modal" id="label-telefono-modal">Teléfono</label>
            <input class="input-modal" id="input-telefono-modal" type="number" placeholder="1122222222">
            <span id="span-telefono" class="error">* El telefono es obligatorio</span>
            <label class="label-modal" id="label-direccion-modal">Dirección</label>
            <input class="input-modal" id="input-direccion-modal" type="text" placeholder="Calle Falsa 123">
            <span id="span-direccion" class="error">* La direccion es obligatoria</span>
        </form>
    `;

    const botonAceptar = document.createElement('button');
    botonAceptar.className="boton-modal";
    botonAceptar.id="boton-modal-aceptar";
    botonAceptar.textContent="ACEPTAR";
    botonAceptar.addEventListener('click', () =>{
        if(config.modoCreacion){ 
            cliente.push({
                nombre:nombre.value,
                telefono:telefono.value,
                direccion:direccion.value
            });
        }else{
            cliente[config.idModificar].nombre= nombre.value;
            cliente[config.idModificar].telefono= telefono.value;
            cliente[config.idModificar].direccion= direccion.value;
        }

        modal.style.display='none';

        muestraClientes();

    });

    const botonCerrar = document.createElement('button');
    botonCerrar.className="boton-modal";
    botonCerrar.id="boton-modal-cerrar";
    botonCerrar.textContent="X";
    botonCerrar.addEventListener('click', () =>{
        modal.style.display = 'none';
    });

    modal.append(botonAceptar, botonCerrar);
    document.body.appendChild(modal);

}

//************ CREA FILTRO */

const inputFicha = document.getElementById('input-buscador');
inputFicha.addEventListener('input', e =>{
        muestraClientes();
});    

//************ MUESTRA CLIENTES */

function muestraClientes(){

    const fg = document.createDocumentFragment();

    const tbody = document.getElementById('tbody-tabla');
    tbody.addEventListener('click', e=>{
        const index = Number(e.target.parentNode.parentNode.parentNode.dataset.index);
        if(e.target.parentNode.nodeName === 'BUTTON'){
            if(e.target.parentNode.dataset.type === 'delete'){
                handleDelete(index);
            }else{
                handleEdit(index);
            }
        }
    });
    tbody.innerHTML=``;

    cliente
        .filter(p => {
            return (p.nombre.toLowerCase().includes(inputFicha.value));
        })

        .forEach((p,index)=>{
            const trCuerpo = document.createElement('tr');
            trCuerpo.dataset.index = index;
            trCuerpo.className="tr-cuerpo";
            trCuerpo.innerHTML=`
                <td class="td-nombre input-td">${p.nombre}</td>
                <td class="td-telefono input-td">${p.telefono}</td>
                <td class="td-direccion input-td">${p.direccion}</td>
            `;
            
            //************ EDICION */

            const tdEdicion = document.createElement('td');
            tdEdicion.className="td-edicion";

            const botonEdicion = document.createElement('button');
            botonEdicion.className="boton-edicion boton-tabla";
            botonEdicion.dataset.type='edit';
            botonEdicion.innerHTML=`<img src="images/icons8-editar-48.png" alt="" class="imagen-editar">`;

            tdEdicion.append(botonEdicion);

            //************ ELIMINAR */

            const tdEliminar = document.createElement('td');
            tdEliminar.className="td-eliminar";
            
            const botonEliminar = document.createElement('button');
            botonEliminar.className="boton-eliminar boton-tabla";
            botonEliminar.dataset.type='delete';
            botonEliminar.innerHTML=`<img src="images/icons8-basura-48.png" alt="" class="imagen-eliminar">`;

            tdEliminar.append(botonEliminar);
            trCuerpo.append(tdEdicion,tdEliminar);
            fg.append(trCuerpo);
            
        });

        tbody.append(fg);
    
    //table.append(tbody);

}

modalCliente();
pideClientes();
//muestraClientes();

function handleDelete(index){

    cliente.splice(index,1);
    muestraClientes();
}

function handleEdit(index){
    const modal = document.getElementById('container-modal');
    const nombre = document.getElementById('input-nombre-modal');
    const telefono = document.getElementById('input-telefono-modal');
    const direccion = document.getElementById('input-direccion-modal');
    
    config.modoCreacion=false;
    config.idModificar=index;

    nombre.value=cliente[index].nombre;
    telefono.value=cliente[index].telefono;
    direccion.value=cliente[index].direccion;
    
    modal.style.display='flex';
}

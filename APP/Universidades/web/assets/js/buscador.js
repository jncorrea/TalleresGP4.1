/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var urlquery;
var op = 0;
var nom = "";

///MENU DE NAVEGACION

$(document).ready(function () {
    $(".nav-tabs a").click(function () {
        $(this).tab('show');
    });
    $('.nav-tabs a').on('shown.bs.tab', function (event) {
        var x = $(event.target).text();         // active tab
        var y = $(event.relatedTarget).text();  // previous tab
        $(".act span").text(x);
        $(".prev span").text(y);
    });
});
function cargarBuscador() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Universidades/webresources/entities.universidades',
        dataType: "json",
        crossDomain: true
    }).done(function (data, textStatus, jqXHR)
    {
        var myDiv = document.getElementById("buscador");
        var selectList = document.createElement("select");
        selectList.id = "selectUniversity";
        selectList.className = "selectpicker";
        myDiv.appendChild(selectList);
        var att = document.createAttribute("data-live-search");
        att.value = "true";
        selectList.setAttributeNode(att);
        for (l in data) {
            var option = document.createElement("option");
            option.id = data[l].idUniversidad;
            option.value = data[l].idUniversidad;
            option.text = data[l].universidad;
            option.className = data[l].latitud + "|" + data[l].longitud;
            selectList.appendChild(option);
        }
    });
}

function viewUniversity() {
    var uni = document.getElementById("selectUniversity");
    var opt = document.getElementById(uni.value);
    var dat = opt.className.split("|");
    var latitud = dat[0];
    var longitud = dat[1];
    var nombre = uni.options[uni.selectedIndex].text;
    nom = nombre;
    op = 2;
    getEstadisticas(document.getElementById("selectAnio"), document.getElementById("selectItem"));
    drawChart([{"latitud": latitud, "longitud": longitud, "universidad": nombre}], "mark");
}

//Obtener paises
google.charts.load('current', {'packages': ['corechart', 'map', 'table']});

function getPaises(continente) {
    var divresult = document.getElementById("regions_div");
    divresult.innerHTML = "";
    divresult.innerHTML = "<img style='width: 20%; margin: 0 40%;' src='assets/images/loading.gif'>";
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Universidades/webresources/entities.paises',
        dataType: "json",
        crossDomain: true
    }).done(function (data, textStatus, jqXHR)
    {
        google.charts.setOnLoadCallback(function () {
            drawRegionsMap(data, continente.value);
        });
    });
}
//Dibujar paises en el mapa
function drawRegionsMap(data, cont) {
    var datos = [];
    datos.push(['Country', 'País']);
    for (l in data) {
        datos.push([data[l].pais, data[l].pais]);
    }
    var datos_paises = new google.visualization.arrayToDataTable(datos);
    if (cont == null || cont == "") {
        var options = {};
        op = 0;
        getUniversidades();
    } else {
        var options = {region: cont};
    }
    var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
    chart.draw(datos_paises, options);
    google.visualization.events.addListener(chart, 'select', function () {
        var selection = chart.getSelection();
        if (typeof selection[0] !== "undefined") {
            var value = datos_paises.getValue(selection[0].row, 0);
            var idPais = "";
            for (l in data) {
                if (data[l].pais == value) {
                    idPais = data[l].idPais;
                }
            }
            op = 1;
            nom = value;
            getUniversidades(idPais);
            getEstadisticas(document.getElementById("selectAnio"), document.getElementById("selectItem"));
        }
    });
}

function getUniversidades(pais) {
    var divresult = document.getElementById("resultData");
    divresult.innerHTML = "";
    divresult.innerHTML = "<img style='width: 20%; margin: 0 40%;' src='assets/images/loading.gif'>";
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Universidades/webresources/entities.universidades',
        dataType: "json",
        crossDomain: true
    }).done(function (data, textStatus, jqXHR)
    {
        google.charts.setOnLoadCallback(function () {
            drawChart(data, pais);
        });
    });
}

function drawChart(data, pais) {
    var zoom;
    var paisUni = document.getElementById("pais_uni");
    var datos = [];
    datos.push(['Lat', 'Long', 'Name']);
    for (l in data) {
        if (pais == null) {
            paisUni.innerHTML = "Universidades del Mundo";
            zoom = 2;
            datos.push([parseFloat(data[l].latitud), parseFloat(data[l].longitud), data[l].universidad]);
        } else if (pais == "mark") {
            paisUni.innerHTML = data[l].universidad;
            zoom = 18;
            datos.push([parseFloat(data[l].latitud), parseFloat(data[l].longitud), data[l].universidad]);
        } else if (pais == data[l].idPais.idPais) {
            paisUni.innerHTML = "Universidades de " + data[l].idPais.pais;
            zoom = 5;
            datos.push([parseFloat(data[l].latitud), parseFloat(data[l].longitud), data[l].universidad]);
        }
    }
    document.getElementById("nroResultados").innerHTML = (datos.length - 1) + " Universidades Encontradas";
    var data_universidades = google.visualization.arrayToDataTable(datos);
    var map = new google.visualization.Map(document.getElementById('resultData'));
    var options = {showTip: true, zoomLevel: zoom};
    map.draw(data_universidades, options);
    google.visualization.events.addListener(map, 'select', function () {
        var selection = map.getSelection();
        var latUniversity = data_universidades.getValue(selection[0].row, 0);
        var longUniversity = data_universidades.getValue(selection[0].row, 1);
        var nomUniversity = data_universidades.getValue(selection[0].row, 2);
        if (typeof selection[0] !== "undefined") {
            nom = nomUniversity;
            op = 2;
            getEstadisticas(document.getElementById("selectAnio"), document.getElementById("selectItem"));
            drawChart([{"latitud": latUniversity, "longitud": longUniversity, "universidad": nomUniversity}], "mark");
        }
    });
}

function getEstadisticas(uni, item) {
    var divresult = document.getElementById("firstChart");
    divresult.innerHTML = "";
    divresult.innerHTML = "<img style='width: 20%; margin: 0 40%;' src='assets/images/loading.gif'>";
    var anio = uni.value;
    var indicador = item.value;
    var limit = document.getElementById("nroUni").value;
    var uri = "";
    var menuAnios = document.getElementById("menuAnios");
    var menuLimite = document.getElementById("menuLimite");
    switch (op) {
        case 0:
            uri = 'http://localhost:8080/Universidades/webresources/entities.estadisticas/allData/' + indicador + ',' + anio + ',' + limit;
            menuAnios.style.display = "";
            menuLimite.style.display = "";
            break;
        case 1:
            uri = 'http://localhost:8080/Universidades/webresources/entities.estadisticas/countryData/' + indicador + ',' + anio + ',' + limit + ',' + nom;
            menuAnios.style.display = "";
            menuLimite.style.display = "";
            break;
        case 2:
            uri = 'http://localhost:8080/Universidades/webresources/entities.estadisticas/universityData/anio' + ',' + nom;
            menuAnios.style.display = "none";
            menuLimite.style.display = "none";
            break;
    }
    $.ajax({
        type: 'GET',
        url: uri,
        dataType: "json",
        crossDomain: true
    }).done(function (data, textStatus, jqXHR)
    {
        google.charts.setOnLoadCallback(function () {
            drawFirst(data, item);
        });
    });
}

function drawFirst(data, item) {
    var datos = [];
    var i;
    for (l in data) {
        switch (item.value) {
            case 'ibe':
                i = data[l].ibe;
                break;
            case 'lac':
                i = data[l].lac;
                break;
            case 'co':
                i = data[l].co;
                break;
            case 'o':
                i = data[l].o;
                break;
            case 'ic':
                i = data[l].ic;
                break;
            case 'ni':
                i = data[l].ni;
                break;
            case 'q1':
                i = data[l].q1;
                break;
            case 'exc':
                i = data[l].exc;
                break;
            case 'lead':
                i = data[l].lead;
                break;
            case 'ewl':
                i = data[l].ewl;
                break;
        }
        if (op == 2) {
            datos.push([data[l].anio, i]);
        } else {
            datos.push([data[l].idUniversidad.universidad, i]);
        }
    }
    var data_table = new google.visualization.DataTable();
    if (op == 2) {
        data_table.addColumn('string', 'AÑO');
    } else {
        data_table.addColumn('string', 'UNIVERSIDAD');
    }
    data_table.addColumn('number', item.options[item.selectedIndex].text);

    data_table.addRows(datos);

    var options = {
        title: 'Ranking de Universidades por: ' + item.options[item.selectedIndex].text,
        hAxis: {title: 'Universidades'},
        vAxis: {title: 'Nro. Publicaciones'},
        is3D: true,
        vAxis: { 
              viewWindowMode:'explicit',
              viewWindow:{
                min:0
              }
            }
    };
    
    var chart = new google.visualization.ChartWrapper({
          chartType: document.getElementById("selectChar").value,
          dataTable: data_table,
          options: options,
          containerId: 'firstChart'
        });
        chart.draw();

    var datos = [];
    for (l in data) {
        if (op == 2) {
            datos.push([data[l].idUniversidad.universidad, data[l].anio, data[l].ibe, data[l].lac, data[l].co, data[l].o,
                data[l].ic, data[l].ni, data[l].q1, data[l].spec, data[l].exc, data[l].lead, data[l].ewl]);
        } else {
            datos.push([data[l].idUniversidad.universidad, data[l].ibe, data[l].lac, data[l].co, data[l].o,
                data[l].ic, data[l].ni, data[l].q1, data[l].spec, data[l].exc, data[l].lead, data[l].ewl]);
        }
    }
    var data_table = new google.visualization.DataTable();
    data_table.addColumn('string', 'UNIVERSIDAD');
    if (op == 2) {
        data_table.addColumn('string', 'AÑO');
    }
    data_table.addColumn('number', 'IBE');
    data_table.addColumn('number', 'LAC');
    data_table.addColumn('number', 'CO');
    data_table.addColumn('number', 'O');
    data_table.addColumn('number', 'IC');
    data_table.addColumn('number', 'NI');
    data_table.addColumn('number', 'Q1');
    data_table.addColumn('number', 'SPEC');
    data_table.addColumn('number', 'EXC');
    data_table.addColumn('number', 'LEAD');
    data_table.addColumn('number', 'EWL');

    data_table.addRows(datos);

    var table = new google.visualization.Table(document.getElementById('tableData'));

    table.draw(data_table, {showRowNumber: true, width: '100%', height: '100%'});
}

function enviar(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        $('#btn_buscar').trigger('click');
    }
}

function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

cargarBuscador();
getPaises(document.getElementById("selectContinente"));
getUniversidades();
getEstadisticas(document.getElementById("selectAnio"), document.getElementById("selectItem"));

$(window).resize(function () {
    getPaises(document.getElementById("selectContinente"));
    getUniversidades();
    getEstadisticas(document.getElementById("selectAnio"), document.getElementById("selectItem"));
});

$('.selectpicker').selectpicker({
    size: 4
});



API De gestion de Instrumentos Financieros

Descripción
Esta API permite gestionar los instrumentos financieros de una entidad financiera. Permite realizar las siguientes operaciones:


Endpoints

1.- Agregar instrumento financiero

URL: http://localhost:5000/api/instrumentos/
Method: POST

Request Body para un BONO:
{
    "tipo": "BONO",
    "nombre": "AL30",
    "precio": 45,
    "tasaInteres": 3.5
}

Request Body para una ACCION:
{
    "tipo": "ACCION",
    "nombre": "BYMA",
    "precio": 200,
    "dividendo": 10
}

Respuesta 201 Created

Para Bono la respuesta sera asi
{
"id": 1,
"tipo": "BONO",
"nombre": "AL30",
"precio": 45.0,
"tasaInteres": 3.5,
"finDelParking": "2024-09-30"
}

Para Accion la respuesta sera asi
{
"id": 2,
"tipo": "ACCION",
"nombre": "BYMA",
"precio": 200.0,
"dividendo": 10.0,
"finDelParking": "2024-09-30"
}


--------------------------------------------------------------------------------

2.- Obtener instrumentos financieros

URL: http://localhost:5000/api/instrumentos/
Method: GET

Respuesta 200 OK
[
    {
    "id": 1,
    "tipo": "BONO",
    "nombre": "AL30",
    "precio": 45.0,
    "tasaInteres": 3.5,
    "finDelParking": "2024-09-30"
    },
    {
    "id": 2,
    "tipo": "ACCION",
    "nombre": "BYMA",
    "precio": 200.0,
    "dividendo": 10.0,
    "finDelParking": "2024-09-30"
    }
]

--------------------------------------------------------------------------------

3.- Obtener instrumento financiero por ID

URL: http://localhost:5000/api/instrumentos/{id}

Method: GET

{
"id": 1,
"tipo": "BONO",
"nombre": "AL30",
"precio": 45.0,
"tasaInteres": 3.5,
"finDelParking": "2024-09-30"
}

--------------------------------------------------------------------------------


4.- Eliminar instrumento financiero por ID

URL: http://localhost:5000/api/instrumentos/{id}

Method: DELETE

Respuesta 204 No Content


--------------------------------------------------------------------------------

5.- Actualizar instrumento financiero por ID

URL: http://localhost:5000/api/instrumentos/{id}

Method: PUT

Request Body para un BONO:
{
    "tipo": "BONO",
    "nombre": "AL30",
    "precio": 45,
    "tasaInteres": 3.5
}

Request Body para una ACCION:
{
    "tipo": "ACCION",
    "nombre": "BYMA",
    "precio": 200,
    "dividendo": 10
}

Respuesta 201 Created

Para Bono la respuesta sera asi
{
"id": 1,
"tipo": "BONO",
"nombre": "AL30",
"precio": 45.0,
"tasaInteres": 3.5,
"finDelParking": "2024-09-30"
}

Para Accion la respuesta sera asi
{
"id": 2,
"tipo": "ACCION",
"nombre": "BYMA",
"precio": 200.0,
"dividendo": 10.0,
"finDelParking": "2024-09-30"
}




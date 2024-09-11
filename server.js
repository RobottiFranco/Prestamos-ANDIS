var { graphql, buildSchema } = require("graphql")
var express = require("express")
var { createHandler } = require("graphql-http/lib/use/express")
var { ruruHTML } = require("ruru/server")
 
let prestamos = [
    {
        "id": 1,
        "montoPrestamo": 10000,
        "interes": 3,
        "cuotas": 24,
        "fechainicio": "2023-01-01",
        "balance": 9500,
        "idCliente": 101,
        "pagado": true
      },
      {
        "id": 2,
        "montoPrestamo": 15000,
        "interes": 4,
        "cuotas": 36,
        "fechainicio": "2023-03-15",
        "balance": 14000,
        "idCliente": 102,
        "pagado": true
      },
      {
        "id": 3,
        "montoPrestamo": 5000,
        "interes": 2,
        "cuotas": 12,
        "fechainicio": "2023-05-10",
        "balance": 4500,
        "idCliente": 103,
        "pagado": false
      },
      {
        "id": 4,
        "montoPrestamo": 20000,
        "interes": 5,
        "cuotas": 48,
        "fechainicio": "2022-11-20",
        "balance": 18000,
        "idCliente": 104,
        "pagado": false
      },
      {
        "id": 5,
        "montoPrestamo": 30000,
        "interes": 3,
        "cuotas": 60,
        "fechainicio": "2022-09-05",
        "balance": 28000,
        "idCliente": 105,
        "pagado": false
      },
      {
        "id": 6,
        "montoPrestamo": 12000,
        "interes": 4,
        "cuotas": 36,
        "fechainicio": "2023-02-28",
        "balance": 11000,
        "idCliente": 106,
        "pagado": false
      },
      {
        "id": 7,
        "montoPrestamo": 25000,
        "interes": 3,
        "cuotas": 48,
        "fechainicio": "2022-08-10",
        "balance": 23000,
        "idCliente": 107,
        "pagado": false
      },
      {
        "id": 8,
        "montoPrestamo": 8000,
        "interes": 4,
        "cuotas": 24,
        "fechainicio": "2023-06-01",
        "balance": 7500,
        "idCliente": 108,
        "pagado": false
      },
      {
        "id": 9,
        "montoPrestamo": 18000,
        "interes": 2,
        "cuotas": 36,
        "fechainicio": "2023-04-18",
        "balance": 17000,
        "idCliente": 109,
        "pagado": false
      },
      {
        "id": 10,
        "montoPrestamo": 22000,
        "interes": 5,
        "cuotas": 48,
        "fechainicio": "2023-03-25",
        "balance": 21000,
        "idCliente": 110,
        "pagado": false
      },
      {
        "id": 11,
        "montoPrestamo": 27000,
        "interes": 4,
        "cuotas": 60,
        "fechainicio": "2023-05-05",
        "balance": 25000,
        "idCliente": 111,
        "pagado": false
      },
      {
        "id": 12,
        "montoPrestamo": 4000,
        "interes": 3,
        "cuotas": 12,
        "fechainicio": "2023-01-30",
        "balance": 3500,
        "idCliente": 112,
        "pagado": false
      },
      {
        "id": 13,
        "montoPrestamo": 32000,
        "interes": 4,
        "cuotas": 72,
        "fechainicio": "2023-07-10",
        "balance": 31000,
        "idCliente": 113,
        "pagado": false
      },
      {
        "id": 14,
        "montoPrestamo": 9000,
        "interes": 3,
        "cuotas": 24,
        "fechainicio": "2023-06-25",
        "balance": 8500,
        "idCliente": 114,
        "pagado": false
      },
      {
        "id": 15,
        "montoPrestamo": 11000,
        "interes": 2,
        "cuotas": 36,
        "fechainicio": "2023-08-01",
        "balance": 10000,
        "idCliente": 115,
        "pagado": false
      }
]

// Construct a schema, using GraphQL schema language
var schema = buildSchema(`
  type Query {
    obtenerTodos: [Prestamo]
    obtenerPorId(id:Int): Prestamo
    obtenerPorCliente(idCliente: Int): [Prestamo]
    obtenerPagados: [Prestamo]
    obtenerNoPagados: [Prestamo]
  }

  type Mutation {
    guardar(prestamo: PrestamoInput): Boolean
    actualizar(id: Int, prestamo: PrestamoInput): Boolean
    eliminar(id: Int): Boolean
  }

  type Prestamo {
    id: Int
    montoPrestamo: Int
    interes: Int
    cuotas: Int
    fechaInicio: String
    balance: Int
    idCliente: Int
    pagado: Boolean
  }

  input PrestamoInput {
    id: Int
    montoPrestamo: Int
    interes: Int
    cuotas: Int
    fechaInicio: String
    balance: Int
    idCliente: Int
    pagado: Boolean
  }
`)

function obtenerTodos() {
    return prestamos
  }

function obtenerPorId ({id}) {
    return prestamos.find((e) => e.id === id)
}

function obtenerPorCliente({idCliente}) {
    return prestamos.filter((e) => e.idCliente === idCliente)
}

function obtenerPagados() {
    return prestamos.filter((e) => e.pagado)
}

function obtenerNoPagados() {
    return prestamos.filter((e) => !e.pagado)
}

function guardar({prestamo}) {
    prestamos.push(prestamo)
    return true
}

function actualizar(id, prestamo) {
    prestamos = prestamos.map(p => {
        if (p.id === id.id) {
            return id.prestamo
        }
        else {
            return p
        }
    })

    return true
}

function eliminar({id}) {
    prestamos.splice(prestamos.findIndex((e) => e.id === id), 1)
    return true
}

// The rootValue provides a resolver function for each API endpoint
var rootValue = {
obtenerTodos: obtenerTodos,
obtenerPorId: obtenerPorId,
obtenerPorCliente: obtenerPorCliente,
obtenerPagados: obtenerPagados,
obtenerNoPagados: obtenerNoPagados,
guardar: guardar,
actualizar: actualizar,
eliminar: eliminar
}
 
var app = express()
 
// Create and use the GraphQL handler.
app.all(
  "/graphql",
  createHandler({
    schema: schema,
    rootValue: rootValue
  })
)

app.get("/", (_req, res) => {
    res.type("html")
    res.end(ruruHTML({ endpoint: "/graphql" }))
})

app.listen(4000)
console.log("Running a GraphQL API server at http://localhost:4000/graphql")
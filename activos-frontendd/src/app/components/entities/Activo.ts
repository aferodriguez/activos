export class Activo {

    idactivo: number;
    nombre: String;
    descripcion: String;
    tipo: string;
    serial: String;
    numerointernoinventario: String;
    peso: number;
    alto: number;
    ancho: number;
    largo: number;
    valor_compra: number;
    fecha_compra: Date;
    fecha_baja: Date;
    estado: number;
    color: string;

    constructor(idactivo: number, nombre: String, descripcion: String, tipo: string, serial: String,
        numerointernoinventario: String, peso: number, alto: number, ancho: number, largo: number,
        valor_compra: number, fecha_compra: Date, fecha_baja: Date, estado: number, color: string ) {
        this.idactivo = idactivo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.serial = serial;
        this.numerointernoinventario = numerointernoinventario;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.valor_compra = valor_compra;
        this.fecha_compra = fecha_compra;
        this.fecha_baja = fecha_baja;
        this.estado = estado;
        this.color = color;


    }

}
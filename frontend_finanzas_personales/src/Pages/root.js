import React, { Component } from 'react';
import {
    SHr,
    SInput,
    SList,
    SMath,
    SNavigation,
    SPage,
    SText,
    SView,
    SIcon,
    STheme,
    SDate,
    SViewRoll,
} from 'servisofts-component';
import { Btn, Container, Link } from '../Components';
import Model from '../Model';
import user from '../Model/user';

export default class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
            filtros: {
                gestion: "2023-10",
                fecha_inicio: new SDate().setDay(1).toString("yyyy-MM-dd"),
                fecha_fin: new SDate().addMonth(1).setDay(-1).toString("yyyy-MM-dd"),
                cuenta_origen: "",
                cuenta_destino: "",
                descripcion: "",
            }
        };
    }

    componentDidMount() {
        if (!Model.user.jwt) {
            SNavigation.replace('/login');
            return;
        }
        this.loadData();

    }

    async loadData() {
        const userLog = Model.user.getUserLog();
        this.setState({ loading: true });
        const cuentas = await Model.cuenta.bytoken();
        let movimientos = await Model.movimiento.getbyuser()
        const categoriacuenta = await Model.categoriacuenta.getalluser();


        let montoTotal = 0;
        cuentas.forEach(cuenta => {
            montoTotal += parseFloat(cuenta.monto.monto);
        });

        movimientos.map((mov) => {
            mov.cuentaOrigen = cuentas.find(a => a.key == mov?.keyCuentaOrigen)
            mov.cuentaDestino = cuentas.find(a => a.key == mov?.keyCuentaDestino)
        })


        this.setState({
            loading: false,
            cuentas: cuentas,
            movimientos: movimientos,
            categoriacuenta: categoriacuenta,
            montoTotal: montoTotal,
        });
    }
    render() {
        const styleLink = { padding: 4 };
        return (
            <SPage
                preventBack
                onRefresh={end => {
                    this.componentDidMount();
                    end();
                }}
            >
                <SView row col={'xs-12'}>
                    <SView col={'xs-6'} loading={this.state.loading} padding={10}>
                        <SView row col={'xs-12'}>
                            {/* <Link style={styleLink} src={"/user"}  >{`Lista de usuarios.`}</Link> */}
                            <Link
                                style={styleLink}
                                src={'/cuenta/new'}
                            >{`+  Nueva cuenta`}</Link>
                            <Link
                                style={styleLink}
                                src={'/categoriacuenta'}
                            >{`Mis categorias cuenta`}</Link>
                            <Link
                                style={styleLink}
                                src={'/categoriamovimiento'}
                            >{`Mis categorias movimientos`}</Link>
                            <Link
                                style={styleLink}
                                src='/traspaso'
                                params={{ keyCuentaOrigen: this.pk }}
                            >
                                Realizar traspaso
                            </Link>
                            <Link
                                style={styleLink}
                                color={'#a00'}
                                src={'/user/unlogin'}
                            >{`Cerrar session.`}</Link>
                        </SView>

                        <SHr h={8} />

                        <SView card padding={15} center>
                            <SText fontSize={25} center>
                                Total:{' '}
                                {SMath.formatMoney(this.state.montoTotal)} Bs.
                            </SText>
                            <SText fontSize={14} center>
                                Suma de todas las cuentas o resumen de saldo
                                total.
                            </SText>
                        </SView>

                        <SHr h={20} />

                        <SView>
                            <SList
                                buscador
                                data={this.state.cuentas}
                                render={c => {
                                    const categoria = this.state.categoriacuenta.find(a => a.key == c.keyCategoria)
                                    return (
                                        <SView
                                            col={'xs-12'}
                                            card
                                            padding={8}
                                            onPress={SNavigation.navigate.bind(
                                                this,
                                                '/cuenta',
                                                { pk: c.key }
                                            )}
                                        >
                                            <SText fontSize={12} color={categoria?.color}>
                                                {categoria?.nombre}
                                            </SText>
                                            <SText fontSize={20} bold>
                                                {c.nombre}
                                            </SText>
                                            <SHr />
                                            <SText>
                                                Bs.{' '}
                                                {SMath.formatMoney(c.monto?.monto)}
                                            </SText>
                                        </SView>
                                    )
                                }}
                            />
                        </SView>
                    </SView>

                    <SView col={'xs-6'} loading={this.state.loading} padding={10}>
                        {/* <SInput
                            type='date_my'
                            label={"Gestion"}
                            value={this.state.filtros.gestion}
                            onChangeText={e => {
                                this.state.filtros.gestion = e;
                                this.setState({ ...this.state });
                            }}
                        /> */}
                        <SView row>
                            <SInput
                                flex
                                type='date'
                                label={"Desde"}
                                placeholder={"Fecha"}
                                defaultValue={this.state.filtros.fecha_inicio}
                                onChangeText={e => {
                                    this.state.filtros.fecha_inicio = e;
                                    this.setState({ ...this.state });
                                }}
                            />
                            <SView width={8} />
                            <SInput
                                flex
                                type='date'
                                label={"Hasta"}
                                placeholder={"Fecha"}
                                defaultValue={this.state.filtros.fecha_fin}
                                onChangeText={e => {
                                    this.state.filtros.fecha_fin = e;
                                    this.setState({ ...this.state });
                                }}
                            />
                        </SView>
                        <SHr />
                        <SInput
                            label={"Cuenta origen"}
                            placeholder={"Cuenta origen"}
                            onChangeText={e => {
                                this.state.filtros.cuenta_origen = e;
                                this.setState({ ...this.state });
                            }}
                        />
                        <SHr />
                        <SInput
                            label={"Cuenta destino"}
                            placeholder={"Cuenta destino"}
                            onChangeText={e => {
                                this.state.filtros.cuenta_destino = e;
                                this.setState({ ...this.state });
                            }}
                        />
                        <SHr />
                        <SInput
                            label={"Descripcion"}
                            type='textArea'
                            placeholder={"Descripcion"}
                            onChangeText={e => {
                                this.state.filtros.descripcion = e;
                                this.setState({ ...this.state });
                            }}
                        />

                        <SHr h={20} />
                        <SHr h={1} color={"#fff"} />
                        <SHr h={20} />
                        <SList
                            data={this.state.movimientos}
                            order={[{ key: 'fecha', order: 'desc' }]}
                            filter={a => {
                                // if (a.fecha.indexOf(this.state.filtros.gestion) <= -1) {
                                //     return false;
                                // }
                                if (this.state.filtros.cuenta_origen) {
                                    if (!a?.cuentaOrigen?.nombre) return false;
                                    if (a?.cuentaOrigen?.nombre.indexOf(this.state.filtros.cuenta_origen) <= -1) {
                                        return false;
                                    }
                                }
                                if (this.state.filtros.cuenta_destino) {
                                    if (!a?.cuentaDestino?.nombre) return false;
                                    if (a?.cuentaDestino?.nombre.indexOf(this.state.filtros.cuenta_destino) <= -1) {
                                        return false;
                                    }
                                }
                                if (this.state.filtros.descripcion) {
                                    if (a?.descripcion.indexOf(this.state.filtros.descripcion) <= -1) {
                                        return false;
                                    }
                                }

                                if (this.state.filtros.fecha_inicio) {
                                    if (new SDate(a.fecha, "yyyy-MM-ddThh:mm:ss").isBefore(new SDate(this.state.filtros.fecha_inicio))) {
                                        return false;
                                    }
                                }
                                if (this.state.filtros.fecha_fin) {
                                    if (new SDate(a.fecha, "yyyy-MM-ddThh:mm:ss").isAfter(new SDate(this.state.filtros.fecha_fin))) {
                                        return false;
                                    }
                                }

                                return true;

                            }}
                            render={mov => {

                                return (
                                    <SView
                                        col={'xs-12'}
                                        card
                                        padding={8}
                                        row
                                        center
                                    >
                                        <SView width={40} height={40}>
                                            <SIcon
                                                name={
                                                    mov.monto > 0
                                                        ? 'Ingreso'
                                                        : 'Egreso'
                                                }
                                            />
                                        </SView>
                                        <SView width={8} />
                                        <SView flex>
                                            <SView row>
                                                <SText bold>{mov?.cuentaOrigen?.nombre}</SText>
                                                <SView width={8} />
                                                <SText>{"->"}</SText>
                                                <SView width={8} />
                                                <SText bold>{mov?.cuentaDestino?.nombre}</SText>
                                            </SView>
                                            <SHr />
                                            <SText>{mov.descripcion}</SText>
                                            <SText
                                                fontSize={10}
                                                color={STheme.color.gray}
                                            >
                                                {new SDate(mov.fecha).toString(
                                                    'yyyy, DAY, dd de MONTH a las hh:mm'
                                                )}
                                            </SText>
                                        </SView>
                                        <SText fontSize={20} margin={10}>
                                            Bs. {SMath.formatMoney(mov.monto)}
                                        </SText>
                                    </SView>
                                )
                            }}
                        />
                    </SView>
                </SView>
            </SPage>
        );
    }
}

import {Text, View} from 'react-native';
import React, {Component} from 'react';
import {
    SDate,
    SHr,
    SIcon,
    SList,
    SMath,
    SNavigation,
    SPage,
    SText,
    STheme,
    SView,
    SPopup,
} from 'servisofts-component';
import Model from '../../Model';
import {Container, Link} from '../../Components';

export default class profile extends Component {
    state = {};
    constructor(props) {
        super(props);
        this.pk = SNavigation.getParam('pk');
        if (!this.pk) {
            SNavigation.goBack();
        }
    }

    componentDidMount() {
        if (!this.pk) return;
        this.setState({loading: true});
        Model.cuenta
            .getByKey({key: this.pk})
            .then(async e => {
                console.log(e);
                // const movimientos = await Model.movimiento.getbycuenta({ keyCuenta: this.pk })
                const movimientos = await Model.movimiento.getbycuenta({
                    keyCuenta: this.pk,
                });
                console.log(movimientos);
                this.setState({
                    loading: false,
                    cuenta: e,
                    movimientos: movimientos,
                });
            })
            .catch(e => {
                console.error(e);
                this.setState({loading: false});
            });
        this.loadData();
    }

    async loadData() {
        const categoriamovimiento =
            await Model.categoriamovimiento.getalluser();
    }

    validateTypeMovimiento = mov => {
        // return SMath.formatMoney(mov.monto);
        // if (mov.descripcion == 'Monto retirado de la cuenta') {
        //     return SMath.formatMoney(mov.monto);
        // }

        if (mov.keyCuentaDestino == this.pk) {
            return SMath.formatMoney(mov.monto);
        } else {
            return SMath.formatMoney(mov.monto * -1);
        }
    };

    render() {
        return (
            <SPage title={'Perfil de la cuenta'}>
                <Container loading={this.state.loading}>
                    <SView row>
                        <Link
                            padding={4}
                            src='/traspaso'
                            params={{keyCuentaOrigen: this.pk}}
                        >
                            Realizar traspaso
                        </Link>
                        <SView width={16} />
                        <Link
                            padding={4}
                            src='/cuenta/addmonto'
                            params={{pk: this.pk}}
                        >
                            Añadir dinero a la cuenta
                        </Link>
                        <SView width={16} />
                        <Link
                            padding={4}
                            src='/cuenta/retirarmonto'
                            params={{pk: this.pk}}
                        >
                            Retirar dinero de la cuenta
                        </Link>
                        <SView width={16} />
                        <Link
                            padding={4}
                            src='/cuenta/edit'
                            params={{pk: this.pk}}
                            color={STheme.color.warning}
                        >
                            Editar
                        </Link>
                        <SView width={16} />
                        <SView
                            width={40}
                            height={40}
                            margin={3}
                            onPress={() => {
                                SPopup.confirm({
                                    title: 'Esta seguro de eliminar?',
                                    onPress: () => {
                                        Model.cuenta
                                            .delete({key: this.pk})
                                            .then(e => {
                                                delete this.state.data[obj.key];
                                                this.setState({
                                                    ...this.state,
                                                });
                                            })
                                            .catch(e => {
                                                console.error(e);
                                            });
                                    },
                                });
                            }}
                        >
                            <SIcon name={'Delete'}></SIcon>
                        </SView>
                    </SView>

                    <SHr h={20} />
                    {/* <SText fontSize={14} bold>{this.state?.cuenta?.key}</SText> */}
                    <SText fontSize={20} bold>
                        {this.state?.cuenta?.nombre}
                    </SText>
                    <SText fontSize={20} bold>
                        Bs. {SMath.formatMoney(this.state?.cuenta?.monto)}
                    </SText>
                    <SHr h={50} />

                    <SList
                        buscador
                        data={this.state.movimientos}
                        order={[{key: 'fecha', order: 'desc'}]}
                        render={mov => (
                            <SView col={'xs-12'} card padding={8} row center>
                                <SView width={40} height={40}>
                                    <SIcon
                                        name={
                                            mov.keyCuentaDestino == this.pk
                                                ? 'Ingreso'
                                                : 'Egreso'
                                        }
                                    />
                                </SView>
                                <SView width={8} />
                                <SView flex>
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
                                    Bs.
                                    {this.validateTypeMovimiento(mov)}
                                </SText>

                                <SText fontSize={20} margin={10}>
                                    {}
                                </SText>

                                <SView
                                    width={40}
                                    height={40}
                                    margin={3}
                                    onPress={() => {
                                        SNavigation.navigate(
                                            '/movimiento/edit',
                                            {
                                                pk: mov.key,
                                            }
                                        );
                                    }}
                                >
                                    <SIcon name={'Edit'} />
                                </SView>

                                <SView
                                    width={40}
                                    height={40}
                                    margin={3}
                                    onPress={() => {
                                        SPopup.confirm({
                                            title: 'Esta seguro de eliminar?',
                                            onPress: () => {
                                                Model.movimiento
                                                    .delete(mov.key)
                                                    .then(e => {
                                                        delete this.state.data[
                                                            obj.key
                                                        ];
                                                        this.setState({
                                                            ...this.state,
                                                        });
                                                    })
                                                    .catch(e => {
                                                        console.error(e);
                                                    });
                                            },
                                        });
                                    }}
                                >
                                    <SIcon name={'Delete'}></SIcon>
                                </SView>
                            </SView>
                        )}
                    />
                </Container>
            </SPage>
        );
    }
}

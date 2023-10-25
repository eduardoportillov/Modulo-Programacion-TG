import React, { Component } from 'react'
import { SForm, SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../Model'
import { Container } from '../Components'

export default class index extends Component {
    state = {}
    componentDidMount() {

    }
    render() {
        return <SPage>
            <Container>
                <SForm
                    inputs={{
                        "keyCuentaOrigen": {
                            type: "text", label: "Cuenta origen.", isRequired: true,
                            value: this.state?.cuentaOrigen?.nombre,
                            editable: false,
                            onPress: () => {
                                SNavigation.navigate("/cuenta/select", {
                                    // exclude: [this.state?.cuentaDestino?.key],
                                    onSelect: (cc) => {
                                        this.setState({ cuentaOrigen: cc })
                                        SNavigation.goBack();
                                    }
                                })
                            }
                        },
                        "keyCuentaDestino": {
                            type: "text", label: "Cuenta destino.", 
                            value: this.state?.cuentaDestino?.nombre,
                            editable: false,
                            onPress: () => {
                                SNavigation.navigate("/cuenta/select", {
                                    exclude: [this.state?.cuentaOrigen?.key],
                                    onSelect: (cc) => {
                                        this.setState({ cuentaDestino: cc })
                                        SNavigation.goBack();
                                    }
                                })
                            }
                        },
                        "keyCategoria": {
                            type: "text", label: "Categoria.", isRequired: true,
                            value: this.state?.categoria?.nombre,
                            editable: false,
                            onPress: () => {
                                SNavigation.navigate("/categoriamovimiento", {
                                    onSelect: (cc) => {
                                        this.setState({ categoria: cc })
                                        SNavigation.goBack();
                                    }
                                })
                            }
                        },
                        "monto": { type: "money", label: "Monto.", col:"xs-10", isRequired: true },
                        "descripcion": { type: "textArea", label: "Descripcion.", isRequired: true },

                    }}

                    onSubmitName={"SUBIR"}
                    loading={this.state.loading}
                    error={this.state.error}
                    onSubmit={(e) => {
                        e.keyCuentaOrigen = this.state?.cuentaOrigen?.key
                        e.keyCuentaDestino = this.state?.cuentaDestino?.key
                        e.keyCategoria = this.state?.categoria?.key
                        this.setState({ loading: true })
                        Model.movimiento.create(e).then(e => {
                            this.setState({ loading: false })
                            console.log(e)
                            SNavigation.goBack();
                        }).catch(e => {
                            console.error(e)
                            this.setState({ loading: false, error: e })
                        })
                    }}
                />

            </Container>
        </SPage>
    }
}
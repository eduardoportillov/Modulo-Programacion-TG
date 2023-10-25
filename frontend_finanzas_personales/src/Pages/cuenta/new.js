import React, { Component } from 'react'
import { SForm, SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../../Model'
import { Container } from '../../Components'

export default class index extends Component {
    state = {}
    componentDidMount() {

    }
    render() {
        return <SPage>
            <Container>
                <SForm
                    inputs={{
                        "nombre": { type: "text", label: "Nombre de la cuenta.", isRequired: true },
                        "keyCategoria": {
                            type: "text", label: "Categoria.", isRequired: true,
                            value: this.state?.cc?.nombre,
                            editable: false,
                            onPress: () => {
                                SNavigation.navigate("/categoriacuenta", {
                                    onSelect: (cc) => {
                                        this.setState({ cc: cc })
                                        SNavigation.goBack();
                                    }
                                })
                            }
                        },
                        "monto": { type: "money", label: "Saldo inicial.", isRequired: true },
                    }}

                    onSubmitName={"SUBIR"}
                    loading={this.state.loading}
                    onSubmit={(e) => {
                        e.keyCategoria = this.state?.cc?.key
                        this.setState({ loading: true })
                        Model.cuenta.create(e).then(e => {
                            this.setState({ loading: false })
                            console.log(e)
                            SNavigation.goBack();
                        }).catch(e => {
                            console.error(e)
                            this.setState({ loading: false })
                        })
                    }}
                />

            </Container>
        </SPage>
    }
}
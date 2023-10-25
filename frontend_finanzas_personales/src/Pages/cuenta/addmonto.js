import React, { Component } from 'react'
import { SForm, SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../../Model'
import { Container } from '../../Components'

export default class index extends Component {

    constructor(props) {
        super(props);
        this.pk = SNavigation.getParam("pk")
    }
    state = {}
    componentDidMount() {

    }
    render() {
        return <SPage>
            <Container>
                <SForm
                    inputs={{
                        "monto": { type: "money", label: "Monto.", isRequired: true },
                        // "descripcion": { type: "textArea", label: "Descripcion." },

                    }}

                    onSubmitName={"SUBIR"}
                    loading={this.state.loading}
                    error={this.state.error}
                    onSubmit={(e) => {
                        this.setState({ loading: true })
                        Model.cuenta.addmonto({
                            key: this.pk,
                            monto: parseFloat(e.monto),
                        }).then(e => {
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